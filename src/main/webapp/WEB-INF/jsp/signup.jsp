<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

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
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/index.css"/>
    <title>Like It | ${signUp}</title>
</head>
<body>
<header class="header_container">
    <div class="header_background"></div>
    <div class="header_menu">
        <a href="/NetworkController?command=go_to_main_page">
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
        <c:if test="${sessionScope.role == 2}">
            <a href="/NetworkController?command=go_to_profile">
                <div class="header_menu-item">
                        ${requestScope.user.name}
                </div>
            </a>
        </c:if>
    </div>
</header>

<main>
    <div class="card sign_up_container">
        <div class="sign_up_header">
            ${signUp}
        </div>
        <form action="/NetworkController" method="post">
            <input type="hidden" name="command" value="sign_up"/>

            <input type="text" id="login" name="login" value="" placeholder="${login}"
                   pattern="[a-zA-Z][A-Za-z_0-9]{4,}"
                   required>
            <label for="login" class="hidden error_validation error_validation-login">Login must contain only latin
                letters, digits and '_'. Starts with letter. More than 4.</label>

            <input type="password" id="password" name="password" value="" placeholder="${password}"
                   pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,}$" required>
            <label class="hidden error_validation error_validation-password">Password must contain at least one lowcase
                letter, upcase letter, digit. More than 5.</label>

            <input type="password" id="confirmation" name="confirmation" value="" oninput="validatePassword()"
                   placeholder="${confirm}" required>
            <label class="hidden error_validation error_validation-confirmation">Passwords must match</label>

            <input id="name" type="text" name="name" value="" placeholder="${nameUser}" pattern="[А-Яа-яA-Za-z]+"
                   required>
            <label class="hidden error_validation error_validation-name">Name must contain only letters</label>


            <input id="email" type="email" name="email" value="" placeholder="${email}" required>
            <label class="hidden error_validation error_validation-email">Wrong e-mail</label>


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
