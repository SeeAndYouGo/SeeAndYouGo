package com.SeeAndYouGo.SeeAndYouGo.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequestDto {
    private String restaurant;
    private String writer;
    private String madeTime;
    private String comment;
    private String image;
    private Double rate;

}
