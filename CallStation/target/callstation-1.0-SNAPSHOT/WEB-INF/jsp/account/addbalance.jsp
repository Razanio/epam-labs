<%--
  Created by IntelliJ IDEA.
  User: Razanio
  Date: 24.05.2021
  Time: 3:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<u:page title="Пополнение счёта">
    <h1>Телефонная станция</h1>
    <h2>${title}</h2>
    <h3>Текущий баланс: ${balance}</h3>
    <form action="savebalance.html" method="post">
        <label for="integer">Введите баланс:</label>
        <input type="number" min="1" id="integer" name="integer" value="1">
        <label for="decimal">.</label>
        <input type="number" min="0" max="99" id="decimal" name="decimal" value="0">
        <br>
        <button class="save">Сохранить</button>
    </form>
    <form action="accountview.html" method="get">
        <button class="cancel">Обратно</button>
    </form>
</u:page>
