$().ready(function() {
	$(".iptphone").change(function() {
		if($(this).val() != "") {
			$(".takey").addClass("on");
		} else {
			$(".takey").removeClass("on");
		}
	});
	$(".agree").click(function() {
		if($(this).find(".agreeicon").hasClass("agreeiconed")) {
			$(this).find(".agreeicon").removeClass("agreeiconed");
			$(".actbtn").removeClass("on");
		} else {
			$(this).find(".agreeicon").addClass("agreeiconed");
			$(".actbtn").addClass("on");
		}
	});
	var clock = "";
	var nums = 60;
	$(".takey").click(function() {
		if($(this).hasClass("on")){
			$(this).attr("disabled", "disabled").html(nums + "s<span>重新获取</span>");
			clock = setInterval(function(){	
				nums--;
				if(nums > 0) {
					$(".takey").html(nums + "s<span>重新获取</span>");
				}else{
					clearInterval(clock);
					$(".takey").removeAttr("disabled")
					$(".takey").html('点击获取验证码');
					nums = 60;
				}
			},1000);
		}
	});
	$(".actbtn").click(function(){
		$(".boom").show();
	});
	$(".optionbtn").click(function(){
		$(this).parents(".boom").hide();
	});
})