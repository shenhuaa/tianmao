;(function ($) {
    var permission = {
        //初始化
        init: function () {
            this.initPermissionAddFormValidate();
            this.initOKClick();
        },
        //点击OK事件，刷新页面
        initOKClick:function () {
            $(document).on('click', 'button.confirm', function () {
                window.location.reload();
            })
        },
        //添加权限事件
        initPermissionAddFormValidate: function () {
            $("#permissionAddForm").validate({
                submitHandler: function (form) {
                    $.post(adminUrl + '/permission/add', $(form).serialize(), function (response) {
                        if (response.code == 200) {
                            $('#ajaxModal').modal('hide');
                            swal('添加成功', '', 'success');
                        } else {
                            swal(response.message, '', 'warning');
                        }
                    })
                },
                rules: {
                    'parent.id': {
                        required: true,
                    },
                    name: {
                        required: true,
                        maxlength: 50,
                    },
                    url: {
                        maxlength: 50,
                    },
                    mark: {
                        maxlength: 50,
                    },
                },
                messages: {
                    'parent.id': {
                        required: icon + "请选择上级权限",
                    },
                    name: {
                        required: icon + "权限名字不能为空",
                        maxlength: icon + "最大长度为{0}"
                    },
                    url: {
                        maxlength: icon + "最大长度为{0}"
                    },
                    mark: {
                        maxlength: icon + "最大长度为{0}"
                    },
                }
            });
        },
    };

    permission.init();

})(jQuery);
