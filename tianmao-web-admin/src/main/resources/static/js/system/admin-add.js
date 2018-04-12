$(function () {
     var icon = "<i class='fa fa-times-circle'></i> ";
     $("#adminForm").validate({
         submitHandler: function (form) {
            var url = '/admin/add';
             var shopId = $('#shopId').val();
             if(shopId) {
                 url = '/shop/saveShopMag';
             }
             $.post(adminUrl + url, $(form).serialize(), function (response) {
                 if (response.code == 200) {
                     swal({
                         title: "添加成功",
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
        	 account: {
                 required: true,
                 minlength: 4,
                 maxlength: 20,
                 checkAdminAccount: true,
                 checkChinese: true
             },
             name: {
                 required: true,
                 minlength: 2,
                 maxlength: 20
             },
             password: {
                 required: true,
                 minlength: 5,
                 maxlength: 16
             },
             confirm_password: {
                 required: true,
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
        	 account: {
                 checkAdminAccount: icon + "账户已存在",
                 checkChinese: icon + "账户不能为汉字"
             },
             mobile: {
            	 checkMobile: icon + "手机号码格式不正确",
             }
         }
     });
 });