package restful.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "exhibits")
@NamedQueries({
        @NamedQuery(name = "Exhibit.findById", query = "SELECT exhibits FROM Exhibit exhibits WHERE exhibits.id = :id"),
        @NamedQuery(name = "Exhibit.findAll", query = "SELECT exhibits FROM Exhibit exhibits ORDER BY exhibits.categoryId ASC, exhibits.num ASC"),
        @NamedQuery(name = "Exhibit.countByNum", query = "SELECT COUNT(c) FROM Exhibit c WHERE c.num = :num"),
        @NamedQuery(name = "Exhibit.findByCategoryId", query = "SELECT exhibits FROM Exhibit exhibits WHERE exhibits.categoryId = :categoryId ORDER BY exhibits.num ASC"),
        @NamedQuery(name = "Exhibit.findIdByCategoryId", query = "SELECT exhibits.id FROM Exhibit exhibits WHERE exhibits.categoryId = :categoryId ORDER BY exhibits.num ASC")
})
@Data
public class Exhibit extends IdEntity {
    private String title;
    private String num;
    @Column(name = "category_id")
    private int categoryId;
    private String description;
    private String img = "default-img.png";

    public void setDataWithoutId (Exhibit exhibit) {
        this.title = exhibit.title;
        this.num = exhibit.num;
        this.categoryId = exhibit.categoryId;
        this.description = exhibit.description;
        if(exhibit.img != null)
            this.img = exhibit.img;
    }
}
