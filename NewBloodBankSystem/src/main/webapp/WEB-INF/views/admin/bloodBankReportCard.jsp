<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title> Blood Bank Report Card</title>
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

            h1 {
                color: #b71c1c;
            }

            table {
                width: 80%;
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

            .no-receivers-message {
                margin-top: 20px;
                font-size: 18px;
                color: #333;
            }

            .action-links {
                margin-top: 20px;
            }

            .action-links a {
                text-decoration: none;
                margin: 0 10px;
                padding: 10px 20px;
                border: 2px solid #1565c0;
                border-radius: 5px;
                color: #1565c0;
                transition: all 0.3s ease;
            }

            .action-links a:hover {
                background-color: #1565c0;
                color: #ffffff;
            }
            select, input[type="text"] {
                         width: calc(100% - 20px);
                         padding: 10px;
                         margin-top: 5px;
                         border: 1px solid #ccc;
                         border-radius: 5px;
                         font-size: 16px;
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
        <h2>Blood Bank Report Card Details </h2>
        <table>
            <tr>
                          <th>Blood Group</th>
                           <th>Available Units</th>
                          <th>Donation Req</th>
                          <th>Receive Req</th>
                          <th>Accepted Req</th>
                          <th>Rejected Req</th>
                          <th>Pending Req</th>

                      </tr>
        <c:forEach var="report" items="${bloodBankReportCard}">
                        <tr>
                            <td>${report.bloodGroup}</td>
                             <td>${report.units}</td>
                            <td>${report.donateReq}</td>
                            <td>${report.receiveReq}</td>
                            <td>${report.acceptReq}</td>
                            <td>${report.rejectReq}</td>
                            <td>${report.pendingReq}</td>

                        </tr>
                    </c:forEach>

        </table>
    </div>
        <a href="/com/BloodBank/v1/admin/dashboard" class="btn">Back</a>
</body>

</html>
