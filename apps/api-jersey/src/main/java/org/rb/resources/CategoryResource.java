package org.rb.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.persistence.*;
import org.rb.entities.Category;
import org.rb.entities.Item;
import java.util.List;

@Path("/categories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoryResource {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    // Liste toutes les catégories
    @GET
    public List<Category> getCategories() {
        EntityManager em = emf.createEntityManager();
        List<Category> categories = em.createQuery("SELECT c FROM Category c", Category.class)
                                      .getResultList();
        em.close();
        return categories;
    }

    // Obtenir une catégorie par ID
    @GET
    @Path("/{id}")
    public Category getCategory(@PathParam("id") Long id) {
        EntityManager em = emf.createEntityManager();
        Category category = em.find(Category.class, id);
        em.close();
        return category;
    }

    // Lister les items d'une catégorie spécifique
    @GET
    @Path("/{id}/items")
    public List<Item> getItemsByCategory(@PathParam("id") Long categoryId) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT i FROM Item i JOIN FETCH i.category c WHERE c.id = :cid";
        List<Item> items = em.createQuery(jpql, Item.class)
                             .setParameter("cid", categoryId)
                             .getResultList();
        em.close();
        return items;
    }

    // Créer une nouvelle catégorie
    @POST
    public Category createCategory(Category category) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(category);
        tx.commit();
        em.close();
        return category;
    }

    // Mettre à jour une catégorie existante
    @PUT
    @Path("/{id}")
    public Category updateCategory(@PathParam("id") Long id, Category updatedCategory) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Category category = em.find(Category.class, id);
        if (category != null) {
            category.setName(updatedCategory.getName());
        }
        tx.commit();
        em.close();
        return category;
    }

    // Supprimer une catégorie
    @DELETE
    @Path("/{id}")
    public void deleteCategory(@PathParam("id") Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Category category = em.find(Category.class, id);
        if (category != null) {
            em.remove(category);
        }
        tx.commit();
        em.close();
    }
}
