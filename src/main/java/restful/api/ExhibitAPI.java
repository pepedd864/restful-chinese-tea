package restful.api;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.ws.rs.*;
import restful.bean.Result;
import restful.database.EM;
import restful.entity.Exhibit;

import java.util.ArrayList;
import java.util.List;

@Path("/exhibits")
public class ExhibitAPI {

    @POST
    @Path("/create")
    @Consumes("application/json;charset=UTF-8")
    @Produces("application/json;charset=UTF-8")
    public Result create(Exhibit exhibit) {
        EntityManagerFactory emf = EM.getEntityManagerFactory();
        EntityTransaction transaction = null;

        try (EntityManager entityManager = emf.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // 使用 NamedQuery 检查 num 是否唯一
            Long count = entityManager.createNamedQuery("Exhibit.countByNum", Long.class)
                    .setParameter("num", exhibit.getNum())
                    .getSingleResult();

            if (count > 0) {
                transaction.rollback();
                return new Result(-2, "展品编号: " + exhibit.getNum() + " 已存在", null, "");
            }

            // 检查分类是否存在 exhibit.getCategoryId
            Long categoryCount = entityManager.createNamedQuery("Category.countById", Long.class)
                    .setParameter("id", exhibit.getCategoryId())
                    .getSingleResult();

            if (categoryCount == 0) {
                transaction.rollback();
                return new Result(-1, "分类id: " + exhibit.getCategoryId() + " 不存在", null, "");
            }

            // 插入新分类
            exhibit.setId(0); // 重置 ID 以生成新记录
            entityManager.persist(exhibit);
            transaction.commit();
            return new Result(0, "添加成功", exhibit, "");
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
    public Result delete(long exhibitId) {
        EntityManagerFactory emf = EM.getEntityManagerFactory();
        EntityTransaction transaction = null;
        try (EntityManager entityManager = emf.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Exhibit exhibit = entityManager.find(Exhibit.class, exhibitId);
            if (exhibit != null) {
                // 删除 Category 实体
                entityManager.remove(exhibit);
                transaction.commit();
                return new Result(0, "成功删除", exhibit, "");
            } else {
                transaction.rollback();
                return new Result(-1, "未找到指定的展品", null, "");
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
    public Result update(Exhibit updatedExhibit) {
        EntityManagerFactory emf = EM.getEntityManagerFactory();
        EntityTransaction transaction = null;

        try (EntityManager entityManager = emf.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // 查找现有的展品
            Exhibit existingExhibit = entityManager.find(Exhibit.class, updatedExhibit.getId());

            if (existingExhibit == null) {
                transaction.rollback();
                return new Result(-1, "未找到指定的展品", null, "");
            }

            // 检查 num 是否更新，且新 num 是否唯一
            if (!updatedExhibit.getNum().equals(existingExhibit.getNum())) {
                Long count = entityManager.createNamedQuery("Exhibit.countByNum", Long.class)
                        .setParameter("num", updatedExhibit.getNum())
                        .getSingleResult();

                if (count > 0) {
                    transaction.rollback();
                    return new Result(-2, "新的展品编号: " + updatedExhibit.getNum() + " 已存在", null, "");
                }
            }

            if (existingExhibit.getCategoryId() != updatedExhibit.getCategoryId()) {
                Long categoryCount = entityManager.createNamedQuery("Category.countById", Long.class)
                        .setParameter("id", updatedExhibit.getCategoryId())
                        .getSingleResult();

                if (categoryCount == 0) {
                    transaction.rollback();
                    return new Result(-1, "分类id: " + updatedExhibit.getCategoryId() + " 不存在", null, "");
                }
            }
            // 更新属性
            existingExhibit.setDataWithoutId(updatedExhibit);

            entityManager.merge(existingExhibit);
            transaction.commit();
            return new Result(0, "更新成功", existingExhibit, "");

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return new Result(-100, "更新失败: " + e.getMessage(), null, "");
        }
    }

    @GET
    @Path("/get/{exhibitId}")
    @Consumes("application/json;charset=UTF-8")
    @Produces("application/json;charset=UTF-8")
    public Result get(@PathParam("exhibitId") long exhibitId) {
        EntityManagerFactory emf = EM.getEntityManagerFactory();
        EntityTransaction transaction = null;

        try (EntityManager entityManager = emf.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // 查找现有的分类
            Exhibit exhibit = entityManager.find(Exhibit.class, exhibitId);

            if (exhibit == null) {
                transaction.rollback();
                return new Result(-1, "未找到指定的展品", null, "");
            }

            return new Result(0, "查询成功", exhibit, "");

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

            List<Exhibit> exhibits = entityManager.createNamedQuery("Exhibit.findAll", Exhibit.class).getResultList();

            if (exhibits.isEmpty()) {
                transaction.rollback();
                return new Result(-1, "没有展品数据", null, "");
            }

            return new Result(0, "查询成功", exhibits, "");

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return new Result(-100, "查询失败: " + e.getMessage(), null, "");
        }
    }


    @GET
    @Path("/get/exList/{categoryId}")
    @Consumes("application/json;charset=UTF-8")
    @Produces("application/json;charset=UTF-8")
    public Result getExhibitsList(@PathParam("categoryId") long categoryId) {
        EntityManagerFactory emf = EM.getEntityManagerFactory();
        EntityTransaction transaction = null;

        try (EntityManager entityManager = emf.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // 使用命名查询并传递 categoryId 参数
            TypedQuery<Exhibit> query = entityManager.createNamedQuery("Exhibit.findByCategoryId", Exhibit.class);
            query.setParameter("categoryId", categoryId);
            List<Exhibit> exhibits = query.getResultList();

            if (exhibits.isEmpty()) {
                transaction.rollback();
                return new Result(-1, "分类不存在或没有展品数据", null, "");
            }

            transaction.commit(); // 提交事务
            return new Result(0, "查询成功", exhibits, "");

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return new Result(-100, "查询失败: " + e.getMessage(), null, "");
        }
    }

    @GET
    @Path("/get/exIdList/{categoryId}")
    @Consumes("application/json;charset=UTF-8")
    @Produces("application/json;charset=UTF-8")
    public Result getExIdList(@PathParam("categoryId") long categoryId) {
        EntityManagerFactory emf = EM.getEntityManagerFactory();
        EntityTransaction transaction = null;

        try (EntityManager entityManager = emf.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // 使用命名查询并传递 categoryId 参数
            TypedQuery<Long> query = entityManager.createNamedQuery("Exhibit.findIdByCategoryId", Long.class);
            query.setParameter("categoryId", categoryId);
            List<Long> exIdList = query.getResultList();

            if (exIdList.isEmpty()) {
                transaction.rollback();
                return new Result(-1, "分类不存在或没有展品数据", null, "");
            }

            transaction.commit(); // 提交事务
            return new Result(0, "查询成功", exIdList, "");

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return new Result(-100, "查询失败: " + e.getMessage(), null, "");
        }
    }

    @GET
    @Path("/search")
    @Consumes("application/json;charset=UTF-8")
    @Produces("application/json;charset=UTF-8")
    public Result search(@QueryParam("category") Integer categoryId, @QueryParam("keyword") String keyword, @QueryParam("title") String title, @QueryParam("description") String description, @QueryParam("num") String num) {
        EntityManagerFactory emf = EM.getEntityManagerFactory();
        EntityTransaction transaction = null;
        try (EntityManager entityManager = emf.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Exhibit> cq = cb.createQuery(Exhibit.class);
            Root<Exhibit> exhibit = cq.from(Exhibit.class);

            List<Predicate> predicates = new ArrayList<>();

            if (categoryId != null) {
                predicates.add(cb.equal(exhibit.get("categoryId"), categoryId));
            }
            if ((title != null && !title.isEmpty()) || (description != null && !description.isEmpty()) || (num != null && !num.isEmpty())) {
                if (title != null && !title.isEmpty())
                    predicates.add(cb.like(exhibit.get("title"), "%" + title + "%"));

                if (description != null && !description.isEmpty())
                    predicates.add(cb.like(exhibit.get("description"), "%" + description + "%"));

                if (num != null && !num.isEmpty())
                    predicates.add(cb.like(exhibit.get("num"), "%" + num + "%"));

            } else if (keyword != null && !keyword.isEmpty()) {
                String likeKeyword = "%" + keyword + "%";
                predicates.add(cb.or(
                        cb.like(exhibit.get("title"), likeKeyword),
                        cb.like(exhibit.get("description"), likeKeyword),
                        cb.like(exhibit.get("num"), likeKeyword)
                ));
            }

            cq.where(predicates.toArray(new Predicate[0]));

            TypedQuery<Exhibit> query = entityManager.createQuery(cq);
            List<Exhibit> results = query.getResultList();

            // 假设Result类有一个构造函数接受List<Exhibit>
            if (results.isEmpty())
                return new Result(-1, "没有找到相关展品", null, "");
            else
                return new Result(0, "查询成功", results, "");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return new Result(-100, "查询失败: " + e.getMessage(), null, "");
        }
    }
}
