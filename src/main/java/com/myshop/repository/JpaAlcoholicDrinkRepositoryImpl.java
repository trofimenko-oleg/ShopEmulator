package com.myshop.repository;

import com.myshop.domain.Drink;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;


public class JpaAlcoholicDrinkRepositoryImpl extends AbstractJpaDrinkRepository implements DrinkRepository{

   /* @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Drink save(Drink drink) {
        if (drink.isNew()){
            em.persist(drink);
            return drink;
        }
        else {
            return em.merge(drink);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        em.remove(get(id));
    }

    @Override
    public Drink get(int id) {
        return em.find(Drink.class, id);
    }

    @Override
    public List<Drink> getByPartOfName(String searchString) {
        List<Drink> result = em.createNamedQuery(Drink.SEARCH_BY_PART_OF_NAME, Drink.class)
                .getResultList();
        return result != null ? result : Collections.EMPTY_LIST;
    }

    @Override
    public List<Drink> getAll() {
        return em.createNamedQuery(Drink.NO_SORT, Drink.class)
                .getResultList();
    }*/

}
