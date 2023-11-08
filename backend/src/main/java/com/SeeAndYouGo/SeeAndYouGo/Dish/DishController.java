package com.SeeAndYouGo.SeeAndYouGo.Dish;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class DishController {
    private final DishService dishService;

    @PutMapping("/mainMenu")
    public String updateMainDish(@RequestBody List<MainDishResponse> mainDishResponses){

        dishService.updateMainDish(mainDishResponses);
        return "Data updated successfully";
    }

    @GetMapping("/week")
    @Scheduled(cron="0 0 0 * * SAT")
    public void week() throws Exception {
        dishService.saveAndCacheWeekDish(1);
        dishService.saveAndCacheWeekDish(2);
        dishService.saveAndCacheWeekDish(3);
    }

    @GetMapping("/day")
    @Scheduled(cron="0 0 0 * * MON-FRI")
    public void day() throws Exception {
        dishService.saveAndCacheTodayDish();
    }


}
