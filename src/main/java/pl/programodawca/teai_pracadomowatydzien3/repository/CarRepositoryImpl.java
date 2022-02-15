package pl.programodawca.teai_pracadomowatydzien3.repository;

import org.springframework.stereotype.Repository;
import pl.programodawca.teai_pracadomowatydzien3.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CarRepositoryImpl implements ICarRepository {

    List<Car> cars = new ArrayList<>();

    public void generateCars() {
        cars.add(new Car(1L, "Polonez", "Atu", "niebieski"));
        cars.add(new Car(2L, "Fiat", "125p", "niebieski"));
        cars.add(new Car(3L, "Ford", "Scorpio", "czarny"));
    }

    public List<Car> getCarsRepo() {
        return cars;
    }

    public Optional<Car> getCarRepo(Long id) {
        return cars.stream().filter(c -> c.getId() == id).findFirst();
    }

    public List<Car> getCarByColorRepo(String color) {
        List<Car> carsWithColor = cars.stream().filter(car -> car.getColor().equals(color.toLowerCase())).collect(Collectors.toList());
        return carsWithColor;
    }

    @Override
    public boolean addCarRepo(Car car) {
        return cars.add(car);
    }

    @Override
    public Optional<Car> removeCar(long id) {
        Optional<Car> first = cars.stream().filter(car -> car.getId() == id).findFirst();
        if (first.isPresent()) {
            cars.remove(first.get());
        }
        return first;
    }
}

