package ru.springtry.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.springtry.models.Car;

import java.util.List;

public interface CarsRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByOwner_FirstName(String firstNameOwner);

    @Query(nativeQuery = true, value = "SELECT * FROM car_table WHERE model = :model")
    List<Car> findAllByModel(@Param("model") String model);
}
