<%--
  Created by IntelliJ IDEA.
  User: Razanio
  Date: 24.05.2021
  Time: 2:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<u:page title="Список подключенных услуг">
    <h1>Телефонная станция</h1>
    <h2>${title}</h2>
    <h4>Пользователь: ${user.login}</h4>
    <table>
        <tr>
            <th>Название услуги</th>
            <th>Стоимость</th>
            <td>&nbsp;</td>
        </tr>
        <c:forEach var="addon" items="${addons}">
            <tr>
                <td class="content">${addon.name}</td>
                <td class="content">${addon.cost}</td>
            </tr>
        </c:forEach>
    </table><br>
    <form action="addaddon.html" method="post">
        <button class="add">Добавить</button>
    </form>
    <form action="changeaddon.html" method="post">
        <select size="1" name="changeaddon">
            <option disabled>Выберите услугу</option>
            <c:forEach var="addon" items="${addons}">
                <option value="${addon}">${addon}</option>
            </c:forEach>
        </select>
        <c:choose>
            <c:when test="${not empty addons}">
                <button class="change">Изменить</button>
            </c:when>
            <c:otherwise>
                <button class="change" disabled>Изменить</button>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="deladdon.html" method="post">
        <select size="1" name="deladdon">
            <option disabled>Выберите услугу</option>
            <c:forEach var="addon" items="${addons}">
                <option value="${addon}">${addon}</option>
            </c:forEach>
        </select>
        <c:choose>
            <c:when test="${not empty addons}">
                <button class="delete">Удалить</button>
            </c:when>
            <c:otherwise>
                <button class="delete" disabled>Удалить</button>
            </c:otherwise>
        </c:choose>
    </form>

    <form action="../adminview.html" method="get">
        <button class="cancel">Обратно</button>
    </form>
</u:page>