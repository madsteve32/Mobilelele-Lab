package mobilelele.app.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

    private String name;
    private Model model;

    public Brand() {
    }

    @Column(unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(mappedBy = "brand")
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
