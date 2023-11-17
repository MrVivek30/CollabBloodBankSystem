

<form action="/com/BloodBank/v1/request/editRequest" method="post">
    <input type="hidden" name="id" value="${editRequest.id}">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${editRequest.name}" readonly><br>
    <label for="age">Age:</label>
    <input type="number" id="age" name="age" value="${editRequest.age}" readonly><br>
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${editRequest.email}" readonly><br>
    <label for="bloodGroup">Blood Group:</label>
    <input type="text" id="bloodGroup" name="bloodGroup" value="${editRequest.bloodGroup}" readonly><br>

    <label for="unit">Unit:</label>
        <input type="text" id="unit" name="unit" value="${editRequest.unit}" required><br>
    <label for="address">Address:</label>
    <input type="text" id="address" name="address" value="${editRequest.address}" required><br>

    <label for="type">Type:</label>
    <input type="text" id="type" name="type" value="${editRequest.type}" readonly><br>


    <input type="hidden" name="role" value="${editRequest.role}" >



    <button type="submit">Submit Edit</button>
</form>
