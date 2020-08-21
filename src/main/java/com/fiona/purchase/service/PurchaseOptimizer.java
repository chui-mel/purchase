package com.fiona.purchase.service;

import com.fiona.purchase.model.*;

import javax.management.StandardEmitterMBean;
import java.awt.print.PrinterJob;
import java.util.*;
import java.util.stream.Collectors;

public class PurchaseOptimizer {

  public void optimize(List<PurchaseRequirement> purchaseRequirementList) {

    List<FilledProductPurchase> filledOrder = new ArrayList<>();
    purchaseRequirementList.forEach(pr -> filledOrder.add(calculateFilledProductPurchase(pr)));

    Map<Supplier, Integer> supplierAmounts = new HashMap<>();
    filledOrder.forEach(pr -> pr.getSupplierAmounts().entrySet().forEach(
        e -> supplierAmounts.put(e.getKey(), supplierAmounts.getOrDefault(e, 0) + e.getValue())));

  }

  private FilledProductPurchase calculateFilledProductPurchase(PurchaseRequirement purchaseRequirement) {

    Product product = purchaseRequirement.getProduct();
    List<ProductStock> stocks = TestData.stocks.get(product);

    int quantity = purchaseRequirement.getQuantity();

    FilledProductPurchase dpCost[] = new FilledProductPurchase[quantity + 1];
    for (int i = 0 ; i <= quantity; i++) {
      dpCost[i] = new FilledProductPurchase(product, 0);
    }
    dpCost[0].setFilled(true);

    for (int i = 1; i <= quantity; i++) {
      List<FilledProductPurchase> tmpResults = new ArrayList<>();
      for (ProductStock stock : stocks) {
        int preAmount = dpCost[i - 1].getSupplierAmount(stock.getSupplier());
        if (preAmount + 1 <= stock.getStockOnHand()) {
          FilledProductPurchase tmpResult = new FilledProductPurchase(i, dpCost[i - 1]);
          tmpResult.addOneSupplier(stock.getSupplier());
          tmpResults.add(tmpResult);
        }
      }

      if (tmpResults.size() == 0) {
        System.out.println("Cannot fill the purcharse request");
        break;
      }

      Collections.sort(tmpResults);
      dpCost[i] = tmpResults.get(0);
      System.out.println(dpCost[i]);
    }

    return dpCost[quantity];
  }

  private double getShippingCost(int amount, Supplier supplier) {
    if (amount < supplier.getShippingCostMinOrderValue()
        || amount > supplier.getShippingCostMaxOrderValue()) {
      return supplier.getShippingCost();
    } else {
      return 0;
    }
  }
}
