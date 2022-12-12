package learn.capstone.controllers;

import learn.capstone.domain.Result;
import learn.capstone.domain.WishService;
import learn.capstone.models.Wish;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/travelgenie/wish")
public class WishController {
    private final WishService service;

    public WishController(WishService service) {
        this.service = service;
    }

    @GetMapping
    public List<Wish> findAll() {
        return service.findAll();
    }

    @PostMapping("/match")
    public List<Wish> findAllMatching(@RequestBody Wish wish) {
        return service.findAllMatching(wish);
    }

//    Success: 200 OK
//    Invalid Input: 400 Bad Request
//    Failure (cannot be found): 404 Not Found
    @GetMapping("/{wishId}")
    public ResponseEntity<?> findById(@PathVariable int wishId) {
        Result<Wish> result = service.findById(wishId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.OK);
        }
        return ErrorResponse.build(result);
    }

    @GetMapping ("/user/{userId}")
    public List<Wish> findByAppUserId(@PathVariable int userId) {
        return service.findByAppUserId(userId);
    }


//    Success: 201 Created
//    Invalid Input: 400 Bad Request
    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Wish wish) {
        Result<Wish> result = service.add(wish);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

//    Success: 204 No Content
//    Failure (cannot be found): 404 Not Found
    @DeleteMapping("/{wishId}")
    public ResponseEntity<Void> deleteById(@PathVariable int wishId) {
        if (service.deleteById(wishId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
