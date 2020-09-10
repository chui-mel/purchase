package com.fiona.purchase.service;

import com.fiona.purchase.model.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.stream.Collectors.toMap;

public class PurchaseOptimizerBruteForce {

  public void optimize(List<PurchaseRequirement> purchaseRequirementList) {

    Map<PurchaseRequirement, List<FilledProductPurchase>> allPosibilities =
        purchaseRequirementList.stream()
            .map(pr -> new AbstractMap.SimpleEntry<>(pr, calculateBruteForce(pr)))
            .collect(toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));

    PurchaseRequirement[] reqs = purchaseRequirementList.toArray(new PurchaseRequirement[0]);

    AtomicReference<Double> finalCost = new AtomicReference(-1.0);
    AtomicReference<FilledOrder> finalResult = new AtomicReference<>();

    List<FilledOrder>[] dpAll = new List[purchaseRequirementList.size()];
    dpAll[0] = new ArrayList<>();
    allPosibilities.get(reqs[0]).forEach(
        f -> {
          FilledOrder fo = new FilledOrder();
          fo.addFilledPurchase(f);
          dpAll[0].add(fo);
          if (purchaseRequirementList.size() == 1) {
            if (finalCost.get() < 0 || fo.cost() < finalCost.get()) {
              finalResult.set(fo);
              finalCost.set(fo.cost());
            }
          }
        }
    );

    for (int i = 1; i < purchaseRequirementList.size(); i++) {
      dpAll[i] = new ArrayList<>();
      for (FilledOrder filledOrder: dpAll[i-1]) {
        if (allPosibilities.get(reqs[i]).size() == 0) {
          FilledOrder tmp = new FilledOrder(filledOrder);
          dpAll[i].add(tmp);
        } else {
          int finalI = i;
          allPosibilities.get(reqs[i]).forEach(
              f -> {
                FilledOrder tmp = new FilledOrder(filledOrder);
                tmp.addFilledPurchase(f);
                if (finalI == purchaseRequirementList.size() - 1) {
                  if (finalCost.get() < 0 || tmp.cost() < finalCost.get())  {
                    finalResult.set(tmp);
                    finalCost.set(tmp.cost());
                  }
                }
                dpAll[finalI].add(tmp);
              }
          );
        }
      }
    }

    System.out.println(dpAll[dpAll.length - 1].size());
    System.out.println(finalResult);

  }

  private List<FilledProductPurchase> calculateBruteForce(PurchaseRequirement purchaseRequirement) {
    Product product = purchaseRequirement.getProduct();
    List<ProductStock> stocks = TestData.stocks.get(product);

    int quantity = purchaseRequirement.getQuantity();

    List<FilledProductPurchase>[] dpCost = new List[quantity + 1];
    for (int i = 0 ; i <= quantity; i++) {
      dpCost[i] = new ArrayList<>();
    }
    FilledProductPurchase init = new FilledProductPurchase(product, 0);
    dpCost[0].add(init);

    for (int i = 1; i <= quantity; i++) {
      List<FilledProductPurchase> tmpResults = new ArrayList<>();
      FilledProductPurchase tmpResult;
      for (ProductStock stock : stocks) {
        if (stock.getStockOnHand() <= 0) {
          continue;
        }
        if (i < stock.getStockOnHand()) {
          tmpResult = new FilledProductPurchase(product, i);
          tmpResult.addSupplierWithAmount(stock.getSupplier(), i);
          tmpResults.add(tmpResult);
        } else {
          for (FilledProductPurchase preFilled : dpCost[i - stock.getStockOnHand()]) {
            if (preFilled.getSupplierAmount(stock.getSupplier()) == 0) {
              tmpResult = new FilledProductPurchase(i, preFilled);
              tmpResult.addSupplierWithAmount(stock.getSupplier(), stock.getStockOnHand());
              tmpResults.add(tmpResult);
            }
          }
        }
      }

      if (tmpResults.size() == 0) {
        System.out.println("Cannot fill the purchase request");
        break;
      }

      dpCost[i] = tmpResults;
    }

    return dpCost[quantity];
  }
}
