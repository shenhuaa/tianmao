$(function () {
    var sensitiveWordUpdate = {
        //初始化
        init: function () {
            this.initValidateSensitiveWordForm();
        },
        //初始化表单验证
        initValidateSensitiveWordForm: function () {
            var $sensitiveWordForm = $("#sensitiveWordForm");
            $sensitiveWordForm.validate({
                rules: {
                    wordName: {
                        required: true,
                        maxlength: 50,
                    },
                    sort: {
                        required: true,
                    },

                },
                messages: {
                    wordName: {
                        required: icon + "不能为空",
                        maxlength: icon + "最多可以输入{0}个字符",
                    },
                },
                submitHandler: function (form) {
                    $.post(adminUrl + '/sensitive/word/update', $(form).serialize(), function (response) {
                        if (response.code == 200) {
                            $('#ajaxModal').modal('hide');
                            $("#bootstrapTable").bootstrapTable('refresh');
                            swal('修改成功', '', 'success');
                        } else {
                            swal(response.message, '', 'warning');
                        }
                    })
                },
            });
        }
    };

    sensitiveWordUpdate.init();
})