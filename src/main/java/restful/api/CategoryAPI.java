package restful.api;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.ws.rs.*;
import restful.bean.Result;
import restful.database.EM;
import restful.entity.Category;

import java.util.List;
import java.util.Objects;

@Path("/category")
public class CategoryAPI {

  @POST
  @Path("/create")
  @Consumes("application/json;charset=UTF-8")
  @Produces("application/json;charset=UTF-8")
  public Result create(Category category) {
    EntityManagerFactory emf = EM.getEntityManagerFactory();
    EntityTransaction transaction = null;

    try (EntityManager entityManager = emf.createEntityManager()) {
      transaction = entityManager.getTransaction();
      transaction.begin();

      // 使用 NamedQuery 检查 num 是否唯一
      Long count = entityManager.createNamedQuery("Category.countByNum", Long.class)
          .setParameter("num", category.getNum())
          .getSingleResult();

      if (count > 0) {
        transaction.rollback();
        return new Result(-2, "分类编号: " + category.getNum() + " 已存在", null, "");
      }

      // 插入新分类
      category.setId(0); // 重置 ID 以生成新记录
      entityManager.persist(category);
      transaction.commit();
      return new Result(0, "添加成功", category, "");
    } catch (Exception e) {
      if (transaction != null && transaction.isActive()) {
        transaction.rollback();
      }
      return new Result(-100, "添加失败: " + e.getMessage(), null, "");
    }
  }


  @DELETE
  @Path("/delete")
  @Consumes("application/json;charset=UTF-8")
  @Produces("application/json;charset=UTF-8")
  public Result delete(long categoryId) {
    EntityManagerFactory emf = EM.getEntityManagerFactory();
    EntityTransaction transaction = null;
    try (EntityManager entityManager = emf.createEntityManager()) {
      transaction = entityManager.getTransaction();
      transaction.begin();

      Category category = entityManager.find(Category.class, categoryId);
      if (category != null) {
        // 删除 Category 实体
        entityManager.remove(category);
        transaction.commit();
        return new Result(0, "成功删除", category, "");
      } else {
        transaction.rollback();
        return new Result(-1, "未找到指定的分类", null, "");
      }
    } catch (Exception e) {
      if (transaction != null && transaction.isActive()) {
        transaction.rollback();
      }
      return new Result(-100, "删除失败: " + e.getMessage(), null, "");
    }
  }


  @PUT
  @Path("/update")
  @Consumes("application/json;charset=UTF-8")
  @Produces("application/json;charset=UTF-8")
  public Result update(Category updatedCategory) {
    EntityManagerFactory emf = EM.getEntityManagerFactory();
    EntityTransaction transaction = null;

    try (EntityManager entityManager = emf.createEntityManager()) {
      transaction = entityManager.getTransaction();
      transaction.begin();

      // 查找现有的分类
      Category existingCategory = entityManager.find(Category.class, updatedCategory.getId());

      if (existingCategory == null) {
        transaction.rollback();
        return new Result(-1, "未找到指定的分类", null, "");
      }

      // 如果属性值为 null, 保持原有值
      if (Objects.isNull(updatedCategory.getTitle())) {
        updatedCategory.setTitle(existingCategory.getTitle());
      }
      if (Objects.isNull(updatedCategory.getIcon())) {
        updatedCategory.setIcon(existingCategory.getIcon());
      }
      if (Objects.isNull(updatedCategory.getNum())) {
        updatedCategory.setNum(existingCategory.getNum());
      }

      // 检查 num 是否更新，且新 num 是否唯一
      if (!updatedCategory.getNum().equals(existingCategory.getNum())) {
        Long count = entityManager.createNamedQuery("Category.countByNum", Long.class)
            .setParameter("num", updatedCategory.getNum())
            .getSingleResult();

        if (count > 0) {
          transaction.rollback();
          return new Result(-2, "新的分类编号: " + updatedCategory.getNum() + " 已存在", null, "");
        }
      }

      // 更新属性
      existingCategory.setDataWithoutId(updatedCategory);

      entityManager.merge(existingCategory);
      transaction.commit();
      return new Result(0, "更新成功", existingCategory, "");

    } catch (Exception e) {
      if (transaction != null && transaction.isActive()) {
        transaction.rollback();
      }
      return new Result(-100, "更新失败: " + e.getMessage(), null, "");
    }
  }


  @GET
  @Path("/get/{categoryId}")
  @Consumes("application/json;charset=UTF-8")
  @Produces("application/json;charset=UTF-8")
  public Result get(@PathParam("categoryId") long categoryId) {
    EntityManagerFactory emf = EM.getEntityManagerFactory();
    EntityTransaction transaction = null;

    try (EntityManager entityManager = emf.createEntityManager()) {
      transaction = entityManager.getTransaction();
      transaction.begin();

      // 查找现有的分类
      Category category = entityManager.find(Category.class, categoryId);

      if (category == null) {
        transaction.rollback();
        return new Result(-1, "未找到指定的分类", null, "");
      }

      return new Result(0, "查询成功", category, "");

    } catch (Exception e) {
      if (transaction != null && transaction.isActive()) {
        transaction.rollback();
      }
      return new Result(-100, "查询失败: " + e.getMessage(), null, "");
    }
  }

  @GET
  @Path("/get/All")
  @Consumes("application/json;charset=UTF-8")
  @Produces("application/json;charset=UTF-8")
  public Result get() {
    EntityManagerFactory emf = EM.getEntityManagerFactory();
    EntityTransaction transaction = null;

    try (EntityManager entityManager = emf.createEntityManager()) {
      transaction = entityManager.getTransaction();
      transaction.begin();

      List<Category> categories = entityManager.createNamedQuery("Category.findAll", Category.class).getResultList();

      if (categories.isEmpty()) {
        transaction.rollback();
        return new Result(-1, "没有分类数据", null, "");
      }

      return new Result(0, "查询成功", categories, "");

    } catch (Exception e) {
      if (transaction != null && transaction.isActive()) {
        transaction.rollback();
      }
      return new Result(-100, "查询失败: " + e.getMessage(), null, "");
    }
  }


}
