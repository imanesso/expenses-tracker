<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'expense.label', default: 'Expense')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-expense" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="lupe" action="analyse">Analysis</g:link></li>
            </ul>
        </div>
        <div id="list-expense" class="content scaffold-list" role="main">
            <h1>Monthly Statement</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:form controller="expense" action="statement">
                <fieldset class="form">
                <label>Month</label>
                <g:datePicker name="startDate"  precision="month" relativeYears="[-10..0]"/>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="Show Statement" />
                </fieldset>
            </g:form>
            <f:table collection="${expenseList}" />

            <div class="pagination">
                <g:paginate total="${expenseCount ?: 0}" />
            </div>
        </div>
    </body>
</html>