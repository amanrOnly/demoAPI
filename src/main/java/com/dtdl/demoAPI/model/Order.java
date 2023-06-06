package com.dtdl.demoAPI.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private User user;

    @ManyToMany
    @JoinTable(
            name = "orders_foods",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private Set<Food> foodSet = new HashSet<>();
}
