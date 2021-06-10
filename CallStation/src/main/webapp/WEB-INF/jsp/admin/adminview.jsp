<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<u:page title="Главная страница">
  <h1>Телефонная станция</h1>
  <h4>Здраствуйте "${user.login}"</h4>
  <ul>
    <li><a href="account/accounts.html">Список абонентов</a></li>
    <li><a href="addon/addons.html">Список услуг</a></li>
    <li><a href="../user/changepass.html">Изменить пароль</a></li>
  </ul>
  <form action="../logout.html" method="get">
    <button class="cancel">Выйти</button>
  </form>
</u:page>