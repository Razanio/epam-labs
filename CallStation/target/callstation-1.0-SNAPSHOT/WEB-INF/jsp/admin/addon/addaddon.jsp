<%--
  Created by IntelliJ IDEA.
  User: Razanio
  Date: 06.06.2021
  Time: 1:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<u:page title="Добавление услуги">
    <h1>Телефонная станция</h1>
    <h2>${title}</h2>
    <h4>Пользователь: ${user.login}</h4>
    <c:if test="${not empty param.message}">
        <p class="error"><fmt:message key="${param.message}"/></p>
    </c:if>
    <form action="saveaddons.html" method="post">
        <table>
            <tr>
                <td>
                    <label for="name">Введите название услуги: </label>
                    <input type="text" id="name" name="name" >
                </td>
            </tr>
            <tr>
                <td>
                    <label for="integer">Введите стоимость улуги:</label>
                    <input type="number" min="1" id="integer" name="integer" value="1">
                    <label for="decimal">.</label>
                    <input type="number" min="0" max="99" id="decimal" name="decimal" value="0">
                </td>
            </tr>
        </table>
        <br>
        <button class="save">Сохранить</button>
    </form>
    <form action="addons.html" method="get">
        <button class="cancel">Обратно</button>
    </form>
</u:page>
