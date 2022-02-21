package pl.programodawca.teai_pracadomowatydzien3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.programodawca.teai_pracadomowatydzien3.dto.request.CarRequestDTO;
import pl.programodawca.teai_pracadomowatydzien3.dto.response.CarResponseDTO;
import pl.programodawca.teai_pracadomowatydzien3.service.CarService;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;


@RestController
@Validated
@RequestMapping(value = "/cars", produces = {
        MediaType.APPLICATION_XML_VALUE,
        MediaType.APPLICATION_JSON_VALUE
})
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity getCars() {
        return carService.getCars();
    }

    @GetMapping("/id")
    public ResponseEntity<CarResponseDTO> getCar(@RequestParam("id") @NotNull @Min(1) @Max(1000) Long id) {
        return carService.getCarById(id);
    }

    @GetMapping("/color")
    public ResponseEntity<List<CarResponseDTO>> getCarByColor(@RequestParam("color") @NotNull String color) {
        return carService.getCarByColorService(color);
    }

    @PostMapping
    public ResponseEntity<CarResponseDTO> addCar(@RequestBody @NotNull CarRequestDTO carRequestDTO) {
        return carService.addCar(carRequestDTO);
    }

    @PutMapping
    public ResponseEntity<CarResponseDTO> updateCar(@RequestParam("id") @NotNull @Min(1) @Max(1000) Long id, @RequestBody CarRequestDTO carRequestDTO) {
        return carService.updateCar(id, carRequestDTO);
    }

    @PatchMapping()
    public ResponseEntity<CarResponseDTO> patchCar(@RequestParam("id") @NotNull @Min(1) @Max(1000) Long id, @RequestBody final Map<Object, Object> fields) {
        return carService.patchCarById(id, fields);
    }

    @DeleteMapping("/id/{id}")
    public HttpStatus remove(@PathVariable @NotNull @Min(1) @Max(1000) long id) {
        return carService.removeCar(id);
    }

    // Old @PatchMapping
//    @PatchMapping("/id/{id}")
//    public ResponseEntity patchCar(@PathVariable long id, @RequestBody Map<Object, Object> fields) {
//        Optional<Car> car = carService.getCarByIdService(id);
//        if (car.isPresent()) {
//            fields.forEach((key, value) -> {
//                Field field = ReflectionUtils.findField(Car.class, (String) key);
//                assert field != null;
//                field.setAccessible(true);
//                ReflectionUtils.setField(field, car.get(), value);
//            });
//            carService.removeCar(id);
//            carService.addCar(car.get());
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.notFound().build();

//    }
}
