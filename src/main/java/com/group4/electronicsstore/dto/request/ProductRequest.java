package com.group4.electronicsstore.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    @NotBlank(message = "PRODUCT_NAME_REQUIRED")
    private String name;


    @NotNull(message = "PRICE_REQUIRED")
    @Positive(message = "PRICE_MUST_BE_POSITIVE")
    private Double price;

    private String description;

    private String image;


    @NotNull(message = "STOCK_QUANTITY_REQUIRED")
    @Min(value = 0, message = "STOCK_QUANTITY_CANNOT_BE_NEGATIVE")
    private Integer stockQuantity;

    @Builder.Default
    private boolean active = true;

    @NotNull(message = "CATEGORY_ID_IS_REQUIRED")
    private Long categoryId;
}

