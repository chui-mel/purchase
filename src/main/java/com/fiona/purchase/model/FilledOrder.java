package com.fiona.purchase.model;

import com.fiona.purchase.service.TestData;

import java.util.*;
import java.util.stream.Collectors;

public class FilledOrder implements Comparator<FilledOrder> {
	private List<FilledProductPurchase> filledProductPurchases = new ArrayList<>();;

	public FilledOrder() {
	}

	public FilledOrder(FilledOrder fo) {
		this.filledProductPurchases.addAll(fo.getFilledProductPurchases().stream().collect(Collectors.toList()));
	}
	public List<FilledProductPurchase> getFilledProductPurchases() {
		return filledProductPurchases;
	}

	public void addFilledPurchase(FilledProductPurchase filledProductPurchase) {
		filledProductPurchases.add(filledProductPurchase);
	}

	@Override
	public String toString() {
		return "FilledOrder{" +
				"filledProductPurchases=" + filledProductPurchases +
				"total cost: " + cost() +
				'}';
	}

	public double cost() {
		return filledProductPurchases.stream()
				.map(FilledProductPurchase::cost).mapToDouble(d -> d).sum() + shippingCost();
	}

	public double shippingCost() {
		Map<Supplier, Double> valuesForSupplier = new HashMap<>();
		filledProductPurchases.stream()
				.map(FilledProductPurchase::valuePerSupplier)
				.forEach(p -> p.entrySet().stream().forEach(
						e -> valuesForSupplier.put(e.getKey(), valuesForSupplier.getOrDefault(e.getKey(), 0.0) + e.getValue())
				));

		return valuesForSupplier.entrySet().stream()
				.map(se -> se.getKey().shippingCost(se.getValue()))
				.mapToDouble(d -> d).sum();
	}

	@Override
	public int compare(FilledOrder o1, FilledOrder o2) {
		return (o1.cost() <= o2.cost()) ? -1 : 1;
	}
}
