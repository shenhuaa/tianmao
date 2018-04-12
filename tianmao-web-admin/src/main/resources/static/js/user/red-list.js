$(function () {
      $.base.laydate('#beginTime', '#endTime','date');

    $('#beginTime').val(formatDate(Date.parse(new Date()),'yMd'));
    $('#endTime').val(formatDate(Date.parse(new Date()),'yMd'));

    var oTable = new TableInit("/user/red/page");
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
                    var headImgUrl = $.base.getImgUrl(row.user.headImgUrl);
                    return "<img class='img-60x60' src='" + headImgUrl + "'>";
                }
                return '<img src="/static/image/default_180x180.png" class="img-60x60">'
            }
        },
        {
            title: '红包编号',
            field: 'redId'
        },
        {
            title: '红包类型',
            field: 'categoryName'
        },
        {
            title: '金额(元)',
            field: 'amount',
        },
        {
            title: 'IP地址',
            field: 'ip',
        },
        {
            title: '发放时间',
            order: 'desc',
            field: 'addTime',
            formatter: function (value, row, index) {
                return formatDate(value);
            }
        },
        {
            title: '领取时间',
            order: 'desc',
            field: 'receiveTime',
            formatter: function (value, row, index) {
                if (value != null) {
                    return formatDate(value);
                }
            }
        },
        {
            title: '状态',
            field: 'status',
            formatter: function (value, row, index) {
                return value.remark;
            }
        }];
};