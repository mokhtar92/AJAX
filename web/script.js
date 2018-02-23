/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $("#sendBtn").click(function () {
        $.get("ChatServlet?msg=" + $("#msg").val(),
                ajaxCallBack);
        $('#msg').val("");
    });
});

$(document).ready(function () {
    $("#signOutBtn").click(function () {
        
    });
});

function ajaxCallBack(responseTxt, statusTxt, xhr) {
    if (statusTxt == "success")
        console.log(responseTxt);
        
    if (statusTxt == "error")
        alert("Error: " + xhr.status + ": " + xhr.statusText);
}

function getChatMessages() {
    $.ajax({
        url: 'ChatServlet',
        type: 'GET',
        contentType: 'application/json',
        dataType: 'json',
        success: refreshMessages
    });
}

function refreshMessages(val) {
    
    var messages = val;

    $("#messagesTable tr").remove();
    for (var i = 0; i < val.length; i++) {
        $('#messagesTable').append('<tr><td><b>' + messages[i].sender + ": " + '</b></td><td>' + messages[i].message + '</td></tr>');
        
        $("#statusOfUsers tr").remove();
        $('#statusOfUsers').append('<tr><td><b>' + messages[i].sender + " " + '</b></td><td>' + "is online" + '</td></tr>');
    }
   
}

