Практическая работа №1
Приложение с типовой архитектурой JakartaEE

6133_СильнягинаОА

* **Сервер приложений:** Payara Server 6.2025.9
* **СУБД:** PostgreSQL
* **Сборка:** Maven
* **Версия Java:** JDK 17
* **Используемые спецификации Jakarta EE:**
    * **Представление:** Jakarta Faces (JSF 4.0)
    * **Логика:** Jakarta Contexts and Dependency Injection (CDI 4.0)
    * **Данные:** Jakarta Persistence (JPA 3.1) + EJB 4.0
    * **Соединение с БД:** JDBC

Проект разделен на три слоя
Развёртывание приложения осуществлялось вручную через консоль администратора Payara (asadmin)
Для подключения к PostgreSQL были настроены следующие ресурсы на Payara
1. Connection Pool: PostgresPool (драйвер PostgreSQL)
2. JDBC Resource: jdbc/MyDataSource (используется JPA)
Приложение доступно по адресу http://localhost:8080/eeapp/bookList.xhtml
<img width="959" height="475" alt="image" src="https://github.com/user-attachments/assets/f2ae9756-6f10-4a09-a228-3d49cb3dad7e" />
