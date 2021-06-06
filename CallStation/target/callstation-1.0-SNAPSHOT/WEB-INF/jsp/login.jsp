<%--
  Created by IntelliJ IDEA.
  User: Razanio
  Date: 23.05.2021
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:message var="title" key="login.title"/>
<u:page title="Авторизация">
    <center>
        <c:if test="${not empty param.message}">
            <p class="error"><fmt:message key="${param.message}"/></p>
        </c:if>
        <c:url var="loginUrl" value="/login.html"/>
        <form action="${loginUrl}" method="post">
            Имя пользователя:<br>
            <input type="text" name="login"><br><br>
            Пароль:<br>
            <input type="password" name="password"><br><br>
            <button type="submit">Войти</button>
            <a href="index.html">Войти без авторизации</a>
        </form>
    </center>
</u:page>
