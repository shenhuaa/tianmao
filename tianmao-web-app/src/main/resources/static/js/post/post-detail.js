var androidStore;
var iosStore;
$(function(){
    $.ajax({
       url: appUrl + "/com.tianmao.common/getGeneralPostDetail",
       data: "postId="+postId,
       success: function(res){
//                    console.log(res)
           var resAll = res.data;
           androidUrl = resAll.appVersion.androidStore;
           androidApp = resAll.appVersion.androidLink;
           iosUrl = resAll.appVersion.iosStore;
           iosApp = resAll.appVersion.iosLink;
           var str = '';
              if(resAll.baseMap.essence==0){
                str+='';
              }else{
                  document.title = "爱士多-"+resAll.baseMap.title;
                str+='<span class="seal"><img src="/static/image/share/seal.png" alt="" /></span>'
              }
//                    str+='<span class="seal"><img src="../img/seal.png" alt="" /></span>'
              str+='<div class="author" onclick="submitFn();"><img src="'+resAll.baseMap.headImgUrl+'" alt="" />'
              str+='<span class="nameAll"><span>'+resAll.baseMap.nickname+'</span>'
              str+='<span>'+getFotmatTime(resAll.baseMap.releaseTime,"MM-dd-HH-mm")+'</span></span>'
              str+='<span class="gz" onclick="submitFn();">+关注</span></div>'
              str+='<span class="headline1">已赏  ￥'+resAll.baseMap.rewardAllCount+'</span>'
              str+='<h4>'+resAll.baseMap.title+'</h4>'
              str+='<span class="headline2">'+resAll.baseMap.circleNames+'</span>'
              str+='<div class="photograph">'
              for(var i = 0;i<resAll.contentList.length;i++){
                if(resAll.contentList[i].type==0){
                    str+='<p class="describe">'+resAll.contentList[i].formatContent+'</p>'
                }else if(resAll.contentList[i].type==1){
                    str+='<img src="'+resAll.contentList[i].imgUrl+'" alt="" />'
                    if(resAll.contentList[i].formatContent!='null' && resAll.contentList[i].formatContent!=''){
                        str+='<p class="img-detail">'+resAll.contentList[i].formatContent+'</p>';
                    }
                }else if(resAll.contentList[i].type==2){
                    str+='<iframe src="'+resAll.contentList[i].videoUrl+'" allowfullscreen="" scrolling="no" frameborder="0" width="100%" height="250px"></iframe>';
                }
              }
              str+='</div><div class="like"><span onclick="submitFn();"><img src="/static/image/share/zannew@2x.png" alt="" /><p>'+resAll.rewardMap.linkAndReward.linkNumCount+'</p></span>'
              str+='<span onclick="submitFn();"><img src="/static/image/share/shangnew@3x.png" alt="" /><p>'+resAll.rewardMap.linkAndReward.rewardAllCount+'</p></span></div>'
              $('#main').html(str);
            //土豪榜
           var announcement = '<div class="ryjl_min_xs"><img src="/static/image/share/hg.png" alt="" /><span>土豪榜</span></div><div class="imgAll">';
           if(resAll.rewardMap.tyrantList[1]==undefined){
               announcement+='<span style="width: 33%;"><img src="/static/image/share/ic_art_rich_two_bg.png" alt="" />'
               announcement+='<span class="ryjl-span">虚位以待</span></span>'
           }else{
               announcement+='<span class="second" style="width: 33%;"><img src="/static/image/share/ic_art_rich_two.png" alt="" />'
               announcement+='<span class="ryjl-span" >'+resAll.rewardMap.tyrantList[1].nickaName+'</span>'
               announcement+='<span class="head1"><img style="left: 0.15rem;top: 0.6rem;" src="'+resAll.rewardMap.tyrantList[1].headImg+'" alt="" />'
               announcement+='<span class="head1_wz"><span></span><span>'+resAll.rewardMap.tyrantList[1].rewardCount+'</span></span></span></span>'

           }
           if(resAll.rewardMap.tyrantList[0]==undefined){
               announcement+='<span style="width: 33%;"><img src="/static/image/share/ic_art_rich_one_bg.png" alt=""/>'
               announcement+='<span class="ryjl-span">虚位以待</span></span>'
           }else{
               announcement+='<span class="first" style="width: 33%;"><img src="/static/image/share/ic_art_rich_one.png" alt=""/>'
               announcement+='<span class="ryjl-span">'+resAll.rewardMap.tyrantList[0].nickaName+'</span>'
               announcement+='<span class="head2"><img style="left: 0.6rem;top: 0.5rem;" src="'+resAll.rewardMap.tyrantList[0].headImg+'" alt="" />'
               announcement+='<span class="head_wz"><span></span><span>'+resAll.rewardMap.tyrantList[0].rewardCount+'</span></span></span></span>'
           }

           if(resAll.rewardMap.tyrantList[2]==undefined){
               announcement+='<span style="width: 33%;"><img src="/static/image/share/ic_art_rich_three_bg.png" alt="" />'
               announcement+='<span class="ryjl-span">虚位以待</span></span>'
           }else{
               announcement+='<span style="width: 33%;"><img src="/static/image/share/ic_art_rich_three.png" alt="" />'
               announcement+='<span class="ryjl-span" >'+resAll.rewardMap.tyrantList[2].nickaName+'</span>'
               announcement+='<span class="head3"><img style="left: 0.4rem;top: 0.6rem;"  src="'+resAll.rewardMap.tyrantList[2].headImg+'" alt="" />'
               announcement+='<span class="head2_wz head3_wz"><span></span><span>'+resAll.rewardMap.tyrantList[2].rewardCount+'</span></span></span></span>'

           }
           announcement+="</div>";
           $('#local_tyrant').html(announcement);

           //荣耀记录
           if(resAll.rewardMap.honorList.length>0){
               var ryjl = '<div class="ryjl_min_xs"><img src="/static/image/share/ryjl.png" alt="" /><span>荣耀记录</span></div>';
                for(var i=0;i<resAll.rewardMap.honorList.length;i++){
                    if(resAll.rewardMap.honorList[i].honorType == 0){
                        ryjl+='<div class="record " ><div class="record1"><span class="span1">此贴被设为精华</span><span class="span2">+'+resAll.rewardMap.honorList[i].honorGold+'金币</span></div>';
                        ryjl+='<div class="record2"><span class="span3">+'+resAll.rewardMap.honorList[i].honorIntegral+'积分</span></div></div>';
                    /*}else if(resAll.rewardMap.honorList[i].honorType == 2){*/
                        ryjl+='<div class="record "><div class="record1"><span class="span1">此贴入选<span class="span-2"><img src="/static/image/share/edit.png" alt=""/>首页</span></span><span class="span2">+'+resAll.rewardMap.honorList[i].honorGold+'金币</span></div>';
                        ryjl+='<div class="record2"><span class="span3">+'+resAll.rewardMap.honorList[i].honorIntegral+'积分</span></div></div>';
                    }
                }
               $("#ryjl").html(ryjl);
           }else{
               $("#ryjl").hide();
           }


          //热门
           /* var remen = '';
            remen+='<div><img src="/static/image/share/comment@3x.png" alt="" /><span>热门评价</span></div>'
            for(var n = 0;n<resAll.hotCommentList.length;n++){
                 remen+='<div class="author main_min1min"><img src="'+resAll.hotCommentList[n].headImgUrl+'" alt="" />'
                 remen+='<span class="nameAll"><span>'+resAll.hotCommentList[n].nickname+'</span>'
                 remen+='<span>['+resAll.hotCommentList[n].floor+'楼]'+getFotmatTime(resAll.hotCommentList[n].contentTime,'yyyy/MM/dd HH:mm:ss')+'</span>'
                 remen+='</span><span class="gz"><img style="width: 31px;height: 21px;vertical-align:middle;" src="/static/image/share/zan.png" alt="" />'+resAll.hotCommentList[n].likeNum+'</span></div>'
                 remen+='<div class="headline3"><span>'+resAll.hotCommentList[n].content+'</span>'
                 if(resAll.hotCommentList[n].original.content==undefined){

                 }else{
                    remen+='<span class="commentAll show"><span>原评论&nbsp;<p>'+resAll.hotCommentList[n].original.nickname+'</p></span>'
                    remen+='<span>于'+getFotmatTime(resAll.hotCommentList[n].contentTime,'MM-dd-HH-mm')+'发表在'+resAll.hotCommentList[n].floor+'楼</span>'
                    remen+='<span>'+resAll.hotCommentList[n].original.content+'</span></span>'
                 }
                 remen+='</div>'
            }
           $('#hottest_most').html(remen);*/



       //最新
       var zuixin = '';
        zuixin+='<div style="height: 0px;margin-top: -30px; margin-bottom: 30px;"></div>'
        for(var n = 0;n<resAll.commentList.length;n++){
             zuixin+='<div class="author main_min1min" onclick="submitFn();"><img src="'+resAll.commentList[n].headImgUrl+'" alt="" />'
             zuixin+='<span class="nameAll"><span>'+resAll.commentList[n].nickname+'</span>'
             zuixin+='<span>['+resAll.commentList[n].floor+'楼]'+getFotmatTime(resAll.commentList[n].contentTime,'yyyy/MM/dd HH:mm:ss')+'</span>'
             zuixin+='</span><span class="gz" onclick="submitFn();"><img style="width: 31px;height: 21px;vertical-align:middle;" src="/static/image/share/zan.png" alt="" />'+resAll.commentList[n].likeNum+'</span></div>'
             zuixin+='<div class="headline3"><span>'+resAll.commentList[n].content+'</span>'
             if(resAll.commentList[n].original.content==undefined){

             }else{
                zuixin+='<span class="commentAll show"><span>原评论&nbsp;<p>'+resAll.commentList[n].original.nickname+'</p></span>'
                zuixin+='<span>于'+getFotmatTime(resAll.commentList[n].contentTime,'MM-dd-HH-mm')+'发表在'+resAll.commentList[n].floor+'楼</span>'
                zuixin+='<span>'+resAll.commentList[n].original.content+'</span></span>'
             }
             zuixin+='</div>'
        }
        if(resAll.commentList==null || resAll.commentList.length==0){
            $("#comment").hide();
        }
       $('#newest').html(zuixin);



//            console.log(resAll.hotCommentList[1].original.content==undefined)  
              /**
             * 将时间戳转化为格式时间  默认yyyy-MM-dd HH:mm:ss格式
             * @param {Object} times
             * @param {Object} format
             */
            function getFotmatTime(times, format) {
                var time = new Date(times);
                var year = time.getFullYear();//年
                var month = (time.getMonth() + 1) < 10 ? "0" + (time.getMonth() + 1) : (time.getMonth() + 1);//月份，由于月份是从0开始计算，所以要加1
                var day = time.getDate() < 10 ? "0" + time.getDate() : time.getDate(); //日
                var hour = time.getHours() < 10 ? "0" + time.getHours() : time.getHours(); //时
                var minutes = time.getMinutes() < 10 ? "0" + time.getMinutes() : time.getMinutes(); //分
                var second = time.getSeconds() < 10 ? "0" + time.getSeconds() : time.getSeconds();//秒
                if(!format) {
                    return year + "-" + month + "-" + day + "  " + hour + ":" + minutes + ":" + second;
                }else if(format == "yyyy-MM-dd") {
                    return year + "-" + month + "-" + day;
                }else if(format == "HH:mm:ss") {
                    return hour + ":" + minutes + ":" + second;
                }else if(format == "HH:mm") {
                    return hour + ":" + minutes;
                }else if(format == "MM-dd-HH-mm"){
                    return month+"月"+day+"日"+" "+hour+":"+minutes
                }else if(format == "yyyy/MM/dd HH:mm:ss"){
                    return year + "/" + month + "/" + day+" "+hour + ":" + minutes + ":" + second
                }

                }

        }
    });
});


function submitFn(){
    //判断浏览器
    var u = navigator.userAgent;
    var d = new Date();
    var t0 = d.getTime();
    if(u.indexOf('Android') > -1 || u.indexOf('Linux') > -1){
        //Android
        if(openApp("istore://post?postId="+postId)){
            openApp("istore://post?postId="+postId);
        }else{
            //由于打开需要1～2秒，利用这个时间差来处理－－打开app后，返回h5页面会出现页面变成app下载页面，影响用户体验
            var delay = setInterval(function(){
                var d = new Date();
                var t1 = d.getTime();
                if( t1-t0<3000 && t1-t0>2000){
                    window.location.href = androidStore;
                }
                if(t1-t0>=3000){
                    clearInterval(delay);
                }
            },1000);
        }
    }else if(u.indexOf('iPhone') > -1){
        //IOS
        if(openApp("istore://post?postId="+postId)){
            openApp("istore://post?postId="+postId);
        }else{
            var delay = setInterval(function(){
                var d = new Date();
                var t1 = d.getTime();
                if( t1-t0<3000 && t1-t0>2000){
                    window.location.href = iosStore;
                }
                if(t1-t0>=3000){
                    clearInterval(delay);
                }
            },1000);
        }
    }
}

function openApp(src) {
// 通过iframe的方式试图打开APP，如果能正常打开，会直接切换到APP，并自动阻止a标签的默认行为
// 否则打开a标签的href链接
    var ifr = document.createElement('iframe');
    ifr.src = src;
    ifr.style.display = 'none';
    document.body.appendChild(ifr);
    window.setTimeout(function(){
        document.body.removeChild(ifr);
    },2000);
}

