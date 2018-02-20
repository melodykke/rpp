$(document).ready(function() {
    console.log("custom ready")
    $("#submitForm").click(function () {//button的click事件
        console.log($("#powerPlantBaseInfoForm"))

        $("#powerPlantBaseInfoForm").submit();

        console.log("dddsss")
    });
})