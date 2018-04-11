$(function () {
    var goldIntegralRule = {
        //初始化
        init: function () {
            this.initBootstrapTable();
            this.initDelete();
        },
        //初始化bootstrapTable
        initBootstrapTable: function () {
            var oTable = new TableInit("/gold/integral/rule/page");
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
                        $.post(adminUrl + '/gold/integral/rule/delete', {
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
    };

    goldIntegralRule.init();
})

function columnsDefined() {
    return [{
        title: '规则名称',
        field: 'ruleName',
        align: 'center',
    }, {
        title: '获得金币',
        field: 'gold',
        align: 'center',
    }, {
        title: '获得积分',
        field: 'integral',
        align: 'center',
    }, {
        title: '操作',
        field: 'opt',
        align: 'center',
        formatter: function (value, row, index) {
                var html = [
                    '<a href="' + adminUrl + '/gold/integral/rule/update?id=' + row.id + '" data-toggle="ajaxModal" class="btn btn-white btn-sm">',
                    '   <i class="fa fa-pencil" aria-hidden="true">修改</i>',
                    '</a>',
                    '<button data-id="' + row.id + '" data-deleted="' + row.deleted + '"class="deleted btn btn-white btn-sm">',
                    '   <i class="fa fa-pencil"></i> 删除',
                    '</button>'
                ].join('');
            return html;
        }
    }];
};

 