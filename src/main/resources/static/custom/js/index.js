var pos=0;
var dir=2;
var len=0;
var t_id;

initAnimation();

$(document).ready(function(){

    $('#user-manage').click(function () {
        var targelem = document.getElementById('loader_container');
        targelem.style.display='block';
    })
});


function initAnimation(){
    document.write('<div id="loader_container">' +
                        '<div id="loader">' +
                            '<div align="center">页面加载中……<br />请耐心稍候……</div>' +
                            '<div id="loader_bg">' +
                                '<div class="progress">\n' +
                                    '<div class="progress-bar-indeterminate" style="display: block;"></div>\n' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                    '</div>');
    var t_id = setInterval(animate,20);

}

function animate()
{
    var elem = document.getElementById('progress');
    if(elem != null) {
        if (pos==0) len += dir;
        if (len>32 || pos>179) pos += dir;
        if (pos>179) len -= dir;
        if (pos>179 && len==0) pos=0;
        elem.style.left = pos+'px';
        elem.style.width = len+'px';
    }

}

function remove_loading() {
    this.clearInterval(t_id);
    var targelem = document.getElementById('loader_container');
    targelem.style.display='none';

}
