
var websocket = null;
if('WebSocket' in window) {
    websocket = new WebSocket('ws://sell01.natapp1.cc/websocket')
}else {
    alert('该浏览器不支持ws！');
}
websocket.onopen = function (event) {
    console.log('建立连接');
}
websocket.onclose = function (event) {
    console.log('连接关闭')
}
websocket.onmessage = function (event) {
    console.log('收到消息：'+event.data)
}
websocket.onerror = function (event) {
    alert('ws通信错误！');
}
websocket.onbeforeunload = function () {
    websocket.close();
}
