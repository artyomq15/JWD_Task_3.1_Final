<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.auth" var="auth"/>
<fmt:setBundle basename="localization.mainpage" var="main"/>

<fmt:message key="label.name" bundle="${main}" var="name"/>

<fmt:message key="label.themes" bundle="${main}" var="themes"/>
<fmt:message key="button.signIn" bundle="${auth}" var="signIn"/>
<fmt:message key="button.signUp" bundle="${auth}" var="signUp"/>
<html>
<head>
    <title></title>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/index.css"/>
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
    <nav class="card themes_block">
        <div class="themes_block-name" onclick="toggle('themes_toggle_element')">${themes}</div>
        <div class="themes_block_items hidden" id="themes_toggle_element">
            <c:forEach var="theme" items="${requestScope.theme_list}">
                <a href="#">
                    <div class="themes_block-item">${theme.name}</div>
                </a>
            </c:forEach>
        </div>
    </nav>

    <article>
        <div class="topics_block">

            <div class="card topics_block-item">

                <div class="topics_block-item-header">
                    ${requestScope.topic.header}
                </div>

                <div class="topics_block-item-date-author">
                    <a href="#">${requestScope.topic.creatingDate}</a> by <a
                        href="#">${requestScope.topic.user.name}</a>
                </div>
                <div class="topics_block-item-theme">
                    <a href="#">${requestScope.topic.theme.name}</a>
                </div>
                <div class="topics_block-item-context">
                    ${requestScope.topic.context}
                </div>
                <div class="topics_block-item-rating">
                    44 <a href="#"><img src="img/like.png"></a>
                </div>
            </div>
        </div>
    </article>
</main>




<c:import url="footer.jsp"/>

<script type="text/javascript" src="../../js/index.js"></script>

</body>
</html>
