package com.onlineECSystem.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProductDTO {
    private Long productId;
    private String productName;
    private Long vendorId;
    private String vendorName;
    private int quantity;
}
