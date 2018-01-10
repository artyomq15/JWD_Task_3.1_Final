<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.auth" var="auth"/>
<fmt:setBundle basename="localization.main" var="main"/>

<fmt:message key="label.name" bundle="${main}" var="name"/>
<fmt:message key="label.themes" bundle="${main}" var="themes"/>
<fmt:message key="button.signIn" bundle="${auth}" var="signIn"/>
<fmt:message key="button.signUp" bundle="${auth}" var="signUp"/>
<fmt:message key="label.search" bundle="${main}" var="search"/>
<fmt:message key="label.addTopic" bundle="${main}" var="addTopic"/>
<fmt:message key="label.header" bundle="${main}" var="addTopicHeader"/>
<fmt:message key="label.context" bundle="${main}" var="addTopicContext"/>
<fmt:message key="button.create" bundle="${main}" var="addTopicButton"/>
<fmt:message key="label.openTopic" bundle="${main}" var="openTopic"/>


<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/index.css"/>
    <title>Like It</title>
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
            <a href="/NetworkController?command=go_to_profile&profile_user_id=${requestScope.user.id}">
                <div class="header_menu-item">
                        ${requestScope.user.name}
                </div>
            </a>
        </c:if>
    </div>
</header>


<main>
    <nav class="card themes_block">
        <div class="themes_block-name" onclick="toggle('themes_toggle_element')">
        ${themes}<img id="down_img" src="img/down.png">
            <img id="up_img" class="hidden" src="img/up.png">
        </div>
        <div class="themes_block_items hidden" id="themes_toggle_element">
            <c:forEach var="theme" items="${requestScope.theme_list}">
                <a href="#">
                    <div class="themes_block-item">${theme.name}</div>
                </a>
            </c:forEach>
        </div>
    </nav>


    <article>
        <div class="search_topic_block">
            <div class="search_topic_block-content">
                <form action="#" method="get">
                    <input type="hidden" name="command" value="search_topic"/>
                    <input class="search_topic_block-field" type="search" name="text" value="" placeholder="${search}"/>
                    <input class="search_topic_content-item-button" type="submit" value="OK"/>
                </form>
            </div>
        </div>


        <c:if test="${sessionScope.role >= 2}">
            <div class="card add_topic_block">
                <div class="add_topic_opening_line" onclick="toggle('add_topic_block_content')">
                    ${addTopic}
                </div>

                <div class="add_topic_content hidden" id="add_topic_block_content">
                    <form action="/NetworkController" method="post">
                        <input type="hidden" name="command" value="add_topic"/>
                        <input type="text" name="topic_header" value="" placeholder="${addTopicHeader}" required/>
                        <textarea name="topic_context" rows="5" placeholder="${addTopicContext}" required></textarea>
                        <label>
                            <select name="topic_theme_id" required>
                                <c:forEach var="theme" items="${requestScope.theme_list}">
                                    <option value="${theme.id}">
                                            ${theme.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </label>
                        <input class="add_topic_content-item-button" type="submit" value="${addTopicButton}"/>
                    </form>
                </div>
            </div>
        </c:if>


        <div class="topics_block">
            <c:forEach var="topic" items="${requestScope.topic_list}">
                <div class="card topics_block-item">
                    <a href="/NetworkController?command=go_to_topic_page&topic_id=${topic.id}">
                        <div class="topics_block-item-header">
                                ${topic.header}
                        </div>
                    </a>
                    <div class="topics_block-item-date-author">
                        <a href="#">${topic.creatingDate}</a> by <a href="/NetworkController?command=go_to_profile&profile_user_id=${topic.user.id}">${topic.user.name}</a>
                    </div>
                    <div class="topics_block-item-theme">
                        <a href="#">${topic.theme.name}</a>
                    </div>
                    <div class="topics_block-item-context">
                            ${topic.context}
                    </div>
                    <a href="/NetworkController?command=go_to_topic_page&topic_id=${topic.id}">
                        <div class="topics_block-item-open">
                            ${openTopic}
                        </div>
                    </a>
                </div>
            </c:forEach>
        </div>
    </article>


</main>

<c:import url="footer.jsp"/>

<script type="text/javascript" src="../../js/index.js"></script>
</body>
</html>
