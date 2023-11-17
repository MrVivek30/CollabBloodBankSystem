<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>History Of Requests</title>
</head>
<body>
<table border="2">
    <tr>
        <th>
            HistId
        </th>
        <th>
            Name
        </th>
        <th>
            email
        </th>
        <th>
            age
        </th>
        <th>
            DateTillOrOn
        </th>
        <th>
            RequestIterCount
        </th>
        <th>
            BloodGroup
        </th>
        <th>
            noOfUnits
        </th>
        <th>
            Action
        </th>
        <th>
            Status
        </th>

    </tr>
    <c:forEach items="${arrayList}" var="item">
        <tr>
            <td>
                <c:out value="${item.histId}"/>
            </td>
            <td>
                <c:out value="${item.placedFor}"/>
            </td>
            <td>
                <c:out value="${item.email}"/>
            </td>
            <td>
                <c:out value="${item.age}"/>
            </td>
            <td>
                <c:out value="${item.dateTillOrOn}"/>
            </td>
            <td>
                <c:out value="${item.requestIterationCount}"/>
            </td>
            <td>
                <c:out value="${item.bloodGroup}"/>
            </td>
            <td>
                <c:out value="${item.noOfUnits}"/>
            </td>
            <td>
                <c:out value="${item.action}"/>
            </td>
            <td>
                <c:out value="${item.status}"/>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>