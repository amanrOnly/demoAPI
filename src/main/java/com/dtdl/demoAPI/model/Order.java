package com.dtdl.demoAPI.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "demoAPI_orders")
public class Order {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderID;

    @Column
    private String dateTime;

    @Column
    private String paymentMethod;

    @Column
    private String paymentStatus;

    @Column
    private int price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "orderID", insertable = false, updatable = false)
    User user;

    @ManyToMany
    private List<Food> foods = new ArrayList<>();

}
