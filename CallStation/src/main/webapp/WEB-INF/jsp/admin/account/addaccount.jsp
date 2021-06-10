<%--
  Created by IntelliJ IDEA.
  User: Razanio
  Date: 06.06.2021
  Time: 1:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<u:page title="Добавление аккаунта">
    <h1>Телефонная станция</h1>
    <h2>${title}</h2>
    <h4>Пользователь: ${user.login}</h4>
    <c:if test="${not empty param.message}">
        <p class="error"><fmt:message key="${param.message}"/></p>
    </c:if>
    <form action="saveaccount.html" method="post">
        <label for="name">Введите имя пользователя:</label>
        <input type="text" id="name" name="name" >
        <br>
        <button class="save">Сохранить</button>
    </form>
    <form action="accounts.html" method="get">
        <button class="cancel">Обратно</button>
    </form>
</u:page>