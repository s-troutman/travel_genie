package learn.capstone.data;

import learn.capstone.models.Wish;

import java.util.List;

public interface WishFileRepository {

    Wish findById(int app_userId);

    List<Wish> findByAppUserId(int app_userId);

    List<Wish> findAll();

    List<Wish> findAllPotentialWish();

    Wish add(Wish wish);

    boolean deleteById(int wishId);
}