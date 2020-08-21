package com.fiona.purchase.service;

import com.fiona.purchase.model.*;
import junit.framework.TestCase;

import java.util.List;

import static com.fiona.purchase.service.TestData.*;

public class PurchaseOptimizerTest extends TestCase {

  List<PurchaseRequirement> requirements;

  PurchaseOptimizer optimizer;

  public void setUp() {
    requirements = create();
    optimizer = new PurchaseOptimizer();
  }

  public void testOptimize() {
    optimizer.optimize(requirements);
  }

  private List<PurchaseRequirement> create() {
    return List.of(
        new PurchaseRequirement(product1, 4),
        new PurchaseRequirement(product2, 8),
        new PurchaseRequirement(product3, 50),
        new PurchaseRequirement(product4, 40),
        new PurchaseRequirement(product5, 50),
        new PurchaseRequirement(product6, 25),
        new PurchaseRequirement(product7, 10),
        new PurchaseRequirement(product8, 4)
    );
  }
}