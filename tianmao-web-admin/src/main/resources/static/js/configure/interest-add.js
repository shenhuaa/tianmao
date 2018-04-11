$(document).ready(function() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	var $interestForm = $("#interestForm");
	$interestForm.validate({
		rules : {
			interestName : {
				required : true,
				maxlength : 50,
			},
			interestTypeId : {
				required : true,
			},
			sort : {
				required : true,
				maxlength : 999999,
			}
		},
		messages : {
			interestName : {
				required : icon + "不能为空",
				maxlength : icon + "最多可以输入{0}个字符",
				checkInterestName : icon + "圈子名称已经存在",
			},
			interestTypeId : {
				required : icon + "不能为空",
			},
			sort : {
				required : icon + "不能为空",
				maxlength : icon + "最大值不能超过{0}",
			}
		},
		submitHandler : function(form) {
			alert(home)
			$.post(home + '/configure/interest/add', $interestForm.serialize(), function(response) {
				if(response.code == 200){
					$('#ajaxModal').modal('hide');
					$("#bootstrapTable").bootstrapTable('refresh');
					swal('添加成功', '', 'success');
				}else if(response.code == 500){
					swal(response.message, '', 'error');
				}
			})

		},
	});
});