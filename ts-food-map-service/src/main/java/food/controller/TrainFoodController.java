package food.controller;

import edu.fudan.common.util.Response;
import food.entity.TrainFood;
import food.service.FoodMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/foodmapservice")
public class TrainFoodController {

    @Autowired
    FoodMapService foodMapService;

    @GetMapping(path = "/trainfoods/welcome")
    public String home() {
        return "Welcome to [ Train Food Service ] !";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/trainfoods")
    public HttpEntity getAllTrainFood(@RequestHeader HttpHeaders headers) {
        System.out.println("[Food Map Service][Get All TrainFoods]");
        List<TrainFood> trainFoodList = foodMapService.listTrainFood(headers);
        if (trainFoodList != null && trainFoodList.size() > 0) {
            return ok(new Response(1, "Success", trainFoodList));
        } else {
            return ok(new Response(0, "No content", null));
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/trainfoods/{tripId}")
    public HttpEntity getTrainFoodOfTrip(@PathVariable String tripId, @RequestHeader HttpHeaders headers) {
        System.out.println("[Food Map Service][Get TrainFoods By TripId]");
        List<TrainFood> trainFoodList = foodMapService.listTrainFoodByTripId(tripId, headers);
        if (trainFoodList != null) {
            return ok(new Response(1, "Success", trainFoodList));
        } else {
            return ok(new Response(0, "No content", null));
        }
    }
}