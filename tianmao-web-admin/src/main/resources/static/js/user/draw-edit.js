$(document).ready(function() {
	if("${model.status}" == 2){
		$("#bhDvi").show();
	}
	
	$("#status").change(function() {
		if($(this).val()==2){
			$("#bhDvi").show();
		}else{
			$("#bhDvi").hide();
			$("#remark").val("");
		}
	});
	
});

function saveDraw(){
	$.post(adminUrl + '/user/draw/edit', $("#modalForm1").serialize(), function(result) {
		if(result){
			$('#ajaxModal').modal('hide');
			$("#bootstrapTable").bootstrapTable('refresh');
		}else{
			alert("修改失败");

		}
	})
}