/*电站基础信息录入访问后台*/
$(document).ready(function() {
    $("#submitForm").click(function () {//button的click事件
        $('#ppbiu-submit').modal({backdrop: 'static', keyboard: false});
        $("#goahead-button").hide();
        $("#return-button").hide();
        var url = 'http://sell01.natapp1.cc/power-plant/power-plant-base-info-upload';
        $.ajax({
            async: true,
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:url,
            //超时时间设置，单位毫秒
            timeout : 5000,
            //提交的数据
            data:$("#powerPlantBaseInfoForm").serialize(),
            //缓存
            cache:false,
            //返回数据的格式
            datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
            //在请求之前调用的函数
            beforeSend:function(){
                $('#h5title').text("提交中");
                $('#ptext').text("数据正在提交，请稍等...");
            },
            //成功返回之后调用的函数
            success:function(data){
                if(data.code==100) {
                    $('#h5title').text('提交成功！');
                    $('#ptext').text('若还要继续录入请单击"继续"按钮，否则请返回！');
                    $("#goahead-button").show();
                    $("#return-button").show();
                }else{
                    $('#h5title').text('错误码：' + data.code);
                    $('#ptext').text(data.msg);
                    $('#return-button').show();
                }
            },
            //调用执行后调用的函数
            /*complete: function(XMLHttpRequest, textStatus){
              alert(XMLHttpRequest.responseText);
               alert(textStatus);
                //HideLoading();
            },*/
            //调用出错执行的函数
            error: function(){
                //请求出错处理
                $('#ptext').text("十分抱歉！数据提交出现错误，请重启浏览器并重新尝试！");
                $('#return-button').show();
            }
        });
    });
    $('#goahead-button').click(function(){
        window.location = "http://sell01.natapp1.cc/power-plant/power-plant-base-info-upload"
        $('#goahead-button').attr('disabled',"true");
    })
})