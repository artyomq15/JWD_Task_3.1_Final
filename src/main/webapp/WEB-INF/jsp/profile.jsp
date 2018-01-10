<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>

<fmt:setBundle basename="localization.main" var="main"/>
<fmt:setBundle basename="localization.profile" var="profile"/>
<fmt:setBundle basename="localization.auth" var="auth"/>


<fmt:message key="label.name" bundle="${main}" var="nameSite"/>

<fmt:message key="label.name" bundle="${profile}" var="name"/>
<fmt:message key="label.rating" bundle="${profile}" var="rating"/>
<fmt:message key="label.email" bundle="${profile}" var="email"/>
<fmt:message key="label.about" bundle="${profile}" var="about"/>
<fmt:message key="label.topic" bundle="${profile}" var="topic"/>
<fmt:message key="label.message" bundle="${profile}" var="message"/>

<fmt:message key="label.exit" bundle="${auth}" var="exit"/>
<fmt:message key="label.password" bundle="${auth}" var="password"/>
<fmt:message key="label.newPassword" bundle="${auth}" var="newPassword"/>
<fmt:message key="label.confirm" bundle="${auth}" var="confirm"/>
<fmt:message key="label.errorChangePasswordMessage" bundle="${auth}" var="errMessage"/>
<fmt:message key="label.changePassword" bundle="${auth}" var="changePassword"/>

<fmt:message key="button.signIn" bundle="${auth}" var="signIn"/>
<fmt:message key="button.signUp" bundle="${auth}" var="signUp"/>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/index.css"/>
    <link rel="stylesheet" href="../../css/profile.css"/>
    <title>Title</title>
</head>
<body>
<header class="header_container">
    <div class="header_background"></div>
    <div class="header_menu">
        <a href="/NetworkController?command=go_to_main_page">
            <div class="header_menu-item header_menu-name">
                ${nameSite}
            </div>
        </a>
        <c:if test="${sessionScope.role == null || sessionScope.role == 1}">
            <a href="/NetworkController?command=go_to_sign_up">
                <div class="header_menu-item">
                        ${signUp}
                </div>
            </a>
            <a href="/NetworkController?command=go_to_sign_in">
                <div class="header_menu-item">
                        ${signIn}
                </div>
            </a>
        </c:if>
        <c:if test="${sessionScope.role == 2}">
            <a href="/NetworkController?command=go_to_profile&profile_user_id=${requestScope.user.id}">
                <div class="header_menu-item">
                        ${requestScope.user.name}
                </div>
            </a>
        </c:if>
    </div>
</header>


<main>

    <div class="card profile_info_block">
        <div class="profile_info-name">
            ${requestScope.profile_user.name}
        </div>
        <c:if test="${requestScope.user != null && requestScope.user.id==requestScope.profile_user.id}">
            <div class="profile_info-settings">
                <a href="#">
                    <img src="../../img/settings.png">
                </a>
            </div>
        </c:if>
        <div class="profile_info-email">
            ${email}:<c:out value="${requestScope.profile_user.email}"/>
        </div>
        <div class="profile_info-about">
            ${about}:<c:out value="${requestScope.profile_user.about}"/>
        </div>

    </div>


    <div class="card profile_info-rating">
        <div class="profile-digit">
            <c:out value="${requestScope.profile_user.rating}"/>
        </div>
        <div class="profile-text">
            ${rating}
        </div>
    </div>

    <div class="card profile_post_info">
        <div class="profile_post_info-topics">
            <a href="#">
                <div class="profile-digit">
                    <c:out value="${requestScope.profile_user.rating}"/>
                </div>
                <div class="profile-text">

                    ${topic}

                </div>
            </a>
        </div>
        <div class="profile_post_info-messages">
            <a href="#">
                <div class="profile-digit">
                    <c:out value="${requestScope.profile_user.rating}"/>
                </div>
                <div class="profile-text">

                    ${message}

                </div>
            </a>
        </div>
    </div>
</main>

<%--
<br>
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

--%>


<c:import url="footer.jsp"/>

<script type="text/javascript" src="../../js/index.js"></script>
</body>
</html>
