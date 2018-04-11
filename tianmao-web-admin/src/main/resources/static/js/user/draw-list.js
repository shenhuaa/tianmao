$(function () {
      $.base.laydate('#beginTime', '#endTime','date');

    $('#beginTime').val(formatDate(Date.parse(new Date()),'yMd'));
    $('#endTime').val(formatDate(Date.parse(new Date()),'yMd'));

    var oTable = new TableInit("/user/draw/page");
    oTable.Init();
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

});

function columnsDefined() {
    return [
        {
            title: '编号',
            field: 'id'
        },
        {
            title: '用户ID',
            field: 'userId'
        },
        {
            title: '用户昵称',
            field: 'user',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value.nickname;
                }
            }
        },
        {
            title: '用户图像',
            field: 'user',
            formatter: function (value, row, index) {
                if (value != null) {
                    return "<img class='img-60x60' src='" + $.base.getImgUrl(value.headImgUrl) + "'>";
                }
            }
        },
        {
            title: '提款人',
            field: 'remittanceAccount',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value.name;
                }
            }
        },
        {
            title: '提款账号(支付宝)',
            field: 'remittanceAccount',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value.accountNumber;
                }
            }
        },
        {
            title: '提现金额(元)',
            field: 'amount',
            sortable: true,
        },
        {
            title: '申请时间',
            field: 'drawTime',
            sortable: true,
            order: 'desc',
            formatter: function (value, row, index) {
                return formatDate(value);
            }
        },
        {
            title: '处理结果',
            field: 'status',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value.remark;
                }
            }
        },
        {
            title : '处理时间',
            field : 'disposeTime',
            sortable : true,
            order : 'desc',
            formatter : function(value, row, index) {
                return formatDate(value);
            }
        },
        {
            title: '备注',
            field: 'remark',
            width: '20%'
        },
        {
            title: '转账处理',
            field: 'status',
            formatter: function (value, row, index) {
                if (value != null) {
                    if (value.index == 0 || value.index == 3) {
                        return getOpt(adminUrl + "/user/draw/edit?userDrawId=" + row.id, "edit", null, "转账处理");
                    }
                }
            }
        }];
};

function exportReport() {
    $("#searchForm").attr("action", "/user/draw/export");
    $("#searchForm").submit();
    $("#searchForm").attr("action", "/user/draw/list");
}
