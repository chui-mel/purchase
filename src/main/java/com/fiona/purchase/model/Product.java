package com.fiona.purchase.model;

public class Product {
  private int id;
  private String name;
  private ProductType type;

  public Product(int id, String name, ProductType type) {
    this.id = id;
    this.name = name;
    this.type = type;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public ProductType getType() {
    return type;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", type=" + type +
        '}';
  }
}
