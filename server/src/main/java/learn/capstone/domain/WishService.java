package learn.capstone.domain;

import learn.capstone.data.WishFileRepository;
import learn.capstone.models.Wish;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishService {
    private final WishFileRepository repository;

    public WishService(WishFileRepository repository) {
        this.repository = repository;
    }

    public Result<Wish> findById(int wishId) {
        Result<Wish> result = new Result<>();

        if (wishId <= 0) {
            result.addMessage("Wish Id cannot be less than or equal to zero", ResultType.INVALID);
            return result;
        }

        Wish wish = repository.findById(wishId);

        if (wish == null) {
            result.addMessage("Wish Id was not found.", ResultType.NOT_FOUND);
            return result;
        }

        result.setPayload(wish);
        return result;
    }

    public List<Wish> findByAppUserId(int app_userId) {
        return repository.findByAppUserId(app_userId);
    }

    public List<Wish> findAll() {
        return repository.findAll();
    }

    public List<Wish> findAllMatching(Wish wish) {
        List<Wish> allPotentialWishes = repository.findAllPotentialWish();

        return allPotentialWishes.stream().filter(w -> w.equals(wish)).collect(Collectors.toList());
    }

    public Result<Wish> add(Wish wish) {
        Result<Wish> result = validate(wish);
        if (!result.isSuccess()) {
            return result;
        }

        wish = repository.add(wish);
        result.setPayload(wish);
        return result;
    }

    public boolean deleteById(int wishId) {
        return repository.deleteById(wishId);
    }

    private Result<Wish> validate(Wish wish) {
        Result<Wish> result = new Result<>();
        if (wish == null) {
            result.addMessage("Wish cannot be null.", ResultType.INVALID);
            return result;
        }

        if (wish.getWishId() != 0) {
            result.addMessage("Wish Id was not zero.", ResultType.INVALID);
            return result;
        }

        if (wish.getAppUserId() <= 0) {
            result.addMessage("AppUser Id cannot be less than or equal to zero", ResultType.INVALID);
            return result;
        }

        if (wish.getCityName() == null || wish.getCityName().trim().isEmpty() ) {
            result.addMessage("City Name cannot be null or empty", ResultType.INVALID);
            return result;
        }

        if (wish.getCountryName() == null || wish.getCountryName().trim().isEmpty() ) {
            result.addMessage("Country Name cannot be null or empty", ResultType.INVALID);
            return result;
        }

        if (wish.getScenery() == null || wish.getScenery().toString().trim().isEmpty() ) {
            result.addMessage("Scenery cannot be null or empty", ResultType.INVALID);
            return result;
        }

        if (wish.getEntertainmentName() == null || wish.getEntertainmentName().trim().isEmpty() ) {
            result.addMessage("Entertainment Name cannot be null or empty", ResultType.INVALID);
            return result;
        }

        if (wish.getActivityLevel() == null || wish.getActivityLevel().toString().trim().isEmpty() ) {
            result.addMessage("Activity Level cannot be null or empty", ResultType.INVALID);
            return result;
        }

        if (wish.getPriceRange() == null || wish.getPriceRange().toString().trim().isEmpty() ) {
            result.addMessage("Activity Level cannot be null or empty", ResultType.INVALID);
            return result;
        }
        return result;
    }

}
