$(document).ready(function () {
    $.get("/getAllMessages", function (data, status) {
        addIncomingTableRow(data)
    });
});

function addIncomingTableRow(data) {
    let header = "<tr>\n" +
        "        <th>from</th>\n" +
        "        <th>to</th>\n" +
        "        <th>message</th>\n" +
        "        <th>X</th>\n" +
        "    </tr>";
    $("#allMessagesTableId").empty();
    $("#allMessagesTableId").append(header);
    data.forEach((item, index) => {
        let div = " <tr>\n" +
            "    <td>" + item.fromUser.firstName + "</td>\n" + "    " +
            "    <td>" + item.toUser.firstName + "</td> \n" +
            "    <td>" + item.message + "</td>\n" +
            "    <td><input type='button' value='X' onclick='deleteMessage(" + "message" + index + ")'></td> \n" + "  </tr>" +
            "<input id='message" + index + "' type='hidden' value='" + JSON.stringify(item) + "'>";
        $("#allMessagesTableId").append(div);
    });
}

function deleteMessage(message) {
    $.ajax({
        url: '/deleteMessage',
        type: 'POST',
        data: message.value,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function (msg) {
            window.window.history.back();
        }
    });
    $.get("/getAllMessages", function (data, status) {
        addIncomingTableRow(data)
    });
}