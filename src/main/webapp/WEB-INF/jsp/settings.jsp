<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>

<fmt:setBundle basename="localization.auth" var="auth"/>
<fmt:setBundle basename="localization.main" var="main"/>
<fmt:setBundle basename="localization.profile" var="profile"/>

<fmt:message key="name" bundle="${main}" var="nameSite"/>

<fmt:message key="name" bundle="${profile}" var="name"/>
<fmt:message key="rating" bundle="${profile}" var="rating"/>
<fmt:message key="email" bundle="${profile}" var="email"/>
<fmt:message key="about" bundle="${profile}" var="about"/>

<fmt:message key="passwordChanged" bundle="${profile}" var="passwordChanged"/>
<fmt:message key="passwordNotChanged" bundle="${profile}" var="passwordNotChanged"/>
<fmt:message key="profileInfoChanged" bundle="${profile}" var="profileInfoChanged"/>
<fmt:message key="profileInfoNotChanged" bundle="${profile}" var="profileInfoNotChanged"/>
<fmt:message key="settings" bundle="${profile}" var="settings"/>

<fmt:message key="exit" bundle="${auth}" var="exit"/>
<fmt:message key="password" bundle="${auth}" var="password"/>
<fmt:message key="newPassword" bundle="${auth}" var="newPassword"/>
<fmt:message key="confirm" bundle="${auth}" var="confirm"/>
<fmt:message key="errorChangePasswordMessage" bundle="${auth}" var="errMessage"/>
<fmt:message key="changePassword" bundle="${auth}" var="changePassword"/>
<fmt:message key="changeProfileInfo" bundle="${auth}" var="changeProfileInfo"/>
<fmt:message key="signIn" bundle="${auth}" var="signIn"/>
<fmt:message key="signUp" bundle="${auth}" var="signUp"/>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/index.css"/>
    <link rel="stylesheet" href="../../css/profile.css"/>
    <title>Like It | ${settings}</title>
</head>
<body>
<header class="header_container">
    <div class="header_background"></div>
    <div class="header_menu">
        <a href="/NetworkController?command=go_to_main_page&page_number=1&count=10">
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

    <div class="sign_up_container img_change">
        <div class="sign_up_header">
            ${changeProfileInfo}
        </div>
        <img src="/images/?file=${requestScope.user.img}">

        <form action="/images/" method="post" enctype="multipart/form-data">
            <input type="file" name="file">
            <input type="submit" value="submit">
        </form>
    </div>
    <div class="sign_up_container info_change">
        <div class="sign_up_header">
            ${changeProfileInfo}
        </div>
        <form action="/NetworkController" method="post">
            <input type="hidden" name="command" value="change_profile_info"/>

            <input id="name" type="text" name="name" value="${requestScope.user.name}" placeholder="${name}" pattern="^[A-Za-zА-Яа-яІіўЁё'-]{2,50}$"
                   required>
            <label class="hidden error_validation error_validation-name">Name must contain only letters</label>


            <input id="email" type="text" name="email" placeholder="${email}" value="${requestScope.user.email}"
                   pattern="^[A-Za-z0-9_-]+(\.[A-Za-z0-9_-]+)?@[A-Za-z0-9]+\.[A-Za-z]{2,4}$" required>

            <label class="hidden error_validation error_validation-email">Wrong e-mail</label>

            <textarea name="about" placeholder="${about}" rows="5">${requestScope.user.about}</textarea>

            <c:if test="${requestScope.message != null}">
                <br/>
                <label class="">${errMessage}</label>
            </c:if>
            <input type="submit" value="OK">
        </form>
    </div>

    <div class="sign_up_container password_change">
        <div class="sign_up_header">
            ${changePassword}
        </div>
        <form action="/NetworkController" method="post">
            <input type="hidden" name="command" value="change_password"/>

            <input id="old_password" type="password" name="password" value="" placeholder="${password}"
                   pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,}$" required>


            <input id="password" type="password" name="new_password" value="" placeholder="${newPassword}"
                   pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,}$" required>
            <label class="hidden error_validation error_validation-password">Password must contain at least one lowcase
                letter, upcase letter, digit. More than 5.</label>

            <input id="confirmation" type="password" name="confirmation" value="" oninput="validatePassword()"
                   placeholder="${confirm}"
                   pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,}$" required>
            <c:if test="${requestScope.message != null}">
                <label class="">${errMessage}</label>
            </c:if>
            <br>
            <input type="submit" value="OK">

        </form>
    </div>

    <div class="sign_up_container">
        <div class="sign_up_header">
            <a href="/NetworkController?command=exit">${exit}</a>
        </div>
    </div>

    <c:if test="${requestScope.password_changed != null}">
        <div id="popup_message" class="card added">
                ${passwordChanged}
        </div>
    </c:if>
    <c:if test="${requestScope.password_not_changed != null}">
        <div id="popup_message" class="card not_added">
                ${passwordNotChanged}
        </div>
    </c:if>
    <c:if test="${requestScope.profile_info_changed != null}">
        <div id="popup_message" class="card added">
                ${profileInfoChanged}
        </div>
    </c:if>
    <c:if test="${requestScope.profile_info_not_changed != null}">
        <div id="popup_message" class="card not_added">
                ${profileInfoNotChanged}
        </div>
    </c:if>

</main>




<c:import url="footer.jsp"/>

<script type="text/javascript" src="../../js/index.js"></script>
<script type="text/javascript" src="../../js/popup.js"></script>
</body>
</html>
