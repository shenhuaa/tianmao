$(function () {
    var searchHotWordAdd = {
        //初始化
        init: function () {
            this.initValidateSearchHotWordForm();
        },
        //初始化表单验证
        initValidateSearchHotWordForm: function () {
            var $searchHotWordForm = $("#searchHotWordForm");
            $searchHotWordForm.validate({
                rules: {
                    hotWord: {
                        required: true,
                        maxlength: 50,
                    },
                    sort: {
                        required: true,
                    },

                },
                messages: {
                    hotWord: {
                        required: icon + "不能为空",
                        maxlength: icon + "最多可以输入{0}个字符",
                    },
                    sort: {
                        required: icon + "不能为空",
                    },
                },
                submitHandler: function (form) {
                    $.post(adminUrl + '/search/hot/word/add', $(form).serialize(), function (response) {
                        if (response.code == 200) {
                            $('#ajaxModal').modal('hide');
                            $("#bootstrapTable").bootstrapTable('refresh');
                            swal('添加成功', '', 'success');
                        } else {
                            swal(response.message, '', 'warning');
                        }
                    })
                },
            });
        }
    };

    searchHotWordAdd.init();
})