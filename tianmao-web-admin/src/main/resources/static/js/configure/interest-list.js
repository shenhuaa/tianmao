$(function() {

	var oTable = new TableInit("/configure/interest/page");
	oTable.Init();
	var oButtonInit = new ButtonInit();
	oButtonInit.Init();
	
	

});
function columnsDefined() {
	return [
			{
				title : '圈子名称',
				field : 'interestName'
			},
			{
				title : '圈子类型名称',
				field : 'typeName',
			},
			{
				title : '排序',
				field : 'sort',
			},
			{
				title : '操作',
				field : 'opt',
				align : 'center',
				formatter : function(value, row, index) {
					var opt = getOpt(home + "/user/red/list?status=2&userId=" + row.id, "edit", row.nickname + "修改", "修改");
					opt += getOpt(home + "/user/edit?userId=" + row.id, "edit", null, "设为推荐");
					opt += getOpt(home + "/user/ratioEdit?userId=" + row.id, "delete", null, "删除");
					return opt;
				}
			} ];
};

 