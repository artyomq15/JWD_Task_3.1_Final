<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/taglib.tld" prefix="lin" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.auth" var="auth"/>
<fmt:setBundle basename="localization.main" var="main"/>
<fmt:setBundle basename="localization.admin" var="admin"/>

<fmt:message key="name" bundle="${main}" var="name"/>
<fmt:message key="search" bundle="${main}" var="search"/>

<fmt:message key="signIn" bundle="${auth}" var="signIn"/>
<fmt:message key="signUp" bundle="${auth}" var="signUp"/>

<fmt:message key="users" bundle="${admin}" var="users"/>
<fmt:message key="themes" bundle="${admin}" var="themes"/>



<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/index.css"/>
    <link rel="stylesheet" href="../../css/admin.css"/>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <link rel="icon" href="../../img/logo.png">
    <title>Like It | Admin</title>
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
            <div class="themes_block_items">
                <div class="themes_block-item">
                    <a href="/NetworkController?command=go_to_admin_page&action=not_banned&page_number=1&count=10">
                        ${users}
                    </a>
                </div>
                <div class="themes_block-item">
                    <a href="/NetworkController?command=go_to_admin_page&action=themes_lists">
                        ${themes}
                    </a>
                </div>
            </div>
        </nav>
    </aside>

    <section>
        <article>


        <c:if test="${requestScope.themes_lists == null}">

                <div class="search_topic_block">
                    <div class="search_topic_block-content">
                        <form action="/NetworkController" method="get">
                            <input type="hidden" name="command" value="go_to_admin_page"/>
                            <input type="hidden" name="page_number" value="1"/>
                            <input type="hidden" name="count" value="10"/>
                            <input class="search_topic_block-field" type="search" name="expression" value=""
                                   placeholder="${search}"/>
                            <input class="search_topic_content-item-button" type="submit" value="OK"/>
                        </form>
                    </div>
                </div>



            <div class="pagination">
                <a href="/NetworkController?command=go_to_admin_page&action=not_banned&page_number=1&count=10"
                        <c:if test="${requestScope.not_banned != null}">
                            class="selected"
                        </c:if>
                >
                    NOT BANNED
                </a>
                <a href="/NetworkController?command=go_to_admin_page&action=banned&page_number=1&count=10"
                        <c:if test="${requestScope.banned != null}">
                            class="selected"
                        </c:if>
                >
                    BANNED
                </a>
                <a href="/NetworkController?command=go_to_admin_page&action=admins&page_number=1&count=10"
                        <c:if test="${requestScope.admins != null}">
                            class="selected"
                        </c:if>
                >
                    ADMINS
                </a>
            </div>
            <c:if test="${requestScope.search_user_list != null}">
                <c:forEach items="${requestScope.search_user_list}" var="search_user">
                    <div class="card search_user
                    <c:if test="${search_user.banned}">
                    banned
                    </c:if>
                    <c:if test="${search_user.role.getRole() > 2}">
                    admin
                    </c:if>
                ">
                        <div class="search_user-id">#${search_user.id}</div>
                        <div class="search_user-name">
                            <a href="/NetworkController?command=go_to_profile&profile_user_id=${search_user.id}">
                                    ${search_user.name}
                            </a>
                        </div>
                        <div class="search_user-login"><sup>login</sup> ${search_user.about}</div>
                        <div class="search_user-email"><sup>email</sup> ${search_user.email}</div>
                        <div class="search_user-role">
                            <sup>role</sup> ${search_user.role}
                            <c:if test="${sessionScope.role == 100 && search_user.role.getRole() < sessionScope.role}">
                                <a href="/NetworkController?command=change_role&id=${search_user.id}&role=${search_user.role}"
                                   class="change_role">CHANGE
                                    ROLE</a>
                            </c:if>

                        </div>
                        <div class="search_user-banned">
                            <sup>banned</sup>
                            <c:if test="${search_user.banned}">
                                YES
                                <c:if test="${search_user.role.getRole() < sessionScope.role}">
                                    <a href="/NetworkController?command=ban_unban&action=unban&id=${search_user.id}&role=${search_user.role}"
                                       class="unban">UNBAN</a>
                                </c:if>
                            </c:if>
                            <c:if test="${!search_user.banned}">
                                NO
                                <c:if test="${search_user.role.getRole() < sessionScope.role}">
                                    <a href="/NetworkController?command=ban_unban&action=ban&id=${search_user.id}&role=${search_user.role}"
                                       class="ban">BAN</a>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>

            </c:if>

            <div class="pagination">
                <c:if test="${requestScope.expression != null}">

                    <lin:pagination command="go_to_admin_page" expression="${requestScope.expression}" count="10"
                                    pageNumber="${requestScope.page_number}"
                                    listSize="${requestScope.search_user_list.size()}"/>

                </c:if>

                <c:if test="${requestScope.not_banned != null}">

                    <lin:pagination command="go_to_admin_page" action="not_banned" count="10"
                                    pageNumber="${requestScope.page_number}"
                                    listSize="${requestScope.search_user_list.size()}"/>

                </c:if>

                <c:if test="${requestScope.banned != null}">

                    <lin:pagination command="go_to_admin_page" action="banned" count="10"
                                    pageNumber="${requestScope.page_number}"
                                    listSize="${requestScope.search_user_list.size()}"/>

                </c:if>

                <c:if test="${requestScope.admins != null}">

                    <lin:pagination command="go_to_admin_page" action="admins" count="10"
                                    pageNumber="${requestScope.page_number}"
                                    listSize="${requestScope.search_user_list.size()}"/>

                </c:if>
            </div>
        </c:if>


        <c:if test="${requestScope.themes_lists != null}">
            <div class="themes_lists">
                <h1>SHOWN THEMES</h1>


                <c:set var="prev" scope="session" value="${0}"/>
                <c:forEach items="${requestScope.shown_themes}" begin="0" end="2" step="1" var="shown">

                <c:if test="${prev == 0}">
                <div class="card themes_info">
                    <!--${prev = shown.get(0)}-->
                    <div class="themes_info-id">ADD</div>
                    <form action="/NetworkController" method="post">
                        <input type="hidden" name="command" value="change_themes">
                        <input type="hidden" name="action" value="add">
                        </c:if>
                        <c:if test="${prev == shown.get(0)}">
                            <div class="themes_info-language">${shown.get(2)}</div>
                            <input type="text" name="${shown.get(2)}" value=""/>
                        </c:if>

                        </c:forEach>
                        <c:if test="${prev > 0}">
                        <input type="submit" class="save_button" value="ADD"/>
                    </form>
                </div>
                </c:if>
                <lin:themes_output themes="${requestScope.shown_themes}" action="hide"/>


            </div>
            <div class="themes_lists">
                <h1>HIDDEN THEMES</h1>
                <lin:themes_output themes="${requestScope.hidden_themes}" action="show"/>
            </div>
        </c:if>
        </article>
    </section>
</main>

<c:import url="footer.jsp"/>

<script type="text/javascript" src="../../js/index.js"></script>
<script type="text/javascript" src="../../js/scroll.js"></script>
</body>
</html>
