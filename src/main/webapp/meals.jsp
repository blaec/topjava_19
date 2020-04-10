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
            width: 60%;
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

        .date {
            width: 20%
        }
        .description {
            width: 50%
        }
        .calories {
            width: 15%
        }
        .symbol {
            width: 7.5%
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<table>
    <thead>
    <caption>Meals</caption>
    <th class="date">Date</th>
    <th class="description">Description</th>
    <th class="calories">Calories</th>
    <th class="symbol">Edit</th>
    <th class="symbol">Remove</th>
    </thead>
    <tbody>
    <c:forEach var="meal" items="${meals}">
        <jsp:useBean id="meal" class="ru.javawebinar.topjava.model.MealTo"/>
        <tr class=${meal.excess ? 'excess' : 'normal'}>
            <td>${f:formatLocalDateTime(meal.dateTime, 'yyyy-MM-dd HH:mm')}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=edit&id=${meal.id}" class="fa fa-edit"></a></td>
            <td><a href="meals?action=delete&id=${meal.id}" class="fa fa-trash"></a></td>
        </tr>
    </c:forEach>
    </tbody>
    <tfoot>
        <tr>
            <td colspan="5"><a href="meals?action=create" class="fa fa-plus"> Add new meal</a></td>
        </tr>
    </tfoot>
</table>
</body>
</html>