<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/topjava.common.js" defer></script>
<script type="text/javascript" src="resources/js/topjava.meals.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="meal.title"/></h3>

        <div class="card border-dark">
            <div class="card-body pb-0">
                <form id="filter">
                    <div class="row">
                        <div class="offset-1 col-2">
                            <label for="startDate"><spring:message code="meal.startDate"/></label>
                            <input class="form-control" id="startDate" name="startDate" autocomplete="off"/>
                        </div>
                        <div class="col-2">
                            <label for="endDate"><spring:message code="meal.endDate"/></label>
                            <input class="form-control" id="endDate" name="endDate" autocomplete="off"/>
                        </div>
                        <div class="offset-2 col-2">
                            <label for="startTime"><spring:message code="meal.startTime"/></label>
                            <input class="form-control" id="startTime" name="startTime" autocomplete="off"/>
                        </div>
                        <div class="col-2">
                            <label for="endTime"><spring:message code="meal.endTime"/></label>
                            <input class="form-control" id="endTime" name="endTime" autocomplete="off"/>
                        </div>
                    </div>
                </form>
            </div>
            <div class="card-footer text-right">
                <button class="btn btn-outline-danger" onclick="alert('clearFilter()')">
                    <span class="fa fa-remove"></span>
                    <spring:message code="meal.cancel"/>
                </button>
                <button class="btn btn-outline-primary" onclick="alert('updateFilteredTable()')">
                    <span class="fa fa-filter"></span>
                    <spring:message code="meal.filter"/>
                </button>
            </div>
        </div>
        <button class="btn btn-outline-primary mt-3" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="meal.add"/>
        </button>
        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th><spring:message code="meal.dateTime"/></th>
                <th><spring:message code="meal.description"/></th>
                <th><spring:message code="meal.calories"/></th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${meals}" var="meal">
                <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealTo"/>
                <tr data-mealExcess="${meal.excess}">
                    <td>
                            <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                            <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                            <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                            ${fn:formatDateTime(meal.dateTime)}
                    </td>
                    <td>${meal.description}</td>
                    <td class="text-right pr-5">${meal.calories}</td>
                    <td><a onclick="updateRow(${meal.id});"><span class="fa fa-pencil"></span></a></td>
                    <td><a onclick="deleteRow(${meal.id});"><span class="fa fa-remove"></span></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="modal fade" tabindex="-1" id="editRow">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title" id="modalTitle"></h4>
                    <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">×</button>
                </div>
                <div class="modal-body">
                    <form id="detailsForm">
                        <input type="hidden" id="id" name="id">

                        <div class="form-group">
                            <label for="dateTime" class="col-form-label"><spring:message code="meal.dateTime"/></label>
                            <input class="form-control" type="datetime-local" id="dateTime" name="dateTime" autocomplete="off" placeholder="Date/Time">
                        </div>

                        <div class="form-group">
                            <label for="description" class="col-form-label"><spring:message code="meal.description"/></label>
                            <input type="text" class="form-control" id="description" name="description" placeholder="Description">
                        </div>

                        <div class="form-group">
                            <label for="calories" class="col-form-label"><spring:message code="meal.calories"/></label>
                            <input type="number" class="form-control" id="calories" name="calories" placeholder="1000">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                        <span class="fa fa-close"></span>
                        <spring:message code="common.cancel"/>
                    </button>
                    <button type="button" class="btn btn-primary" onclick="save()">
                        <span class="fa fa-check"></span>
                        <spring:message code="common.save"/>
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>