package com.huseyinkombayci.shop.services;

import com.huseyinkombayci.shop.domains.dtos.LocationDTO;
import com.huseyinkombayci.shop.domains.dtos.ShopRequestDTO;
import com.huseyinkombayci.shop.domains.dtos.ShopResponseDTO;
import com.huseyinkombayci.shop.repositories.ShopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

	private final ShopRepository shopRepository = new ShopRepository();
	private ShopService shopService;

	@BeforeEach
	void setUp() {
		shopService = new ShopService(shopRepository);
	}

	private List<ShopResponseDTO> setupRequestAndGetShops(double latitude, double longitude, int limit) {
		LocationDTO locationDTO = new LocationDTO(latitude, longitude);
		ShopRequestDTO shopRequestDTO = new ShopRequestDTO(locationDTO, limit);
		return shopService.getShops(shopRequestDTO);
	}


	@Test
	@DisplayName("Check invalid latitude value")
	void should_throw_exception_with_invalid_latitude_value() {
		assertThrows(RuntimeException.class, () -> setupRequestAndGetShops(-100, 0, 1));
	}


	@Test
	@DisplayName("Check invalid longitude value")
	void should_throw_exception_with_invalid_longitude_value() {
		assertThrows(RuntimeException.class, () -> setupRequestAndGetShops(0, 200, 1));
	}

	@Test
	@DisplayName("Check invalid limit value")
	void should_throw_exception_with_invalid_limit_value() {
		assertThrows(RuntimeException.class, () -> setupRequestAndGetShops(0, 0, -1));
	}

	@Test
	@DisplayName("Check list is returned")
	void should_return_list() {
		List<ShopResponseDTO> shops = setupRequestAndGetShops(0, 0, 1);
		assertFalse(shops.isEmpty());
	}

	@Test
	@DisplayName("Check list of shops is returned in correct order")
	void should_return_ordered_list() {
		List<ShopResponseDTO> shops = setupRequestAndGetShops(55.378051, -3.435973, 10);
		assertAll(() -> {
			assertEquals("Shop 1", shops.get(0).name());
			assertEquals("Shop 6", shops.get(1).name());
			assertEquals("Shop 7", shops.get(2).name());
			assertEquals("Shop 3", shops.get(3).name());
			assertEquals("Shop 2", shops.get(4).name());
			assertEquals("Shop 5", shops.get(5).name());
			assertEquals("Shop 4", shops.get(6).name());
			assertEquals("Shop 8", shops.get(7).name());
			assertEquals("Shop 9", shops.get(8).name());
			assertEquals("Shop 10", shops.get(9).name());
		});
	}

	@Test
	@DisplayName("Check list of shops is returned in correct order with limit")
	void should_return_ordered_list_with_limit() {
		List<ShopResponseDTO> shops = setupRequestAndGetShops(55.378051, -3.435973, 2);
		assertAll(() -> {
			assertEquals("Shop 1", shops.get(0).name());
			assertEquals("Shop 6", shops.get(1).name());
		});

		assertThrows(IndexOutOfBoundsException.class, () -> shops.get(2));
	}
}