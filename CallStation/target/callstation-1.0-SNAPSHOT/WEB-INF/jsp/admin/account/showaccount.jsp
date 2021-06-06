<%--
  Created by IntelliJ IDEA.
  User: Razanio
  Date: 06.06.2021
  Time: 15:35
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

  <table>
    <tr>
      <th>Абонент</th>
      <th>Кол-во услуг</th>
      <th>Кол-во долгов</th>
      <th>Статус</th>
    </tr>
    <tr>
      <td class="content">${account.client.login}</td>
      <td class="content">${addonssize}</td>
      <td class="content">${debt}</td>
      <c:choose>
        <c:when test="${account.status==true}">
          <td class="content">Активен</td>
        </c:when>
        <c:otherwise>
          <td class="content">Заблокирован</td>
        </c:otherwise>
      </c:choose>
    </tr>
  </table><br>
  <table>
    <th>
      <form action="blockaccount.html" method="post">
        <c:choose>
        <c:when test="${account.status==true}">
          <button class="block">Заблокировать</button>
        </c:when>
        <c:otherwise>
          <button class="block" disabled>Заблокировать</button>
         </c:otherwise>
        </c:choose>
      </form>
    </th>
    <th>
      <form action="unblockaccount.html" method="post">
        <c:choose>
          <c:when test="${account.status==true}">
            <button class="unblock" disabled>Разблокировать</button>
          </c:when>
          <c:otherwise>
            <button class="unblock">Разблокировать</button>
          </c:otherwise>
        </c:choose>
      </form>
    </th>
    <th>
      <form action="resetpass.html" method="post">
        <button class="reset">Сбросить пароль</button>
      </form>
    </th>
    <th>
      <form action="delaccount.html" method="post">
        <button class="delete">Удалить</button>
      </form>
    </th>
  </table>
  <form action="accounts.html" method="get">
    <button class="cancel">Обратно</button>
  </form>

</u:page>