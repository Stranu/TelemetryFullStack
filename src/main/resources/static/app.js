const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/gs-guide-websocket'
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/telemetry', (sensors) => {
        showTelemetry(JSON.parse(sensors.body));
    });

    let cicle = 0;
    let tid = setInterval(function(){
        //called 5 times each time after one second
        //before getting cleared by below timeout.
        stompClient.publish({
            destination: "/app/connect",
            body: JSON.stringify({'cicle': cicle})
        });
        cicle++;
    },1000); //delay is in milliseconds


    /*setTimeout(function(){
        clearInterval(tid); //clear above interval after 5 seconds
    },5000);
    */

};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#datatable").show();
    }
    else {
        $("#datatable").hide();
    }
    $("#telemetry").html("");
}

function connect() {
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.publish({
        destination: "/app/connect",
        body: JSON.stringify({'name': $("#name").val()})
    });
}

function showTelemetry(messages) {
    messages.forEach(function (message) {
        $("#telemetry").append("<tr>");
        $("#telemetry").append("<td>" + message.timestamp + "</td>");
        $("#telemetry").append("<td>" + message.press + "</td>");
        $("#telemetry").append("<td>" + message.position + "</td>");
        $("#telemetry").append("<td>" + message.temp + "</td>");
        $("#telemetry").append("<td>" + message.omega + "</td>");
        $("#telemetry").append("<td>" + message.speed + "</td>");
        $("#telemetry").append("<td>" + message.car + "</td>");
        $("#telemetry").append("</tr>");
    });
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendName());
});

