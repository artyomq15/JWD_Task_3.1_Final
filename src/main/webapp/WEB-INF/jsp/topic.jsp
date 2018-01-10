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
<fmt:message key="label.addCommentHeader" bundle="${main}" var="addCommentHeader"/>
<fmt:message key="label.addCommentPlaceholder" bundle="${main}" var="addCommentPlaceholder"/>
<fmt:message key="label.likes" bundle="${main}" var="likes"/>
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
        <div class="topics_block">

            <div class="card topics_block-item">

                <div class="topics_block-item-header">
                    ${requestScope.topic.header}
                </div>

                <div class="topics_block-item-date-author">
                    <a href="#">${requestScope.topic.creatingDate}</a> by <a
                        href="/NetworkController?command=go_to_profile&profile_user_id=${requestScope.topic.user.id}">${requestScope.topic.user.name}</a>
                </div>
                <div class="topics_block-item-theme">
                    <a href="#">${requestScope.topic.theme.name}</a>
                </div>
                <div class="topics_block-item-context">
                    ${requestScope.topic.context}
                </div>


                <div class="messages_block">

                    <c:forEach var="message" items="${requestScope.message_list}">
                        <div class="messages_block-item">
                            <div class="message_block-item-name">
                                <a href="/NetworkController?command=go_to_profile&profile_user_id=${message.user.id}">${message.user.name}</a>
                            </div>
                            <c:if test="${message.user.id == requestScope.user.id}">
                                <div class="message_block-item-delete">

                                    <a href="#"><img src="img/delete.png"></a>

                                </div>
                            </c:if>
                            <div class="message_block-item-context">${message.context}</div>
                            <div class="message_block-item-date">${message.creatingDate}</div>
                            <div class="message_block-item-like">
                                <c:if test="${requestScope.user!=null}">
                                    <c:if test="${!message.likedUserId.contains(requestScope.user.id)}">
                                        <a href="/NetworkController?command=rate_message&action=like&message_id=${message.id}">${message.likes}<img
                                                src="img/notlike.png"></a>
                                    </c:if>
                                    <c:if test="${message.likedUserId.contains(requestScope.user.id)}">
                                        <a href="/NetworkController?command=rate_message&action=unlike&message_id=${message.id}">${message.likes}<img
                                                src="img/like.png"></a>
                                    </c:if>
                                </c:if>
                                <c:if test="${requestScope.user==null}">
                                    ${message.likes} ${likes}
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>




            </div>


        </div>

        <div class="card add_message_block">
            <div class="add_message_content">
                <div class="add_message-header">
                    ${addCommentHeader}
                </div>
                <form action="/NetworkController" method="post">
                    <input type="hidden" name="command" value="add_message"/>
                    <input type="hidden" name="topic_id" value="${requestScope.topic.id}"/>
                    <textarea name="message_context" rows="5" placeholder="${addCommentPlaceholder}" required></textarea>
                    <c:if test="${sessionScope.role < 2}">
                        <input class="add_message_content-button" type="button" disabled value="OK"/>
                    </c:if>
                    <c:if test="${sessionScope.role >= 2}">
                        <input class="add_message_content-button" type="submit" value="OK"/>
                    </c:if>
                </form>
            </div>
        </div>
    </article>
</main>


<c:import url="footer.jsp"/>

<script type="text/javascript" src="../../js/index.js"></script>

</body>
</html>
