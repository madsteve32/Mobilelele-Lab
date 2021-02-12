package mobilelele.app.models.view;

import mobilelele.app.models.entities.Model;
import mobilelele.app.models.entities.enums.Engine;
import mobilelele.app.models.entities.enums.Transmission;

public class OfferSummaryViewModel {

    private long id;
    private Engine engine;
    private String imageUrl;
    private int mileage;
    private double price;
    private int year;
    private Transmission transmission;
    private String model;
    private String brand;

    public Engine getEngine() {
        return engine;
    }

    public long getId() {
        return id;
    }

    public OfferSummaryViewModel setId(long id) {
        this.id = id;
        return this;
    }

    public OfferSummaryViewModel setEngine(Engine engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferSummaryViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public OfferSummaryViewModel setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public OfferSummaryViewModel setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferSummaryViewModel setYear(int year) {
        this.year = year;
        return this;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public OfferSummaryViewModel setTransmission(
            Transmission transmission) {
        this.transmission = transmission;
        return this;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "OfferSummaryViewModel{" +
                "engine=" + engine +
                ", imageUrl='" + imageUrl + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                ", year=" + year +
                ", transmission=" + transmission +
                '}';
    }
}
