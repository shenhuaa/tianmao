;(function ($) {
    var car = {
        //初始化
        init: function () {
            this.initBootstrapTable();
            this.initBootstrapTableOn();
        },
        //初始化bootstrapTable
        initBootstrapTable: function () {
            var oTable = new TableInit("/admin/page");
            oTable.Init();
            var oButtonInit = new ButtonInit();
            oButtonInit.Init();
        },
        //点击事件
        initBootstrapTableOn: function () {
            $('#bootstrapTable').on('load-success.bs.table toggle.bs.table column-switch.bs.table', function (e, name, args) {
                switchStatus("/admin/update/status");
            });
        }
    };

    car.init();
})(jQuery)

function columnsDefined() {
    return [{
        title: '编号',
        field: 'id',
        align: 'center',
        order: 'desc'
    }, {
        title: '账户',
        field: 'username',
        align: 'center',
    }, {
        title: '姓名',
        field: 'nickname',
        align: 'center',
    }, {
        title: '角色名称',
        field: 'roleName',
        align: 'center',
    }, {
        title: '手机号码',
        field: 'mobile',
        align: 'center',
        formatter: function (value, row, index) {
            if (value == "") {
                return "-"
            }
            return value;
        }
    }, {
        title: '邮箱',
        field: 'email',
        align: 'center',
        formatter: function (value, row, index) {
            if (value == "") {
                return "-"
            }
            return value;
        }
    }, {
        title: '添加时间',
        field: 'addTime',
        align: 'center',
        order: 'desc',
        formatter: function (value, row, index) {
            return formatDate(value);
        }
    }, {
        title: '最后登录时间',
        field: 'lastLoginTime',
        align: 'center',
        order: 'desc',
        formatter: function (value, row, index) {
            return formatDate(value);
        }
    }, {
        title: '状态(冻结/正常)',
        field: 'status',
        align: 'center',
        formatter: function (value, row, index) {
            return getStatusCheckBox(row.id, value.index);
        }
    }, {
        title: '操作',
        field: 'opt',
        align: 'center',
        formatter: function (value, row, index) {
            var opt = getOpt(adminUrl + "/admin/update?adminId=" + row.id, "edit");
            return opt;
        }
    }];
};

