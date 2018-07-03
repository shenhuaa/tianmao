;(function ($) {
    var timeDateComparison;
    $.base = {
        ico: '<i class="fa fa-times-circle"></i>',
        _init: function () {
            this.initCloseTab();
            this.initIsNumberKeyUp();
            this.initAddTab();
        },
        //获取图片地址
        getImgUrl: function (imgUrl) {
            if (!$.trim(imgUrl)) {
                return "";
            }
            if (imgUrl.indexOf('http://') != -1 || imgUrl.indexOf('https://') != -1) {
                return imgUrl;
            }
            return imageUrl + imgUrl;
        },
        //添加手机验证规则
        addMobileMethod: function () {
            $.validator.addMethod('isMobile', function (value, element) {
                var length = value.length;
                var mobile = /^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[57])[0-9]{8}$/;
                return this.optional(element) || (length == 11 && mobile.test(value));
            });
        },
        //添加字段验证
        addFieldValidate: function () {
            $(arguments).each(function (index, $obj) {
                $obj.rules('add', 'required');
            })
        },
        //移除字段验证
        removeFieldValidate: function () {
            $(arguments).each(function (index, $obj) {
                $obj.rules('remove', 'required');
            })
        },
        //提示信息
        showMessage: function (message) {
            $('.message-error').removeAttr('style').removeClass('hide').addClass('show').html(this.ico + message);
        },
        //关闭当前选中卡
        initCloseTab: function () {
            $('button.close-tab').click(function () {
                parent.$('.J_menuTabs .active i').click()
            });
        },
        initIsNumberKeyUp: function () {
            $(".isNumber").keyup(function () {
                var c = $(this);
                if (/[^\d]/.test(c.val())) {
                    var temp_amount = c.val().replace(/[^\d]/g, '');
                    $(this).val(temp_amount);
                }
            });
        },
        initAddTab: function () {
            //添加选项卡
            $(document).on('click', '.add-tab', function () {
                var dataUrl = $(this).data('url');
                var menuName = $(this).data('name');
                if (!menuName) {
                    menuName = $(this).text();
                }
                changeIframe(dataUrl, menuName);
            });
        },
        laydate: function (beginTime, endTime, format) {
            var overDate;
            var startDate;
            if (!format) {
                format = "date";
            }
            laydate.render({
                elem: beginTime,
                type: format,
                done: function (value, date, endDate) {
                    startDate = value;
                    if (!startDate) {
                        swal('时间不能为空', '', 'warning');
                    }
                    if (value != "" && overDate != "") {
                        startDate = Date.parse(new Date(value));
                        overDate = Date.parse(new Date(overDate));
                        if (overDate < startDate || startDate == overDate) {
                            swal('结束时间不能小于或等于开始时间', '', 'warning');
                        }

                    }
                },
                choose: function (datas) {
                    endTime.min = datas; //开始日选好后，重置结束日的最小日期
                    endTime.start = datas //将结束日的初始值设定为开始日
                }
            });

            laydate.render({
                elem: endTime,
                type: format,
                done: function (value, date, endDate) {
                    overDate = value;
                    if (!overDate) {
                        swal('时间不能为空', '', 'warning');
                    }
                    if (value != "" && startDate != "") {
                        startDate = Date.parse(new Date(startDate));
                        overDate = Date.parse(new Date(value));
                        if (overDate < startDate || startDate == overDate) {
                            swal('结束时间不能小于或等于开始时间', '', 'warning');
                        }
                    }
                },
                choose: function (datas) {
                    endTime.max = datas //将结束日的初始值设定为开始日
                    alert(1)
                }
            });

            $('.laydate-btns-confirm').click(function () {

            });
        },
    };

    $.base._init();

})(jQuery);


/*****列表表格******参数(分页数据url，是否展示表格底部一行，是否需要分页，行内修改的url,表格的id，表格新增按钮的id用于完善新增按钮样式,查询按钮)****/
var TableInit = function (url, showFooter, isUsePage, editUrl, tableId, toolbar, searchId) {
    if (tableId == null) {
        tableId = "bootstrapTable";
    }
    if (toolbar == null) {
        toolbar = "tableToolbar";
    }
    if (searchId == null) {
        searchId = "searchForm";
    }

    var oTableInit = new Object();

    //初始化Table
    oTableInit.Init = function () {
        $('#' + tableId).bootstrapTable({
            url: adminUrl + url,
            toolbar: '#' + toolbar,
            cache: false,
            pagination: (isUsePage == undefined || isUsePage == true) ? true : false,
            sortOrder: 'desc',
            showFooter: showFooter ? true : false,
            queryParamsType: '',
            striped: true,
            sidePagination: "server",
            pageSize: 20,
            pageList: [10, 50, 100, 1000],
            showRefresh: true,
            uniqueId: "id",
            //showToggle : true,
            showColumns: true,
            clickToSelect: true,
            showExport: true,                     	 //是否显示导出
            exportDataType: "all",              //basic', 'all', 'selected'.
            queryParams: oTableInit.queryParams,
            columns: columnsDefined(),
            iconSize: 'outline',
            icons: {
                refresh: 'glyphicon-repeat',
                //toggle : 'glyphicon-list-alt',
                columns: 'glyphicon-list'
            },
            onEditableSave: function (field, row, oldValue, $el) {
                $.ajax({
                    type: "POST",
                    url: adminUrl + editUrl,
                    data: {"param": JSON.stringify(row)},
                    success: function (data, status) {
                        if (data.code == 200) {
                            swal("修改成功");
                            $("#bootstrapTable").bootstrapTable('refresh');
                        } else {
                            $("#bootstrapTable").bootstrapTable('refresh');
                            swal("修改失败");
                        }
                    },
                    error: function () {
                        $("#bootstrapTable").bootstrapTable('refresh');
                        swal("修改失败");
                    },
                    complete: function () {
//                    	$("#bootstrapTable").bootstrapTable('refresh');
                    }
                });
            }
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var param = $("#" + searchId).serializeArray();
        ;
        for (var p in param) {
            if (param[p].value != '') {
                params[param[p].name] = param[p].value;
            }
        }
        return params;
    };
    return oTableInit;
};

/*****查询按钮绑定表格******参数(按钮id,表格id)****/
var ButtonInit = function (queryBtn, tableId) {
    if (queryBtn == null) {
        queryBtn = "btn_query";
    }
    if (tableId == null) {
        tableId = "bootstrapTable";
    }
    var oInit = new Object();
    oInit.Init = function () {
        $("#" + queryBtn).click(function () {
            $("#" + tableId).bootstrapTable('refreshOptions', {pageNumber: 1, pageSize: 50});
            $("#" + tableId).bootstrapTable('refresh', TableInit);
        });
    };
    return oInit;
};
var switcheryMap;

function switchStatus(url, status) {
    if (status == undefined) {
        status = 0;
    }
    var elemList = document.querySelectorAll('.js-switch');
    switcheryMap = {};
    for (var i = 0; i < elemList.length; i++) {
        var elem = elemList[i];
        var id = $(elem).attr("data");
        var switchery = new Switchery(elem, {
            className: 'switchery',
            color: '#1AB394'
        });
        switcheryMap[id] = switchery;
        elem.onchange = function () {
            var sw = this;
            swal({
                    title: "您确定要修改数据状态吗",
                    text: "修改后将直接影响到APP，请谨慎操作！",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "是的，我要修改！",
                    cancelButtonText: "让我再考虑一下…",
                    closeOnConfirm: false,
                    closeOnCancel: false
                },
                function (isConfirm) {
                    if (isConfirm) {
                        var statusValue;
                        if (sw.checked) {
                            statusValue = 1;
                        } else {
                            statusValue = status;
                        }
                        $.ajax({
                            type: "post",
                            url: adminUrl + url,
                            async: false,
                            data: {
                                'id': $(sw).attr("data"),
                                'status': statusValue
                            },
                            success: function (msg) {
                            }
                        });
                        swal("操作成功！", "您已经修改了状态。", "success");
                        $("#bootstrapTable").bootstrapTable('refresh');
                    } else {
                        switcheryMap[$(sw).attr("data")].setPosition(true);
                        switcheryMap[$(sw).attr("data")].handleOnchange(true);
                        swal("已取消", "您取消了修改操作！", "error");
                    }
                });
        };
    }
}

function getStatusCheckBox(id, value) {
    /*  console.log(value)*/
    var item = '<input data="' + id + '" type="checkbox" class="js-switch"  id="switch' + id + '"';
    if (value == 1) {
        item += "checked";
    }
    item += "/>"
    return item;
}


function getButtion(type, name) {
    var opt = "";
    if (type == 'edit') {
        opt += '<i class ="fa fa-pencil" ></i> ';
    } else if (type == 'reply') {
        opt += '<i class ="fa fa-reply" ></i> ';
    } else if (type == 'list') {
        opt += '<i class ="fa fa-list" ></i>  ';
    } else if (type == 'detail') {
        opt += '<i class ="fa fa-list" ></i>  ';
    } else if (type == "time") {
        opt += '<i class ="fa fa-clock-o" ></i> ';
    } else if (type == "money") {
        opt += '<i class ="fa fa-money" ></i>  ';
    } else if (type == "refresh") {
        opt += '<i class ="fa fa-refresh" ></i>  ';
    } else if (type == "delete") {
        opt += '<i class ="fa fa-trash-o" ></i>  ';
    }
    opt += name;
    return opt;
}

/*****(href,type,menuName不使用传null,linkName不使用传null)**********/
function getOpt(href, type, menuName, linkName) {
    var opt = ' <a class="btn btn-white btn-sm"';
    if (menuName == undefined || menuName == null) {
        opt += 'href="' + href + '" data-toggle="ajaxModal">';
    } else {
        opt += 'onclick="changeIframe(' + '\'' + href + '\'' + ',' + '\'' + menuName + '\'' + ');">'
    }
    if (type == 'edit') {
        opt += '<i class ="fa fa-pencil" ></i> ' + (linkName == undefined || linkName == null ? '编辑' : linkName);
    } else if (type == 'reply') {
        opt += '<i class ="fa fa-reply" ></i> ' + (linkName == undefined || linkName == null ? '回复' : linkName);
    } else if (type == 'list') {
        opt += '<i class ="fa fa-list" ></i>  ' + (linkName == undefined || linkName == null ? '账户流水' : linkName);
    } else if (type == 'detail') {
        opt += '<i class ="fa fa-list" ></i>  ' + (linkName == undefined || linkName == null ? '详情' : linkName);
    } else if (type == "time") {
        opt += '<i class ="fa fa-clock-o" ></i>  ' + (linkName == undefined || linkName == null ? '时间设置' : linkName);
    } else if (type == "money") {
        opt += '<i class ="fa fa-money" ></i>  ' + (linkName == undefined || linkName == null ? '开奖结果' : linkName);
    } else if (type == "refresh") {
        opt += '<i class ="fa fa-refresh" ></i>  ' + (linkName == undefined || linkName == null ? '刷新' : linkName);
    } else if (type == "delete") {
        opt += '<i class ="fa fa-trash-o" ></i>  ' + (linkName == undefined || linkName == null ? '删除' : linkName);
    } else {
        opt += linkName;
    }

    opt += '</a>';
    return opt;
}

function changeIframe(dataUrl, menuName) {
    var flag = true;
    if (dataUrl == undefined || $.trim(dataUrl).length == 0) flag = false;
    // 选项卡菜单已存在
    parent.$('.J_menuTab').each(function () {
        if ($(this).data('id') == dataUrl) {
            if (!$(this).hasClass('active')) {
                $(this).addClass('active').siblings('.J_menuTab').removeClass('active');
                // 显示tab对应的内容区
                parent.$('.J_mainContent .J_iframe').each(function () {
                    if ($(this).data('id') == dataUrl) {
                        $(this).show().siblings('.J_iframe').hide();
                        return false;
                    }
                });
            }
            flag = false;
        }
    });
    if (flag) {
        var str = '<a href="javascript:;" class="active J_menuTab" data-id="' + dataUrl + '">' + menuName + '<i class="fa fa-times-circle"></i></a>';
        parent.$('.J_menuTab').removeClass('active');
        parent.$('.J_menuTabs .page-tabs-content').append(str);

        var str1 = '<iframe class="active J_iframe" name="iframe0" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></iframe>';
        parent.$('.J_mainContent').find('iframe.J_iframe').hide().parents('.J_mainContent').append(str1);
    }
}


/*****列表表格****/

/*****文件上传****/
var UploadInit = function (id, type, exts, limit) {
    var uploadInit = new Object();
    uploadInit.Init = function () {
        var file = $('#' + id).Huploadify({
            id: id,
            auto: true,
            fileTypeExts: exts,
            multi: false,
            fileSizeLimit: (limit == null || limit == undefined) ? 0 : limit,
            fileSplitSize: 1024 * 1024,
            breakPoints: true,
            saveInfoLocal: false,
            showUploadedPercent: true,
            showUploadedSize: true,
            removeTimeout: 9999999,
            uploader: adminUrl + '/com.tianmao.common/fileUpload',
            onUploadStart: function () {
                file.Huploadify('settings', 'formData', {
                    fileType: type
                });
            },
            onUploadSuccess: function (file, data) {
                var data = {
                    fileName: file.name,
                    lastModifiedDate: file.lastModifiedDate.getTime()
                };
                $.ajax({
                    url: adminUrl + '/com.tianmao.common/updateUploadedStatus',
                    data: data,
                    async: false,
                    type: 'POST',
                    success: function (returnData) {
                        $("#" + id + "Id").val(returnData.media.id);
                        if ($("#img" + id).length > 0) {
                            $("#img" + id).attr("src", imageUrl + returnData.media.fileurl);
                            $("#img" + id).attr("alt", returnData.media.filename);
                        }
                    }
                });
            },
            getUploadedSize: function (file) {
                var data = {
                    fileName: file.name,
                    lastModifiedDate: file.lastModifiedDate.getTime()
                };
                var url = adminUrl + '/com.tianmao.common/getUploadedSize';
                var uploadedSize = 0;
                $.ajax({
                    url: url,
                    data: data,
                    async: false,
                    type: 'POST',
                    success: function (returnData) {
                        uploadedSize = returnData;
                    }
                });
                return uploadedSize;
            },
            onCancel: function (file) {
                $("#" + id + "Name").val("");
                $("#" + id + "Id").val("");
            },
            onSelect: function (files) {
                $("#" + id + "Name").val(files[0].name);
                $("#" + id + "Id").val("");
            }
        });
    };
    return uploadInit;
};


/*****文件上传****/


/*****日期****/
var beginTime = {
    elem: '#beginTime',
    format: 'YYYY-MM-DD hh:mm:ss',
    max: '2099-06-16', //最大日期
    istoday: false,
    choose: function (datas) {
        endTime.min = datas; //开始日选好后，重置结束日的最小日期
        endTime.start = datas //将结束日的初始值设定为开始日
    }
};

var endTime = {
    elem: '#endTime',
    format: 'YYYY-MM-DD ',
    max: '2099-06-16',
    istoday: false,
    choose: function (datas) {
        beginTime.max = datas; //结束日选好后，重置开始日的最大日期
    }
};

function formatDate(time, format) {
    if (!time) {
        return "";
    }
    var now = new Date(time);
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    var date = now.getDate();
    var hour = now.getHours();
    var minute = now.getMinutes();
    var second = now.getSeconds();
    if (format == "hms") {
        return fixZero(hour, 2) + ":" + fixZero(minute, 2) + ":"
            + fixZero(second, 2);
    } else if (format == "yMd") {
        return year + "-" + fixZero(month, 2) + "-" + fixZero(date, 2);
    } else {
        return year + "-" + fixZero(month, 2) + "-" + fixZero(date, 2) + "  "
            + fixZero(hour, 2) + ":" + fixZero(minute, 2) + ":"
            + fixZero(second, 2);
    }

}

function fixZero(num, length) {
    var str = "" + num;
    var len = str.length;
    var s = "";
    for (var i = length; i-- > len;) {
        s += "0";
    }
    return s + str;
}
/*****日期****/










