$(document).ready(function () {
    $.get("/getAllUsers", function (data, status) {
        addUsersTableRow(data)
    });
});

function addUsersTableRow(data) {
    let header = "<tr>\n" +
        "        <th>Firstname</th>\n" +
        "        <th>Lastname</th>\n" +
        "        <th>X</th>\n" +
        "    </tr>";
    $("#usersTableId").empty();
    $("#usersTableId").append(header);
    data.forEach((item, index) => {
        let div = " <tr>\n" +
            "    <td>" + item.firstName + "</td>\n" + "    " +
            "    <td>" + item.lastName + "</td> \n" +
            "    <td><input type='button' value='X' onclick='deleteUser(" + "user" + index + ")'></td> \n" +
            "  </tr>" +
            "<input id='user" + index + "' type='hidden' value='" + JSON.stringify(item) + "'>";
        $("#usersTableId").append(div);
    });
}

function deleteUser(user) {
    $.ajax({
        url: '/userDelete',
        type: 'POST',
        data: user.value,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function (msg) {
            window.window.history.back();
        }
    });
    $.get("/getAllUsers", function (data, status) {
        addUsersTableRow(user)
    });
}