$(document).ready(function() {
	  $('.collapse.in').prev('.panel-heading').addClass('active');
	  $('#accordion, #bs-collapse')
	    .on('show.bs.collapse', function(a) {
	      $(a.target).prev('.panel-heading').addClass('active');
	    })
	    .on('hide.bs.collapse', function(a) {
	      $(a.target).prev('.panel-heading').removeClass('active');
	    });
	  
	  $('.collapse.in').prev('.panel-sub-heading').addClass('active');
	  $('#accordion, #bs-collapse')
	    .on('show.bs.collapse', function(a) {
	      $(a.target).prev('.panel-sub-heading').addClass('active');
	    })
	    .on('hide.bs.collapse', function(a) {
	      $(a.target).prev('.panel-sub-heading').removeClass('active');
	    });
	  
	  	$(".panel-group ul").each(function(){
		    var $ul = $(this);
		    var oldStyle = $($ul).attr("style") == undefined ? "" : $($ul).attr("style");
		    var newStyle = oldStyle + "border-top-left-radius: 0; border-top-right-radius: 0;border-bottom-right-radius: 0;border-bottom-left-radius: 0;";
		    $($ul).attr("style", newStyle);
		});
	  	
	  	$(".panel-group li").each(function(){
		    var $li = $(this);
		    var oldStyle = $($li).attr("style") == undefined ? "" : $($li).attr("style");
		    var newStyle = oldStyle + "border-top-left-radius: 0; border-top-right-radius: 0;border-bottom-right-radius: 0;border-bottom-left-radius: 0;";
		    $($li).attr("style", newStyle);
		    $($li).attr("style", newStyle);
		    
		});
	  	
	  	$(".panel-group a").each(function(){
		    var $a = $(this);
		    var oldStyle = $($a).attr("style") == undefined ? "" : $($a).attr("style");
		    var newStyle = oldStyle + "border: none;border-top-left-radius: 0; border-top-right-radius: 0;border-bottom-right-radius: 0;border-bottom-left-radius: 0;";
		    $($a).attr("style", newStyle);
		    $($a).attr("style", newStyle);
		});
	  	
	  	$(".panel-group .menu-item").click(function(){
	  		var liGroup = $(".panel-group li");
	  		for(var i = 0; i < liGroup.length; i ++){
  	    		$(liGroup[i]).css("background-color","#fff");
  	    		$(liGroup[i]).children().css("background-color","#fff");
  	    		$(liGroup[i]).children().children().css("background-color","#fff");
  	    		$(liGroup[i]).children().children().children().css("background-color","#fff");
  	    		$(liGroup[i]).children().children().children().children().css("background-color","#fff");
	  		}
	  		
	  		var listGroup = $(".panel-group .menu-item");
	  		for(var i = 0; i < listGroup.length; i ++){
	  			var $subEnum = listGroup[i];
	  			$($subEnum).removeClass("click-active");
	  			$($subEnum).parent().removeClass("click-active");
	  			$($subEnum).css("background-color","#fff");
	  			$($subEnum).parent().css("background-color","#fff");
	  		}
	  		var $click = $(this);
	  		$($click).addClass("click-active");
	  		$($click).parent().addClass("click-active");
	  		$($click).parent().css("background-color","#ececec");
	  		$($click).css("background-color","#ececec");
	  		$($click).parent().css("background-color","#ececec");
	  	});
	  	
	  	 $(".panel-group li").hover(function(){
		  	    $(this).css("background-color","#ececec");
		  	  	$(this).children().css("background-color","#ececec");
		  	  	var clickStyle = $(this).children().attr("class");
		  	    var bool = clickStyle.indexOf("panel-sub");
	  	    	//返回大于等于0的整数值，若不包含"Text"则返回"-1。
	  	    	if(bool==0){
	  	    		$(this).children().children().css("background-color","#ececec");
	  	    		$(this).children().children().children().css("background-color","#ececec");
	  	    		$(this).children().children().children().children().css("background-color","#ececec");
	  	    	}
	  	    },function(){
	  	    	var clickStyle = $(this).attr("class");
	  	    	var bool = clickStyle.indexOf("click-active");
	  	    	//返回大于等于0的整数值，若不包含"Text"则返回"-1。
	  	    	if(bool<0){
			  	    $(this).css("background-color","#fff");
			  	    $(this).children().css("background-color","#fff");
			  	    var subClickStyle = $(this).children().attr("class");
			  	    var bool = subClickStyle.indexOf("panel-sub");
		  	    	//返回大于等于0的整数值，若不包含"Text"则返回"-1。
		  	    	if(bool==0){
		  	    		$(this).children().children().css("background-color","#fff");
		  	    		$(this).children().children().children().css("background-color","#fff");
		  	    		$(this).children().children().children().children().css("background-color","#fff");
		  	    	}
	  	    	}
	  	  });
	});
	
	function showModal(options){
		var modal = $.scojs_modal(options);
		modal.show();
	}
	
	function closeModal(){
		var modal = $.scojs_modal({
	  		keyboard: true
		});
		modal.show();
		modal.close();
	}