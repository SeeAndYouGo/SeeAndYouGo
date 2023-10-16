package com.SeeAndYouGo.SeeAndYouGo.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Transactional
@RequiredArgsConstructor
public class RestaurantRepository {

    private final EntityManager em;

    public void save(Restaurant restaurant) {
        em.persist(restaurant);
    }

    public Restaurant findByName(String name) {
        try {
            return em.createQuery("select r from Restaurant r where r.name = :name", Restaurant.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public Restaurant findOne(Long id) {
        return em.find(Restaurant.class, id);
    }

    public List<Restaurant> findAll() {
        return em.createQuery("SELECT r FROM Restaurant r", Restaurant.class)
                .getResultList();
    }

    public void deleteAll() {
        em.createQuery("DELETE FROM Restaurant").executeUpdate();
    }

//    public Restaurant findbyNameAndDate(String parseRestaurantName, String date) {
//    }
}
