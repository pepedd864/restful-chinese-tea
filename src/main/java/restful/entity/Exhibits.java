package restful.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "exhibits")
@NamedQueries({

        @NamedQuery(name = "Exhibits.findAll", query = "SELECT exhibits FROM Exhibits exhibits"),
})
@Data
public class Exhibits extends IdEntity {
    private String title;
    private int num;
    private int categoryId;
    private String description;
    private String img;
}
