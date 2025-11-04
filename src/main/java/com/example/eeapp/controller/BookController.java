package com.example.eeapp.controller;

import com.example.eeapp.model.Book;
import com.example.eeapp.model.Publisher;
import com.example.eeapp.repository.BookRepository;
import com.example.eeapp.repository.PublisherRepository;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped; 
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

// @Named делает его доступным из JSF: #{bookController.books}
@Named("bookController")
// @SessionScoped - компонент живет, пока активна сессия браузера.
@SessionScoped
public class BookController implements Serializable {

    // @Inject автоматически внедряет наш EJB (Слой Логики)
    @Inject
    private BookRepository bookRepository;
    
    @Inject
    private PublisherRepository publisherRepository;

    private List<Book> books;
    private Book newBook = new Book(); // Модель для формы добавления/редактирования
    private Book selectedBook; // Для редактирования

    // Метод, который запускается сразу после создания контроллера
    @PostConstruct
    public void init() {
        loadBooks();
        // Вспомогательный метод для начальной загрузки данных
        initializeDefaultPublisher(); 
    }

    public void loadBooks() {
        this.books = bookRepository.findAll();
    }

    // CREATE/UPDATE: Сохраняет или обновляет книгу
    public void saveBook() {
        // Если у книги нет издателя, берем первого доступного (для простоты)
        if (newBook.getPublisher() == null) {
            List<Publisher> publishers = publisherRepository.findAll();
            if (!publishers.isEmpty()) {
                newBook.setPublisher(publishers.get(0));
            }
        }
        
        bookRepository.save(newBook);
        newBook = new Book(); // Сбрасываем форму
        loadBooks(); // Обновляем список на странице
    }
    
    // DELETE: Удаляет книгу
    public void deleteBook(Book book) {
        bookRepository.delete(book.getId());
        loadBooks(); // Обновляем список
    }
    
    // --- Вспомогательный метод для начального заполнения БД ---
    private void initializeDefaultPublisher() {
        // Если издателей нет, создаем одного
        if (publisherRepository.findAll().isEmpty()) {
            Publisher defaultPublisher = new Publisher("Java EE Press", "USA");
            publisherRepository.save(defaultPublisher);
        }
    }

    // --- Геттеры и Сеттеры (нужны для JSF) ---
    public List<Book> getBooks() {
        return books;
    }
    
    public Book getNewBook() {
        return newBook;
    }

    public void setNewBook(Book newBook) {
        this.newBook = newBook;
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }
    
    // Добавь остальные геттеры/сеттеры, если понадобятся (например, для selectedBook)
}