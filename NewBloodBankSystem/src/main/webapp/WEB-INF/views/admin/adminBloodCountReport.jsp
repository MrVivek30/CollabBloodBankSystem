<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Blood Count By Group</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        h2 {
            color: #b71c1c;
        }

        table {
            width: 50%;
            margin-top: 20px;
            border-collapse: collapse;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        th, td {
            border: 1px solid #dddddd;
            padding: 12px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }
        .btn {
                            margin-top: 10px;
                            display: inline-block;
                            padding: 10px 20px;
                            background-color: #4CAF50;
                            color: white;
                            text-decoration: none;
                            border-radius: 5px;
                        }

                        .btn:hover {
                            background-color: #45a049;
                        }
    </style>
</head>

<body>
    <div class="container">
        <h2>Blood Count By Group</h2>
        <table>
            <tr>
                <th>Blood Group</th>
                <th>Unit Count</th>
            </tr>
            <c:forEach var="entry" items="${reqBloodCount}">
                <tr>
                    <td>${entry.key}</td>
                    <td>${entry.value}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
        <a href="/com/BloodBank/v1/admin/dashboard" class="btn">Dashboard</a>
</body>

</html>
