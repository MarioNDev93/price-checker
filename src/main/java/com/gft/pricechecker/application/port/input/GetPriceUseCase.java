package com.gft.pricechecker.application.port.input;

import com.gft.pricechecker.application.dto.PriceResponseDTO;

import java.time.LocalDateTime;

public interface GetPriceUseCase {
    PriceResponseDTO execute(LocalDateTime applicationDate, Long productId, Long brandId);
}