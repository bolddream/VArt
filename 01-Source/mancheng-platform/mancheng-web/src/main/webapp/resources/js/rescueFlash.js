$(function(){
   	var $resource = $("#resource");
   	var $ccp = $("#countryCodePackage");
   	$resource.chosen();
   	$ccp.chosen();
	
	var $addRomForm = $("#addRomForm");
	var $editDiv =$(".edit_div");
 	$editDiv.mouseover(function(){
        var $this = $(this);
        $this.find("img").hide();
        $this.find(".btn_div").show();
    });
    $editDiv.mouseout(function(){
        var $this = $(this);
        $this.find("img").show();
        $this.find(".btn_div").hide();
    })
    $('#addRomForm').validate({
    	rules:{
    		version:{
    			required:true,
				maxlength:200
    		},
    		fingerPrint:{
    			required:true,
				maxlength:255
    		},
    		roCarrier:{
    			required:true,
				maxlength:100
    		},
    		fsgVersion:{
    			required:true,
				maxlength:255
    		}
    		
    	},
    		messages:{
    			version:{
    				required:"Please enter soft ware version!",
    				maxlength:"Please enter no more than 200 characters."
    			},
        		fingerPrint:{
        			required:"Please enter finger print!",
    				maxlength:"Please enter no more than 255 characters."
        		},
        		roCarrier:{
        			required:"Please enter ro carrier!",
    				maxlength:"Please enter no more than 100 characters."
        		},
        		fsgVersion:{
        			required:"Please enter baseband version!",
    				maxlength:"Please enter no more than 255 characters."
        		}
       		},
    		submitHandler:function(form){
    			var rom = $("#resource").val();
    			var $isLatest=$("#isLatest");
    			if(this.submitButton.value =="AddNew")
    				$isLatest.val(1);
    			var late = $isLatest.val();
       			if(late == 1 && (rom==undefined||rom=="")){
           			alertMsg("Please choose a rescue rom!", $.scojs_message.TYPE_ERROR);
           			return;
       			}       			
       			var ccp = $("#countryCodePackage").val();
       			if((ccp!=undefined && ccp!="") && (rom==undefined||rom=="")){
           			alertMsg("Please don't choose a ccp before choose a rom resource!", $.scojs_message.TYPE_ERROR);
           			return;
       			}       			
    		$('#addRomForm').ajaxSubmit({
    	      	dataType:"json",
    	       	beforeSubmit:function(){
    	       		$("#saveNewbtn").attr("disabled","disabled");
    	       	},
    	       	success:function(result){
    	       		if(result.code =="0000"){
    	    			debugger;
    	       			alertMsg(result.desc, $.scojs_message.TYPE_OK);
    	       			$('#addRomForm').resetForm();
    	       			$("#resource").val("");
    	       			$('select').attr('selectedIndex', 0);
    	       			$("#tipspan").html("Add ROM");
    	       			$("#saveNewbtn").attr("disabled",false);
    	       		}else{
    	       			alertMsg(result.desc, $.scojs_message.TYPE_ERROR);
    	       			$("#saveNewbtn").attr("disabled",false);
    	       		}
    	       }
    		});
    		}
    });

});
function editFlash(id){
	  $.ajax({
			url: "editFlash.jhtml?t="+(new Date).valueOf(),
			type: "POST",
			dataType: "json",
			cache: false,
			data: {"flashId":id},
			success:function(flash){
				$("#flashId").val(flash.id);
				$("#isLatest").val(flash.isLatest);
				$("#tipspan").html("Edit ROM");
				$("#version").val(flash.rescueRom.version);
				$("#fingerPrint").val(flash.rescueRom.fingerPrint); 
				$("#roCarrier").val(flash.rescueRom.roCarrier);
				$("#releaseNote").val(flash.rescueRom.releaseNote); 
				$("#fsgVersion").val(flash.rescueRom.fsgVersion); 
				$("#blurVersion").val(flash.rescueRom.blurVersion);
				$("#resource").val(flash.rescueRom.resource.id);
			    $("#countryCodePackage").val(flash.rescueRom.countryCodePackage.id);
			    
			}
	  }); 
}
function deleteFlash(flashId){
	$this=$(this.event.target);
	var confirm = $.scojs_confirm({
		  content: "Are you sure to delete?",
		  action: function() {
			  this.close();
			   $.ajax({
					url: "../rescueFlash/deleteFlash.jhtml",
					type: "get",
					traditional : true,
					data:{
						"flashId":flashId
					},
					success: function(result) {
						if(result.code =="0000"){			       		
			       			alertMsg(result.desc, $.scojs_message.TYPE_OK);
			       			$this.parent().parent().remove();
			       		}else{
			       			alertMsg(result.desc, $.scojs_message.TYPE_ERROR);
			       		}
					}
				});			
				
		  }
		});
		confirm.show();
}