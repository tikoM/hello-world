$(document).ready(function () {
    $.get("/getIncomingMessages", function (data, status) {
        addIncomingTableRow(data)
    });
});

function addIncomingTableRow(data) {
    let header = "<tr>\n" +
        "        <th>from</th>\n" +
        "        <th>to</th>\n" +
        "        <th>message</th>\n" +
        "    </tr>";
    $("#incomingTableId").empty();
    $("#incomingTableId").append(header);
    data.forEach((item, index) => {
        let div = " <tr>\n" +
            "    <td>" + item.fromUser.firstName + "</td>\n" + "    " +
            "    <td>" + item.toUser.firstName + "</td> \n" +
            "    <td>" + item.message + "</td> \n" +            "  </tr>";
        $("#incomingTableId").append(div);
    });
}