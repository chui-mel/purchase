package com.fiona.purchase.model;

import com.fiona.purchase.service.TestData;

import java.util.HashMap;
import java.util.Map;

public class FilledProductPurchase implements Comparable<FilledProductPurchase> {
  private final Product product;
  private final int amount;
  private Map<Supplier, Integer> supplierAmounts = new HashMap<>();
  private boolean filled = false;

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

  public boolean isFilled() {
    return filled;
  }

  public void setFilled(boolean filled) {
    this.filled = filled;
  }

  @Override
  public String toString() {
    return "FilledProductPurchase{" +
        "product=" + product +
        ", amount=" + amount +
        ", supplierAmounts=" + supplierAmounts +
        '}';
  }
}
