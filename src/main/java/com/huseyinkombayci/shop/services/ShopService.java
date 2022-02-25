package com.huseyinkombayci.shop.services;

import com.huseyinkombayci.shop.domains.dtos.LocationDTO;
import com.huseyinkombayci.shop.domains.dtos.ShopRequestDTO;
import com.huseyinkombayci.shop.domains.dtos.ShopResponseDTO;
import com.huseyinkombayci.shop.domains.models.Shop;
import com.huseyinkombayci.shop.repositories.ShopRepository;
import com.huseyinkombayci.shop.utils.DistanceCalculator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.huseyinkombayci.shop.domains.validators.RequestValidator.validateShopRequestDTO;

public record ShopService(ShopRepository shopRepository) {

  public List<ShopResponseDTO> getShops(ShopRequestDTO dto) {
    validateShopRequestDTO(dto);

    LocationDTO location = dto.location();
    int limit = dto.limit();
    double latitude = location.latitude();
    double longitude = location.longitude();

    List<Shop> shops = shopRepository.getShops();

    return shops.stream()
        .map(shop -> {
          double shopLatitude = shop.location().latitude();
          double shopLongitude = shop.location().longitude();
          double distance = DistanceCalculator.calculate(latitude, longitude, shopLatitude, shopLongitude);
          return new ShopResponseDTO(
              shop.id(),
              shop.name(),
              new LocationDTO(shopLatitude, shopLongitude),
              distance
          );
        })
        .sorted(Comparator.comparing(ShopResponseDTO::distance))
        .limit(limit)
        .collect(Collectors.toList());
  }
}
