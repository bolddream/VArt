$(document).ready(function(){ 
    $('#addForm').validate({
       		rules:{
       			name:{	
       				required:true,
    				maxlength:50
       			},
       			notes:{	
       				required:false,
    				maxlength:255
       			}
       		},
			messages:{
				name:{
					required:"Please enter name!",
					maxlength:"Please enter no more than 50 characters.",
				},
				notes:{
					required:"Please enter notes!",
					maxlength:"Please enter no more than 255 characters.",
				}
       		},
       		submitHandler:function(form){
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
	
	enableCheckbox();
	
	$('input').iCheck({
        checkboxClass: 'icheckbox_flat-blue',
        radioClass: 'iradio_flat-blue'
    });
});

function closePage(){
	$(".modal-backdrop").remove();
	$('#modal').remove();
}

function enableCheckbox(){
  $ulbox = $(".checkVersion");
  var check = $("input[name=resources]");    	
  $ulbox.css({'background-color':'#fff'});
  $("input[name=resources]").each(function(){
	  $(this).attr("disabled", false);
  });
}