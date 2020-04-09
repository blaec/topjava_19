<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        table {
            border-collapse: collapse;
            width: 80%;
            margin: auto;
        }

        th, td {
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        caption {
            text-align: left;
            font-size: 23px;
            font-weight: bold;
        }

        .excess {
            color: red;
        }

        .normal {
            color: #4CAF50;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<table>
    <thead>
    <caption>Meals</caption>
    <th>Date</th>
    <th>Description</th>
    <th>Calories</th>
    <th>Add</th>
    <th>Remove</th>
    </thead>
    <tbody>
    <c:forEach var="meal" items="${meals}">
        <jsp:useBean id="meal" class="ru.javawebinar.topjava.model.MealTo"/>
        <tr class=${meal.excess ? 'excess' : 'normal'}>
            <td>${f:formatLocalDateTime(meal.dateTime, 'yyyy-MM-dd HH:mm')}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><i class="fa fa-plus-circle"></td>
            <td><i class="fa fa-trash"></td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot></tfoot>
</table>
</body>
</html>