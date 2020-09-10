package com.fiona.purchase.service;

import com.fiona.purchase.model.PurchaseRequirement;
import junit.framework.TestCase;

import java.util.List;

import static com.fiona.purchase.service.TestData.*;
import static com.fiona.purchase.service.TestData.product8;

public class PurchaseOptimizerBruteForceTest extends TestCase {

	List<PurchaseRequirement> requirements;

	PurchaseOptimizerBruteForce optimizer;

	public void setUp() {
		requirements = create();
		optimizer = new PurchaseOptimizerBruteForce();
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