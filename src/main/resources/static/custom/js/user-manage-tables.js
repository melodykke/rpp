$(document).ready(function() {

    $("#basicTable").bootstrapTable({
        url: 'http://sell01.natapp1.cc/user/user-manage',
        method: 'POST',
        striped: true,//设置为 true 会有隔行变色效果
        undefinedText: "-",//当数据为 undefined 时显示的字符
        contentType: "application/x-www-form-urlencoded",//一种编码。好像在post请求的时候需要用到。这里用的get请求，注释掉这句话也能拿到数据
        dataField: "data",//这是返回的json数组的key.默认好像是"rows".这里只有前后端约定好就行
        pageNumber: 1, //初始化加载第一页，默认第一页
        pagination:true,//是否分页
        showToggle: "true",//是否显示 切换试图（table/card）按钮
        showColumns: "true",//是否显示 内容列下拉框
        queryParams:queryParams,//请求服务器时所传的参数
        sidePagination:'server',//指定服务器端分页
        pageSize:10,//单页记录数
        pageList:[10,20,30,40],//分页步进值
        paginationPreText: '‹',//指定分页条中上一页按钮的图标或文字,这里是<
        paginationNextText: '›',//指定分页条中下一页按钮的图标或文字,这里是>
        responseHandler:responseHandler,//请求数据成功后，渲染表格前的方法
        onLoadSuccess:function(){
            if( window.parent!=window.self){
                $("#page-container-content",window.parent.document).onload=parent.reinitIframe();
                parent.remove_loading();
            }
        },
        columns :[
            {
                title: '<div class="col-2 text-center"><i class="fs-14 fa fa-th"></i></div>',//表的列名
                align: 'center',//水平居中
                formatter: function (value, row, index) {//自定义显示可以写标签哦~
                    return  '<span class="badge btn-complete user-role-manage-button" onclick ="roleManage(\'' + row.username + '\')"> </span>';
                }
            },
            {
                title: '用户名',//表的列名
                field: 'username',//json数据中rows数组中的属性名
                align: 'center'//水平居中
            },
            {
                title: '角色',//表的列名
                field: 'roles',//json数据中rows数组中的属性名
                align: 'center'//水平居中
            },
            {
                title: '行政区',
                field: 'regionName',
                align: 'center'
            },
            {
                title: '行政区代码',
                field: 'regionCode',
                align: 'center'
            },
            {
                //EMAIL
                title: '姓名',
                field: 'name',
                align: 'center'
            },
            {
                //部门名字
                title: '电话',
                field: 'phone',//可以直接取到属性里面的属性，赞
                align: 'center'
            },
            {
                title: '邮箱',
                field: 'email',
                align: 'center'
                /* formatter: function (value, row, index) {//自定义显示，这三个参数分别是：value该行的属性，row该行记录，index该行下标
                     return row.userStatus == 0 ? "正常" : row.userStatus == 1 ? "请假" : "离职";
                 }*/

            },
            {
                title: '状态',
                field: 'userId',
                align: 'center',
                formatter: function (value, row, index) {//自定义显示可以写标签哦~
                    console.log('user.state:'+row.state)
                    return  row.state == "未激活" ? '<button type="button" class="btn btn-complete" data-toggle="button" onclick ="changeUserState(\'' +this+ "','" + row.username + '\')">未激活</button>':
                        '<button type="button" class="btn btn-complete" data-toggle="button" onclick ="changeUserState(\'' +this+ "','" + row.username + '\')">正常</button>';

                }
            }
        ]
    })

//请求服务数据时所传参数
    function queryParams(params){
        return {
            pageSize : params.limit, //每一页的数据行数，默认是上面设置的10(pageSize)
            pageIndex : params.offset/params.limit+1, //当前页面,默认是上面设置的1(pageNumber)
            param : "Your Param" //这里是其他的参数，根据自己的需求定义，可以是多个
        }
    }

//请求成功方法
    function responseHandler(data){
        var errcode = data.code;//在此做了错误代码的判断
        if(errcode != 100){
            alert("错误代码" + errcode);
            return;
        }

        //如果没有错误则返回数据，渲染表格
        return {
            total : data.data.totalElements, //总条目elements数,前面的key必须为"total"
            data : data.data.content, //行数据，前面的key要与之前设置的dataField的值一致.
        };
    };

    //table的搜索
    $("#user-table-search").keyup(function () {
        refresh();
    });

})

//刷新表格数据,点击你的按钮调用这个方法就可以刷新
function refresh() {
    var opt = {
        url: "http://sell01.natapp1.cc/user/user-manage",
        silent: true,

    };

    $('#basicTable').bootstrapTable('refresh', opt);

};

//设置角色的state 0未激活 1激活
function changeUserState(obj, username) {
    var thisButton = this;
    var data = "username=" + username;
    var url = "http://sell01.natapp1.cc/user/change-state";
    $.ajax({
        async: true,
        //提交数据的类型 POST GET
        type: "GET",
        //提交的网址
        url: url,
        //超时时间设置，单位毫秒
        timeout: 5000,
        //提交的数据
        data: data,
        //缓存
        cache: false,
        //返回数据的格式
        datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
        success: function (data) {
            obj.innerText = data.msg;
            console.log("userInfo.state 改变成功; msg=" + data.msg);
            refresh()
        },
    });
}