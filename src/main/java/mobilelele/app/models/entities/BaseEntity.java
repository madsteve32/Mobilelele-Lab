package mobilelele.app.models.entities;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
public abstract class BaseEntity {

    private long id;
    private Instant created;
    private Instant updated;

    public BaseEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @PrePersist
    public void prePersist() {
        setCreated(Instant.now());
        setUpdated(Instant.now());
    }

    @PreUpdate
    public void preUpdate() {
        setUpdated(Instant.now());
    }

    @Column(nullable = false)
    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    @Column(nullable = false)
    public Instant getUpdated() {
        return updated;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }
}
