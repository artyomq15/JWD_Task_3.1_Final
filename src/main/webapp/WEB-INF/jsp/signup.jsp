<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.auth" var="auth"/>
<fmt:setBundle basename="localization.profile" var="profile"/>
<fmt:setBundle basename="localization.main" var="main"/>

<fmt:message key="login" bundle="${auth}" var="login"/>
<fmt:message key="password" bundle="${auth}" var="password"/>
<fmt:message key="confirm" bundle="${auth}" var="confirm"/>
<fmt:message key="signIn" bundle="${auth}" var="signIn"/>
<fmt:message key="signUp" bundle="${auth}" var="signUp"/>
<fmt:message key="label.validation.login" bundle="${auth}" var="loginValidation"/>
<fmt:message key="label.validation.password" bundle="${auth}" var="passwordValidation"/>
<fmt:message key="label.validation.confirmation" bundle="${auth}" var="confirmationValidation"/>
<fmt:message key="label.validation.name" bundle="${auth}" var="nameValidation"/>
<fmt:message key="label.validation.email" bundle="${auth}" var="emailValidation"/>

<fmt:message key="errorSignUpMessage" bundle="${auth}" var="errMessage"/>

<fmt:message key="name" bundle="${profile}" var="nameUser"/>
<fmt:message key="email" bundle="${profile}" var="email"/>


<fmt:message key="name" bundle="${main}" var="name"/>

<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/index.css"/>
    <link rel="icon" href="../../img/logo.png">
    <title>Like It | ${signUp}</title>
</head>
<body>
<header class="header_container">
    <div class="header_background"></div>
    <div class="header_menu">
        <a href="/NetworkController?command=go_to_main_page&page_number=1&count=10">
            <div class="header_menu-item header_menu-name">
                ${name}
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
        <c:if test="${sessionScope.role >= 2}">
            <a href="/NetworkController?command=go_to_profile&profile_user_id=${requestScope.user.id}">
                <div class="header_menu-item">
                    <img src="../../img/user.png">${requestScope.user.name}
                </div>
            </a>
        </c:if>
    </div>
</header>

<main>
    <div class="sign_up_container">
        <div class="sign_up_header">
            ${signUp}
        </div>
        <form action="/NetworkController" method="post">
            <input type="hidden" name="command" value="sign_up"/>

            <input type="text" id="login" name="login" value="" placeholder="${login}"
                   pattern="^[A-Za-z][A-Za-z0-9_]{4,49}$"
                   required>
            <label for="login" class="hidden error_validation error_validation-login">${loginValidation}</label>

            <input type="password" id="password" name="password" value="" placeholder="${password}"
                   pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,}$" required>
            <label class="hidden error_validation error_validation-password">${passwordValidation}</label>

            <input type="password" id="confirmation" name="confirmation" value="" oninput="validatePassword()"
                   placeholder="${confirm}" pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,}$" required>
            <label class="hidden error_validation error_validation-confirmation">${confirmationValidation}</label>

            <input id="name" type="text" name="name" value="" placeholder="${nameUser}" pattern="^[A-Za-zА-Яа-яІіўЁё'-]{2,50}$"
                   required>
            <label class="hidden error_validation error_validation-name">${nameValidation}</label>


            <input id="email" type="text" name="email" value="" placeholder="${email}"
                   pattern="^[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)?@[A-Za-z0-9]+\.[A-Za-z]{2,4}$" required>
            <label class="hidden error_validation error_validation-email">${emailValidation}</label>


            <input type="submit" value="${signUp}">

            <c:if test="${requestScope.message != null}">
                <br/>
                <label class="error_validation">${errMessage}</label>
            </c:if>
        </form>
    </div>
</main>

<c:import url="footer.jsp"/>

<script type="text/javascript" src="../../js/index.js"></script>
</body>
</html>
