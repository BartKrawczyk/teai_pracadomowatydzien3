package pl.programodawca.teai_pracadomowatydzien3.dto.request;

import pl.programodawca.teai_pracadomowatydzien3.model.Car;

public class CarRequestDTO {

    private Long id;
    private String mark;
    private String model;
    private String color;

    public CarRequestDTO(Car car) {
        this.id = car.getId();
        this.mark = car.getMark();
        this.model = car.getModel();
        this.color = String.valueOf(car.getColor());
    }

    public CarRequestDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "CarRequestDTO{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

