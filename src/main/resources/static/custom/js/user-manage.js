/*user相关ajax*/

var username='';

$(document).ready(function() {

//ajax调用服务端借口,产生遮罩，分配角色
    $("#assign-role-confirm").click(function () {
        $("#assign-role-confirm").attr('disabled',"true");
        var maxIndex = $("#roletree .icon-ok").parent(".tree-selected").find(".tree-item-name").length - 1;
        var roles = '';
        var data;
        $("#roletree .icon-ok").parent(".tree-selected").find(".tree-item-name").text(function(index, content){
            roles += (index === maxIndex) ? content : content + ',';
        })
        data =  "username="+username+"&roles="+roles;
        console.log(data)
        var url = 'http://sell01.natapp1.cc/user/assign-role';
        $.ajax({
            async: true,
            //提交数据的类型 POST GET
            type:"POST",
            //提交的网址
            url:url,
            //超时时间设置，单位毫秒
            timeout : 5000,
            //提交的数据
            data: data,
            //缓存
            cache:false,
            //返回数据的格式
            datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
            //在请求之前调用的函数
            //成功返回之后调用的函数
            success:function(data){
                if(data.code == 400){
                    $("#code").text('Code: '+ data.code);
                    $("#msg").text('　message: ' + data.msg)
                }else if(data.code == 99){
                    $("#msg").text(data.msg)
                    setTimeout(function () {
                        $("#role-manager").modal('hide');
                        window.location = "http://sell01.natapp1.cc/user/user-manage";
                    }, 3000)
                }
            },
            error: function(){
                //请求出错处理
                $("#msg").text('配置失败，系统异常，请关闭浏览器重试！')
            }
        });
    });

})

function roleManage(username1) {
    username = username1;
    $('#role-manager').modal()
}


