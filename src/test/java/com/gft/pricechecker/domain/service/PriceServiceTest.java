package com.gft.pricechecker.domain.service;

import com.gft.pricechecker.application.dto.PriceResponseDTO;
import com.gft.pricechecker.application.service.PriceService;
import com.gft.pricechecker.domain.model.Price;
import com.gft.pricechecker.domain.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    @Test
    void shouldReturnPriceResponse_WhenPriceExists() {
        LocalDateTime date = LocalDateTime.now();
        Price mockPrice = new Price(1L, 35455L, 1, date, date, BigDecimal.valueOf(35.50), "EUR");
        when(priceRepository.findApplicablePrice(any(), any(), any()))
                .thenReturn(Optional.of(mockPrice));

        PriceResponseDTO result = priceService.execute(date,35455L, 1L);

        assertNotNull(result);
        assertEquals(35.50, result.price().doubleValue());
        verify(priceRepository).findApplicablePrice(1L, 35455L, date);
    }
}