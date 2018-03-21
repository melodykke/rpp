jQuery(document).ready(function () {
    initApplicationList()
    //搜索
    $("#btn_Search").click(function () {
        initApplicationList()
    })
    $(document).ready(function() {
        var table = $('#approval-table').DataTable();

        $('#approval-table tbody').on('click', 'tr', function () {
            var data = table.row( this ).data();

            var dialog = parent.$('#approval-detail-modal');

            dialog.modal("show");
        } );
    } );
  /*  //按钮的绑定事件要放到document或者其他父标签上，因为元素是在datatable方法加载完成之后才显示出来的
    //编辑
    $(".portlet-body").on('click', 'button#editrow', function () {
        var data = $("#UserList").DataTable().row($(this).parents("tr")).data();
        alert(data.User_Ename + "'s Division is: " + data.Division);
    });
    //删除
    $(".portlet-body").on('click', 'button#delrow', function () {
        var data = $("#UserList").DataTable().row($(this).parents("tr")).data();
        alert("Do you want delete " + data.User_Ename + "?");
    });*/
});


var oTable = null;
var initApplicationList = function () {
    var table = $('#approval-table');
    if (oTable == null) { //仅第一次检索时初始化Datatable
        oTable = table.dataTable({
            "bLengthChange": false, //改变每页显示数据数量
            "bFilter": false, //过滤功能
            "bProcessing": true, //开启读取服务器数据时显示正在加载中……特别是大数据量的时候，开启此功能比较好
            "bServerSide": false, //开启服务器模式，使用服务器端处理配置datatable。注意：sAjaxSource参数也必须被给予为了给datatable源代码来获取所需的数据对于每个画。 这个翻译有点别扭。
            "iDisplayLength": 10,//每页显示10条数据
            //ajax地址
            "sAjaxSource": "http://sell01.natapp1.cc/user/get-approval-list",
            "fnServerData": retrieveData,//执行方法

            //默认排序
            "order": [
                [0, 'asc']//第一列正序
            ],
            "lengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "pageLength": 10,
            //向服务器传额外的参数
           /* "fnServerParams": function (aoData) {
                aoData.push(
                    { "name": "UserName", "value": $('#txt_UserName').val() },//员工名字
                    { "name": "Division", "value": $('#Sel_Division').val() })//所处Division
            },*/
            //配置列要显示的数据
            "columns": [
                { "data": "applicationId" },
                { "data": "ppbiuPlantId" },
                { "data": "applicationType" },
                { "data": "status" },
                { "data": "createTime" },
                { "data": "marks" },
            ],

            //按钮列
         /*   "columnDefs": [
                //{
                //    "targets": -2,//编辑
                //    "data": null,
                //    "defaultContent": "<button id='editrow' class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button>"
                //},
                {
                    "targets": -1,//删除
                    "data": null,
                    "defaultContent": "<button id='editrow' class='btn btn-primary' type='button'><i class='fa fa-edit'></i></button><button id='delrow' class='btn btn-primary' type='button'><i class='fa fa-trash-o'></i></button>"
                }
            ] ,
*/
            //语言配置--页面显示的文字
            "language": {
                "aria": {
                    "sortAscending": ": activate to sort column ascending",
                    "sortDescending": ": activate to sort column descending"
                },
                "emptyTable": "No data available in table",
                "info": "Showing _START_ to _END_ of _TOTAL_ entries",
                "infoEmpty": "No entries found",
                "infoFiltered": "(filtered1 from _MAX_ total entries)",
                "lengthMenu": "Show _MENU_ entries",
                "search": "Search:",
                "zeroRecords": "No matching records found"
            }


        });
    }

    //刷新Datatable，会自动激发retrieveData
    oTable.fnDraw();
    // tableWrapper.find('.dataTables_length select').select2(); // initialize select2 dropdown

}

//获取当前用户下级提交的基础电站需审核信息
function retrieveData(sSource, aoData, fnCallback) {
    $.ajax({
        type:"POST",
        async: true,
        url:sSource,
        timeout : 5000,
        cache:false,
        data: aoData,
        datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
       /* beforeSend:function(){ },*/
        success: function (resp) {
            fnCallback(resp); //服务器端返回的对象的returnObject部分是要求的格式
            $("#page-container-content",window.parent.document).onload=parent.reinitIframe();
        },
    });
}