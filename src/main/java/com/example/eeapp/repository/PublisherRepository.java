package com.example.eeapp.repository;

import com.example.eeapp.model.Publisher;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PublisherRepository {

    @PersistenceContext(unitName = "myAppPU")
    private EntityManager em;

    /**
     * Сохранение или обновление издателя
     */
    public Publisher save(Publisher publisher) {
        return em.merge(publisher);
    }

    /**
     * Поиск по ID
     */
    public Publisher findById(Long id) {
        return em.find(Publisher.class, id);
    }

    /**
     * Получение всех издателей
     */
    public List<Publisher> findAll() {
        return em.createQuery("SELECT p FROM Publisher p", Publisher.class)
                 .getResultList();
    }
}