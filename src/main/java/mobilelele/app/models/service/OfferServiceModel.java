package mobilelele.app.models.service;

import mobilelele.app.models.entities.enums.Engine;
import mobilelele.app.models.entities.enums.Transmission;
import mobilelele.app.validation.YearInPastOrPresent;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class OfferServiceModel {

    private Long modelId;
    private double price;
    private Engine engine;
    private Transmission transmission;
    private int year;
    private int mileage;
    private String description;
    private String imageUrl;

    public OfferServiceModel() {
    }

    @NotNull
    public Long getModelId() {
        return modelId;
    }

    public OfferServiceModel setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }

    @NotNull
    @Min(value = 100)
    public double getPrice() {
        return price;
    }

    public OfferServiceModel setPrice(double price) {
        this.price = price;
        return this;
    }

    @NotNull
    public Engine getEngine() {
        return engine;
    }

    public OfferServiceModel setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    @NotNull
    public Transmission getTransmission() {
        return transmission;
    }

    public OfferServiceModel setTransmission(Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    @YearInPastOrPresent(minYear = 1900)
    public int getYear() {
        return year;
    }

    public OfferServiceModel setYear(int year) {
        this.year = year;
        return this;
    }

    @NotNull
    @PositiveOrZero
    public int getMileage() {
        return mileage;
    }

    public OfferServiceModel setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    @NotEmpty
    @Length(min = 10, max = 512)
    public String getDescription() {
        return description;
    }

    public OfferServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotEmpty
    public String getImageUrl() {
        return imageUrl;
    }

    public OfferServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
