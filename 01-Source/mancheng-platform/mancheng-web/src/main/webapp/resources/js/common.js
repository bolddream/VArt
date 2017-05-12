/*
 * JavaScript - Common
 * Version: 3.0
 */

var setting = {
	priceScale: "2",
	priceRoundType: "roundHalfUp",
	uploadImageExtension: "jpg,jpeg,bmp,gif,png",
	uploadFlashExtension: "swf,flv",
	uploadMediaExtension: "swf,flv,mp3,wav,avi,rm,rmvb",
	uploadFileExtension: "zip,rar,7z,doc,docx,xls,xlsx,ppt,pptx"
};

var messages = {
	"admin.message.success": "操作成功",
	"admin.message.error": "操作错误",
	"admin.dialog.ok": "确&nbsp;&nbsp;定",
	"admin.dialog.cancel": "取&nbsp;&nbsp;消",
	"admin.dialog.deleteConfirm": "您确定要删除吗？",
	"admin.browser.title": "选择文件",
	"admin.browser.upload": "本地上传",
	"admin.browser.parent": "上级目录",
	"admin.browser.orderType": "排序方式",
	"admin.browser.name": "名称",
	"admin.browser.size": "大小",
	"admin.browser.type": "类型",
	"admin.browser.select": "选择文件",
	"admin.upload.sizeInvalid": "上传文件大小超出限制",
	"admin.upload.typeInvalid": "上传文件格式不正确",
	"admin.upload.invalid": "上传文件格式或大小不正确",
	"admin.validate.required": "必填",
	"admin.validate.email": "E-mail格式错误",
	"admin.validate.url": "网址格式错误",
	"admin.validate.date": "日期格式错误",
	"admin.validate.dateISO": "日期格式错误",
	"admin.validate.number": "只允许输入数字",
	"admin.validate.digits": "只允许输入零或正整数",
	"admin.validate.minlength": "长度不允许小于{0}",
	"admin.validate.maxlength": "长度不允许大于{0}",
	"admin.validate.rangelength": "长度必须在{0}-{1}之间",
	"admin.validate.min": "不允许小于{0}",
	"admin.validate.max": "不允许大于{0}",
	"admin.validate.range": "必须在{0}-{1}之间",
	"admin.validate.accept": "输入后缀错误",
	"admin.validate.equalTo": "两次输入不一致",
	"admin.validate.remote": "输入错误",
	"admin.validate.integer": "只允许输入整数",
	"admin.validate.positive": "只允许输入正数",
	"admin.validate.negative": "只允许输入负数",
	"admin.validate.decimal": "数值超出了允许范围",
	"admin.validate.pattern": "格式错误",
	"admin.validate.extension": "文件格式错误",
	"report.ruleTrend.date.null": "请选择日期",
	"report.ruleTrend.rule.null":"请选择规则名称",
	"report.ruleTrend.title":"单一检核规则趋势分析",
	"report.singleTable.title":"单表数据分析",
	"report.tableTrend.title":"表级趋势分析",
	"report.singleTable.proj.null":"请选择方案",
	"report.singleTable.tipCount":"数量",
	"report.singleTable.table.null":"请选择表",
	"report.xAxis.time":"时间",
	"report.no.records":"无记录",
};

// 添加Cookie
function addCookie(name, value, options) {
	if (arguments.length > 1 && name != null) {
		if (options == null) {
			options = {};
		}
		if (value == null) {
			options.expires = -1;
		}
		if (typeof options.expires == "number") {
			var time = options.expires;
			var expires = options.expires = new Date();
			expires.setTime(expires.getTime() + time * 1000);
		}
		document.cookie = encodeURIComponent(String(name)) + "=" + encodeURIComponent(String(value)) + (options.expires ? "; expires=" + options.expires.toUTCString() : "") + (options.path ? "; path=" + options.path : "") + (options.domain ? "; domain=" + options.domain : ""), (options.secure ? "; secure" : "");
	}
}

// 获取Cookie
function getCookie(name) {
	if (name != null) {
		var value = new RegExp("(?:^|; )" + encodeURIComponent(String(name)) + "=([^;]*)").exec(document.cookie);
		return value ? decodeURIComponent(value[1]) : null;
	}
}

// 移除Cookie
function removeCookie(name, options) {
	addCookie(name, null, options);
}

// 多语言
function message(code) {
	if (code != null) {
		var content = messages[code] != null ? messages[code] : code;
		if (arguments.length == 1) {
			return content;
		} else {
			if ($.isArray(arguments[1])) {
				$.each(arguments[1], function(i, n) {
					content = content.replace(new RegExp("\\{" + i + "\\}", "g"), n);
				});
				return content;
			} else {
				$.each(Array.prototype.slice.apply(arguments).slice(1), function(i, n) {
					content = content.replace(new RegExp("\\{" + i + "\\}", "g"), n);
				});
				return content;
			}
		}
	}
}

//获取所有业务系统信息
function getAllSysInfo(selectSysInfo){
	$.ajax({
		url: "../metadata/findAllSysInfo.jhtml",
		type: "POST",
		data: {},
		dataType: "json",
		cache: false,
		success: function(message) {
			selectSysInfo.empty();
			selectSysInfo.append("<option value=''>-请选择-</option>");
			for (var i=0; i < message.length; i ++){
				var option = "<option value='" + message[i].id + "'>" + message[i].sysName + "</option>";
				selectSysInfo.append(option);
			}
		}
	});
}

//获取业务表
function getTableInfos(sysInfoId, selectSysTableInfo){
	//console.log('---- sysInfoId:' + sysInfoId);
	$.ajax({
		url: "../metadata/findTableBySysInfo.jhtml",
		type: "POST",
		data: {"sysInfoId": sysInfoId},
		dataType: "json",
		cache: false,
		success: function(message) {
			selectSysTableInfo.empty();
			selectSysTableInfo.append("<option value=''>-请选择-</option>");
			for (var i=0; i < message.length; i ++){
				//console.log(message[i].id);
				//console.log(message[i].tableName);
				var option = "<option value='" + message[i].id + "'>" + message[i].tableName + "</option>";
				selectSysTableInfo.append(option);
			}

		}
	});
}

//获取业务系统字段
function getColumnInfos(sysTableInfoId, selectSysColumnInfo){
	
	$.ajax({
		url: "../metadata/findColumnByTableInfo.jhtml",
		type: "POST",
		data: {"sysTableId": sysTableInfoId},
		dataType: "json",
		cache: false,
		success: function(message) {
			selectSysColumnInfo.empty();
			selectSysColumnInfo.append("<option value=''>-请选择-</option>");
			for (var i=0; i < message.length; i ++){
				var option = "<option value='" + message[i].id + "'>" + message[i].columnName + "</option>";
				selectSysColumnInfo.append(option);
			}
		}
	});
}

(function($) {

	var zIndex = 100;
	
	// 消息框
	var $message;
	var messageTimer;
	$.message = function() {
		var message = {};
		if ($.isPlainObject(arguments[0])) {
			message = arguments[0];
		} else if (typeof arguments[0] === "string" && typeof arguments[1] === "string") {
			message.type = arguments[0];
			message.content = arguments[1];
		} else {
			return false;
		}
		
		if (message.type == null || message.content == null) {
			return false;
		}
		
		if ($message == null) {
			$message = $('<div class="xxMessage"><div class="messageContent message' + message.type + 'Icon"><\/div><\/div>');
			if (!window.XMLHttpRequest) {
				$message.append('<iframe class="messageIframe"><\/iframe>');
			}
			$message.appendTo("body");
		}
		
		$message.children("div").removeClass("messagewarnIcon messageerrorIcon messagesuccessIcon").addClass("message" + message.type + "Icon").html(message.content);
		$message.css({"margin-left": - parseInt($message.outerWidth() / 2), "z-index": zIndex ++}).show();
		
		clearTimeout(messageTimer);
		messageTimer = setTimeout(function() {
			$message.hide();
		}, 3000);
		return $message;
	}

	// 对话框
	$.dialog = function(options) {
		var settings = {
			width: 320,
			height: "auto",
			top:null,
			left:null,
			modal: true,
			ok: message("admin.dialog.ok"),
			cancel: message("admin.dialog.cancel"),
			onShow: null,
			onClose: null,
			onOk: null,
			onCancel: null,
			close:true
		};
		$.extend(settings, options);
		
		if (settings.content == null) {
			return false;
		}
		
		var $dialog = $('<div class="modal-dialog xxDialog"><\/div>');
		var $dialogWrap = $('<div class="modal-content"><\/div>');
		var $dialogHeader = $('<div class="modal-header"><\/div>');
		var $dialogTitle;
		var $dialogClose ;
		var $dialogContent;
		var $dialogBottom;
		var $dialogOk;
		var $dialogCancel;
		var $dialogOverlay;
		$dialogWrap.appendTo($dialog);
		$dialogHeader.appendTo($dialogWrap);
		if(settings.close){
			$dialogClose = $('<button data-dismiss="modal" class="close" type="button"><span aria-hidden="true"><i class="fa fa-times"></i></span><span class="sr-only">Close</span><\/button>').appendTo($dialogHeader);
		}
		if (settings.title != null) {
			$dialogTitle = $('<h4 class="modal-title"></h4>').appendTo($dialogHeader);
		}
		if (settings.type != null) {
			$dialogContent = $('<div class="modal-body dialog' + settings.type + 'Icon"><\/div>').appendTo($dialogWrap);
		} else {
			$dialogContent = $('<div class="modal-body"><\/div>').appendTo($dialogWrap);
		}
		if (settings.ok != null || settings.cancel != null) {
			$dialogBottom = $('<div class="modal-footer"><\/div>').appendTo($dialogWrap);
		}
		if (settings.ok != null) {
			$dialogOk = $('<button  class="btn btn-default" type="button" >' + settings.ok + '<\/button>').appendTo($dialogBottom);
		}
		if (settings.cancel != null) {
			$dialogCancel = $('<button type="button" data-dismiss="modal" class="btn btn-default">' + settings.cancel + '<\/button>').appendTo($dialogBottom);
		}
		if (!window.XMLHttpRequest) {
			$dialog.append('<iframe class="dialogIframe"><\/iframe>');
		}
		$dialog.appendTo("body");
		if (settings.modal) {
			$dialogOverlay = $('<div class="dialogOverlay"><\/div>').insertAfter($dialog);
		}
		
		var dialogX;
		var dialogY;
		if (settings.title != null) {
			$dialogTitle.text(settings.title);
		}
		$dialogContent.html(settings.content);
		$dialog.css({"width": settings.width, "height": "auto", "margin-left": - (parseInt(settings.width / 2)), "z-index": zIndex ++});
		$(".modal-body").css({"height": settings.height});
		if(settings.top!=null){
			$dialog.css({"top": settings.top});
		}
		if(settings.left!=null){
			$dialog.css({ "left": settings.left});
		}
		dialogShow();
		
		if ($dialogTitle != null) {
			$dialogTitle.mousedown(function(event) {
				$dialog.css({"z-index": zIndex ++});
				var offset = $(this).offset();
				if (!window.XMLHttpRequest) {
					dialogX = event.clientX - offset.left;
					dialogY = event.clientY - offset.top;
				} else {
					dialogX = event.pageX - offset.left;
					dialogY = event.pageY - offset.top;
				}
				$("body").bind("mousemove", function(event) {
					$dialog.css({"top": event.clientY - dialogY, "left": event.clientX - dialogX, "margin": 0});
				});
				return false;
			}).mouseup(function() {
				$("body").unbind("mousemove");
				return false;
			});
		}
		
		if ($dialogClose != null) {
			$dialogClose.click(function() {
				dialogClose();
				return false;
			});
		}
		
		if ($dialogOk != null) {
			$dialogOk.click(function() {
				if (settings.onOk && typeof settings.onOk == "function") {
					if (settings.onOk($dialog) != false) {
						dialogClose();
					}
				} else {
					dialogClose();
				}
				return false;
			});
		}
		
		if ($dialogCancel != null) {
			$dialogCancel.click(function() {
				if (settings.onCancel && typeof settings.onCancel == "function") {
					if (settings.onCancel($dialog) != false) {
						dialogClose();
					}
				} else {
					dialogClose();
				}
				return false;
			});
		}
		
		function dialogShow() {
			if (settings.onShow && typeof settings.onShow == "function") {
				if (settings.onShow($dialog) != false) {
					$dialog.show();
					$dialogOverlay.show();
				}
			} else {
				$dialog.show();
				$dialogOverlay.show();
			}
		}
		function dialogClose() {
			if (settings.onClose && typeof settings.onClose == "function") {
				if (settings.onClose($dialog) != false) {
					$dialogOverlay.remove();
					$dialog.remove();
				}
			} else {
				$dialogOverlay.remove();
				$dialog.remove();
			}
		}
		return $dialog;
	}

	

	// 令牌
	$(document).ajaxSend(function(event, request, settings) {
		if (!settings.crossDomain && settings.type != null && settings.type.toLowerCase() == "post") {
			var token = getCookie("token");
			if (token != null) {
				request.setRequestHeader("token", token);
			}
		}
	});
	
	$(document).ajaxComplete(function(event, request, settings) {
		var loginStatus = request.getResponseHeader("loginStatus");
		var tokenStatus = request.getResponseHeader("tokenStatus");
		
		if (loginStatus == "accessDenied") {
			$.message("warn", "登录超时，请重新登录");
			setTimeout(function() {
				location.reload(true);
			}, 2000);
		} else if (loginStatus == "unauthorized") {
			$.message("warn", "对不起，您无此操作权限！");
		} else if (tokenStatus == "accessDenied") {
			var token = getCookie("token");
			if (token != null) {
				$.extend(settings, {
					global: false,
					headers: {token: token}
				});
				$.ajax(settings);
			}
		}
	});

})(jQuery);

// 令牌
$().ready(function() {
	
	$("form").submit(function() {
		var $this = $(this);
		if ($this.attr("method") != null && $this.attr("method").toLowerCase() == "post" && $this.find("input[name='token']").size() == 0) {
			var token = getCookie("token");
			if (token != null) {
				$this.append('<input type="hidden" name="token" value="' + token + '" \/>');
			}
		}
	});
	
// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

});
function alertMsg(desc, type){
	window.parent.$.scojs_message(desc, type);
}