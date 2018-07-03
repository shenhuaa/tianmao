$().ready(function() {
	$(".iptphone").keyup(function() {
		var reg = /^1\d{10}$/; //定义正则表达式
		if(reg.test($(this).val())) {
			$("#tAccount").text("");
			$(".takey").removeAttr("disabled");
			$(".takey").addClass("on");
		} else {
			if($(this).val()==""){
				$("#tAccount").text("*请输入手机号！");
			}else{
				$("#tAccount").text("*手机号码不正确！");
			}
			$(".takey").attr("disabled","disabled");
			$(".takey").removeClass("on");
		}
		displaySubmit()
	});
	
	$("#Verfication").keyup(function() {
		if($(this).val()!=""){
			$("#tVerfication").text("");
		}else{
			$("#tVerfication").text("*请输入验证码！");
		}
		displaySubmit()
	});
	
	
	$("#Password").keyup(function() {
		var reg = /^[_a-zA-Z\d]{6,16}$/;
		if(reg.test($(this).val())) {
			$("#tPassword").text("");
		} else {
			if($(this).val()!=""){
				$("#tPassword").text("*请输入6-16位字母、数字、_！");
			}else{
				$("#tPassword").text("*请输入密码！");
			}
		}
		var PasswordS = $("#PasswordS").val();
		if(PasswordS!=""){
			if(PasswordS!=$(this).val()){
				$("#tPasswordS").text("*输入的密码不一致");
			}else{
				$("#tPasswordS").text("");
			}
		}
		displaySubmit()
	});
	
	$("#PasswordS").keyup(function() {
		var password = $("#Password").val();
		if($(this).val()== password) {
			$("#tPasswordS").text("");
		} else {
			if($(this).val()!=""){
				$("#tPasswordS").text("*输入的密码不一致");
			}else{
				$("#tPasswordS").text("*请再次确认密码！");
			}
		}
		displaySubmit()
	});
	

	
	function displaySubmit(){
		var agree = $(this).find(".agreeicon").hasClass("agreeiconed")
		var tAccount = $("#tAccount").text();
		var tVerfication = $("#tVerfication").text();
		var tPassword = $("#tPassword").text();
		var tPasswordS = $("#tPasswordS").text();
		if(!agree && tAccount=="" && tVerfication=="" && tPassword=="" && tPasswordS==""){
			$(".actbtn").addClass("on");
			$(".actbtn").removeAttr("disabled");
		}else{
			$(".actbtn").removeClass("on");
			$(".actbtn").attr("disabled","disabled");
		}
	}
	
	
	$(".agree").click(function() {
		if($(this).find(".agreeicon").hasClass("agreeiconed")) {
			$(this).find(".agreeicon").removeClass("agreeiconed");
			$(".actbtn").removeClass("on");
			$(".actbtn").attr("disabled","disabled");
		} else {
			$(this).find(".agreeicon").addClass("agreeiconed");
			var agree = $(this).find(".agreeicon").hasClass("agreeiconed")
			var tAccount = $("#tAccount").text();
			var tVerfication = $("#tVerfication").text();
			var tPassword = $("#tPassword").text();
			var tPasswordS = $("#tPasswordS").text();
			if(tAccount=="" && tVerfication=="" && tPassword=="" && tPasswordS==""){
				$(".actbtn").addClass("on");
				$(".actbtn").removeAttr("disabled");
			}
		}
	});
	var clock = "";
	var nums = 60;
	$(".takey").click(function() {
		$.ajax({
			url : home+ 'com.tianmao.common/sendSms',
			method : 'get',
			data : {
				mobile : $("#Account").val(),
				type : 1,
				sign : $("#v").val()
			},
			dataType : 'json',
			success : function(result) {
				if (result!=null) {
					if(result.StatueCode==1){
						if($(this).hasClass("on")){
							$(this).attr("disabled", "disabled").text(nums + "s<span>重新获取</span>");
							clock = setInterval(function(){	
								nums--;
								if(nums > 0) {
									$(".takey").text(nums + "s<span>重新获取</span>");
								}else{
									clearInterval(clock);
									$(".takey").removeAttr("disabled")
									$(".takey").text('点击获取验证码');
									nums = 60;
								}
							},1000);
						}
					}else if(result.StatueCode==2){
						$("#msg").text("您注册的账号已被注册");
						$(".boom").show();
					}
				}else{
					$("#msg").text("获取验证码失败");
					$(".boom").show();
				}
			}
		});
		
	});
	$(".actbtn").click(function(){
		var agree = $(this).find(".agreeicon").hasClass("agreeiconed")
		var tAccount = $("#tAccount").text();
		var tVerfication = $("#tVerfication").text();
		var tPassword = $("#tPassword").text();
		var tPasswordS = $("#tPasswordS").text();
		
		if(!agree && tAccount=="" && tVerfication=="" && tPassword=="" && tPasswordS==""){
			$.ajax({
				url : home+ 'com.tianmao.common/register',
				method : 'post',
				data : {
					mobile : $("#Account").val(),
					verfication : $("#Verfication").val(),
					password : $("#Password").val(),
					confirmPassword :  $("#PasswordS").val(),
					code : $("#code").val()
				},
				dataType : 'json',
				success : function(result) {
					if(result!=null){
						if(result.StatueCode==1){
							$("#msg").text("注册成功");
							$(".boom").show();
						}else if(result.StatueCode==2){
							$("#msg").text("您注册的账号已被注册");
							$(".boom").show();
						}else if(result.StatueCode==3){
							$("#msg").text("验证码错误");
							$(".boom").show();
						}else{
							$("#msg").text("注册失败");
							$(".boom").show();
						}
					}else{
						$("#msg").text("注册失败");
						$(".boom").show();
					}
				}
			});
		}
	});
	$(".optionbtn").click(function(){
		$(this).parents(".boom").hide();
	});
})

var u = navigator.userAgent;
var isAndroid = false,isIos = false;
if (u.indexOf('Android') > -1 || u.indexOf('Linux') > -1) {//安卓手机
	isAndroid = true;
} else if (u.indexOf('iPhone') > -1) {//苹果手机
	isIos = true;
}

function dd(){
	if(isAndroid == true){
		window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.carsland.cld";
	}else{
		window.location.href="https://itunes.apple.com/us/app/%E7%8E%A9%E8%BD%A6%E4%B9%90%E5%9B%AD-%E5%88%86%E4%BA%AB%E4%B8%8E%E8%BD%A6%E7%9B%B8%E5%85%B3%E7%9A%84%E4%B9%90%E5%9B%AD/id1214258270?l=zh&ls=1&mt=8";
	}
}