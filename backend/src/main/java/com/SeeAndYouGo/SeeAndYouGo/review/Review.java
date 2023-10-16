package com.SeeAndYouGo.SeeAndYouGo.review;

import com.SeeAndYouGo.SeeAndYouGo.Menu.Menu;
import com.SeeAndYouGo.SeeAndYouGo.restaurant.Restaurant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;
    @Column(name = "writer")
    private String writer;
    @Column(name = "madeTime")
    private LocalDateTime madeTime;
    @Column(name = "likeCount")
    private Integer likeCount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;
    @Column(name = "comment")
    private String comment;
    @Column(name = "imgLink")
    private String imgLink;
    @Column(name = "reviewRate")
    private Double reviewRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public void setMadeTime(String madeTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm");
        this.madeTime = LocalDateTime.parse(madeTimeStr, formatter);
    }
}
