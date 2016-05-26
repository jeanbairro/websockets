var path = window.location.pathname;
var contextoWeb = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + contextoWeb + "/chat";

var clienteChat = null;

function conecta() {
    clienteChat = new WebSocket(endPointURL);
    clienteChat.onmessage = function (event) {
        var $mensagens = $("ul.mensagens");
        var jsonObj = JSON.parse(event.data);
        var mensagem = jsonObj.user + ": " + jsonObj.message + "\r\n";
        $("<li style='display:none;' class='mensagem'>"+ mensagem +"</li>").appendTo($mensagens).show('slow');
    };
}

function desconecta() {
    clienteChat.close();
}

function enviaMensagem() {
    var usuario =$("#usuario").val().trim();
    if (usuario === "")
        alert("Digite o seu nome!");

    var $campoMensagem = $("#messageInput");
    var mensagem = $campoMensagem.val().trim();
    if (mensagem !== "") {
        var jsonObj = {"user": usuario, "message": mensagem};
        clienteChat.send(JSON.stringify(jsonObj));
        $campoMensagem.val("");
    }
    $campoMensagem.focus();
}