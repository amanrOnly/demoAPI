package com.dtdl.demoAPI.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderDto {

    private int orderID;
    private String dateTime;
    private String paymentMethod;
    private String paymentStatus;
    private int price;
}
