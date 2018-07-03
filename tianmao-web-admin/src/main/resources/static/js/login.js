$(function () {
    var icon = "<i class='fa fa-times-circle'></i> ";
    var action = {
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
            $("#loginForm").validate({
                submitHandler: function (form) {
                    $.post(adminUrl + '/login', $(form).serialize(), function (response) {
                        if (response.code == 200) {
                            window.location.href = adminUrl;
                        } else {
                            console.log(response.message)
                           alert(response.message)
                        }
                    })
                },
                rules: {
                    username: {
                        required: true,
                        maxlength: 50,
                    },
                    password: {
                        required: true,
                        maxlength: 20,
                    },
                },
                messages: {
                    clubName: {
                        required: icon + "不能为空",
                        maxlength: icon + "最大长度为{0}"
                    },
                    cityParent: {
                        required: icon + "不能为空",
                        maxlength: icon + "最大长度为{0}"
                    },
                }
            });
        },
    }

    action.init();
});
