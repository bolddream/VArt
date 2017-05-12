$(function(){
	  var $pageNumber = $("input[name='pageNumber']");
	  var $pageSkip = $("#pageSkip");
	  
	  // 页码跳转
		$pageSkip.click(function(){
			debugger;
			var skipNum = $("input[name='skipNum'").val();
			if(skipNum == "")
			{
				return;
			}
			if(!/^[0-9]*$/.test(skipNum))
			{
				return;
			}
			$pageNumber.val(skipNum);
			$("#tableListForm").submit();
		});
	  
	  var $inp = $('input'); //所有的input元素 
	  $inp.keydown(function(event){
	          if(event.which ==13){
	              debugger;
	              $pageSkip.click();
	          }
	        });
	  

	  
	var pageIds = ["#firstPage","#prePage","#min8Page","#min7Page","#min6Page","#min5Page",
	               "#min4Page","#min3Page","#min2Page","#min1Page","#minPage","#nextPage",
	               "#addPage","#add1Page","#add2Page","#add3Page","#add4Page","#add5Page",
	               "#add6Page","#add7Page","#add8Page","#lastPage"];
			
	for(var i=0;i<pageIds.length;i++){
		$(pageIds[i]).click(function() {
					var $this = $(this);
					$pageNumber.val($this.attr("data-pageNumber"));
					$("#tableListForm").submit();
				});	
	}
	
	var $search = $("#search");
	$search.click(function(){
		$("#tableListForm").submit();
  	});
	
	$desc = $("#desc");
	$asc = $("#asc");
	
	$desc.click(function(){
		
	});
	
	$asc.click(function(){
		
	});
})