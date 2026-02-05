package com.gft.pricechecker.infrastructure.web;

import com.gft.pricechecker.application.dto.PriceResponseDTO;
import com.gft.pricechecker.application.port.input.GetPriceUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Validated
@Tag(name = "Prices", description = "Price query endpoint")
public class PriceController {

    private final GetPriceUseCase getPriceUseCase;

    @GetMapping("/prices")
    @Operation(summary = "Get applicable price for a product")
    public ResponseEntity<PriceResponseDTO> getPrice(@Parameter(description = "Application date", example = "2020-06-14T10:00:00")
                                                         @RequestParam
                                                         @NotNull
                                                         LocalDateTime applicationDate,

                                                     @Parameter(description = "Product identifier", example = "35455")
                                                     @RequestParam
                                                     @NotNull
                                                     Long productId,

                                                     @Parameter(description = "Brand identifier", example = "1")
                                                         @RequestParam
                                                         @NotNull
                                                         Long brandId) {
        PriceResponseDTO response = getPriceUseCase.execute(applicationDate, productId, brandId);
        return ResponseEntity.ok(response);
    }
}
