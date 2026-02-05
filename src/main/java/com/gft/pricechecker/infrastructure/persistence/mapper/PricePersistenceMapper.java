package com.gft.pricechecker.infrastructure.persistence.mapper;

import com.gft.pricechecker.domain.model.Price;
import com.gft.pricechecker.infrastructure.persistence.entity.PriceEntity;
import org.springframework.stereotype.Component;

@Component
public class PricePersistenceMapper {

    public Price toDomain(PriceEntity entity) {
        if (entity == null) return null;
        return new Price(
                entity.getBrandId(),
                entity.getProductId(),
                entity.getPriceList(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPrice(),
                entity.getCurrency()
        );
    }
}