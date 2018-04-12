$(function () {
    var auditSwitch = {
        //初始化
        init: function () {
            this.initBootstrapTable();
            this.initUpdateSwitchStatus();
        },
        //初始化bootstrapTable
        initBootstrapTable: function () {
            var oTable = new TableInit("/audit/switch/page");
            oTable.Init();
            var oButtonInit = new ButtonInit();
            oButtonInit.Init();
        },
        //修改开关状态
        initUpdateSwitchStatus: function () {
            $(document).on('click', '.update', function () {
                var id = $(this).data('id'),
                    auditSwitch = $(this).data('post-switch');
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
                        if (auditSwitch == 0) {
                            auditSwitch = 1;
                        } else if (auditSwitch == 1) {
                            auditSwitch = 0;
                        }
                        $.post(adminUrl + '/audit/switch/update', {
                            id: id,
                            auditSwitch: auditSwitch,
                        }, function (response) {
                            if (response.code == 200) {
                                swal('修改成功', '', 'success');
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

    auditSwitch.init();
})

function columnsDefined() {
    return [{
        title: '类型',
        field: 'type',
        align: 'center',
        formatter: function (value, row, index) {
            if (value) {
                return value.remark;
            }
        }
    }, {
        title: '帖子开关',
        field: 'stutas',
        align: 'center',
        formatter: function (value, row, index) {
            if (value == 1) {
                return '开-需要审核';
            } else {
                return '关-无需审核';
            }
        }
    }, {
        title: '修改时间',
        field: 'updateTime',
        align: 'center',
        formatter: function (value, row, index) {
            return formatDate(value);
        }
    }, {
        title: '操作',
        field: 'opt',
        align: 'center',
        formatter: function (value, row, index) {
            var html = [
                '<button data-id="' + row.id + '" data-post-switch="' + row.type.index + '"class="update btn btn-white btn-sm">',
                '   <i class="fa fa-pencil"></i> 修改开关',
                '</button>'
            ].join('');
            return html;
        }
    }];
};

 