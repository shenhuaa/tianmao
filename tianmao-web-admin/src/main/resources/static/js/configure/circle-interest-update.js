$(function () {
    var circleInterestUpdate = {
        //初始化
        init: function () {
            this.initValidateCircleInterestForm();
        },
        //初始化表单验证
        initValidateCircleInterestForm: function () {
            var $circleInterestForm = $("#circleInterestForm");
            $circleInterestForm.validate({
                rules: {
                    name: {
                        required: true,
                        maxlength: 50,
                    },
                    sort: {
                        required: true,
                    },

                },
                messages: {
                    name: {
                        required: icon + "不能为空",
                        maxlength: icon + "最多可以输入{0}个字符",
                    },
                    sort: {
                        required: icon + "不能为空",
                    },
                },
                submitHandler: function (form) {
                    $.post(adminUrl + '/circle/interest/update', $(form).serialize(), function (response) {
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

    circleInterestUpdate.init();
})