package com.dtdl.demoAPI.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Order> orderSet;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userReview", fetch = FetchType.LAZY)
    private Set<FoodReview> foodReviewSet;

}
