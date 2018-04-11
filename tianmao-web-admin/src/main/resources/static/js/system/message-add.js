$(document).ready(function () {
     var icon = "<i class='fa fa-times-circle'></i> ";

    //富文本初始化
	KindEditor.create('#kindeditor', {
		uploadJson: adminUrl + '/system/message/kindEditorUpload',
		allowFileManager: true, //浏览图片空间
		imageTabIndex: 1, //点击上传图片按钮默认显示标签，1为本地上传，默认为0网络图片
	});

     $("#modalForm1").validate({
         rules: {
        	 title: {
                 required: true,
                 minlength: 2,
                 maxlength: 100
             },
             details: {
                 required: true,
                 minlength: 10,
                 maxlength: 20000
             }
         },
         messages: {
        	 title: {
            	 required: icon + "请输入消息标题",
            	 minlength: icon + "消息标题不能少于2个字符",
                 maxlength: icon + "消息标题不能超过100个字符"
             },
             details: {
            	 required: icon + "请输入消息内容",
            	 minlength: icon + "消息内容不能少于10个字符",
                 maxlength: icon + "消息内容过长请删减"
             }
         }
     });
     
     changeAdType();
 	$("#adType").change(function() {
 		changeAdType();
 	});
 });

function changeAdType(){
	var adType = $("#adType").val();
	if(adType==''){
		$("#adDiv1").hide();
		$("#adDiv2").hide();
		$("#adDiv3").hide();
	}else if (adType == 13) {
		$("#adDiv1").hide();
		$("#adDiv2").hide();
		$("#adDiv3").show();
	} else if(adType == 0) {
		$("#adDiv1").hide();
		$("#adDiv2").show();
		$("#adDiv3").hide();
	}else{
		$("#adDiv1").show();
		$("#adDiv2").hide();
		$("#adDiv3").hide	();
	}	
}