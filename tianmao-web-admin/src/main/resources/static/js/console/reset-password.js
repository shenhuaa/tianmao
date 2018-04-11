$(document).ready(function () {
     var icon = "<i class='fa fa-times-circle'></i> ";
     $("#modalForm1").validate({
         rules: {
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
             }
         },
         messages: {
             password: {
            	 required: icon + "请输入密码",
                 minlength: icon + "密码不能少于5个字符",
                 maxlength: icon + "密码不能超过16个字符"
             },
             confirm_password: {
            	 required: icon + "请再次输入密码",
                 minlength: icon + "密码必须5个字符以上",
                 equalTo: icon + "两次输入的密码不一致"
             }
         }
     });
     
     $("#closed, #close").click(function(){
    	 close();
     });
 }); 

function subForm(){
	$.ajax({
	    type: 'post',
	    url: home+"/console/reset",
	    data: $("#modalForm1").serialize(),
	    success: function(data) {
	    	 close();
	    },
		errer:null
	});
}

function close(){
	 var closeTabId = home+"/console/resetPassword"
	   //  移除当前选项卡
	 parent.$('.J_menuTab').each(function(){
   	  if ($(this).data('id') == closeTabId) {
             $(this).remove();
         }
    });
 // 移除tab对应的内容区
	 parent.$('.J_mainContent .J_iframe').each(function () {
        if ($(this).data('id') == closeTabId) {
            $(this).remove();
        }
    });
}
