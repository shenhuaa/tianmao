$(function () {
    var advert = {
        //初始化
        init: function () {
            this.initUploader();
            this.initValidateAdvertForm();
            this.changeAdType();
            this.initKindEditor();
        },
        //logo上传初始化
        initUploader: function () {
            $.initUploader('/advert/upload');
        },
        //富文本初始化
        initKindEditor: function () {
            KindEditor.create('#kindeditor', {
                uploadJson: adminUrl + '/advert/kindEditorUpload',
                allowFileManager: true, //浏览图片空间
                imageTabIndex: 1, //点击上传图片按钮默认显示标签，1为本地上传，默认为0网络图片
            });
        },
        //广告选择变更事件
        changeAdType: function () {
            var show  = $('#show').val();
            if (show == '') {
                $("#adDiv1").hide();
                $("#adDiv2").hide();
            }else if(show == 0) {
                $("#adDiv1").hide();
                $("#adDiv3").hide();
            }else if(show == 13){
                $("#adDiv1").hide();
                $("#adDiv2").hide();
            } else{
                $("#adDiv2").hide();
                $("#adDiv3").hide();
            }
            commonChange();
            $("#adType").change(function () {
                commonChange();
                var adType = $("#adType").val();

                if (adType == '') {
                    $("#adDiv1").hide();
                    $("#adDiv2").hide();
                    $("#adDiv3").hide();
                }else if (adType == 0) {
                    $("#adDiv1").hide();
                    $("#adDiv2").show();
                    $("#adDiv3").hide();
                }else if (adType == 13) {
                    $("#adDiv1").hide();
                    $("#adDiv2").hide();
                    $("#adDiv3").show();
                } else {
                    $("#adDiv1").show();
                    $("#adDiv2").hide();
                    $("#adDiv3").hide();
                }
            });
        }
        ,
        //初始化表单验证
        initValidateAdvertForm: function () {
            var $advertForm = $("#advertForm");
            $advertForm.validate({
                ignore: 'hidden',
                rules: {
                    description: {
                        maxlength: 50,
                    },
                    advertUrl: {
                        required: true,
                    },

                },
                messages: {
                    description: {
                        maxlength: icon + "最多可以输入{0}个字符",
                    },
                    advertUrl: {
                        required: icon + "不能为空",
                        number: icon + "请上传图片",
                    },
                },
                submitHandler: function (form) {
                    $.post(adminUrl + '/advert/update', $(form).serialize(), function (response) {
                        if (response.code == 200) {
                            $('#ajaxModal').modal('hide');
                            $("#bootstrapTable").bootstrapTable('refresh');
                            swal('修改成功', '', 'success');
                        } else {
                            swal(response.message, '', 'warning');
                        }
                    })
                },
            });
        }
    };

    advert.init();
})

function commonChange() {
    //''广告类型0:"链接"1,"文章"2,"微说  3 :短视频","4,"
    // 车友会"5,"车友会活动"6,"车系圈"7,"车系圈活动"8,"地区"9,"地区活动"10,"用户"11,"商家"12,"商品"13,"图文"'',',
    var index = $('#adType').val()
    if(index == 1) {
        $('.contents').html("文章")
    }
    if(index == 2) {
        $('.contents').html("微说")
    }
    if(index == 3) {
        $('.contents').html("短视频")
    }
    if(index == 4) {
        $('.contents').html("社区")
    }
    if(index == 5) {
        $('.contents').html("社区活动")
    }
    if(index == 6) {
        $('.contents').html("社群")
    }
    if(index == 7) {
        $('.contents').html("社群活动")
    }
    if(index == 8) {
        $('.contents').html("地区")
    }
    if(index == 9) {
        $('.contents').html("地区活动")
    }
    if(index == 10) {
        $('.contents').html("用户")
    }
    if(index == 11) {
        $('.contents').html("商家")
    }
    if(index == 12) {
        $('.contents').html("商品")
    }
    if(index == 13) {
        $('.contents').html("图文")
    }
}
