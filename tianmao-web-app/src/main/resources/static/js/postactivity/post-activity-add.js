$(function(){
	    var fal = true;
	    $('.huod').click(function(){
	    	$('.heid').slideToggle(500);
	    });
	    $('.fenl').click(function(){
	    	$('.show').slideToggle(500);
	    });
	    $('.heid').on('click','.category',function(){
	    	 $('.huodleib').html($(this).children().html());
	    	 $('.heid').css('display','none');
            $('.huodleib').attr("index", $(this).index());
	    });
	    
	    $('.show').on('click','.category',function(){
	    	 $('.huodfenl').html($(this).children().html());
	    	 $('.show').css('display','none');
            $('.huodfenl').attr("index", $(this).index());
	    });
	    
	    $('#qita').click(function(){
	    	$('.main_max').slideToggle(500,function(){
	    		if(fal){
	    			$('.qitasq').html('更多内容');
	    			fal=false;
	    			$('.imgqita').css('transform','rotate(0deg)');
	    		}else{
	    			$('.qitasq').html('收起更多');
	    			fal=true;
	    			$('.imgqita').css('transform','rotate(90deg)');
	    		}		
	    	});
	    });
	    var falche = true;
	    $('.chexi').click(function(){
	    	if(falche){
	    		$('.che').css('display','flex');
	    		falche=false;
	    	}else{
	    		$('.che').css('display','none');
	    		falche=true;
	    	}
	    	
	    });
	    
	    var falcs = true;
	    $('.chenshi').click(function(){
	    	if(falcs){
	    		$('.city').css('display','flex');
	    		falcs=false;
	    	}else{
	    		$('.city').css('display','none');
	    		falcs=true;
	    	}
	    	
	    });
	    
	    
	    $('.haoche').on('click','li',function(){
	    	$('.xuanzcx').html($(this).html());
	    	$('.xuanzcx').attr('index',$(this).attr('index'));
	    	$('.che').css('display','none');
	    });
	    
	    
	    
	     $('.chenshi').on('click','li',function(){
	    	$('.xunzcs').html($(this).html());
	    	$('.xunzcs').attr('index',$(this).attr('index'));
	    	$('.city').css('display','none');
	    });
              //Ajax调用处理
            $.ajax({
               url: "http://192.168.0.149:8090/post/activity/getPostActivityBaseData",
               data: "pronId="+1,
               success: function(res){
                    console.log(res);

                    var str = ''; 
                    for(var i = 0;i<res.data.categoryList.length;i++){
                    	str+='<div class="category"><span>'+res.data.categoryList[i].remark+'</span></div>';
                    }
                    $('.heid').html(str);
                    
                    var huod = '';
                    for(var n = 0;n<res.data.classList.length;n++){
                    	huod+='<div class="category"><span>'+res.data.classList[n].remark+'</span></div>'
                    }
                    $('.show').html(huod);
                    //车 
                    var carMax = '';
                    for(var z = 0;z<res.data.carHondlist.length;z++){
                    	carMax+='<li index="'+z+'">'+res.data.carHondlist[z].name+'<img src="/static/image/share/xy.png" alt="" /></li>'
                    }
                     $('.haocheAll').html(carMax);
                   
                    $('.haocheAll').on('click','li',function(){
                    	che($(this).attr('index'));
                    });
                    
                    function che(a){
	                    var car = '';
	                    for(var m=0;m<res.data.carHondlist[a].sonList.length;m++){
	                    	car+='<li index="'+res.data.carHondlist[a].sonList[m].id+'">'+res.data.carHondlist[a].sonList[m].name+'</li>';
	                    }
	                   $('.haoche').html(car);
                    }
                    che(0);
                    //城市
                    var chengs = '';
                    for(var y = 0;y<res.data.cityList.length;y++){
                    	chengs+='<li index="'+y+'">'+res.data.cityList[y].name+'<img src="/static/image/share/xy.png" alt="" /></li>';
                    }
                    $('.chenshiAll').html(chengs)
                    
                    function cs(a){
                    	var chengshi = '';
                    	for(var t = 0;t<res.data.cityList[a].sonList.length;t++){
                    		chengshi+='<li index="'+res.data.cityList[a].sonList[t].id+'">'+res.data.cityList[a].sonList[t].name+'</li>'
                    	}
                    	$('.chenshimin').html(chengshi);
                    }
                    cs(0);
                    
                    $('.chenshiAll').on('click','li',function(){
                    	cs($(this).attr('index'));
                    });
//                  var a = { "postContent": [{ "type": 0, "content": "婆婆说你儿子", "imgUrl": "", "videoUrl": "", "scale": 0 }, { "type": 1, "content": "", "imgUrl": "http://192.168.0.149/upload/image/talk/20180106/56355629c99747429129a95b6c58f383.jpeg", "videoUrl": "", "scale": 1.5 }, { "type": 1, "content": "", "imgUrl": "http://192.168.0.149/upload/image/talk/20180106/8d86edf192d847ef83e81e758721e1a6.jpeg", "videoUrl": "", "scale": 1.498293515358362 }, { "type": 1, "content": "", "imgUrl": "http://192.168.0.149/upload/image/talk/20180106/946ea04987f04d7fa601d4a1412ff512.jpeg", "videoUrl": "", "scale": 1.5 }, { "type": 0, "content": "和 ins 照片", "imgUrl": "", "videoUrl": "", "scale": 0 }] }
                }
            });
            //发文字
            $('.character').click(function(){
            	var strAll = '<div class="characterAll"><textarea style="resize:none" class="wbk" name="" rows="" cols=""></textarea><span><img class="del" src="/static/image/share/del.png" alt="" /></span></div>';
                $('.typeAll').append(strAll);
            });
            $('.typeAll').on('click','.del',function(){
            	$(this).parents('.characterAll').remove();
            });
            
            //发图片
            //预览图片
			document.querySelector('#file').onchange = function(){
				var reader = new FileReader();
				reader.readAsDataURL(this.files[0]);
				reader.onload = function(){

//					console.log(reader.result.split(';')[0].split('/')[1]);
//					console.log(typeof reader.result);
					$.ajax({
						url:"http://192.168.0.149:8090/user/talk/baseUploadImg",
						type:'post',
						data:{
							extension:reader.result.split(';')[0].split('/')[1],
							file:reader.result
						},
       					success: function(res){
       						console.log(res)
//                          					<div class="tupian">
//						<img src="../img/bg.png" alt="" />
//						<span>删除</span>
//						<textarea style="resize:none" type="text" placeholder="请输入图片注释..."/></textarea>
//					</div>
                      
                           var strall='';
                           strall+='<div class="tupian"><img class="imgtpall" src="'+imageUrl+ res.data.upload+'" alt="" /><span class="deltp">删除</span><textarea class="wenziAll" style="resize:none" type="text" placeholder="请输入图片注释..."/></textarea></div>'
                           $('.typeAll').append(strall);
       					}
					});
//                  console.log(reader.result.split(';')[0].split('/')[1]);
//					<div class="preview">
//						<img class="imgAll" src="" alt="" />
//						<span class='span_fs'>发送</span>
//					</div>
//                  str+='<div class="preview"><img class="imgAll" src="'+reader.result+'" alt="" /><span class="span_fs">发送</span>'
//					document.querySelector('.preview').style.display='block';
			//		document.querySelector('preview').style.opacity='1';
//					document.querySelector('.preview').style.background = 'url('+reader.result+')';
//					document.querySelector('.preview').style.backgroundSize = 'cover';
//					document.querySelector('.preview').style.backgroundRepeat = 'no-repeat';
//					document.querySelector('.preview').style.display='block';
				}
			}
            
            
            $('.typeAll').on('click','.deltp',function(){
            	$(this).parents('.tupian').remove();
            });
            
            
            $('.video').click(function(){
            	$('.boxAll').css('display','block')
            });
            
           $('.fsfooter').click(function(){
           	   $.tips('请输入有效的视频地址',2000)
           });
           $('.qxfooter').click(function(){
        	  $('.boxAll').css('display','none')
           });
           
           
           
           $('.message').click(function(){
           	  console.log(+$('.xuanzcx').attr('index'));
           	  console.log(+$('.xunzcs').attr('index'));
           	  var postContentAll = [];
           	  var objAll = {};
//         	  document.querySelectorAll('.typeAll .characterAll').length;
			  var divs = document.querySelectorAll('.wbk');
           	  for(var i = 0;i<divs.length;i++){
//         	      console.log($(divs[i]).val());	
//                {"type":0,"content":"婆婆说你儿子","imgUrl":"","videoUrl":"","scale":0}
                   objAll.type=0;
                   objAll.content=$(divs[i]).val();
                   objAll.imgUrl='';
                   objAll.videoUrl='';
                   objAll.scale=0;
                   postContentAll.push(objAll);
                   objAll={};
           	  }
//         	  console.log(postContentAll);
//            {"type":1,"content":"","imgUrl":"http://192.168.0.149/upload/image/talk/20180106/56355629c99747429129a95b6c58f383.jpeg","videoUrl":"","scale":1.5}/192.168.0.149/upload/image/talk/20180106/946ea04987f04d7fa601d4a1412ff512.jpeg","videoUrl":"","scale":1.5}
              var imgs = document.querySelectorAll('.imgtpall');
              var testAll = document.querySelectorAll('.wenziAll');
              for(var n = 0;n<imgs.length;n++){
//            	console.log($(imgs[n]).attr('src'));
//            	console.log($(testAll[n]).val());
	               objAll.type=1;
	               objAll.content=$(testAll[n]).val();
	               objAll.imgUrl=$(imgs[n]).attr('src');
	               objAll.videoUrl='';
	               objAll.scale=1.5;
	               postContentAll.push(objAll);
	               objAll={};
              }
              var postContenta={};
           	  postContenta.postContent=postContentAll;
//            console.log(postContenta);
//            var  
//         	  return
			   var userId = $('#userId').val();
			   var circleId = $('#circleId').val();
			   var circleType = $('#type').val();
               var title = $('.headlineall').val();
               var activityType = $('.huodleib').attr('index')
               var classify = $('.huodfenl').attr('index')
               var needNum = $('#renshu').val();
               var money = $('#lin').val();
               var hondaId = $('.xuanzcx').attr("index");
               var cityId = $('.xunzcs').attr("index");
               var acitvityPlace = $('#ativdz').val();
              $.ajax({
              	type:"post",
              	url:"http://192.168.0.149:8090/post/activity/saveOrUpdateCarActivityPost",
              	data:{
              		debug:true,
              		userId:userId,
              		circleId:circleId,
              		title:title,
              		activityType:activityType ,
              		circleType:circleType ,
              		classify:classify,
              		needNum:needNum,
              		money:money,
              		hondaId:hondaId,
              		cityId:cityId,
              		acitvityPlace:acitvityPlace,
              		beginTime:$('.timeks').val(),
              		endTime:$('.timejs').val(),
              		enrollEndTime:$('.timejz').val(),
              		postContent:JSON.stringify(postContenta),
              	},
              	success: function(res){
              	   console.log(res);
              	}
              });
           });
})


