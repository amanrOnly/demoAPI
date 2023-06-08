package com.dtdl.demoAPI.payload;

import com.sun.istack.internal.Nullable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class OrderDto {

//    private int orderID;

    @Nullable
    private Date dateTime;

    private String paymentMethod;
    private String paymentStatus;
    private int price;
}
