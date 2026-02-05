package com.gft.pricechecker.infrastructure.persistence;

import com.gft.pricechecker.domain.model.Price;
import com.gft.pricechecker.domain.repository.PriceRepository;
import com.gft.pricechecker.infrastructure.persistence.mapper.PricePersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PricePersistenceAdapter implements PriceRepository {
    private final JpaPriceRepository jpaRepository;
    private final PricePersistenceMapper mapper;

    @Override
    public Optional<Price> findApplicablePrice(Long brandId, Long productId, LocalDateTime date) {
        return jpaRepository.findApplicablePrices(brandId, productId, date)
                .stream()
                .findFirst()
                .map(mapper::toDomain);
    }
}