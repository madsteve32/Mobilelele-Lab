package mobilelele.app.models.entities;

import mobilelele.app.models.entities.enums.Engine;
import mobilelele.app.models.entities.enums.Transmission;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "offers")
public class Offer extends BaseEntity {

    private String description;
    private Engine engine;
    private String imageUrl;
    private int mileage;
    private double price;
    private Transmission transmission;
    private int year;
    private LocalDateTime modified;
    private Model model;
    private User seller;

    public Offer() {
    }

    @Column
    @Lob
    public String getDescription() {
        return description;
    }

    public void setDescription(String text) {
        this.description = text;
    }

    @Enumerated(EnumType.STRING)
    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @Column
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Enumerated(EnumType.STRING)
    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Column
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Column
    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @ManyToOne
    @JoinColumn(name = "model_id")
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @ManyToOne
    @JoinColumn(name = "seller_id")
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
