package com.SeeAndYouGo.SeeAndYouGo.Review;

import com.SeeAndYouGo.SeeAndYouGo.Menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final MenuService menuService;

    // 탑 리뷰5개 조회        --------api명세 및 로직수정
    @GetMapping("/topReview/restaurant{ number }")
    public ResponseEntity<List<Review>> getTopReviews(@PathVariable String number) {
        List<Review> reviews = reviewService.findTopReviewsByRestaurant("restaurant"+number);
        return ResponseEntity.ok(reviews);
    }

    //각 식당 전체 리뷰 조회      ---------추가
    @GetMapping("/review/restaurant{ number }")
    public List<Review> getRestaurantReviews(@PathVariable String number) {
        return reviewService.findRestaurantReviews("restaurant"+number);
    }


    //전체식당 통합 리뷰조회      ---api명세수정
    @GetMapping("/totalReview")
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
