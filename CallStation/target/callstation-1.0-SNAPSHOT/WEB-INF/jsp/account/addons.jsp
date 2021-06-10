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
  <h4>Ваш баланс равен: "${account.balance}"</h4>
  <table>
    <tr>
      <th>Название услуги</th>
      <th>Стоимость</th>
      <th>Оплата</th>
      <td>&nbsp;</td>
    </tr>
    <c:forEach var="service" items="${services}">
      <tr>
        <td class="content">${service.name}</td>
        <td class="content">${service.cost}</td>
        <c:choose>
          <c:when test="${service.payment==true}">
            <td class="content">Оплачено</td>
          </c:when>
          <c:otherwise>
            <td class="content">Не оплачено</td>
          </c:otherwise>
        </c:choose>
        <td class="empty"><a href="edit.html?id=${account.client.id}" class="edit"></a></td>
      </tr>
    </c:forEach>
  </table><br>
  <form action="addservice.html" method="post">
    <select size="1" name="addservice">
      <option disabled>Выберите услугу</option>
      <c:forEach var="service" items="${newservices}">
        <option value="${service}">${service}</option>
      </c:forEach>
    </select>
      <c:choose>
          <c:when test="${(account.status==true) and (not empty newservices)}">
              <button class="add">Добавить</button>
          </c:when>
          <c:otherwise>
              <button class="add" disabled>Добавить</button>
          </c:otherwise>
      </c:choose>
  </form>
 <form action="delservice.html" method="post">
    <select size="1" name="delservice">
    <option disabled>Выберите услугу</option>
    <c:forEach var="service" items="${services}">
      <option value="${service}">${service}</option>
    </c:forEach>
    </select>
     <c:choose>
         <c:when test="${not empty services}">
             <button class="delete">Удалить</button>
         </c:when>
         <c:otherwise>
             <button class="delete" disabled>Удалить</button>
         </c:otherwise>
     </c:choose>

  </form>
  <form action="payservice.html" method="post">
    <select size="1" name="payservice">
    <option disabled>Выберите услугу</option>
    <c:forEach var="service" items="${payservices}">
        <option value="${service}">${service}</option>
    </c:forEach>
    </select>
      <c:choose>
          <c:when test="${not empty payservices}">
              <button class="pay">Оплатить</button>
          </c:when>
          <c:otherwise>
              <button class="pay" disabled>Оплатить</button>
          </c:otherwise>
      </c:choose>
  </form>
  <form action="accountview.html" method="get">
    <button class="cancel">Обратно</button>
  </form>
</u:page>