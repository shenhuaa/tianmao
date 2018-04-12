$(function(){
	
              //Ajax调用处理
            $.ajax({
               url: appUrl + "/user/getUserPronDetail",
               data: "pronId="+userId,
               success: function(res){

                    console.log(res)
                    document.title = "爱士多-"+res.data.userMap.nickname;
                    var str = '';
                    str+='<div class="author"><img src="'+res.data.userMap.headImgUrl+'" alt="" />'
                    str+='<span class="nameAll"><span>'+res.data.userMap.nickname+'['+res.data.userDataMap.level+'级]</span>'
                    str+='<span>关注:'+res.data.userDataMap.myFollows+' &nbsp;|&nbsp; 粉丝:'+res.data.userDataMap.myFans+'</span>'
                    str+='</span><span class="gz">+关注</span></div><span class="headline1">'+res.data.userMap.signature+'</span>'
                    $('#main').html(str);         
                }
            });

})


