$(function () {
    var circleInterest = {
        //初始化
        init: function () {
            this.initBootstrapTable();
            this.initDelete();
            this.initRecommend();
        },
        //初始化bootstrapTable
        initBootstrapTable: function () {
            var oTable = new TableInit("/circle/interest/page");
            oTable.Init();
            var oButtonInit = new ButtonInit();
            oButtonInit.Init();
        },
        //删除
        initDelete: function () {
            $(document).on('click', '.deleted', function () {
                var id = $(this).data('id');
                swal({
                    title: "您确定要操作吗",
                    text: "请谨慎操作！",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "是的，我要操作！",
                    cancelButtonText: "让我再考虑一下…",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        $.post(adminUrl + '/circle/interest/delete', {
                            id: id,
                        }, function (response) {
                            if (response.code == 200) {
                                swal('删除成功', '', 'success');
                                $("#bootstrapTable").bootstrapTable('refresh');
                            } else {
                                swal(response.message, '', 'warning');
                            }
                        })
                    } else {
                        swal('已取消', '', 'info');
                    }
                })
            });
        },
        //推荐事件
        initRecommend: function () {
            $(document).on('click', '.recommend', function () {
                var id = $(this).data('id'),
                    recommend = $(this).data('recommend'),
                    msg = $(this).text() + '成功';
                swal({
                    title: "您确定要操作吗",
                    text: "请谨慎操作！",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "是的，我要操作！",
                    cancelButtonText: "让我再考虑一下…",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {
                        if (recommend == 0) {
                            recommend = 1;
                        } else if (recommend == 1) {
                            recommend = 0;
                        }
                        $.post(adminUrl + '/circle/interest/recommend', {
                            id: id,
                            recommend: recommend
                        }, function (response) {
                            if (response.code == 200) {
                                swal(msg, '', 'success');
                                $("#bootstrapTable").bootstrapTable('refresh');
                            } else {
                                swal(response.message, '', 'warning');
                            }
                        })
                    } else {
                        swal('已取消', '', 'info');
                    }
                })
            });
        }
    };

    circleInterest.init();
})

function columnsDefined() {
    return [{
        title: '兴趣圈名称',
        field: 'name',
        align: 'center',
    }, {
        title: '是否设为推荐',
        field: 'recommend',
        align: 'center',
        formatter: function (value, row, index) {
            return value == 1 ? ' 是' : '否';
        }
    }, {
        title: '排序',
        field: 'sort',
        align: 'center',
    }, {
        title: '操作',
        field: 'opt',
        align: 'center',
        formatter: function (value, row, index) {
            var recommendName = row.recommend == 1 ? "取消推荐" :"设为推荐";
                html = [
                    '<a href="' + adminUrl + '/circle/interest/update?id=' + row.id + '" data-toggle="ajaxModal" class="btn btn-white btn-sm">',
                    '   <i class="fa fa-pencil" aria-hidden="true">修改</i>',
                    '</a>',
                    '<button data-id="' + row.id + '" data-recommend="' + row.recommend + '"class="recommend btn btn-white btn-sm">',
                    '   <i class="fa fa-pencil"></i>'+ recommendName,
                    '</button>',
                    '<button data-id="' + row.id + '" data-deleted="' + row.deleted + '"class="deleted btn btn-white btn-sm">',
                    '   <i class="fa fa-pencil"></i> 删除',
                    '</button>'
                ].join('');
            if(row.id==1||row.name=="全部"){
                return "";
            }else{
                return html;
            }
        }
    }];
};

 