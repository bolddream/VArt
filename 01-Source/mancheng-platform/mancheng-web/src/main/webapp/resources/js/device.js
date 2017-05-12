$(function(){
	var $addDevice = $("#addDevice");
	$addDevice.click(function(){
		 modal = $.scojs_modal({
				      title:'Add New Device',
				      width:590,
				      height:600,
				      remote:"add.jhtml"
				   });
		 modal.show();
	});	
	   
 	var oldName = $("#name").val();
   	var $model = $("#model");  
   	var $hwCode = $("#hwCode");
//   	var $trackid = $("#trackid");
//   	var $pn = $("#pn");
   	var $carrier = $("#carrier");
   	var $memory = $("#memory");
   	var $simCount = $("#simCount");
   	var $color = $("#color");
   	var $region = $("#regionId");
   	var $chipset = $("#chipset");
   	var $icon = $("#icon");
   	var $projectName = $("#projectName");
   	
	$model.chosen();
    $chipset.chosen();
    $simCount.chosen();
    $region.chosen();
    $carrier.chosen();
    $icon.chosen();
    
    $model.change(function(){
  	  var modelId = $model.val();
  	  //show projectName
//  	  $.ajax({
//			url: "getProjectName.jhtml",
//			type: "POST",
//			dataType: "json",
//			cache: false,
//			data: {"id":modelId},
//			success:function(projectName){
//				$projectName.text(projectName);					
//			}
        //zhanglu 2017-03-10: 不需要再次请求后台获取ProjectName
  	    var projectNameText = $model.find("option:selected").attr("projectName");
  	    if(projectNameText.length > 36){//...省略显示
  	    	projectNameText = "<span title='"+projectNameText+"'>"+projectNameText.substring(0,36)+"...</span>";
  	    }
    	$projectName.html(projectNameText);	
    	
  	  //get chipset list by model's platform 
  	  $.ajax({
  				url: "getChipsetByModel.jhtml",
  				type: "POST",
  				dataType: "json",
  				cache: false,
  				data: {"modelId":modelId},
  				success:function(chipsets){
  						$chipset.html("");
  						if(chipsets){
  							//$chipset.append("<option value=''>Please select...</option>");
  							if(chipsets.length>0){	
  							     for(var i=0;i<chipsets.length;i++){
  								  	$chipset.append("<option value='"+chipsets[i].id+"'>"+chipsets[i].name+"</option>");
  								 }  							 
  							 }
  							 
  							$chipset.chosen('destroy');
  							$chipset.chosen();
  						}else{
  							console.log(chipsets);
  						}
  				}
  	});  
  });
    
    $region.change(function(){
    	  var regionId = $region.val();
    	  $.ajax({
    				url: "getCarrierByRegion.jhtml",
    				type: "POST",
    				dataType: "json",
    				cache: false,
    				data: {"regionId":regionId},
    				success:function(carriers){
    						$carrier.html("");
    						if(carriers){
    							//$carrier.append("<option value=''>Please select...</option>");    							
    						     if(carriers.length>0){
    							     for(var i=0;i<carriers.length;i++){
    								  	$carrier.append("<option value='"+carriers[i].id+"'>"+carriers[i].name+"</option>");
    								 }
    							}
    							$carrier.chosen('destroy');
    							$carrier.chosen();
    						}else{
    							console.log(carriers);
    						}
    				}
    	});  
    });

	jQuery.validator.addMethod("isLength2", function(value, element) {     
		if ($.trim(value).length>0&&value.length != 2){
			return false;
		}
		if(!/^[0-9]*$/.test(value)){
			return false;
		}
		return true;  
	}, "Invalid hwCode!");

    $('#addForm').validate({
       		rules:{
       			name:{
       				required:true,
       				maxlength:50,
       				remote: {
       					url: "uniqueNameIdentifier.jhtml",
						cache: false,
						type:"get",
						data:{
							name:function(){
								return $("#name").val();
							},
							oldName: function(){
								return oldName;
							}
						}
       				}
       			},
       			hwCode:{					
					isLength2:true,
					remote: {
						url: "uniqueIdentifier.jhtml",
						cache: false,
						type:"get",
						data:{
							hwCode:function(){
								console.log($("#hwCode").val());
								return $("#hwCode").val();
							},
							model:function(){
								console.log($("#model").val());
								return $("#model").val();
							},
							carrier:function(){
								return $('#carrier').val();
							}
						}				
					}
				},
				note:{
					maxlength:255
				},
				memory:{
					maxlength:20
				},
				color:{
					maxlength:20
				},
				simCount:{
       				required:true
       			},
       			model:{
       				required:true
       			}
    	},
			messages:{
				name:{				
					required:"Please input device name!",
					maxlength:"Please enter no more than 50 characters.",
					remote:"Device name already existed!"
				},
				hwCode:{				
					isLength2:"Invalid length or number!",
					remote:"Device already existed!"
				},
				model:{
					required:"Please choose a model!"
				},
				color:{
					maxlength:"Please enter no more than 20 characters."
				},
				note:{
					maxlength:"Please enter no more than 255 characters."
				},
				simCount:{
					required:"Please choose simCount!"
				},
				memory:{
					maxlength:"Please enter no more than 20 characters."
				}
    	
       		},
       		submitHandler:function(form){       			
       			var simCount = $("#simCount").val();
       			if(simCount==undefined||simCount==""){
           			alertMsg("Sim count can not be empty!", $.scojs_message.TYPE_ERROR);
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

    $('#editForm').validate({
    		rules:{
       			name:{
       				required:true,
       				maxlength:50,
       				remote: {
       					url: "uniqueNameIdentifier.jhtml",
    					cache: false,
    					type:"get",
    					data:{
    						name:function(){
    							return $("#name").val();
    						},
    						oldName: function(){
    							return oldName;
    						}
    					}
       				}
       			},
    			hwCode:{					
    			isLength2:true,
    			remote: {
    				url: "uniqueIdentifier.jhtml",
    				cache: false,
    				type:"get",
    				data:{
    					hwCode:function(){
    						console.log($("#hwCode").val());
    						return $("#hwCode").val();
    					},
    					model:function(){
    						console.log($("#model").val());
    						return $("#model").val();
    					},
    					carrier:function(){
    						return $('#carrier').val();
    					}
    				}				
    			}
    		},
    		trackid:{
    			maxlength:10
    		},
    		pn:{
    			maxlength:30
    		},
    		memory:{
    			maxlength:20
    		},
    		color:{
    			maxlength:20
    		},
    		carrier:{
    				required:true
    			},
    			model:{
    				required:true
    			}
    },
    	messages:{
    		name:{				
    			required:"Please input device name!",
    			maxlength:"Please enter no more than 50 characters.",
    			remote:"Device name already existed!"
    		},
    		hwCode:{				
    			isLength2:"Invalid length or number!",
    			remote:"Device already existed!"
    		},
    		model:{
    			required:"Please choose a model!"
    		},
    		carrier:{
    			required:"Please choose a carrier!"
    		},
    		color:{
    			maxlength:"Please enter no more than 20 characters.",
    		},
    		memory:{
    			maxlength:"Please enter no more than 20 characters.",
    		},
    		pn:{
    			required:"Please enter pn!",
    			maxlength:"Please enter no more than 30 characters.",
    		},
    		trackid:{
    			required:"Please enter trackid!",
    			maxlength:"Please enter no more than 10 characters.",
    		}

    		},
    		submitHandler:function(form){
    		$('#editForm').ajaxSubmit({
    	      	dataType:"json",
    	       	beforeSubmit:function(){
    	       		$("#savebtn").attr("disabled","disabled");
    	       	},
    	       	success:function(result){
    	       		debugger;
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
    
	$('#backbtn').click(function(){
		$(".modal-backdrop").remove();
		$('#modal').remove();
	});
});

function editDevice(id){
	var url = "edit.jhtml?id=" + id;
	if(id == null || id == "" || id == "null" || id == undefined){
		url = "edit.jhtml";
	}
	editModal = $.scojs_modal({
	      title:'Edit Device',
	      width:590,
	      height:600,
	      remote:url
	   });
	editModal.show();
}

function deleteDevice(id,name){
	$this =$(this);
	var msg="";
	deleteConfrim = $.scojs_modal({
	      title:'Delete Device',
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
						self.parent.alertMsg(data.desc, $.scojs_message.TYPE_ERROR,4000);						
					}
				}
			}); 
	});
}

function closePage(){
	$(".modal-backdrop").remove();
	$('#modal').remove();
}
