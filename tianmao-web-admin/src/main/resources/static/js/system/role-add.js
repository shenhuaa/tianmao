;(function ($) {
    var $roleTree;
    var role = {
        //初始化
        init: function () {
            this.initJstree();
            this.initRoleValidform();
        },
        initJstree: function () {
            $roleTree = $('#roleTree').jstree({
                'core': {'themes': {'responsive': true}},
                'types': {
                    'default': {'icon': 'fa fa-folder icon-state-info icon-md'},
                    'file': {'icon': 'fa fa-file icon-state-default icon-md'}
                },
                'plugins': ['state', 'types', 'checkbox']
            }).jstree(true);

            $.get(adminUrl + '/role/jstree/list', function (result) {
                $roleTree.settings.core.data = result.data.jsTrees;
                $roleTree.refresh();
            }).fail(function () {
                alert('未知错误，请稍后再试。');
            })
        },
        initRoleValidform: function () {
            $("#roleForm").validate({
                submitHandler: function (form) {
                    var permissionIds = [];
                    var selected = $roleTree.get_selected(true); // 获取选择中的
                    $.each(selected, function (i, item) {
                        var index = $.inArray(item.id, permissionIds);
                        if (-1 == index) {
                            permissionIds.push(item.id);
                        }
                        var parentId = item.parent;
                        if ('#' != parentId) {
                            var index = $.inArray(parentId, permissionIds);
                            if (-1 == index) {//添加父级权限
                                permissionIds.push(parentId);
                            }
                        }
                    });
                    $('#permissionId').val(permissionIds.join(','));

                    $.post(adminUrl + '/role/update', $(form).serialize(), function (response) {
                        if (response.code == 200) {
                            swal({
                                title: "添加成功",
                                type: "success",
                            }, function (isConfirm) {
                                window.location.reload();
                            });
                        } else {
                            swal(response.message, '', 'warning');
                        }
                    })
                },
                rules: {
                    name: {
                        required: true,
                        minlength: 2,
                        maxlength: 20
                    },
                },
                messages: {
                    name: {
                        checkMobile: $.base.icon + "角色名字不能为空",
                    }
                }
            });

        }
    };

    role.init();

})(jQuery);

