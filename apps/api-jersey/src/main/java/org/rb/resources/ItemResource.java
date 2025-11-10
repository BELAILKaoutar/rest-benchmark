package org.rb.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.persistence.*;
import org.rb.entities.Item;
import org.rb.dto.ItemDTO;

import java.util.List;
import java.util.stream.Collectors;

@Path("/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");

    // Récupérer tous les items ou filtrer par catégorie
    @GET
    public List<ItemDTO> getItems(@QueryParam("categoryId") Long cid) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT i FROM Item i JOIN FETCH i.category";
        if (cid != null) jpql += " WHERE i.category.id = :cid";
        TypedQuery<Item> query = em.createQuery(jpql, Item.class);
        if (cid != null) query.setParameter("cid", cid);
        List<Item> items = query.getResultList();
        em.close();
        return items.stream().map(ItemDTO::new).collect(Collectors.toList());
    }

    // Récupérer un item par ID
    @GET
    @Path("/{id}")
    public ItemDTO getItem(@PathParam("id") Long id) {
        EntityManager em = emf.createEntityManager();
        Item item = em.find(Item.class, id);
        em.close();
        if (item == null) return null;
        return new ItemDTO(item);
    }
}
