package com.gft.pricechecker.application.service;

import com.gft.pricechecker.application.dto.PriceResponseDTO;
import com.gft.pricechecker.application.port.input.GetPriceUseCase;
import com.gft.pricechecker.domain.exception.PriceNotFoundException;
import com.gft.pricechecker.domain.model.Price;
import com.gft.pricechecker.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceService implements GetPriceUseCase {

    private final PriceRepository priceRepository;

    @Override
    public PriceResponseDTO execute(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceRepository.findApplicablePrice(brandId, productId, applicationDate)
                .map(this::mapToResponse)
                .orElseThrow(() -> new PriceNotFoundException("No applicable price found for product " + productId +
                        " in brand " + brandId + " for date " + applicationDate));
    }

    private PriceResponseDTO mapToResponse(Price price) {
        return new PriceResponseDTO(
                price.getProductId(),
                price.getBrandId(),
                price.getPriceList(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice()
        );
    }
}