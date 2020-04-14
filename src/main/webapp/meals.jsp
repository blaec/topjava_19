<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }

        .col-25 {
            float: left;
            width: 25%;
            margin-top: 6px;
        }

        .col-75 {
            float: left;
            width: 75%;
            margin-top: 6px;
        }

        .container {
            display: grid;
            grid-template-columns: [col] 400px [col] 400px;
            grid-template-rows: [row] auto [row] auto [row];
            grid-gap: 10px;
        }

        .box {
            background-color: white;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            border-radius: 5px;
            padding: 10px;
            width: 810px;
        }

        .box:hover {
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
        }

        input[type=time], input[type=date] {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            resize: vertical;
        }

        label {
            padding: 12px 12px 12px 0;
            display: inline-block;
        }

        input[type=submit] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 100px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: right;
            margin-top: 20px;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        .btn {
            grid-column: col / span 2;
            grid-row: row 3;
            margin: auto;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Meals</h2>
    <div>
        <form class="container box" method="get" action="meals">
            <div>
                <div class="col-25">
                    <label for="fdatefrom">From date</label>
                </div>
                <div class="col-75">
                    <input type="date" id="fdatefrom" name="fdatefrom" value="">
                </div>
            </div>
            <div>
                <div class="col-25">
                    <label for="ftimefrom">From time</label>
                </div>
                <div class="col-75">
                    <input type="time" id="ftimefrom" name="ftimefrom" value="">
                </div>
            </div>
            <div>
                <div class="col-25">
                    <label for="fdateto">To date</label>
                </div>
                <div class="col-75">
                    <input type="date" id="fdateto" name="fdateto" value="">
                </div>
            </div>
            <div>
                <div class="col-25">
                    <label for="ftimeto">To time</label>
                </div>
                <div class="col-75">
                    <input type="time" id="ftimeto" name="ftimeto" value="">
                </div>
            </div>
            <div class="btn">
                <input type="submit" value="Filter"/>
            </div>
        </form>
    </div>
    <div class="box">
        <a href="meals?action=create">Add Meal</a>
        <br><br>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
            <tr>
                <th>Date</th>
                <th>Description</th>
                <th>Calories</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <c:forEach items="${meals}" var="meal">
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
                <tr class="${meal.excess ? 'excess' : 'normal'}">
                    <td>
                            <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                            <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                            <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                            ${fn:formatDateTime(meal.dateTime)}
                    </td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                    <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>
</body>
</html>