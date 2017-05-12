$(document).ready(function(){ 
   	var $platform = $("#platform");
   	var $vendor = $("#vendor");
   	var $brand = $("#brand");
   	var $signature = $("#signature");
   	
    $platform.chosen();
    $vendor.chosen();
    $brand.chosen();
    $signature.chosen();
    
    $('#addForm').validate({
       		rules:{
       			modelName:{	
       				required:true,
    				maxlength:50,
    				remote: {
    				    url: "checkModelNameIsUnique.jhtml",     
    				    type: "post",               
    				    dataType: "json",           
    				    data: {                     
    				    	modelName: function() {
    				            return $("#modelName").val();
    				        },
    				        oldName:function(){
    				        	return null;
    				        }
    				    },
    	                dataFilter: function (data, type) {
    	                    if (data == "true") {
    	                        return true;
    	                    } else {
    	                        return false;
    	                    }
    	                }
    				}
       			},
       			projectName:{	
       				required:true,
    				maxlength:50
       			},
       			marketName:{	
       				required:true,
    				maxlength:50
       			},
       			category:{
       				required:true,
    				maxlength:10
       			},
       			series:{
       				required:true,
    				maxlength:20
       			}
    	},
			messages:{
				modelName:{
					required:"Please enter model name!",
					maxlength:"Please enter no more than 50 characters.",
					remote:"Please enter other model name,this one is using!"
				},
				projectName:{
					required:"Please enter project name!",
					maxlength:"Please enter no more than 50 characters.",
				},
				marketName:{
					required:"Please enter market name!",
					maxlength:"Please enter no more than 50 characters.",
				},
				category:{
					required:"Please enter category!",
					maxlength:"Please enter no more than 10 characters.",
				},
				series:{
					required:"Please enter series!",
					maxlength:"Please enter no more than 20 characters.",
				}
       		},
       		submitHandler:function(form){
       			var brand = $("#brand").val();
       			if(brand==undefined){
           			alertMsg("brand can not be empty!", $.scojs_message.TYPE_ERROR);
           			return;
       			}
       			
       			var platform = $("#platform").val();
       			if(platform == undefined){
           			alertMsg("platform can not be empty!", $.scojs_message.TYPE_ERROR);
           			return;
       			}
       			
       			var vendor = $("#vendor").val();
       			if(vendor==undefined){
           			alertMsg("vendor can not be empty!", $.scojs_message.TYPE_ERROR);
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

function closePage(){
	$(".modal-backdrop").remove();
	$('#modal').remove();
}