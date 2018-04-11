$(function () {
    var advert = {
        //初始化
        init: function () {
            this.initBootstrapTable();
            this.initDelete();
        },
        //初始化bootstrapTable
        initBootstrapTable: function () {
            var oTable = new TableInit("/advert/page");
            oTable.Init();
            var oButtonInit = new ButtonInit();
            oButtonInit.Init();
        },
        initAdd: function () {

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
                        $.post(adminUrl + '/advert/delete', {
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

    advert.init();
})

function columnsDefined() {
    return [{
        title: '广告图描述',
        field: 'description',
        align: 'center',
    }, {
        title: '广告图位置',
        field: 'position',
        align: 'center',
        formatter: function (value, row, index) {
            return value ? value.remark : '-';
        }
    }, {
        title: '广告图类型',
        field: 'advertType',
        align: 'center',
        formatter: function (value, row, index) {
            return value ? value.remark : '-';
        }
    } ,{
        title: '微信端',
        field: 'wechat',
        align: 'center',
        formatter: function (value, row, index) {
            if (value) {
                return '<img src="' + imageUrl + value + '" class="img-60x60">'
            }
            return '<img src="/static/image/default_180x180.png" class="img-60x60">'
        }
    }, {
        title: 'APP端',
        field: 'app',
        align: 'center',
        formatter: function (value, row, index) {
            if (value) {
                var ImgUrl = $.base.getImgUrl(value);
                return '<img src="' + ImgUrl + '" class="img-60x60">'
            }
            return '<img src="/static/image/default_180x180.png" class="img-60x60">'
        }
    }, {
        title: '电脑端',
        field: 'pc',
        align: 'center',
        formatter: function (value, row, index) {
            if (value) {
                return '<img src="' + imageUrl + value + '" class="img-60x60">'
            }
            return '<img src="/static/image/default_180x180.png" class="img-60x60">'
        }
    }, {
        title: '操作',
        field: 'opt',
        align: 'center',
        formatter: function (value, row, index) {
            var html = [
                '<a href="' + adminUrl + '/advert/update?id=' + row.id + '" data-toggle="ajaxModal" class="btn btn-white btn-sm">',
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

 