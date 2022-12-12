package learn.capstone.controllers;

import learn.capstone.domain.EntertainmentService;
import learn.capstone.domain.Result;
import learn.capstone.models.City;
import learn.capstone.models.Entertainment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/travelgenie/entertainment")
public class EntertainmentController {
    private final EntertainmentService service;

    public EntertainmentController(EntertainmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Entertainment> findAll() {
        return service.findAll();
    }

    @GetMapping("/{entertainmentId}")
    public ResponseEntity<? extends Object> findById(@PathVariable int entertainmentId) {
        Result<Entertainment> result = service.findById(entertainmentId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        }
        return ErrorResponse.build(result);
    }

    @GetMapping("/city/{cityName}")
    public List<Entertainment> findByCityId(@PathVariable String cityName) {
        return service.findByCityName(cityName);
    }
}
