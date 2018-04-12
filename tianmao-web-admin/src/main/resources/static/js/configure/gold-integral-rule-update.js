$(function () {
    var searchHotWordUpdate = {
        //初始化
        init: function () {
            this.initValidateGoldIntegralRuleForm();
        },
        //初始化表单验证
        initValidateGoldIntegralRuleForm: function () {
            var $goldIntegralRuleForm = $("#goldIntegralRuleForm");
            $goldIntegralRuleForm.validate({
                rules: {
                    ruleName: {
                        required: true,
                        maxlength: 50,
                    },
                    gold: {
                        required: true,
                        number: true,
                    },
                    integral: {
                        required: true,
                        number: true,
                    },
                },
                messages: {
                    ruleName: {
                        required: icon + "不能为空",
                        maxlength: icon + "最多可以输入{0}个字符",
                    },
                    gold: {
                        required: icon + "不能为空",
                        number: icon + "请输入数字",
                    },
                    integral: {
                        required: icon + "不能为空",
                        number: icon + "请输入数字",
                    },
                },
                submitHandler: function (form) {
                    $.post(adminUrl + '/gold/integral/rule/update', $(form).serialize(), function (response) {
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

    searchHotWordUpdate.init();
})