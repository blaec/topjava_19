<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        * {
            box-sizing: border-box;
        }

        input[type=text], input[type=datetime-local] {
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
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            float: right;
            margin-top: 20px;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        .container {
            width: 30%;
            margin: auto;
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px 20px 60px;
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

        /* Clear floats after the columns */
        .row:after {
            content: "";
            display: table;
            clear: both;
        }

        /* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
        @media screen and (max-width: 1200px) {
            .col-25, .col-75 {
                width: 100%;
                margin-top: 0;
            }

            input[type=submit] {
                width: 100%;
                margin-top: 20px;
            }
        }
    </style>
</head>
<body>
<h3><a href="javascript:history.back()" class="fa fa-arrow-circle-left"> Back</a></h3>
<hr>
<div class="container">
    <h2>${action} meal</h2>
    <form method="POST">
        <c:set var="meal" value="${meal}"/>
        <jsp:useBean id="meal" class="ru.javawebinar.topjava.model.MealTo"/>

        <div class="row">
            <div class="col-25">
                <label for="fdate">Date</label>
            </div>
            <div class="col-75">
                <input type="datetime-local" id="fdate" name="fdate" value="${meal.dateTime}">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="fdescription">Description</label>
            </div>
            <div class="col-75">
                <input type="text" id="fdescription" name="fdescription" value="${meal.description}">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="fcalories">Calories</label>
            </div>
            <div class="col-75">
                <input type="text" id="fcalories" name="fcalories" value="${meal.calories}">
            </div>
        </div>

        <input type="submit" value="Submit"/>
    </form>
</div>

</body>
</html>