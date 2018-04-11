$(function(){
	
              //Ajax调用处理
            $.ajax({
               url: appUrl+"/user/talk/getTalkDetailById",
               data: "talkId="+talkId,
               success: function(res){
//                    console.log(res)
//                    return;
                      var resAll = res.data;
                      var str = '';
                      if(resAll.baseMap.essence==0){
                      	str+='';
                      }else{
                          document.title = "爱士多-"+resAll.baseMap.title;
                      	str+='<span class="seal"><img src="/static/image/share/seal.png" alt="" /></span>'
                      }
                      
                      str+='<div class="author"><img src="'+resAll.baseMap.headImgUrl+'" alt="" />'
                      str+='<span class="nameAll"><span>'+resAll.baseMap.nickname+'</span>'
                      str+='<span>'+getFotmatTime(resAll.baseMap.releaseTime,"MM-dd-HH-mm")+'</span></span>'
                      str+='<span class="gz">+关注</span></div>'
                      str+='<span class="headline1">已赏 0￥</span>'
                      str+='<p class="describe">'+resAll.baseMap.title+'</p>'
//                     <p class="describe">
//				                         结构理发店技术估计咖啡季度结案是否哦假数据佛加上附近搜案件假数据方法家居佛驾驶飞机欧萨金佛放假啊煞风景
//		               </p>
//					  <div class="photograph">
//                    str+='<h4>'+resAll.baseMap.title+'</h4>'
//                    str+='<span class="headline2">'+resAll.baseMap.circleNames+'</span>'
                      str+='<div class="photograph">'
                      for(var i = 0;i<resAll.contentList.length;i++){
                      		str+='<img src="'+resAll.contentList[i].imgUrl+'" alt="" />'
                      }
                      str+='</div><div class="like"><span><img src="/static/image/share/zannew@2x.png" alt="" /><p>'+resAll.rewardMap.linkAndReward.linkNumCount+'</p></span></div>'
//                    str+='<span><img src="../img/shangnew@3x.png" alt="" /><p>'+resAll.rewardMap.linkAndReward.rewardAllCount+'</p></span></div>'
                      $('#main').html(str);
              //热门
                 
                var remen = '';
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
               $('#hottest_most').html(remen);
               
               
               //最新
               
               var zuixin = '';
                zuixin+='<div><img src="/static/image/share/comment@3x.png" alt="" /><span>最新评价</span></div>'
                for(var n = 0;n<resAll.commentList.length;n++){
                	 zuixin+='<div class="author main_min1min"><img src="'+resAll.commentList[n].headImgUrl+'" alt="" />'
                	 zuixin+='<span class="nameAll"><span>'+resAll.commentList[n].nickname+'</span>'
                	 zuixin+='<span>['+resAll.commentList[n].floor+'楼]'+getFotmatTime(resAll.commentList[n].contentTime,'yyyy/MM/dd HH:mm:ss')+'</span>'
                	 zuixin+='</span><span class="gz"><img style="width: 31px;height: 21px;vertical-align:middle;" src="/static/image/share/zan.png" alt="" />'+resAll.commentList[n].likeNum+'</span></div>'
                	 zuixin+='<div class="headline3"><span>'+resAll.commentList[n].content+'</span>'
                	 if(resAll.commentList[n].original.content==undefined){
                	 	
                	 }else{
                	 	zuixin+='<span class="commentAll show"><span>原评论&nbsp;<p>'+resAll.commentList[n].original.nickname+'</p></span>'
                	 	zuixin+='<span>于'+getFotmatTime(resAll.commentList[n].contentTime,'MM-dd-HH-mm')+'发表在'+resAll.commentList[n].floor+'楼</span>'
                	 	zuixin+='<span>'+resAll.commentList[n].original.content+'</span></span>'
                	 }
                	 zuixin+='</div>'
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

})


