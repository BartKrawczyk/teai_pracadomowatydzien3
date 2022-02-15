package pl.programodawca.teai_pracadomowatydzien3.repository;

import pl.programodawca.teai_pracadomowatydzien3.model.Car;

import java.util.List;
import java.util.Optional;

public interface ICarRepository {
    List<Car> getCarsRepo();

    Optional<Car> getCarRepo(Long id);

    List<Car> getCarByColorRepo(String color);

    boolean addCarRepo(Car car);

    Optional<Car> removeCar(long id);
}
