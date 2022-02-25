package com.huseyinkombayci.shop.domains.validators;

import com.huseyinkombayci.shop.domains.dtos.LocationDTO;
import com.huseyinkombayci.shop.domains.dtos.ShopRequestDTO;

public class RequestValidator {

	private final static double MIN_LATITUDE = -90.0;
	private final static double MAX_LATITUDE = 90.0;
	private final static double MIN_LONGITUDE = -180.0;
	private final static double MAX_LONGITUDE = 180.0;

	private static boolean isValidLatitude(double latitude) {
		return latitude >= MIN_LATITUDE && latitude <= MAX_LATITUDE;
	}

	private static boolean isValidLongitude(double longitude) {
		return longitude >= MIN_LONGITUDE && longitude <= MAX_LONGITUDE;
	}

	public static void validateShopRequestDTO(ShopRequestDTO dto) {
		LocationDTO location = dto.location();
		int limit = dto.limit();
		double latitude = location.latitude();
		double longitude = location.longitude();

		if (limit <= 0) {
			throw new RuntimeException("Invalid limit value");
		}

		if (!isValidLatitude(latitude)) {
			throw new RuntimeException("Invalid latitude value");
		}

		if (!isValidLongitude(longitude)) {
			throw new RuntimeException("Invalid longitude value");
		}
	}
}
