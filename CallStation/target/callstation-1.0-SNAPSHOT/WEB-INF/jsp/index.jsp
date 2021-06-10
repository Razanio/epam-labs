<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<u:page title="Главная страница">
    <h1>Телефонная станция</h1>
    <h2>Наши услуги</h2>
    <table>
        <tr>
            <th>Название услуги</th>
            <th>Стоимость</th>
        </tr>
        <c:forEach var="addons" items="${addons}">
            <tr>
                <td class="content">${addons.name}</td>
                <td class="content">${addons.cost}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <form action="login.html" method="get">
        <button class="cancel">Авторизоваться</button>
    </form>
</u:page>