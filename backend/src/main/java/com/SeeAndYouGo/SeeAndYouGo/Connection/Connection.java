package com.SeeAndYouGo.SeeAndYouGo.Connection;

import com.SeeAndYouGo.SeeAndYouGo.restaurant.Restaurant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Connection {
    @Id @GeneratedValue
    @Column(name = "connection_id")
    public Long id;

    public Integer connected;

    public String time;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    public Restaurant restaurant;
}
