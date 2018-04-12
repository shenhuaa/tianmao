$(function () {
    var ratioEdit = {
        //初始化
        init: function () {
            this.initRatioEditFormValidate();
        },
        //表单提交验证
        initRatioEditFormValidate: function () {
            $("#ratioEditForm").validate({
                submitHandler: function (form) {
                    $.post(adminUrl + '/user/ratioEdit', $(form).serialize(), function (response) {
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
        },
    }

    ratioEdit.init();
});
