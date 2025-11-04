<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.eeapp.entity.Person" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>EEApp DB Test</title>
</head>
<body>
    <h1>Person List:</h1>
    <ul>
        <%
            // Получаем список из атрибута запроса
            List<Person> persons = (List<Person>) request.getAttribute("persons");
            if (persons != null) {
                for (Person p : persons) {
                    out.println("<li>" + p.getId() + " - " + p.getName() + "</li>");
                }
            } else {
                out.println("<li>Список пуст или сервлет не установлен.</li>");
            }
        %>
    </ul>
</body>
</html>
