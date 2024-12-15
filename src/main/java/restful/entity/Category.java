package restful.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "category")
@NamedQueries({
    @NamedQuery(name = "Category.findById", query = "SELECT category FROM Category category WHERE category.id = :id"),
    @NamedQuery(name = "Category.findAll", query = "SELECT category FROM Category category ORDER BY category.num ASC"),
    @NamedQuery(name = "Category.countByNum", query = "SELECT COUNT(c) FROM Category c WHERE c.num = :num"),
    @NamedQuery(name = "Category.countById", query = "SELECT COUNT(c) FROM Category c WHERE c.id = :id"),
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
  private String num;

  /**
   * 分类图标
   */
  private String icon;

  public void setDataWithoutId(Category category) {
    this.title = category.title;
    this.num = category.num;
    if (category.icon != null)
      this.icon = category.icon;
  }
}

