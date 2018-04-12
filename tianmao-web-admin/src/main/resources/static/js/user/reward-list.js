$(function () {
    var reward = {
        //初始化
        init: function () {
            this.initLaydate();
            this.initBootstrapTable();
        },
        //初始化时间插件
        initLaydate: function () {
              $.base.laydate('#beginTime', '#endTime','date');

            if ($('#beginTime').val() == '') {
                $('#beginTime').val(formatDate(Date.parse(new Date()),'yMd'));
            }
            if ($('#endTime').val() == '') {
                $('#endTime').val(formatDate(Date.parse(new Date()),'yMd'));
            }
        },
        //初始化bootstrapTable
        initBootstrapTable: function () {
            var oTable = new TableInit("/user/reward/page");
            oTable.Init();
            var oButtonInit = new ButtonInit();
            oButtonInit.Init();
        },
        //表单提交验证
        initLoginFormValidate: function () {
            $("#balanceForm").validate({
                submitHandler: function (form) {
                    $.post(adminUrl + '/user/edit', $(form).serialize(), function (response) {
                        if (response.code == 200) {
                            $('#ajaxModal').modal('hide');
                            $("#bootstrapTable").bootstrapTable('refresh');
                            swal('修改成功', '', 'success');
                        } else {
                            swal(response.message, '', 'warning');
                        }
                    })
                },
                rules: {
                    balance: {
                        required: true,
                    }
                },
                messages: {
                    balance: {
                        required: icon + "请输入一个数字"
                    }
                }
            });
        },
    }

    reward.init();
});

function columnsDefined() {
    return [
        {
            title: '编号',
            field: 'id',
            align: 'center',
        }, {
            title: '用户',
            field: 'userId',
            align: 'center',
        }, {
            title: '用户昵称',
            field: 'user',
            align: 'center',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value.nickname;
                }
            }
        }, {
            title: '打赏用户ID',
            field: 'rewardId',
            align: 'center',
        }, {
            title: '打赏用户昵称',
            field: 'rewardUser',
            align: 'center',
            formatter: function (value, row, index) {
                if (value != null) {
                    return value.nickname;
                }
            }
        }, {
            title: '文章ID',
            field: 'postId'
        }, {
            title: '文章标题',
            field: 'post',
            align: 'center',
            formatter: function (value, row, index) {
                if(value!=null) {
                    return getOpt(adminUrl + "/user/reward/list?postId=" + row.postId + "&startTime=" + $("#beginTime").val() + "&endTime=" + $("#endTime").val(), "", value.title + "打赏列表", value.title);
                }
               // if(value!=null){
               //     return getOpt(adminUrl + "/post/detail?postId=" + row.postId , "detail", value.title + "文章详情", value.title);
               // }
            }
        }, {
            title: '打赏金额(元)',
            field: 'amount',
            align: 'center',
        }, {
            title: '打赏时间',
            field: 'addTime',
            order: 'desc',
            formatter: function (value, row, index) {
                return formatDate(value);
            }
        }];
};
