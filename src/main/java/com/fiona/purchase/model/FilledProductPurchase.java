package com.fiona.purchase.model;

import com.fiona.purchase.service.TestData;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FilledProductPurchase implements Comparable<FilledProductPurchase> {
  private final Product product;
  private final int amount;
  private Map<Supplier, Integer> supplierAmounts = new HashMap<>();

  public FilledProductPurchase(Product product, int amount) {
    this.product = product;
    this.amount = amount;
  }

  public FilledProductPurchase(int amount, FilledProductPurchase filledProductPurchase) {
    this.product = filledProductPurchase.product;
    this.amount = amount;
    supplierAmounts.putAll(filledProductPurchase.supplierAmounts);
  }
  public double cost() {
    return TestData.stocks.get(product).stream()
        .map(ps -> supplierAmounts.getOrDefault(ps.getSupplier(), 0) * ps.getCost())
        .mapToDouble(i -> i).sum();
  }

  public void addOneSupplier(Supplier supplier) {
    supplierAmounts.put(supplier, supplierAmounts.getOrDefault(supplier, 0) + 1);
  }

  public void addSupplierWithAmount(Supplier supplier, int amount) {
    supplierAmounts.put(supplier, supplierAmounts.getOrDefault(supplier, 0) + amount);
  }

  public Map<Supplier, Double> valuePerSupplier() {
    return TestData.stocks.get(product).stream().collect(
        Collectors.toMap(ProductStock::getSupplier,
            ps -> supplierAmounts.getOrDefault(ps.getSupplier(), 0) * ps.getCost()));
  }

  public Product getProduct() {
    return product;
  }

  public int getAmount() {
    return amount;
  }

  public Map<Supplier, Integer> getSupplierAmounts() {
    return supplierAmounts;
  }

  public int getSupplierAmount(Supplier tmpStockSupplier) {
    return supplierAmounts.getOrDefault(tmpStockSupplier, 0);
  }

  @Override
  public int compareTo(FilledProductPurchase o) {
    if (cost() <= o.cost()) {
      return -1;
    }
    return 1;
  }

  @Override
  public String toString() {
    return "FilledProductPurchase{" +
        "product=" + product +
        ", amount=" + amount +
        ", supplierAmounts=" + supplierAmounts +
        ", cost= " + cost() +
        '}';
  }
}
