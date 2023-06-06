package com.dtdl.demoAPI.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="demoAPI_user")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;

    @Column
    private String userName;

    @Column
    private double contactNumber;

    @Column
    private String address;

    @Column
    private String location;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Order> orderSet;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userReview")
    private Set<FoodReview> foodReviewSet;

}
