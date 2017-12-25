<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.auth" var="auth"/>
<fmt:setBundle basename="localization.profile" var="profile"/>
<fmt:setBundle basename="localization.mainpage" var="main"/>

<fmt:message key="label.login" bundle="${auth}" var="login"/>
<fmt:message key="label.password" bundle="${auth}" var="password"/>
<fmt:message key="label.confirm" bundle="${auth}" var="confirm"/>
<fmt:message key="button.signIn" bundle="${auth}" var="signIn"/>
<fmt:message key="button.signUp" bundle="${auth}" var="signUp"/>

<fmt:message key="label.errorSignUpMessage" bundle="${auth}" var="errMessage"/>

<fmt:message key="label.name" bundle="${profile}" var="nameUser"/>
<fmt:message key="label.email" bundle="${profile}" var="email"/>


<fmt:message key="label.name" bundle="${main}" var="name"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Like It | ${signUp}</title>
</head>
<body>
<a href="/NetworkController?command=go_to_main_page">${name}</a>
<h1>${signUp}</h1>

<form action="/NetworkController" method="post">
    <input type="hidden" name="command" value="sign_up"/>
    <input type="text" name="login" value="" placeholder="${login}">
    <br>
    <input type="password" name="password" value="" placeholder="${password}">
    <br>
    <input type="password" name="confirmation" value="" placeholder="${confirm}">
    <br>
    <input type="text" name="name" value="" placeholder="${nameUser}">
    <br>
    <input type="text" name="email" value="" placeholder="${email}">
    <br>
    <c:if test="${requestScope.message != null}">
        ${errMessage}
    </c:if>
    <br>
    <input type="submit" value="${signUp}">
</form>

<a href="/NetworkController?command=go_to_sign_in"><p>${signIn}</p></a>
<hr/>
<c:import url="footer.jsp"/>
</body>
</html>
