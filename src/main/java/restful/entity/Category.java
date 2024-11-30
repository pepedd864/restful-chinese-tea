package restful.entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "category")
@NamedQueries({
        @NamedQuery(name = "Category.findById", query = "SELECT category FROM Category category WHERE category.id = :id"),
        @NamedQuery(name = "Category.findAll", query = "SELECT category FROM Category category ORDER BY category.num ASC"),
        @NamedQuery(name = "Category.findByNum", query = "SELECT COUNT(c) FROM Category c WHERE c.num = :num"),
})
@Data
/*
 * 分类表
 */
public class Category extends IdEntity {
    /**
     * 分类标题
     */

    private String title;

    /**
     * 分类编号
     */
    private int num;

    /**
     * 分类图标
     */
    private String icon;

    public Category setDataWithoutId (Category category) {
        this.title = category.title;
        this.num = category.num;
        this.icon = category.icon;
        return this;
    }
}

