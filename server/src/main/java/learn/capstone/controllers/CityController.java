package learn.capstone.controllers;

import learn.capstone.domain.CityService;
import learn.capstone.domain.Result;
import learn.capstone.domain.ResultType;
import learn.capstone.models.City;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/travelgenie/city")
public class CityController {

    private final CityService service;

    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping
    public List<City> findAll() {
        return service.findAll();
    }

//    Success: 200 OK
//    Invalid Input: 400 Bad Request
//    Failure (cannot be found): 404 Not Found
    @GetMapping("/{cityName}")
    public ResponseEntity<?> findByName(@PathVariable String cityName) {
        Result<City> result = service.findByName(cityName);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        }
        return ErrorResponse.build(result);
    }
    @GetMapping("/scenery/{sceneryName}")
    public List<City> findByScenery(@PathVariable String sceneryName) {
        return service.findByScenery(sceneryName);
    }


}