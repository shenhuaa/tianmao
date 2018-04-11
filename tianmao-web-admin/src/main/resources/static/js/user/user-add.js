$(function () {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#adminForm").validate({
        submitHandler: function (form) {
            var url = '/user/add';
            $.post(adminUrl + url, $(form).serialize(), function (response) {
                if (response.code == 200) {
                    swal({
                        title: "添加成功",
                        type: "success",
                    }, function (isConfirm) {
                        window.location.reload();
                    });
                } else {
                    swal(response.message, '', 'warning');
                }
            })
        },
        rules: {
            username: {
                required: true,
                minlength: 4,
                maxlength: 20,
                checkAdminAccount: true,
                checkChinese: true
            },
            nickname: {
                required: true,
                minlength: 2,
                maxlength: 20
            },
            password: {
                required: true,
                minlength: 5,
                maxlength: 16
            },
            passwordSalt: {
                required: true,
                minlength: 5,
                maxlength: 16
                // equalTo: "#password"
            },
            mobile: {
                checkMobile: true
            },
            email: {
                email: true
            }
        },
        messages: {
            username: {
                checkAdminAccount: icon + "账户已存在",
                checkChinese: icon + "账户不能为汉字"
            },
            mobile: {
                checkMobile: icon + "手机号码格式不正确",
            },
            password: {
                required: icon + "请输入密码",
                minlength: icon + "密码不能少于5个字符",
                maxlength: icon + "密码不能超过16个字符"
            },
            passwordSalt: {
                required: icon + "请再次输入密码",
                minlength: icon + "密码必须5个字符以上",
                // equalTo: icon + "两次输入的密码不一致"
            }
        }
    });
    $("#close").click(function () {
        window.location.reload();
    })
    $(".close").click(function () {
        window.location.reload();
    })
});