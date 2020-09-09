<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'expense.label', default: 'Expense')}" />
    <title>Expense Analysis</title>
</head>
<body>
<a href="#list-expense" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
        <li><g:link class="statement" action="statement">Statement</g:link></li>
    </ul>
</div>
<div id="show-expense" class="content scaffold-show" role="main">
    <h1>Expense Analysis</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:each in="${categories}" var="cat">
        <h1>${cat.key} - Total: ${cat.value.sum {it.amount}}</h1>
        <f:table collection="${cat.value}"/>
    </g:each>
</div>
</body>
</html>