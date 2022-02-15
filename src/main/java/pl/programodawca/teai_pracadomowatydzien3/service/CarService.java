package pl.programodawca.teai_pracadomowatydzien3.service;

import org.springframework.stereotype.Service;
import pl.programodawca.teai_pracadomowatydzien3.model.Car;
import pl.programodawca.teai_pracadomowatydzien3.repository.ICarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final ICarRepository iCarRepository;

    public CarService(ICarRepository iCarRepository) {
        this.iCarRepository = iCarRepository;
    }

    public List<Car> getCarsService() {
        return iCarRepository.getCarsRepo();
    }

    public Optional<Car> getCarByIdService(Long id) {
        return iCarRepository.getCarRepo(id);
    }

    public List<Car> getCarByColorService(String color) {
        return iCarRepository.getCarByColorRepo(color);
    }

    public boolean addCar(Car car) {
        return iCarRepository.addCarRepo(car);
    }

    public Optional<Car> removeCar(long id) {
        return iCarRepository.removeCar(id);
    }
}
