<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.mainpage" var="main"/>
<fmt:setBundle basename="localization.auth" var="auth"/>



<fmt:message key="label.name" bundle="${main}" var="name"/>
<fmt:message key="label.text" bundle="${main}" var="text"/>


<fmt:message key="label.login" bundle="${auth}" var="login"/>
<fmt:message key="label.password" bundle="${auth}" var="password"/>
<fmt:message key="button.signIn" bundle="${auth}" var="signIn"/>
<fmt:message key="button.signUp" bundle="${auth}" var="signUp"/>
<fmt:message key="label.errorSignInMessage" bundle="${auth}" var="errMessage"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>${name}</title>
</head>
<body>
<a href="/NetworkController?command=go_to_main_page">${name}</a>
<h2>${signIn}</h2>

<form action="/NetworkController" method="post">
    <input type="hidden" name="command" value="sign_in"/>
    <input type="text" name="login" value="" placeholder="${login}">
    <br>
    <input type="password" name="password" value="" placeholder="${password}">
    <br>
    <c:if test="${requestScope.message != null}">
        ${errMessage}
    </c:if>
    <br>
    <input type="submit" value="${signIn}">
</form>

<a href="/NetworkController?command=go_to_sign_up"><p>${signUp}</p></a>

<hr/>
<c:import url="footer.jsp"/>
</body>
</html>
