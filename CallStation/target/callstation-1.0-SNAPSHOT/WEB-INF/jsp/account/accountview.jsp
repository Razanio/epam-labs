<%--
  Created by IntelliJ IDEA.
  User: Razanio
  Date: 23.05.2021
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<u:page title="Главная страница">
    <h1>Телефонная станция</h1>
    <h4>Здраствуйте "${user.login}"</h4>
    <h4>Статус аккаунта:
        <c:choose>
            <c:when test="${(account.status==true)}">
                Активен
            </c:when>
            <c:otherwise>
                Заблокирован
            </c:otherwise>
        </c:choose>
    </h4>
    <h4>Ваш баланс равен: "${account.balance}"</h4>
    <ul>
        <li><a href="addons.html">Список услуг</a></li>
        <li><a href="addbalance.html">Пополнить баланс</a></li>
        <li><a href="../user/changepass.html">Изменить пароль</a></li>
    </ul>
    <form action="../logout.html" method="get">
        <button class="cancel">Выйти</button>
    </form>
</u:page>
