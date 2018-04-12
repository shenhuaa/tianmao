$(function () {
    var oTable = new TableInit("/system/message/page");
    oTable.Init();
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

});
function columnsDefined() {
	return [
	        {
				title : '消息编号',
				field : 'id',
				align : 'center'
			},
			{
				title : '消息标题',
				field : 'title',
				align : 'center'
			},
			{
				title : '消息内容',
				field : 'details',
				align : 'center'
			},
			{
				title : '类型',
				field : 'adType',
				formatter : function(value, row, index) {
					if(value!=null){
						return value.remark;
					}
				}
			},
			/*{
				title : '跳转ID',
				field : 'outerId'
			},*/
			{
				title : '发送时间',
				field : 'addTime',
				formatter : function(value, row, index) {
					return formatDate(value);
				}
			} ];
};
