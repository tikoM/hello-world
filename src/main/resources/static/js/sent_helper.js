$(document).ready(function () {
    $.get("/getSentMessages", function (data, status) {
        addSentTableRow(data)
    });
});

function addSentTableRow(data) {
    let header = "<tr>\n" +
        "        <th>from</th>\n" +
        "        <th>to</th>\n" +
        "        <th>message</th>\n" +
        "    </tr>";
    $("#sentTableId").empty();
    $("#sentTableId").append(header);
    data.forEach((item, index) => {
        let div = " <tr>\n" +
            "    <td>" + item.fromUser.firstName + "</td>\n" + "    " +
            "    <td>" + item.toUser.firstName + "</td> \n" +
            "    <td>" + item.message + "</td> \n" + "  </tr>";
            $("#sentTableId").append(div);
    });
}