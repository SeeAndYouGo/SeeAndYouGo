package com.SeeAndYouGo.SeeAndYouGo.restaurant;

import com.SeeAndYouGo.SeeAndYouGo.Menu.Menu;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue
    @Column(name= "restaurant_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "date")
    private String date;

    @OneToMany
    private List<Menu> menuList = new ArrayList<>();

//    @OneToOne(mappedBy = "restaurant")
//    private  Connection connection;

    @Column(name = "restaurantRate")
    private Double restaurantRate;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;
}
