package com.fiona.purchase.model;

public class ProductStock {
  private int id;
  private Product product;
  private Supplier supplier;
  private int stockOnHand;
  private double cost;

  public ProductStock(int id, Product product, Supplier supplier, int stockOnHand, double cost) {
    this.id = id;
    this.product = product;
    this.supplier = supplier;
    this.stockOnHand = stockOnHand;
    this.cost = cost;
  }

  public int getId() {
    return id;
  }

  public Product getProduct() {
    return product;
  }

  public Supplier getSupplier() {
    return supplier;
  }

  public int getStockOnHand() {
    return stockOnHand;
  }

  public double getCost() {
    return cost;
  }
}
