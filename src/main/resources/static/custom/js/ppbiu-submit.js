/*电站基础信息录入访问后台*/
$(document).ready(function() {
    var targelem = window.parent.$('#loader_container');
    //根据iframe内容自适应高度
    var mf = window.parent.$('#mainFrame');//$("#mainFrame",window.parent.document);//
    mf.onload=parent.reinitIframe();
    var modal = window.parent.$('#ppbiu-submit-modal');//$("#ppbiu-submit-modal",window.parent.document);-->这种直接是把modal在frame里显示。
    $("#submitForm").click(function () {//button的click事件
        modal.modal({backdrop: 'static', keyboard: false});
        window.parent.$('#goahead-button').hide();
        window.parent.$('#return-button').hide();
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
                window.parent.$('#h5title').text("提交中");
                window.parent.$('#ptext').text("数据正在提交，请稍等...");
            },
            //成功返回之后调用的函数
            success:function(data){

                if(data.code==100) {
                    window.parent.$('#h5title').text('提交成功！');
                    window.parent.$('#ptext').text('若还要继续录入请单击"继续"按钮，否则请返回！');
                    window.parent.$('#goahead-button').show();
                    window.parent.$('#return-button').show();
                }else{
                    window.parent.$('#h5title').text('错误码：' + data.code);
                    window.parent.$('#ptext').text(data.msg);
                    window.parent.$('#return-button').show();
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
                window.parent.$('#ptext').text("十分抱歉！数据提交出现错误，请重启浏览器并重新尝试！");
                window.parent.$('#return-button').show();
            }
        });
    });
    window.parent.$('#goahead-button').click(function(){
        console.log('.............goaheadbutton function called..............')
        mf.attr('src', '/power-plant/power-plant-base-info-upload')
        modal.modal('hide')

        targelem.css('display','block');
    })
    window.parent.$('#return-button').click(function(){
        console.log('.............returnbutton function called..............')

    })



})
