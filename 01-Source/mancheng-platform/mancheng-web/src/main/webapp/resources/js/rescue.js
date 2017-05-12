$(function(){
	var $add = $("#addDevice");
	$add.click(function(){
		 modal = $.scojs_modal({
				      title:'Add Rescue Device',
				      width:600,
				      height:600,
				      remote:"add.jhtml"
				   });
		 modal.show();
	});	
	
	var $editDiv =$(".edit_div");
 	$editDiv.mouseover(function(){
        var $this = $(this);
        $this.find(".more-grey").hide();
        $this.find(".more-orange").show();
        $this.find(".btn_div").show();
    });
    $editDiv.mouseout(function(){
        var $this = $(this);
        $this.find(".more-grey").show();
        $this.find(".more-orange").hide();
        $this.find(".btn_div").hide();
    });

    var $model = $("#model");
   	var $device = $("#device");
   	var $rescueTool = $("#rescueTool");
   	var $recipe = $("#recipe");

   	$model.chosen();
	$device.chosen();
    $recipe.chosen();
	$rescueTool.chosen();
    
    $model.change(function(){
  	  var modelId = $model.val();
  	  $.ajax({
  				url: "getDevicesByModel.jhtml?t="+(new Date).valueOf(),
  				type: "POST",
  				dataType: "json",
  				cache: false,
  				data: {"modelId":modelId},
  				success:function(devices){
  						$device.html("");
  						if(devices){
  							//$device.append("<option value=''>Please select...</option>");    							
  						     if(devices.length>0){
  							     for(var i=0;i<devices.length;i++){
  								  	$device.append("<option value='"+devices[i].id+"'>"+devices[i].name+"</option>");
  								 }
  							}
  							$device.chosen('destroy');
  							$device.chosen();
  						}else{
  							console.log(devices);
  						}
  				}
  	});  
  });

    
    $('#addForm').validate({
       	rules:{
       		pushPath:{
				maxlength:200,
				required:true
			},
			note:{
				maxlength:255
			}
    	},
			messages:{
				pushPath:{
					required:"Please enter recorvery path!",
					maxlength:"Please enter no more than 200 characters."
				},
				note:{
					maxlength:"Please enter no more than 255 characters."
				}
       		},
       		submitHandler:function(form){
       			var device = $("#device").val();
       			if(device==undefined||device==""){
           			alertMsg("Please choose a device!", $.scojs_message.TYPE_ERROR);
           			return;
       			}
				$('#addForm').ajaxSubmit({
			      	dataType:"json",
			       	beforeSubmit:function(){
			       		$("#savebtn").attr("disabled","disabled");
			       	},
			       	success:function(result){
			       		if(result.code =="0000"){			       		
			       			alertMsg(result.desc, $.scojs_message.TYPE_OK);
			       			$('#addForm').resetForm();
			       			closePage();
			       			setTimeout(function(){
			       				location.reload();
		       				},2000);
			       		}else{
			       			alertMsg(result.desc, $.scojs_message.TYPE_ERROR);
			       			$("#savebtn").attr("disabled",false);
			       		}
			       }
				});
       		}
       });
	$('#backbtn').click(function(){
		$(".modal-backdrop").remove();
		$('#modal').remove();
	});
});

function editDevice(id){
	var url = "edit.jhtml?id=" + id+"&t="+(new Date).valueOf()
	if(id == null || id == "" || id == "null" || id == undefined){
		url = "edit.jhtml";
	}
	editModal = $.scojs_modal({
	      title:'Edit Rescue Device',
	      width:600,
	      height:600,
	      remote:url
	   });
	editModal.show();
}

$('#editForm').validate({
	rules:{
   		rescueTool:{
			required:true
		}
	},
		messages:{
			rescueTool:{
				required:"Please choose rescue tool!"
			}
   		},
		submitHandler:function(form){
			var device = $("#device").val();
   			if(device==undefined||device==""){
       			alertMsg("Please choose a device!", $.scojs_message.TYPE_ERROR);
       			return;
   			}
		$('#editForm').ajaxSubmit({
	      	dataType:"json",
	       	beforeSubmit:function(){
	       		$("#savebtn").attr("disabled","disabled");
	       	},
	       	success:function(result){
	       		if(result.code =="0000"){			       		
	       			alertMsg(result.desc, $.scojs_message.TYPE_OK);
	       			$('#editForm').resetForm();
	       			closePage();
	       			setTimeout(function(){
	       				location.reload();
       				},2000);
	       		}else{
	       			alertMsg(result.desc, $.scojs_message.TYPE_ERROR);
	       			$("#savebtn").attr("disabled",false);
	       		}
	       }
		});
		}
});

function deleteRescueDevice(id,name){
	$this =$(this);
	var msg="";
	deleteConfrim = $.scojs_modal({
	      title:'Delete Rescue Device',
	      width:400,
	      height:200,
	      content: msg + "You really want to delete "+name+" ?",
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
function closePage(){
	$(".modal-backdrop").remove();
	$('#modal').remove();
}

function addRom(deviceId){
		var width = 1100;
		var url = "../rescueFlash/list.jhtml"
		var titelPrefix = '<span id="crashSpan" class="flashTabLine">Historical ROM:</span>';
		var searchImg = '<img onclick="searchCrashByFilter()" class="rescueSerchimg" src="/lmsa-web/resources/images/search.png" alt="search"/>';
		var inputHtml = '<span><input type="text" class="rescueSerchinput" id="searchKeyword" onkeypress="searchKeywordKeypressCrash(event)" name="keyword" />';

	  var flashing = $.scojs_modal({
        title:titelPrefix +  inputHtml + searchImg +
        			'</span><input type="hidden" id ="deviceId"  name="deviceId" value="'+deviceId+'">',
        width:width,
        height:560,
        content:'<iframe id="flashIframe" src='+url+'?deviceId='+deviceId+' style="width:100%;height:558px;border:none"/>'
     });	 
	  //window.parent.showModal(flashing);
	flashing.show();
}

function searchCrashByFilter(){
	var deviceId = $("#deviceId").val();
	var searchKeyword = $("#searchKeyword").val();
	if(searchKeyword !=null && searchKeyword.trim() != "" ){
			$("#flashIframe").attr("src","../rescueFlash/list.jhtml?deviceId="+deviceId+"&searchKeyword="+searchKeyword);
	}else{
			$("#flashIframe").attr("src","../rescueFlash/list.jhtml?deviceId="+deviceId);
	}
}

function backBtn(){
$(".modal-backdrop").remove();
$('#modal').remove();
setTimeout("location.href='javascript:location.reload()'",50);
}
function populateSelected(id,value){
	$("#"+id).html(value);
}
function searchKeywordKeypressCrash(event){
	if(event.keyCode == "13")    {
		searchCrashByFilter();
    }
}
