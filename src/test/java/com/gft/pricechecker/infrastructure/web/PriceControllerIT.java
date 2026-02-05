package com.gft.pricechecker.infrastructure.web;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest(name = "Case {index}: Date {0} -> Expected price {1}")
    @CsvSource({
            "2020-06-14T10:00:00, 35.50",
            "2020-06-14T16:00:00, 25.45",
            "2020-06-14T21:00:00, 35.50",
            "2020-06-15T10:00:00, 30.50",
            "2020-06-16T21:00:00, 38.95"
    })
    void shouldReturnCorrectPriceForGivenDate(String date, double expectedPrice) throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("applicationDate", date)
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(expectedPrice));
    }

    @ParameterizedTest(name = "Error Case {index}: {3}")
    @CsvSource({
            "2020-06-14T10:00:00, 'not-a-number', 1, Invalid Product ID",
            "2020-06-14T10:00:00, 35455, 'abc', Invalid Brand ID"
    })
    void shouldReturn400WhenParametersAreInvalid(String date, String productId, String brandId, String description) throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("applicationDate", date)
                        .param("productId", productId)
                        .param("brandId", brandId))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid request parameters"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @ParameterizedTest(name = "Error case {index}: {3}")
    @CsvSource({
            "2029-12-31T23:59:59, 35455, 1, 'Date out of range'",
            "2020-06-14T10:00:00, 99999, 1, 'Product does not exist'",
            "2020-06-14T10:00:00, 35455, 9, 'Brand does not exist'"
    })
    void shouldReturn404WhenNoPriceIsFound(String date, String productId, String brandId, String description) throws Exception {
        mockMvc.perform(get("/api/v1/prices")
                        .param("applicationDate", date)
                        .param("productId", productId)
                        .param("brandId", brandId))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").exists());
    }
}