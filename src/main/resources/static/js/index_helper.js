$(document).ready(function () {
    $.get("/getUser", function (data, status) {
        addProfilInfo(data)
    });
});

function addProfilInfo(data) {
    $("#profilId").empty();
    let div = "<div> FirstName: " + data.firstName + "</div>" +
        "<div> LastName: " + data.lastName + "</div>" +
        " <img src='file?path=" + data.picturePath + "'> ";
    $("#profilId").append(div);
}