$(function(){
    $sort = $('.sort');
    $sort.click(function(e){
    	var $sortBy = $(this);
    	var enable = $sortBy.attr("data-sort-enable");
    	if(enable == true || enable == "true"){
    		var $orderProperty = $("#orderProperty");
        	var $orderDirection = $("#orderDirection");
        	var $tableListForm = $("#tableListForm");
        	
        	var sortKey = $sortBy.attr("data-sort-key");
        	var sortValue = $sortBy.attr("data-sort-value");
        	var sordImg = $sort.find("span");
        	if(sortValue == "asc"){
        		$sortBy.attr("data-sort-value", "desc");
        		sordImg.removeClass("glyphicon-arrow-up");
        		sordImg.addClass("glyphicon-arrow-down");
        	}else if(sortValue == "desc"){
        		$sortBy.attr("data-sort-value", "asc");
        		sordImg.removeClass("glyphicon-arrow-down");
        		sordImg.addClass("glyphicon-arrow-up");
        	}
        	
        	sortKey = $sortBy.attr("data-sort-key");
        	sortValue = $sortBy.attr("data-sort-value");
        	
        	$orderProperty.val(sortKey);
        	$orderDirection.val(sortValue);
        	
        	$tableListForm.submit();
    	}
    });
});