$(function () {
    var comment = {
        //初始化
        init: function () {
            this.initLaydate();
            this.initBootstrapTable();
        },
        //初始化时间插件
        initLaydate: function () {
            $.base.laydate('#beginTime', '#endTime', 'date');

            if ($('#beginTime').val() == '') {
                $('#beginTime').val(formatDate(Date.parse(new Date()), 'yMd'));
            }
            if ($('#endTime').val() == '') {
                $('#endTime').val(formatDate(Date.parse(new Date()), 'yMd'));
            }
        },
        //初始化bootstrapTable
        initBootstrapTable: function () {
            var oTable = new TableInit("/user/postComment/page");
            oTable.Init();
            var oButtonInit = new ButtonInit();
            oButtonInit.Init();
        },
    }
    comment.init();
    //导出
    $('#export').click(function () {
        var parameter = $('#searchForm').serialize();
        window.location.href = adminUrl + '/user/postComment/export?' + parameter;
    });
});
function columnsDefined() {
    return [
        {
            title: '文章id',
            field: 'id',
            align: 'center',
        }, {
            title: '评论时间',
            field: 'commentTime',
            align: 'center',
            formatter: function (value, row, index) {
                return formatDate(value);
            }
        }, {
            title: '评论内容',
            field: 'content',
            align: 'center',
        }, {
            title: '点赞数',
            field: 'likeNum',
            align: 'center',
        }, {
            title: '评论楼层',
            field: 'floor',
            align: 'center'
        }];
};


