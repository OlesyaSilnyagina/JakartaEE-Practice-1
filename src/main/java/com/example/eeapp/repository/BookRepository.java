package com.example.eeapp.repository;

import com.example.eeapp.model.Book;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

// 1. Аннотация @Stateless превращает этот класс в EJB (Enterprise JavaBean).
// EJB - это основной компонент для слоя бизнес-логики в Jakarta EE.
@Stateless
public class BookRepository {

    // 2. Аннотация @PersistenceContext "внедряет" (injects) EntityManager.
    // EntityManager - это интерфейс JPA, который позволяет выполнять SQL-операции.
    // "myAppPU" - это имя persistence-unit из твоего persistence.xml.
    @PersistenceContext(unitName = "myAppPU")
    private EntityManager em;

    /**
     * СОЗДАНИЕ (Create) / ОБНОВЛЕНИЕ (Update)
     * Использует merge: если Book с таким ID есть, он обновляется. Если нет - создается.
     */
    public Book save(Book book) {
        return em.merge(book);
    }

    /**
     * ЧТЕНИЕ (Read) - Поиск по ID
     */
    public Book findById(Long id) {
        return em.find(Book.class, id);
    }

    /**
     * ЧТЕНИЕ (Read) - Получение всех книг
     * Используется JPQL (Jakarta Persistence Query Language) - SQL для объектов.
     */
    public List<Book> findAll() {
        // Запрос "SELECT b FROM Book b" означает "выбрать все объекты Book"
        return em.createQuery("SELECT b FROM Book b", Book.class)
                 .getResultList();
    }

    /**
     * УДАЛЕНИЕ (Delete)
     */
    public void delete(Long id) {
        Book book = findById(id);
        if (book != null) {
            em.remove(book);
        }
    }
}