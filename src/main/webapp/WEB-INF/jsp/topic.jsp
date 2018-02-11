<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.auth" var="auth"/>
<fmt:setBundle basename="localization.main" var="main"/>

<fmt:message key="name" bundle="${main}" var="name"/>
<fmt:message key="messageAdded" bundle="${main}" var="messageAdded"/>
<fmt:message key="messageNotAdded" bundle="${main}" var="messageNotAdded"/>

<fmt:message key="themes" bundle="${main}" var="themes"/>
<fmt:message key="signIn" bundle="${auth}" var="signIn"/>
<fmt:message key="signUp" bundle="${auth}" var="signUp"/>
<fmt:message key="addCommentHeader" bundle="${main}" var="addCommentHeader"/>
<fmt:message key="addCommentPlaceholder" bundle="${main}" var="addCommentPlaceholder"/>
<fmt:message key="likes" bundle="${main}" var="likes"/>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <link rel="icon" href="../../img/logo.png">
    <link rel="stylesheet" href="../../css/index.css"/>
    <title>Like It | ${requestScope.topic.header}</title>
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
                       <img src="../../img/user.png"> ${requestScope.user.name}
                </div>
            </a>
        </c:if>
    </div>
</header>

<main>
    <aside>
        <nav class="themes_block">
            <div class="themes_block-name" id="themes">
                ${themes}<img id="down_img" src="img/down.png">
                <img id="up_img" class="hidden" src="img/up.png">
            </div>
            <div class="themes_block_items hidden" id="themes_toggle_element">
                <c:forEach var="theme" items="${requestScope.theme_list}">
                    <a href="/NetworkController?command=go_to_main_page&theme_id=${theme.id}&page_number=1&count=10">
                        <div class="themes_block-item">${theme.name}</div>
                    </a>
                </c:forEach>
            </div>
        </nav>
    </aside>

    <section>
            <div class="topics_block">
                <div class="topic_container">
                    <hr>
                    <div class="topics_block-item">

                        <div class="topics_block-item-header">
                            ${requestScope.topic.header}
                        </div>
                        <c:if test="${requestScope.topic.user.id == requestScope.user.id}">
                            <div class="topics_block-item-delete">

                                <a href="/NetworkController?command=delete_topic&topic_id=${requestScope.topic.id}"><img
                                        src="img/delete.png"></a>

                            </div>
                        </c:if>
                        <div class="topics_block-item-theme">
                            <a href="/NetworkController?command=go_to_main_page&theme_id=${requestScope.topic.theme.id}&page_number=1&count=10">${requestScope.topic.theme.name}</a>
                        </div>
                        <div class="topics_block-item-context">
                            ${requestScope.topic.context}
                        </div>
                    </div>

                    <div class="topics_block-item-date-author">
                        <div class="user_img">
                            <img class="centred" src="/images/?file=${requestScope.topic.user.img}">
                        </div>
                        <a href="/NetworkController?command=go_to_profile&profile_user_id=${requestScope.topic.user.id}">
                            ${requestScope.topic.user.name}
                        </a>
                        <br>
                        ${requestScope.topic.creatingDate}
                    </div>


                    <div class="messages_block">

                        <c:forEach var="message" items="${requestScope.message_list}">
                            <div class="messages_block-item">
                                <div class="message_block-item-name">
                                    <a href="/NetworkController?command=go_to_profile&profile_user_id=${message.user.id}">${message.user.name}</a>
                                </div>
                                <c:if test="${message.user.id == requestScope.user.id}">
                                    <div class="message_block-item-delete">

                                        <a href="/NetworkController?command=delete_message&message_id=${message.id}"><img
                                                src="img/delete.png"></a>

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

            <div class="add_message_block">
                <div class="add_message_content">
                    <div class="add_message-header">
                        ${addCommentHeader}
                    </div>
                    <form action="/NetworkController" method="post">
                        <input type="hidden" name="command" value="add_message"/>
                        <input type="hidden" name="topic_id" value="${requestScope.topic.id}"/>
                        <textarea name="message_context" rows="5" placeholder="${addCommentPlaceholder}"
                                  required></textarea>
                        <c:if test="${sessionScope.role < 2 || requestScope.user.banned}">
                            <input class="add_message_content-button" type="button" disabled value="OK"/>
                        </c:if>
                        <c:if test="${sessionScope.role >= 2 && !requestScope.user.banned}">
                            <input class="add_message_content-button" type="submit" value="OK"/>
                        </c:if>
                    </form>
                </div>
            </div>

    </section>

    <c:if test="${requestScope.message_added != null}">
        <div id="popup_message" class="card added">
                ${messageAdded}
        </div>
    </c:if>
    <c:if test="${requestScope.message_not_added != null}">
        <div id="popup_message" class="card not_added">
                ${messageNotAdded}
        </div>
    </c:if>

</main>


<c:import url="footer.jsp"/>

<script type="text/javascript" src="../../js/index.js"></script>
<script type="text/javascript" src="../../js/popup.js"></script>
<script type="text/javascript" src="../../js/scroll.js"></script>

</body>
</html>
