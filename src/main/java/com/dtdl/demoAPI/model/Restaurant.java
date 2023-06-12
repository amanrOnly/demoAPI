package com.dtdl.demoAPI.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "demoAPI_restaurant")
public class Restaurant implements Comparable<Restaurant>{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int restaurantID;

    @Column
    private String restaurantName;

    @Column
    private String ownerName;

    @Column
    private String location;

    @Column
    private double contactNumber;

    @Column(columnDefinition = "float default 0")
    private float rating;

    @JsonManagedReference
    @OneToMany(mappedBy = "res", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Food> foodList = new ArrayList<>();


    @Override
    public int compareTo(Restaurant o) {
        return Float.compare(this.rating, o.rating);
    }
}