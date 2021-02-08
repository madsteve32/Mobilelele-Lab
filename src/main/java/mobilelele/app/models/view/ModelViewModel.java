package mobilelele.app.models.view;

import mobilelele.app.models.entities.enums.Category;

public class ModelViewModel {

    private String name;
    private Category category;
    private String imageUrl;
    private int startYear;
    private Integer endYear;

    public String getName() {
        return name;
    }

    public ModelViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public ModelViewModel setCategory(
            Category category) {
        this.category = category;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ModelViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getStartYear() {
        return startYear;
    }

    public ModelViewModel setStartYear(int startYear) {
        this.startYear = startYear;
        return this;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public ModelViewModel setEndYear(Integer endYear) {
        this.endYear = endYear;
        return this;
    }

    @Override
    public String toString() {
        return "ModelViewModel{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", imageUrl='" + imageUrl + '\'' +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                '}';
    }
}
