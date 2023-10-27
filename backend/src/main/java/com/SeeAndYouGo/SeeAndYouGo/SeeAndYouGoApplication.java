package com.SeeAndYouGo.SeeAndYouGo;

import com.SeeAndYouGo.SeeAndYouGo.restaurant.Restaurant;
import com.SeeAndYouGo.SeeAndYouGo.restaurant.RestaurantRepository;
import com.SeeAndYouGo.SeeAndYouGo.review.Review;
import com.SeeAndYouGo.SeeAndYouGo.review.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@Slf4j
public class SeeAndYouGoApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SeeAndYouGoApplication.class, args);


		RestaurantRepository restaurantRepository = context.getBean(RestaurantRepository.class);
		ReviewService reviewService = context.getBean(ReviewService.class);

		Restaurant restaurant1 = new Restaurant();
		restaurant1.setName("1학생회관");
		restaurant1.setCapacity(100);
		restaurant1.setDate("2023-10-27");
		restaurant1.setLatitude(11.1111);
		restaurant1.setLongitude(111.1111);
		restaurant1.setRestaurantRate(1.1);
		restaurantRepository.save(restaurant1);

		Restaurant restaurant2 = new Restaurant();
		restaurant2.setName("2학생회관");
		restaurant2.setCapacity(200);
		restaurant2.setDate("2023-10-27");
		restaurant2.setLatitude(22.2222);
		restaurant2.setLongitude(122.2222);
		restaurant2.setRestaurantRate(2.2);
		restaurantRepository.save(restaurant2);

		Restaurant restaurant3 = new Restaurant();
		restaurant3.setName("3학생회관");
		restaurant3.setCapacity(200);
		restaurant3.setDate("2023-10-27");
		restaurant3.setLatitude(33.3333);
		restaurant3.setLongitude(133.3333);
		restaurant3.setRestaurantRate(3.3);
		restaurantRepository.save(restaurant3);

		Review review1 = new Review();
		review1.setWriter("1학 고정자");
		review1.setMadeTime(DateTimeFormatter.ofPattern("yyyyMMdd HH:mm").format(LocalDateTime.now()));
		review1.setLikeCount(10);
		review1.setComment("1학 is my life");
		review1.setImgLink("1학만이내세상/image.jpg");
		review1.setReviewRate(1.1);
		Long reviewId1 = reviewService.registerReview(review1, "1학생회관");

		Review review2 = new Review();
		review2.setWriter("2학 고정자");
		review2.setMadeTime(DateTimeFormatter.ofPattern("yyyyMMdd HH:mm").format(LocalDateTime.now()));
		review2.setLikeCount(20);
		review2.setComment("2학 is my life");
		review2.setImgLink("2학만이내세상/image.jpg");
		review2.setReviewRate(2.2);
		Long reviewId2 = reviewService.registerReview(review2, "2학생회관");

		Review review22 = new Review();
		review22.setWriter("2학 고정자");
		review22.setMadeTime(DateTimeFormatter.ofPattern("yyyyMMdd HH:mm").format(LocalDateTime.now()));
		review22.setLikeCount(20);
		review22.setComment("2학 is my life");
		review22.setImgLink("2학만이내세상/image.jpg");
		review22.setReviewRate(2.2);
		Long reviewId22 = reviewService.registerReview(review22, "2학생회관");
		Review review3 = new Review();
		review3.setWriter("3학 고정자");
		review3.setMadeTime(DateTimeFormatter.ofPattern("yyyyMMdd HH:mm").format(LocalDateTime.now()));
		review3.setLikeCount(30);
		review3.setComment("3학 is my life");
		review3.setImgLink("3학만이내세상/image.jpg");
		review3.setReviewRate(3.3);
		Long reviewId3 = reviewService.registerReview(review3, "3학생회관");

		System.out.println("Review created with ID: " + reviewId1);
		System.out.println("Review created with ID: " + reviewId2);
		System.out.println("Review created with ID: " + reviewId3);
	}
}
