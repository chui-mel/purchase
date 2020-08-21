package com.fiona.purchase.service;

import com.fiona.purchase.model.Product;
import com.fiona.purchase.model.ProductStock;
import com.fiona.purchase.model.ProductType;
import com.fiona.purchase.model.Supplier;

import java.util.List;
import java.util.Map;

public class TestData {
  public static Supplier supplier1 = new Supplier(1, "Synnex", 10, 150, 1000);
  public static Supplier supplier2 = new Supplier(2, "Ingram Micro", 0, 0, 0);
  public static Supplier supplier3 = new Supplier(3, "Tech Data", 10, 150, 1000);
  public static Supplier supplier4 = new Supplier(4, "Multimedia Technology", 10, 150, 1000);
  public static Supplier supplier5 = new Supplier(5, "Dicker Data", 9, 550, 99999);
  public static Supplier supplier6 = new Supplier(6, "Leader", 20, 0, 500);

  public static Product product1 = new Product(1, "Google Pixel 3 Phone", ProductType.Physical);
  public static Product product2 = new Product(2, "Lenovo X1 Carbon Laptop", ProductType.Physical);
  public static Product product3 = new Product(3, "Microsoft Office 365 Business Premium", ProductType.NonPhysical);
  public static Product product4 = new Product(4, "Professional Services - 1 hour", ProductType.Service);
  public static Product product5 = new Product(5, "Logitech MK450 Wireless Keyboard and Mouse", ProductType.Physical);
  public static Product product6 = new Product(6, "HP 27\\\" LCD LED Professional Series Monitor", ProductType.Physical);
  public static Product product7 = new Product(7, "Symantec Antivius Pro Plus Corporate Edition", ProductType.NonPhysical);
  public static Product product8 = new Product(8, "Netgear Nighthawk NH100X Wireless Router", ProductType.Physical);

  public static List<ProductStock> product1Stock = List.of(
      new ProductStock(1, product1, supplier1, 105, 940.56),
      new ProductStock(2, product1, supplier2, 2, 918.10),
      new ProductStock(3, product1, supplier4, 15, 935.40)
  );

  public static List<ProductStock> product2Stock = List.of(
      new ProductStock(4, product2, supplier2, 1, 1509.49),
      new ProductStock(5, product2, supplier4, 13, 1489.80),
      new ProductStock(6, product2, supplier5, 15, 1492.50)
  );

  public static List<ProductStock> product3Stock = List.of(
      new ProductStock(7, product3, supplier1, 0, 89.91),
      new ProductStock(8, product3, supplier3, 0, 80.42),
      new ProductStock(9, product3, supplier6, 0, 71.56)
  );

  public static List<ProductStock> product4Stock = List.of(
  );

  public static List<ProductStock> product5Stock = List.of(
      new ProductStock(10, product5, supplier3, 1405, 45.49),
      new ProductStock(11, product5, supplier1, 120, 46.50),
      new ProductStock(12, product5, supplier6, 2, 44.50),
      new ProductStock(13, product5, supplier4, 130, 45.40)
  );

  public static List<ProductStock> product6Stock = List.of(
      new ProductStock(14, product6, supplier2, 62, 242.75),
      new ProductStock(15, product6, supplier4, 18, 240.69),
      new ProductStock(16, product6, supplier5, 2, 201.42),
      new ProductStock(17, product6, supplier6, 108, 243.19)
  );

  public static List<ProductStock> product7Stock = List.of(
      new ProductStock(18, product7, supplier3, 0, 67.10),
      new ProductStock(19, product7, supplier1, 0, 68.41),
      new ProductStock(20, product7, supplier6, 9999, 101.42),
      new ProductStock(21, product7, supplier4, 108, 70.19)
  );

  public static List<ProductStock> product8Stock = List.of(
      new ProductStock(22, product8, supplier1, 130, 340.91),
      new ProductStock(23, product8, supplier3, 10, 329.14),
      new ProductStock(24, product8, supplier4, 5, 301.42),
      new ProductStock(25, product8, supplier5, 409, 319.00)
  );

  public static Map<Product, List<ProductStock>> stocks = Map.of(
      product1, product1Stock,
      product2, product2Stock,
      product3, product3Stock,
      product4, product4Stock,
      product5, product5Stock,
      product6, product6Stock,
      product7, product7Stock,
      product8, product8Stock
  );
}
