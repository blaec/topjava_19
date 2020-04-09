<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
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
    <tr>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    </tbody>
    <tfoot></tfoot>
</table>
</body>
</html>