package com.gft.pricechecker.domain.repository;

import com.gft.pricechecker.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> findApplicablePrice(Long brandId, Long productId, LocalDateTime date);
}