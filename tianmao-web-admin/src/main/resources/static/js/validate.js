$(document).ready(function(e) {
	$.validator.setDefaults({
	    highlight: function (element) {
	        $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
	    },
	    success: function (element) {
	        element.closest('.form-group').removeClass('has-error').addClass('has-success');
	    },
	    errorElement: "span",
	    errorPlacement: function (error, element) {
	        if (element.is(":radio") || element.is(":checkbox")) {
	            error.appendTo(element.parent().parent().parent());
	        } else {
	            error.appendTo(element.parent());
	        }
	    },
	    errorClass: "help-block m-b-none",
	    validClass: "help-block m-b-none"
	});
	
	jQuery.validator.addMethod("checkAdminAccount", function(value, element) {
		var flag = 1;
		$.ajax({
			type : "get",
			url : home + "/checkAdminAccount",
			async : false,
			data : {
				'account' : value
			},
			success : function(msg) {
				if (msg.status) {
					flag = 0;
				}
			}
		});
		if (flag == 0) {
			return false;
		} else {
			return true;
		}
	}, "账户已存在");
	
	jQuery.validator.addMethod("checkMobile", function(value, element) {
		var reg = /^1\d{10}$/; //定义正则表达式
		if (reg.test(value)||value=="") {
			return true;
		} else {
			return false;
		}
	}, "手机号码格式不正确");
	
	jQuery.validator.addMethod("checkChinese", function(value, element) {
		var reg = /^(?!.*[\u4E00-\u9FA5])(?!.*\?)(?!.*\？)/; //定义不能为汉字的正则
		if (reg.test(value)&&value!="") {
			return true;
		} else {
			return false;
		}
	}, "账户不能为汉字");
});
