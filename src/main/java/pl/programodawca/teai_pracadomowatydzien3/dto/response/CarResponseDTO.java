package pl.programodawca.teai_pracadomowatydzien3.dto.response;

import pl.programodawca.teai_pracadomowatydzien3.model.Car;


public class CarResponseDTO {

    private Long id;
    private String mark;
    private String model;
    private String color;

    public CarResponseDTO(Car car) {
        this.id = car.getId();
        this.mark = car.getMark();
        this.model = car.getModel();
        this.color = car.getColor().getValue();
    }

    public CarResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "CarResponseDTO{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
