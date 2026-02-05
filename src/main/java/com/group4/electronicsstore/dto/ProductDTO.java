package com.group4.electronicsstore.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {
    Long id;
    String name;
    Double price;
    String description;
    Integer stockQuantity;
    String image;
    String categoryName;
    boolean active;
}
