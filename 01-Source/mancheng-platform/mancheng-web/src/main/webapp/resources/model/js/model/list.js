$(function(){
	var $addModel = $("#addModel");
  	
	$addModel.click(function(){
		 modal = $.scojs_modal({
				      title:'Add New Model',
				      width:590,
				      height:600,
				      remote:"add.jhtml"
				   });
		 modal.show();
	});		
})

function editModel(id){
	var url = "edit.jhtml?id=" + id;
	if(id == null || id == "" || id == "null" || id == undefined){
		url = "edit.jhtml";
	}
	editModal = $.scojs_modal({
	      title:'Edit Model',
	      width:590,
	      height:600,
	      remote:url
	   });
	editModal.show();
}

function deleteModel(id,name){
	$this =$(this);
	var msg="";
	deleteConfrim = $.scojs_modal({
	      title:'Delete Model',
	      width:400,
	      height:200,
	      content: msg + "You really want to delete "+name+"?",
	      target:"#deleteConfrim"
	   });
	deleteConfrim.show();
	
	//Cancel
	$("#cancelbtn").click(function(){
		deleteConfrim.close();
	});
	
	//Delete
	$("#deletebtn").click(function(){
	    $.ajax({
		url: "delete.jhtml",
		type: "get",
		data: {id: id},
		dataType: "json",
		cache: false,
		async: false,
		success: function(data) {
				if(data.code == "0000"){
					alertMsg(data.desc, $.scojs_message.TYPE_OK);
					setTimeout(function(){
						deleteConfrim.close();
						location.reload();
					},2000);
				}else{
					alertMsg(data.desc, $.scojs_message.TYPE_ERROR,4000);						
				}
			}
		}); 
	});	
}