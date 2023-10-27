package com.SeeAndYouGo.SeeAndYouGo.Dish;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Dish {
    @Id @GeneratedValue
    @Column(name = "dish_id")
    public Long id;

    public String name;

    @Enumerated(EnumType.STRING)
    public DishType dishType;
}