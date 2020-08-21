package com.fiona.purchase.model;

public class Supplier {
  private int id;
  private String name;
  private double shippingCost;
  private double shippingCostMinOrderValue;
  private double shippingCostMaxOrderValue;

  public Supplier(int id, String name, double shippingCost, double shippingCostMinOrderValue, double shippingCostMaxOrderValue) {
    this.id = id;
    this.name = name;
    this.shippingCost = shippingCost;
    this.shippingCostMinOrderValue = shippingCostMinOrderValue;
    this.shippingCostMaxOrderValue = shippingCostMaxOrderValue;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public double getShippingCost() {
    return shippingCost;
  }

  public double getShippingCostMinOrderValue() {
    return shippingCostMinOrderValue;
  }

  public double getShippingCostMaxOrderValue() {
    return shippingCostMaxOrderValue;
  }
}
