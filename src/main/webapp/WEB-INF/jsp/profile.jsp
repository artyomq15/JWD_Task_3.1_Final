<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>

<fmt:setBundle basename="localization.mainpage" var="main"/>
<fmt:setBundle basename="localization.profile" var="profile"/>
<fmt:setBundle basename="localization.auth" var="auth"/>


<fmt:message key="label.name" bundle="${main}" var="nameSite"/>

<fmt:message key="label.name" bundle="${profile}" var="name"/>
<fmt:message key="label.rating" bundle="${profile}" var="rating"/>
<fmt:message key="label.email" bundle="${profile}" var="email"/>
<fmt:message key="label.about" bundle="${profile}" var="about"/>

<fmt:message key="label.exit" bundle="${auth}" var="exit"/>
<fmt:message key="label.password" bundle="${auth}" var="password"/>
<fmt:message key="label.newPassword" bundle="${auth}" var="newPassword"/>
<fmt:message key="label.confirm" bundle="${auth}" var="confirm"/>
<fmt:message key="label.errorChangePasswordMessage" bundle="${auth}" var="errMessage"/>
<fmt:message key="label.changePassword" bundle="${auth}" var="changePassword"/>
<html>
<head>
    <link rel="stylesheet" href="../../css/main.css"/>
    <title>Title</title>
</head>
<body>
<a href="/NetworkController?command=go_to_main_page">${nameSite}</a>

<c:if test="${requestScope.user != null}">
    <p>${name}:<c:out value="${requestScope.user.name}"/></p>
    <p>${rating}:<c:out value="${requestScope.user.rating}"/></p>
    <p>${email}:<c:out value="${requestScope.user.email}"/></p>
    <p>${about}:<c:out value="${requestScope.user.about}"/></p>
</c:if>


<button>${changePassword}</button>
<form action="/NetworkController" method="post">
    <input type="hidden" name="command" value="change_password"/>
    <input type="password" name="password" value="" placeholder="${password}">
    <br>
    <input type="password" name="new_password" value="" placeholder="${newPassword}">
    <br>
    <input type="password" name="confirmation" value="" placeholder="${confirm}">
    <br>
    <c:if test="${requestScope.message != null}">
        ${errMessage}
    </c:if>
    <br>
    <input type="submit" value="OK">
</form>
<a href="/NetworkController?command=exit">${exit}</a>
<hr/>
<c:import url="footer.jsp"/>
</body>
</html>
