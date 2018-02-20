/* ============================================================
 * Form Elements
 * This file applies various jQuery plugins to form elements
 * For DEMO purposes only. Extract what you need.
 * ============================================================ */
(function($) {

    'use strict';

    var getBaseURL = function() {
        var url = document.URL;
        return url.substr(0, url.lastIndexOf('/'));
    }

    $(document).ready(function() {



        //Date Pickers
        $('#constructionTime, #constructionEndTime, #productionTime, #completionAcceptanceTime, #compileTime, #fillFormTime').datepicker();
        // disabling dates
        var nowTemp = new Date();
        var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

        //Input mask - Input helper
        $(function($) {
            $("#date").mask("9999/99/99");
            $("#phone").mask("(999) 999-9999");
            $("#tin").mask("99-9999999");
            $("#ssn").mask("999-99-9999");
        });
        //Autonumeric plug-in - automatic addition of dollar signs,etc controlled by tag attributes
        $('.autonumeric').autoNumeric('init');

        //Drag n Drop up-loader
        $("div#myId").dropzone({
            url: "/file/post"
        });
        //Single instance of tag inputs - can be initiated with simply using data-role="tagsinput" attribute in any input field
        $('.custom-tag-input').tagsinput({

        });




    });

})(window.jQuery);