$(function(){
	var $add = $("#addOtaFlash");
	$add.click(function(){
		 modal = $.scojs_modal({
				      title:'Add New OTA Device',
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
   	var $pathType = $("#pathType");
   	var $flashType = $("#flashType");
   	var $pushPath = $("#pushPath");

   	$model.chosen();
	$device.chosen();
    $pathType.chosen();
	$flashType.chosen();
    
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

    
    $('#addOTADevice_form').validate({
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
				$('#addOTADevice_form').ajaxSubmit({
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
//	$('#model').change(function(e){
//		var $this = $(this);
//		$.ajax({
//			url:"getDevicesByModel.jhtml?t="+(new Date).valueOf(),
//			type:"post",
//			traditional : true,
//			data : {
//				"modelId" :$this.val()
//			},
//			success:function(result,response,status){
//				var html="";
//				for(var i=0;i<result.length;i++){
//					html+="<option value="+result[i].id+">"+result[i].name+"</option>";
//				}
//				$("#device").html(html);
//			}
//		});
//		
//	});	
	
});

function editOtaDevice(id){
	var url = "edit.jhtml?id=" + id+"&t="+(new Date).valueOf()
	if(id == null || id == "" || id == "null" || id == undefined){
		url = "edit.jhtml";
	}
	editModal = $.scojs_modal({
	      title:'Edit OTA Device',
	      width:600,
	      height:600,
	      remote:url
	   });
	editModal.show();
}

$('#editForm').validate({
	rules:{
   		pushPath:{
			maxlength:200,
			required:true
		}
	},
		messages:{
			pushPath:{
				required:"Please enter recorvery path!",
				maxlength:"Please enter no more than 200 characters.",
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

function deleteOtaDevice(id,name){
	$this =$(this);
	var msg="";
	deleteConfrim = $.scojs_modal({
	      title:'Delete OTA Device',
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
