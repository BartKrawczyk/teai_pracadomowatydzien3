package pl.programodawca.teai_pracadomowatydzien3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.programodawca.teai_pracadomowatydzien3.repository.CarRepositoryImpl;

@SpringBootApplication
public class TeaiPracadomowatydzien3Application {

    public static void main(String[] args) {
        SpringApplication.run(TeaiPracadomowatydzien3Application.class, args);
    }

    @Bean
    CommandLineRunner init(CarRepositoryImpl carRepository) {
        return args -> {
            carRepository.generateCars();
        };
    }
}

