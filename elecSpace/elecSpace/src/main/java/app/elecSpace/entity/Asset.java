package app.elecSpace.entity;

import app.elecSpace.enums.Status;
import jakarta.persistence.*;

@Entity
@Table(name = "assets")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long tagNumber;
    private String description;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Asset() { }
    public Asset(String name, long tagNumber, String description, Status status) {
        this.name = name;
        this.tagNumber = tagNumber;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public long getTagNumber() {
        return tagNumber;
    }
    public void setTagNumber(long tagNumber) {
        this.tagNumber = tagNumber;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
