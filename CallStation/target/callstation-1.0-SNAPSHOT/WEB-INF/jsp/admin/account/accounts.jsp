<%--
  Created by IntelliJ IDEA.
  User: Razanio
  Date: 05.06.2021
  Time: 20:58
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
            <th>Абонент</th>
            <th>Статус</th>
        </tr>
        <c:forEach var="account" items="${accounts}">
            <tr>
                <td class="content">${account.client.login}</td>
                <c:choose>
                    <c:when test="${account.status==true}">
                        <td class="content">Активен</td>
                    </c:when>
                    <c:otherwise>
                        <td class="content">Заблокирован</td>
                    </c:otherwise>
                </c:choose>
            </tr>
        </c:forEach>
    </table><br>

    <form action="addaccount.html" method="post">
        <button class="add">Добавить</button>
    </form>
    <form action="showaccount.html" method="post">
        <select size="1" name="showaccount">
            <option disabled>Выберите абонента</option>
            <c:forEach var="account" items="${accounts}">
                <option value="${account.client.login}">${account.client.login}</option>
            </c:forEach>
        </select>
        <c:choose>
            <c:when test="${not empty accounts}">
                <button class="show">Просмотреть</button>
            </c:when>
            <c:otherwise>
                <button class="show" disabled>Просмотреть</button>
            </c:otherwise>
        </c:choose>
    </form>
    <form action="../adminview.html" method="get">
        <button class="cancel">Обратно</button>
    </form>
</u:page>