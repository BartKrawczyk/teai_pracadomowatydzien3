package pl.programodawca.teai_pracadomowatydzien3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import pl.programodawca.teai_pracadomowatydzien3.dto.request.CarRequestDTO;
import pl.programodawca.teai_pracadomowatydzien3.dto.response.CarResponseDTO;
import pl.programodawca.teai_pracadomowatydzien3.model.Car;
import pl.programodawca.teai_pracadomowatydzien3.model.Color;
import pl.programodawca.teai_pracadomowatydzien3.repository.ICarRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class CarService {

    private final ICarRepository carRepository;

    public CarService(ICarRepository iCarRepository) {
        this.carRepository = iCarRepository;
    }

    public ResponseEntity getCars() {
        return new ResponseEntity(mapAllCars(), HttpStatus.OK);
    }

    public ResponseEntity<CarResponseDTO> getCarById(Long id) {
        return carRepository.getCar(id)
                .map(this::getResponseWithSelectedCar)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<CarResponseDTO>> getCarByColorService(String color) {
        List<CarResponseDTO> carsWithColor = mapAllCars().stream().filter(car -> car.getColor().equals(color.toLowerCase())).collect(Collectors.toList());
        if (!carsWithColor.isEmpty()) {
            return new ResponseEntity<>(carsWithColor, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<CarResponseDTO> addCar(CarRequestDTO requestDTO) {
        Car car = new Car();
        car.setMark(requestDTO.getMark());
        car.setModel(requestDTO.getModel());
        car.setColor(Color.valueOf(requestDTO.getColor().toUpperCase()));
        carRepository.addCar(car);
        if (carRepository.ifPresentChecker(car.getId())) {
            return getResponseWithSelectedCar(car);
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    public HttpStatus removeCar(Long id) {
        if (carRepository.getCar(id).isPresent()) {
            carRepository.deleteCar(id);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    public ResponseEntity<CarResponseDTO> updateCar(Long id, CarRequestDTO requestDTO) {
        if (carRepository.getCar(id).isPresent()) {
            carRepository.deleteCar(id);
            Car car = new Car();
            car.setId(id);
            car.setMark(requestDTO.getMark());
            car.setModel(requestDTO.getModel());
            car.setColor(Color.valueOf(requestDTO.getColor().toUpperCase()));
            carRepository.updateCar(id, car);
            return getResponseWithSelectedCar(car);
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<CarResponseDTO> patchCarById(final Long id, final Map<Object, Object> fields) {
        final Optional<Car> queriedCar = carRepository.getCar(id);
        if (queriedCar.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Car patchedCar = carRepository.updatePartialCarData(queriedCar.get(), fields);
        return getResponseWithSelectedCar(patchedCar);
    }

    private ResponseEntity<CarResponseDTO> getResponseWithSelectedCar(Car car) {
        return new ResponseEntity<>(new CarResponseDTO(car), HttpStatus.OK);
    }

    private List<CarResponseDTO> mapAllCars() {
        return carRepository.getCars()
                .stream()
                .map(CarResponseDTO::new)
                .collect(Collectors.toList());
    }
}
