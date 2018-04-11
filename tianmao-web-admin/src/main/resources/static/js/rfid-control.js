;(function ($) {
    $.rfid_control = {
        constructor: this,
        ver: 2,                       //当前的版本  用于与主程序进行匹配
        onBasketChanged: null,       //绑定的方法  篮中物品有变化时触发   function(rfids);
        onExitsChanged: null,          //绑定的方法  出口物品有变化时触发   function(rfids);
        onHeadBeat: null,			//绑定的方法  心跳时触发   function(hardinfo);
        basketChanged: function (frids)  //内部使用    可以在控制台通过 $.rfid_control.basketChanged("1001,1003,1004"); 摸拟
        {
            if (this.onBasketChanged) this.onBasketChanged(frids);
            this.initGoodKey(frids);
            return "succ";
        },
        exitsChanged: function (frids) {   //内部使用 可以在控制台通过 $.rfid_control.exitsChanged("1001,1003,1004"); 摸拟
            if (this.onExitsChanged) this.onExitsChanged(frids);
            return "succ";
        },
        headBeat: function (hardinfo) {   //内部使用 可以在控制台通过 $.rfid_control.headBeat("硬件信息"); 摸拟
            if (this.onHeadBeat) this.onHeadBeat(hardinfo);
        },
        openDoor: function (index) {      //执行开门  index门序号  1~4
            if ("undefined" == typeof HOST) return "无法连接到设备";
            HOST.openDoor(index);
        },
        playSound: function (filename) {  //播放声音文件
            if ("undefined" == typeof HOST) return "无法连接到设备";
            HOST.playSound(filename);
        },
        getBasketFrids: function () {    //读取购物栏中的物品
            if ("undefined" == typeof HOST) return "无法连接到设备";
            return HOST.getBasketFrids();
        },
        getExitsFrids: function () {       //读取出口中的物品
            if ("undefined" == typeof HOST) return "无法连接到设备";
            return HOST.getExitsFrids();
        },
        getHostVer: function () {       //获取主程序版本 整数
            if ("undefined" == typeof HOST) return -1;
            return HOST.getVer();
        },
        execUpdate: function (url) {   //提示从指定位置下载新版本  url新版本位置，多个位置以,分隔
            if ("undefined" == typeof HOST) return;
            HOST.updateHost(url);
        },
        //添加商品标签
        initGoodKey: function (frids) {
            //$.rfid_control.basketChanged('E20041252207008809900001,E20041252207008909900002,E20041252207008909900003,E20041252207008909900004,E20041252207008909900005,E20041252207008909900006,E20041252207008909900007');
            //验证商品是否为同一类
            this.verifyKey(frids, $('.J_iframe'));

            $('.J_iframe').each(function (index, iframe) {
                var name = $(iframe).attr('name');
                var url = $(iframe).data('id');
                var length = 0;
                if (frids) {
                    length = frids.split(",").length;
                }
                var iframeName = window.frames[name];
                var rediCount = iframeName.document.getElementById("rediCount");
                var goodKeys = iframeName.document.getElementById("goodKeys");
                var goodKeysText = iframeName.document.getElementById("goodKeysText");
                if (!rediCount || !goodKeys || !goodKeysText) {
                    return;
                }
                $(rediCount).val(length);
                $(goodKeys).val(frids);
                $(goodKeysText).html('');
                var goodKeyArr = frids.split(",");
                $(goodKeyArr).each(function (index, key) {
                    var goodKey = '<div  class="label label-info" style="margin: 3px ;float:left ;width: 200px">' + key + '</div>'
                    $(goodKeysText).append(goodKey);
                })
                var electronicTag = iframeName.document.getElementById("electronicTag").offsetHeight;
                if (electronicTag <= 100) {
                    iframeName.document.getElementById("submit").style.marginTop = "1px";
                } else {
                    iframeName.document.getElementById("submit").style.marginTop = (+electronicTag - 120) + "px";
                }
            });
        },
        //验证商品是否为同一类
        verifyKey: function (frids, J_iframe) {
            J_iframe.each(function (index, iframe) {
                var name = $(iframe).attr('name');
                var iframeName = window.frames[name];
                /*var verifyType = iframeName.document.getElementById("verifyType");
                if (verifyType) {
                    if ($(verifyType).val() == "verifyKey") {*/
                $.post(adminUrl + "/shop/store/verifyKey", {keys: frids}, function (response) {
                    if (response.code == 200) {
                        var $good = response.data.good;
                        iframeName.document.getElementById("goodName").value = $good.goodName;
                        iframeName.document.getElementById("code").value = $good.goodCode;
                        iframeName.document.getElementById("id").value = $good.id;
                        iframeName.document.getElementById("price").value = $good.price;
                        iframeName.document.getElementById("storeCount").value = $good.storeCount;
                        iframeName.document.getElementById("categoryParentName").value = $good.categoryParentName;
                        iframeName.document.getElementById("categorySonName").value = $good.categorySonName;
                    } else {
                        iframeName.swal(response.message, '', 'warning');
                    }
                })
                    /*} else if ($(verifyType).val() == "existKey") {
                        $.post(adminUrl + "/shop/store/existKey", {keys: frids}, function (response) {
                            if (response.code == 200) {
                            } else {
                                iframeName.swal(response.message, '', 'warning');
                            }
                        });
                    }else{

                    }
                }*/
            });

        }
    }
})(jQuery);

