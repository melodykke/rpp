
var user;

$(document).ready(function(){
    getSubject('/getSubject');
});
function getSubject(url){
    $.ajax({
        async: true,
        //提交数据的类型 POST GET
        type:"GET",
        //提交的网址
        url:url,
        //超时时间设置，单位毫秒
        timeout : 10000,
        //提交的数据
        //data:$("#qform").serialize(),
        //缓存
        cache:false,
        //返回数据的格式
        datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
        //在请求之前调用的函数
        //beforeSend:function(){$("#back-msg").text("数据正在提交，请稍等...");},
        //成功返回之后调用的函数
        success:function(data){
            $("#subject-refresh").hide();
            $('#subject-name').text(data.data.name);
            dows(data.data.username);
        },
        //调用执行后调用的函数
        /*complete: function(XMLHttpRequest, textStatus){
          alert(XMLHttpRequest.responseText);
           alert(textStatus);
            //HideLoading();

        },*/
        //调用出错执行的函数
        error: function(){
            //请求出错处理 TODO刷新
            $("#subject-refresh").show();
            $("#back-msg").text("十分抱歉！数据提交出现错误，请重启浏览器并重新尝试！");
        }
    })
}

function reconnect(username){
    websocket = new WebSocket('ws://sell01.natapp1.cc/websocket/' + username)
}

function dows(username){
    var websocket = null;
    //ws心跳检查
    var heartCheck = {
        timeout: 20000,//60ms
        timeoutObj: null,
        reset: function () {
            clearTimeout(this.timeoutObj);
            this.start();
        },
        start: function () {
            this.timeoutObj = setTimeout(function () {
                websocket.send("HeartBeat:"+username, "beat");
            }, this.timeout)
        }
    }
    if('WebSocket' in window) {
        websocket = new WebSocket('ws://sell01.natapp1.cc/websocket/' + username)
    }else {
        alert('该浏览器不支持ws！');
    }
    websocket.onopen = function (event) {
        console.log('建立连接');
        heartCheck.start();
    }
    websocket.onclose = function (event) {
        console.log('连接关闭')
    }
    websocket.onmessage = function (event) {
        console.log('收到消息：'+event.data);
        if(event.data.startsWith('echo')){
            heartCheck.reset();
        }else{
            $('#online-notice-modal').modal();
        }

    }
    websocket.onerror = function (event) {
        reconnect(username);
    }
    websocket.onbeforeunload = function () {
        websocket.close();
    }
}