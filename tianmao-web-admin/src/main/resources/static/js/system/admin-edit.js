$(function () {
     var icon = "<i class='fa fa-times-circle'></i> ";
     $("#adminForm").validate({
         submitHandler: function (form) {
             $.post(adminUrl + '/admin/update', $(form).serialize(), function (response) {
                 if (response.code == 200) {
                     swal({
                         title: "修改成功",
                         type:"success",
                     },function (isConfirm) {
                         window.location.reload();
                     });
                 } else {
                     swal(response.message, '', 'warning');
                 }
             })
         },
         rules: {
             name: {
                 required: true,
                 minlength: 2,
                 maxlength: 20
             },
             roleId: {
                 required: true
             },
             password: {
                 minlength: 5,
                 maxlength: 16
             },
             confirm_password: {
                 minlength: 5,
                 maxlength: 16,
                 equalTo: "#password"
             },
             mobile: {
            	 checkMobile:true
             },
             email: {
                 email: true
             }
         },
         messages: {
             mobile: {
            	 checkMobile: icon + "手机号码格式不正确",
             }
         }
     });
 });