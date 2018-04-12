$(function () {
    $('#beginTime').val(formatDate(Date.parse(new Date()), 'yMd'));
    $('#endTime').val();
    $.base.laydate('#beginTime', '#endTime', 'date');
    var oTable = new TableInit("/user/page");
    oTable.Init();
    var oButtonInit = new ButtonInit();
    oButtonInit.Init();

    $('#bootstrapTable').on('load-success.bs.table toggle.bs.table column-switch.bs.table', function (e, name, args) {
        switchStatus("/user/statusEdit");
    });


    //修改用户资料
    $(document).on('click', '#update', function () {
        var arr = $("#bootstrapTable").bootstrapTable('getSelections');
        if (arr.length != 1) {
            swal('请选择一条数据进行操作', '', 'warning');
            return;
        }
        var userId = JSON.parse(JSON.stringify(arr[0]))['id'];
        $.post(adminUrl + "/user/update", {userId: userId}, function (date) {
            $("#id").val(userId)
            $("#nickname").val(date.nickname);
            $("#mobileTwo").val(date.mobile);
            $("#province").val(date.province);
            $("#city").val(date.city);
            var optionValue = date.sex.index;
            var identityType = date.identityType.index;
            $("#select").find("option[value = '" + optionValue + "']").attr("selected", true);
            $("#selectRed").find("option[value = '" + identityType + "']").attr("selected", true);
        })
        $('#myModal').modal({
            keyboard: true
        })
    });
   /* //关闭模态框
    $("#updateButton").click(function () {
        $("#select").find("option[value = '" + optionValue + "']").attr("selected", false);
        $("#selectRed").find("option[value = '" + identityType + "']").attr("selected", false);
    })
    $(".close").click(function () {
        $("#select").find("option[value = '" + optionValue + "']").attr("selected", false);
        $("#selectRed").find("option[value = '" + identityType + "']").attr("selected", false);
    })*/

    $("#updateUserForm").validate({
        submitHandler: function (form) {
            $.post(adminUrl + '/user/save/update', $(form).serialize(), function (response) {
                if (response.code == 200) {
                    $("#bootstrapTable").bootstrapTable('refresh');
                    swal('修改成功', '', 'success');
                } else {
                    swal(response.message, '', 'warning');
                }
            })
        },
        rules: {
            nickname: {
                required: true,
                minlength: 2,
                maxlength: 20
            },
            password: {
                required: true,
                minlength: 5,
                maxlength: 16
            },
            passwordSalt: {
                required: true,
                minlength: 5,
                maxlength: 16,
                equalTo: "#password"
            },
            mobileTwo: {
                checkMobile: true,
            },

        },
        messages: {
            nickname: {
                required: icon + "昵称不能为空",
                minlength: icon + "昵称长度不能小于2",
                maxlength: icon + "昵称长度不能大于20",
            },
            password: {
                required: icon + "密码不能为空",
                minlength: 5,
                maxlength: 16
            },
            mobileTwo: {
                checkMobile: icon + "手机号码格式不正确",
            },
        }
    });
});

function columnsDefined() {
    return [
        {
            radio: true
        },
        {
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
            title: '注册时间',
            field: 'regTime',
            align: 'center',
            formatter: function (value, row, index) {
                return formatDate(value);
            }
        }, {
            title: 'openId',
            field: 'openId',
            align: 'center'
        }, {
            title: '操作',
            field: 'opt',
            align: 'center',
            formatter: function (value, row, index) {
                var opt = getOpt(adminUrl + "/user/red/list?status=2&userId=" + row.id, "detail", row.nickname + "红包领取明细", "红包领取明细");
                opt += getOpt(adminUrl + "/user/edit?userId=" + row.id, "edit", null, "添加余额");
                opt += getOpt(adminUrl + "/user/ratioEdit?userId=" + row.id, "edit", null, "修改获取比例");
                opt += getOpt(adminUrl + "/user/postComment/list?userId=" + row.id, "detail", row.nickname + "评论列表", "评论管理");
                return opt;
            }
        }];
};


