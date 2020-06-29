package com.myshop.repository;

import com.myshop.domain.Order;
import com.myshop.domain.OrderDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Order save(Order order) {
        if (order.isNew()) {
            em.persist(order);
            return order;
        } else {
            return em.merge(order);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        em.remove(get(id));
    }

    @Override
    public Order get(int id) {
        return em.find(Order.class, id);
    }


    @Override
    public List<Order> getAll() {
        return em.createNamedQuery(Order.BY_DATE, Order.class)
                .getResultList();
    }


    @Override
    public OrderDetails getItem(int orderDetailsId) {
        return em.find(OrderDetails.class, orderDetailsId);
    }
}
