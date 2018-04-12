$(document).ready(function () {
     var icon = "<i class='fa fa-times-circle'></i> ";
     $("#modalForm1").validate({
         rules: {
             name: {
                 required: true,
                 minlength: 2,
                 maxlength: 20
             },
             roleId: {
                 required: true
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
                 required: icon + "请输入账户",
                 minlength: icon + "账户不能少于4个字符",
                 maxlength: icon + "账户不能超过20个字符",
                 checkAdminAccount: icon + "账户已存在"
             },
             name: {
            	 required: icon + "请输入姓名",
            	 minlength: icon + "姓名不能少于2个字符",
                 maxlength: icon + "姓名不能超过20个字符"
             },
             roleId: {
            	 required: icon + "请选择角色",
             },
             mobile: {
            	 checkMobile: icon + "手机号码格式不正确",
             },
             email: {
            	 email: icon + "email格式不正确",
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
	 var closeTabId = home+"/console/resetAdmin"
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

