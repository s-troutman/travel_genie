package learn.capstone.controllers;

import learn.capstone.domain.Result;
import learn.capstone.security.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/travelgenie/user")
public class AppUserController {

    private final AppUserService service;

    public AppUserController(AppUserService service) {
        this.service = service;
    }

//    Success: 204 No Content
//    Failure (validation errors): 400 Bad Request
//    Failure (route path ID and request body ID don't match): 409 Conflict

    @PutMapping("/updateAccount/{appUserId}")
    public ResponseEntity<Object> update(@PathVariable int appUserId, @RequestBody Map<String, String> credentials) {
        int requestUserId = Integer.parseInt(credentials.get("appUserId"));
        String nickname = credentials.get("nickname");

        if (appUserId != requestUserId) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<String> result = service.update(requestUserId, nickname);

        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }
}
