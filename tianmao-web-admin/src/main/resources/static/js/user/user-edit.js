$(function () {
    var balance = {
        //初始化
        init: function () {
            this.initUsernameFocus();
            this.initLoginFormValidate();
        },
        //聚焦
        initUsernameFocus: function () {
            $('#username').focus();
        },
        //表单提交验证
        initLoginFormValidate: function () {
            $("#balanceForm").validate({
                submitHandler: function (form) {
                    $.post(adminUrl + '/user/edit', $(form).serialize(), function (response) {
                        if (response.code == 200) {
                            $('#ajaxModal').modal('hide');
                            $("#bootstrapTable").bootstrapTable('refresh');
                            swal('修改成功', '', 'success');
                        } else {
                            swal(response.message, '', 'warning');
                        }
                    })
                },
                rules: {
                    balance: {
                        required: true,
                    }
                },
                messages: {
                    balance: {
                        required: icon + "请输入一个数字"
                    }
                }
            });
        },
    }

    balance.init();
});
