package com.huseyinkombayci.shop.repositories;

import com.huseyinkombayci.shop.domains.models.Location;
import com.huseyinkombayci.shop.domains.models.Shop;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ShopRepository {

	public List<Shop> getShops() {
		return Stream.of(new Shop(1, "Shop 1", new Location(42.546245, 1.601554)),
				new Shop(2, "Shop 2", new Location(23.424076, 53.847818)),
				new Shop(3, "Shop 3", new Location(33.93911, 67.709953)),
				new Shop(4, "Shop 4", new Location(17.060816, -61.796428)),
				new Shop(5, "Shop 5", new Location(18.220554, -63.068615)),
				new Shop(6, "Shop 6", new Location(41.153332, 20.168331)),
				new Shop(7, "Shop 7", new Location(40.069099, 45.038189)),
				new Shop(8, "Shop 8", new Location(12.226079, -69.060087)),
				new Shop(9, "Shop 9", new Location(-11.202692, 17.873887)),
				new Shop(10, "Shop 10", new Location(-75.250973, -0.071389))
		).collect(Collectors.toList());
	}
}
