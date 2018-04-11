$(function () {
    $('#beginTime').val(formatDate(Date.parse(new Date()), 'yMd'));
    $('#endTime').val();
    $.base.laydate('#beginTime', '#endTime', 'date');
    var oTable = new TableInit("/user/earnestPage");
    oTable.Init();
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

    $('#bootstrapTable').on('load-success.bs.table toggle.bs.table column-switch.bs.table', function (e, name, args) {
        switchStatus("/user/statusEdit");
    });
});

function columnsDefined() {
    return [{
        title: '用户id',
        field: 'id',
        align: 'center',
    }, {
        title: '手机号',
        field: 'mobile',
        align: 'center',
    }, {
        title: '用户昵称',
        field: 'nickname',
        align: 'center',
    }, {
        title: '用户图像',
        field: 'headImgUrl',
        align: 'center',
        formatter: function (value, row, index) {
            return "<img class='img-60x60' src='" + $.base.getImgUrl(value) + "'>";
        }
    }, {
        title: '余额(元)',
        field: 'balance',
        align: 'center',
    }, {
        title: '被打赏数',
        field: 'rewardNum',
        align: 'center',
    }, {
        title: '打赏所得',
        field: 'rewardAmount',
        align: 'center'
    }, {
        title: '获得红包',
        field: 'redNum',
        align: 'center',
    }, {
        title: '红包所得',
        field: 'redAmount',
        align: 'center'
    }, {
        title: '红包获取比例(%)',
        field: 'ratio',
        align: 'center'
    }, {
        title: '收款人',
        field: 'realName',
        align: 'center',
    }, {
        title: '状态（是否冻结）',
        field: 'status.index',
        align: 'center',
        formatter: function (value, row, index) {
            return getStatusCheckBox(row.id, value);
        }
    }, {
        title: 'openId',
        field: 'openId',
        align: 'center'
    }, {
        title: '是否认证',
        field: 'earnest',
        align: 'center',
        formatter: function (value, row, index) {
            if (typeof(row.openId)=="undefined") {
                return "未认证";
            } else {
                return "已认证";
            }
        }
    }, {
        title: '认证时间',
        field: 'regTime',
        align: 'center',
        formatter: function (value, row, index) {
            if (typeof(row.openId)=="undefined") {
                return "";
            } else {
                return formatDate(value);
            }

        }
    }, /*{
        title: '操作',
        field: 'opt',
        align: 'center',
        formatter: function (value, row, index) {
            var opt = getOpt(adminUrl + "/user/red/list?status=2&userId=" + row.id, "detail", row.nickname + "红包领取明细", "红包领取明细");
            opt += getOpt(adminUrl + "/user/edit?userId=" + row.id, "edit", null, "添加余额");
            opt += getOpt(adminUrl + "/user/ratioEdit?userId=" + row.id, "edit", null, "修改获取比例");
            return opt;
        }
    }*/];
};


