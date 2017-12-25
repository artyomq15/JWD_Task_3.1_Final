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
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/main.css"/>
    <title>Main</title>
</head>
<body>
<header>
    <div class="network_name"><a href="/NetworkController?command=go_to_main_page"> ${name}</a></div>
    <div class="header_item">
        <c:if test="${sessionScope.role == null || sessionScope.role == 1}">

            <a href="/NetworkController?command=go_to_sign_in">${signIn}</a>
            <a href="/NetworkController?command=go_to_sign_up">${signUp}</a>

        </c:if>
        <c:if test="${sessionScope.role == 2}">
            <a href="/NetworkController?command=go_to_profile">${requestScope.user.name}</a>
        </c:if>
    </div>
</header>
<section class="content">
    <nav class="card themes">
        <div class="themes_opening_line" onclick="toggleThemes('themes_block')">
            <b>${themes}</b>
        </div>
        <div id="themes_block">
            <c:forEach var="theme" items="${requestScope.theme_list}">
                <a href="#">
                    <div class="theme_item">${theme.name}</div>
                </a>
            </c:forEach>
        </div>
    </nav>


    <nav class="topics">

        <c:if test="${sessionScope.role >= 2}">
            <nav class="card add_topic">
                <div class="add_topic_opening_line" onclick="toggleThemes('add_topic_block')">
                    ADD TOPIC
                </div>

                <div class="add_topic_content" id="add_topic_block">
                    <form action="/NetworkController" method="post">
                        <input type="hidden" name="command" value="add_topic"/>
                        <input type="text" name="topic_header" value="" placeholder="HEADER"/>
                        <br>
                        <textarea name="topic_context" placeholder="CONTEXT"></textarea>
                        <br>
                        <label>
                            <select name="topic_theme_id">
                                <option disabled selected>Выберите THEME</option>
                                <c:forEach var="theme" items="${requestScope.theme_list}">
                                    <option value="${theme.id}">
                                            ${theme.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </label>
                        <br>
                        <input type="submit" value="OK"/>
                    </form>
                </div>
            </nav>
        </c:if>
        <c:forEach var="topic" items="${requestScope.topic_list}">
            <div class="card topic_item">
                <h2>${topic.header}</h2>
                <a href="#">${topic.theme.name}</a>
                <p>${topic.creatingDate} by <a href="#">${topic.user.name}</a></p>
                <p>${topic.context}</p>
                <a href="#">перейти</a>
            </div>
        </c:forEach>
    </nav>
</section>

<hr/>
<c:import url="footer.jsp"/>

<script type="text/javascript" src="../../js/main.js"></script>
</body>
</html>
