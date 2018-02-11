<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="lin" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.auth" var="auth"/>
<fmt:setBundle basename="localization.main" var="main"/>

<fmt:message key="name" bundle="${main}" var="name"/>
<fmt:message key="themes" bundle="${main}" var="themes"/>
<fmt:message key="signIn" bundle="${auth}" var="signIn"/>
<fmt:message key="signUp" bundle="${auth}" var="signUp"/>
<fmt:message key="search" bundle="${main}" var="search"/>
<fmt:message key="addTopic" bundle="${main}" var="addTopic"/>
<fmt:message key="header" bundle="${main}" var="addTopicHeader"/>
<fmt:message key="context" bundle="${main}" var="addTopicContext"/>
<fmt:message key="create" bundle="${main}" var="addTopicButton"/>
<fmt:message key="openTopic" bundle="${main}" var="openTopic"/>
<fmt:message key="topicAdded" bundle="${main}" var="topicAdded"/>
<fmt:message key="topicNotAdded" bundle="${main}" var="topicNotAdded"/>
<fmt:message key="topicDeleted" bundle="${main}" var="topicDeleted"/>
<fmt:message key="topicNotDeleted" bundle="${main}" var="topicNotDeleted"/>


<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/index.css"/>
    <link rel="stylesheet" href="../../css/main.css"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <link rel="icon" href="../../img/logo.png">
    <title>Like It</title>
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
        <article>
            <div class="search_topic_block">
                <div class="search_topic_block-content">
                    <form action="/NetworkController" method="get">
                        <input type="hidden" name="command" value="go_to_main_page"/>
                        <input type="hidden" name="page_number" value="1"/>
                        <input type="hidden" name="count" value="10"/>
                        <input class="search_topic_block-field" type="search" name="expression" value=""
                               placeholder="${search}" required/>
                        <input class="search_topic_content-item-button" type="submit" value="OK"/>
                    </form>
                </div>
            </div>


            <c:if test="${sessionScope.role >= 2 && !requestScope.user.banned}">
                <div class="add_topic_block">
                    <div class="add_topic_opening_line" id="add_topic">
                        + ${addTopic}
                    </div>

                    <div class="add_topic_content hidden" id="add_topic_block_content">
                        <form action="/NetworkController" method="post">
                            <input type="hidden" name="command" value="add_topic"/>
                            <input type="text" name="topic_header" value="" placeholder="${addTopicHeader}"
                                   pattern="^.{1,250}$" required/>
                            <textarea name="topic_context" rows="5" placeholder="${addTopicContext}"
                                      required></textarea>
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


        </article>

        <div class="topics_block">
            <c:if test="${requestScope.theme != null}">
                <div class="topics-theme">
                        ${requestScope.theme.name}
                    <a href="/NetworkController?command=go_to_main_page&page_number=1&count=10">
                        <img src="img/delete.png">
                    </a>
                </div>
            </c:if>
            <c:if test="${requestScope.expression != null}">
                <div class="topics-theme">
                        ${requestScope.expression}
                    <a href="/NetworkController?command=go_to_main_page&page_number=1&count=10">
                        <img src="img/delete.png">
                    </a>
                </div>
            </c:if>


            <c:forEach var="topic" items="${requestScope.topic_list}">
                <div class="topic_container">
                    <hr>
                    <div class="topics_block-item">
                        <div class="topics_block-item-header">
                            <a href="/NetworkController?command=go_to_topic_page&topic_id=${topic.id}">
                                    ${topic.header}
                            </a>
                        </div>

                        <div class="topics_block-item-theme">
                            <a href="/NetworkController?command=go_to_main_page&theme_id=${topic.theme.id}&page_number=1&count=10">
                                    ${topic.theme.name}
                            </a>
                        </div>
                        <div class="topics_block-item-context">
                                ${topic.context}
                        </div>
                    </div>

                    <div class="topics_block-item-date-author">
                        <div class="user_img">
                            <img class="centred" src="/images/?file=${topic.user.img}">
                        </div>
                        <a href="/NetworkController?command=go_to_profile&profile_user_id=${topic.user.id}">${topic.user.name}</a>
                        <br>
                            ${topic.creatingDate}
                    </div>

                    <div class="topics_block-item-open">
                        <a href="/NetworkController?command=go_to_topic_page&topic_id=${topic.id}">
                            <img src="../../img/open.png">
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="pagination">
            <c:if test="${requestScope.theme == null && requestScope.expression == null}">

                <lin:pagination command="go_to_main_page" count="10" pageNumber="${requestScope.page_number}"
                                listSize="${requestScope.topic_list.size()}"/>

            </c:if>

            <c:if test="${requestScope.theme != null}">

                <lin:pagination command="go_to_main_page" themeId="${requestScope.theme.id}" count="10"
                                pageNumber="${requestScope.page_number}"
                                listSize="${requestScope.topic_list.size()}"/>

            </c:if>

            <c:if test="${requestScope.expression != null}">

                <lin:pagination command="go_to_main_page" expression="${requestScope.expression}" count="10"
                                pageNumber="${requestScope.page_number}"
                                listSize="${requestScope.topic_list.size()}"/>

            </c:if>
        </div>

    </section>

    <c:if test="${requestScope.topic_added != null}">
        <div id="popup_message" class="card added">
                ${topicAdded}
        </div>
    </c:if>
    <c:if test="${requestScope.topic_not_added != null}">
        <div id="popup_message" class="card not_added">
                ${topicNotAdded}
        </div>
    </c:if>
    <c:if test="${requestScope.topic_deleted != null}">
        <div id="popup_message" class="card added">
                ${topicDeleted}
        </div>
    </c:if>
    <c:if test="${requestScope.topic_not_deleted != null}">
        <div id="popup_message" class="card not_added">
                ${topicNotDeleted}
        </div>
    </c:if>


</main>

<c:import url="footer.jsp"/>

<script type="text/javascript" src="../../js/index.js"></script>
<script type="text/javascript" src="../../js/popup.js"></script>
</body>
</html>
