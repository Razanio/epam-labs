<%--
  Created by IntelliJ IDEA.
  User: Razanio
  Date: 24.05.2021
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<u:page title="Изменить пароль">
    <h1>Телефонная станция</h1>
    <h2>${title}</h2>
    <h4>Пользователь: ${user.login}</h4>
    <form action="savepass.html" method="post">
        <table>
            <tr>
                <td><label for="oldpass">Введите старый пароль:</label></td>
                <td><input type="password" id="oldpass" name="oldpass" ></td>
            </tr>
            <tr>
                <td><label for="newpass">Введите новый пароль:</label></td>
                <td><input type="password" id="newpass" name="newpass"></td>
            </tr>
        </table>
        <br>
        <button class="save">Сохранить</button>
    </form>
    <c:url var="url" value="${page}"/>
    <form action="${url}" method="get">
        <button class="cancel">Обратно</button>
    </form>
</u:page>
