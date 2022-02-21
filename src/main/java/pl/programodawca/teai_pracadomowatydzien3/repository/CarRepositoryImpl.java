package pl.programodawca.teai_pracadomowatydzien3.repository;

import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;
import pl.programodawca.teai_pracadomowatydzien3.model.Car;
import pl.programodawca.teai_pracadomowatydzien3.model.Color;

import java.lang.reflect.Field;
import java.util.*;

import static java.util.Objects.nonNull;

@Repository
public class CarRepositoryImpl implements ICarRepository {

    private Map<Long, Car> cars = new HashMap<>();
    private Long counter = 0L;

    public CarRepositoryImpl() {
        Car firstCar = new Car(increment(), "Fiat", "125p", Color.NIEBIESKI);
        Car secondCar = new Car(increment(), "Ford", "Transit", Color.NIEBIESKI);
        Car thirdCar = new Car(increment(), "Polonez", "Atu", Color.CZARNY);
        cars.put(firstCar.getId(), firstCar);
        cars.put(secondCar.getId(), secondCar);
        cars.put(thirdCar.getId(), thirdCar);
    }

    private Long increment() {
        return ++counter;
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars.values());
    }

    @Override
    public Optional<Car> getCar(Long id) {
        return Optional.ofNullable(cars.get(id));
    }

    public void addCar(Car car) {
        car.setId(increment());
        cars.put(car.getId(), car);
    }

    public void updateCar(Long id, Car car) {
        cars.put(id, car);
    }

    public Car updatePartialCarData(final Car carToUpdate, final Map<Object, Object> fields) {
        fields.forEach((key, value) -> {
            final Field field = ReflectionUtils.findField(Car.class, (String) key);
            if (nonNull(field)) {
                field.setAccessible(true);
                if (((String) key).contentEquals("color")) {
                    value = Color.valueOf(value.toString().toUpperCase());
                }
                ReflectionUtils.setField(field, carToUpdate, value);
            }
        });
        return carToUpdate;
    }

    public Boolean ifPresentChecker(Long id) {
        Object value = cars.get(id);
        return value != null;
    }

    public void deleteCar(Long id) {
        cars.remove(id);
    }
}


