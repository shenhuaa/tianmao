;(function ($) {
    var role = {
        //初始化
        init: function () {
            this.initBootstrapTable();
            this.initBootstrapTableOn();
        },
        //初始化bootstrapTable
        initBootstrapTable: function () {
            var oTable = new TableInit("/role/page");
            oTable.Init();
            var oButtonInit = new ButtonInit();
            oButtonInit.Init();
        },
        initBootstrapTableOn: function () {
            $('#bootstrapTable').on('load-success.bs.table toggle.bs.table column-switch.bs.table', function (e, name, args) {
                switchStatus("/role/changeStatus");
            });
        }
    };

    role.init();

})(jQuery);

function columnsDefined() {
    return [{
        title: '编号',
        field: 'id',
        align: 'center',
        order: 'desc'
    }, {
        title: '角色名称',
        field: 'name',
        align: 'center',
    }, {
        title: '备注',
        field: 'remark',
        align: 'center',
    }, {
        title: '创建时间',
        field: 'addTime',
        align: 'center',
        formatter: function (value, row, index) {
            return formatDate(value);
        }
    }, {
        title: '操作',
        field: 'opt',
        align: 'center',
        formatter: function (value, row, index) {
            var opt = getOpt(adminUrl + "/role/edit?roleId=" + row.id, "edit");
            return opt;
        }
    }];
};

