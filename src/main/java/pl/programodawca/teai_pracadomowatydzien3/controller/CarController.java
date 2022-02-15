package pl.programodawca.teai_pracadomowatydzien3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import pl.programodawca.teai_pracadomowatydzien3.model.Car;
import pl.programodawca.teai_pracadomowatydzien3.service.CarService;

import javax.print.attribute.standard.Media;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cars", produces = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE
})
public class CarController {


    private final CarService carService;

    //Czy jest dopuszczalne by używać ResponseEntity w ten sposób?
    private static final ResponseEntity STATUS_OK = new ResponseEntity(HttpStatus.OK);
    private static final ResponseEntity STATUS_NOT_FOUND = new ResponseEntity(HttpStatus.NOT_FOUND);
    private static final ResponseEntity STATUS_INTERNAL_SERVER_ERROR = new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carService.getCarsService(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> firstCar = carService.getCarByIdService(id);
        //przy Optional lepiej orElseGet zamiast if
        return firstCar.map(car -> new ResponseEntity<>(car, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping( "/color/{color}")
    public ResponseEntity<List<Car>> getCarByColor(@PathVariable("color") String color) {
        List<Car> carsWithColor = carService.getCarByColorService(color);
        if (!carsWithColor.isEmpty()) {
            return new ResponseEntity<>(carsWithColor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car) {
        car.setId(carService.getCarsService().size() + 1);
        boolean add = carService.addCar(car);
        if (add) {
            return STATUS_OK;
        }
        return STATUS_INTERNAL_SERVER_ERROR;
    }

    @PutMapping
    public ResponseEntity modCar(@RequestBody Car newCar) {
        Optional<Car> first = carService.getCarByIdService(newCar.getId());
        if (first.isPresent()) {
            carService.removeCar(newCar.getId());
            carService.addCar(newCar);
            return STATUS_OK;
        }
        return STATUS_NOT_FOUND;
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity patchCar(@PathVariable long id, @RequestBody Map<Object, Object> fields) {
        Optional<Car> car = carService.getCarByIdService(id);
        if (car.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Car.class, (String) key);
                assert field != null;
                field.setAccessible(true);
                ReflectionUtils.setField(field, car.get(), value);
            });
            carService.removeCar(id);
            carService.addCar(car.get());
            return STATUS_OK;
        }
        return STATUS_NOT_FOUND;
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity remove(@PathVariable long id) {
        //Optional<Car> first = carService.removeCar(id);
        if (carService.removeCar(id).isPresent()) {
            return STATUS_OK;
        }
        return STATUS_NOT_FOUND;
    }
}
