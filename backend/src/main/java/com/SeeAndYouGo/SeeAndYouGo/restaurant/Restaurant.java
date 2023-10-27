package com.SeeAndYouGo.SeeAndYouGo.restaurant;

import com.SeeAndYouGo.SeeAndYouGo.Menu.Menu;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Getter
@Setter
public class Restaurant {
    @Id
    @GeneratedValue
    @Column(name= "restaurant_id")
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "capacity")
    public Integer capacity;

    @Column(name = "date")
    public String date;

    @OneToMany
    public List<Menu> menuList = new ArrayList<>();

//    @OneToOne(mappedBy = "restaurant")
//    public  Connection connection;
    
    @Column(name = "restaurantRate")
    public Double restaurantRate;

    @Column(name = "latitude")
    public Double latitude;

    @Column(name = "longitude")
    public Double longitude;

}
