package com.SeeAndYouGo.SeeAndYouGo.review;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 탑 리뷰 조회
    @GetMapping("/top5Review/{restaurant}")
    public List<ReviewDto> getTopReviews(@PathVariable String restaurant) {
        restaurant = parseRestaurantName(restaurant);

        String date = LocalDate.now().toString();
        List<Review> reviews = reviewService.findTopReviewsByRestaurantAndDate(restaurant, date);

        List<ReviewDto> response = new ArrayList<>();
        for (Review review : reviews) {
            response.add( ReviewDto.of(review) );
        }

        return response;
    }

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.findAllReviews();
    }

    // 리뷰 게시
    @PostMapping(value = "/review", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Long> postReview(@RequestPart(value = "review") ReviewDto requestDto, @RequestPart(value = "image") MultipartFile imgFile) throws Exception {
        Review review = new Review();
        NCloudObjectStorage NCloudObjectStorage = new NCloudObjectStorage();

        String imgUrl = NCloudObjectStorage.imgUpload(imgFile.getInputStream(), imgFile.getContentType());
        review.setWriter(requestDto.getWriter());
        review.setReviewRate(requestDto.getRate());
        review.setComment(requestDto.getComment());
        review.setImgLink(imgUrl);

        review.setMadeTime(requestDto.getMadeTime()); // 문자열 형태의 madeTime을 그대로 전달

        Long reviewId = reviewService.registerReview(review, requestDto.getRestaurant());

        return new ResponseEntity<>(reviewId, HttpStatus.CREATED);
    }



    // 리뷰 삭제
//    @DeleteMapping("/review/{reviewId}")
//    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
//        Review review = reviewService.findOne(reviewId);
//        if (review == null) {
//            return ResponseEntity.notFound().build();
//        }
//        reviewService.delete(review);
//        return ResponseEntity.ok("Review deleted successfully.");
//    }
}
