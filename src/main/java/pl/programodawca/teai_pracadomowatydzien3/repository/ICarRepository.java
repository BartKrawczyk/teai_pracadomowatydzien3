package pl.programodawca.teai_pracadomowatydzien3.repository;

import pl.programodawca.teai_pracadomowatydzien3.model.Car;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ICarRepository {
    List<Car> getCars();

    Optional<Car> getCar(Long id);

    void addCar(Car car);

    void deleteCar(Long id);

    Boolean ifPresentChecker(Long id);

    void updateCar(Long id, Car car);

    Car updatePartialCarData(final Car carToUpdate, final Map<Object, Object> fields);
}