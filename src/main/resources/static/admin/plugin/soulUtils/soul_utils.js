BaseConfig = {
	loginUrl : 'static/login.html',
	preUrl : '../../',
}

AjaxUtil = {
	url : '',
	// 判断是否登录
	isLogin : function(token) {
		// return AjaxUtil.getJson(AjaxUtil.url +
		// "/staff/isLogin.do?token="+token);
		return AjaxUtil.getJson(AjaxUtil.url + "staff/isLogin?token=" + token);
	},

	preDeal : function(resp) { // 预处理请求结果
		if (resp == null) {
			SwalUtil.error("请求失败,请刷新浏览器后重试!");
			return null;
		}
		if (resp.code == 200) { // 成功
			return resp;
		}

		if (resp.code == 422) { // 还没有登录,跳转到登录的页面
			SwalUtil.error("您还没有登录,请先登录!");
			setTimeout(function() { // 延时2s后执行
				window.location.href = BaseConfig.loginUrl;
			}, 1500);
			return null;
		}
		if (resp.code == 403) { // 没有权限
			SwalUtil.error("没有权限,请联系管理员!");
			return null;
		}

		if (resp.code == 412) { // 参数错误!
			SwalUtil.error(resp.msg);
			return null;
		}

		SwalUtil.error(resp.msg);
		return null;
	},

	// 获取对象(get请求)
	getJson : function(url) {
		try {
			var obj = $.ajax({
				url : BaseConfig.preUrl + url,
				async : false,
				dataType : 'json',
				error : function() {
					SwalUtil.error("请求失败,请刷新浏览器后重试!");
					return null;
				}
			});

			return AjaxUtil.preDeal(obj.responseJSON);
		} catch (e) {
			SwalUtil.error("请求失败,请刷新浏览器后重试!");
			return null;
		}
	},

	// post获取json
	postJson : function(url, params) {
		try {
			var obj = $.ajax({
				url : BaseConfig.preUrl +  url,
				async : false,
				type : "POST",
				data : params,
				dataType : 'json',
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				error : function(responseText, status, statusText) {
					console.log(statusText);
					SwalUtil.error("请求失败,请刷新浏览器后重试!");
					return null;
				}
			});
			return AjaxUtil.preDeal(obj.responseJSON);
		} catch (e) {
			SwalUtil.error("请求失败,请刷新浏览器后重试!");
			return null;
		}
	},
	//delete ajax封装
    deleteJson : function(url, params) {
        try {
            var obj = $.ajax({
                url : BaseConfig.preUrl +  url,
                async : false,
                type : "DELETE",
                data : params,
                dataType : 'json',
                contentType : "application/x-www-form-urlencoded;charset=utf-8",
                error : function(responseText, status, statusText) {
                    console.log(statusText);
                    SwalUtil.error("请求失败,请刷新浏览器后重试!");
                    return null;
                }
            });
            return AjaxUtil.preDeal(obj.responseJSON);
        } catch (e) {
            SwalUtil.error("请求失败,请刷新浏览器后重试!");
            return null;
        }
    },
	//ajax put封装
    putJson : function(url, params) {
        try {
            var obj = $.ajax({
                url : BaseConfig.preUrl +  url,
                async : false,
                type : "PUT",
                data : params,
                dataType : 'json',
                contentType : "application/x-www-form-urlencoded;charset=utf-8",
                error : function(responseText, status, statusText) {
                    console.log(statusText);
                    SwalUtil.error("请求失败,请刷新浏览器后重试!");
                    return null;
                }
            });
            return AjaxUtil.preDeal(obj.responseJSON);
        } catch (e) {
            SwalUtil.error("请求失败,请刷新浏览器后重试!");
            return null;
        }
    }


};

SwalUtil = {
	// 基础提示信息,1000ms后自动关闭
	basic : function(msg) {
		swal({
			title : msg,
			showConfirmButton : false,
			timer : 1000,
		}).then(function() {
		}, function(dismiss) {
		});
	},
	// 成功的提示消息,600ms后自动关闭
	success : function(msg) {
		swal({
			title : msg,
			type : "success",
			confirmButtonText : '确定',
			timer : 2000
		}).then(function() {
		}, function(dismiss) {
		});
	},
	// 错误的提示消息不自动关闭
	error : function(msg) {
		swal({
			title : msg,
			confirmButtonText : '确定',
			type : "error",
		});
	},
	// 确定提示框
	confirm : function(onConfirm, title, text) {
		if (!title) {
			title = '您确定要执行该操作吗?';
		}
		if (!text) {
			text = '该操作无法撤销!'
		}
		swal({
			title : title,
			text : text,
			type : 'warning',
			showCancelButton : true,
			confirmButtonText : '确定',
			cancelButtonText : '取消',
		}).then(onConfirm, function(dismiss) {
		});
	},
	// showLoading
	showLoading : function() {
		swal.showLoading();
	},
	// hideLoading
	hideLoading : function() {
		swal.hideLoading();
	}
};

ParamUtil = {
	// 获取GET参数
	getQueryString : function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return decodeURI(r[2]);
		return null;
	},
	// 校验是否是数字
	isNum : function(str) {
		var reg = new RegExp("^\d{n}$");
		return reg.test(str);
	}
};

FormatUtil = {
	// 将时间ms值转化为'yyyy-MM-dd'格式的字符串
	formatDate : function(ms) {
		var d = new Date(ms);
		var year = d.getFullYear();
		var month = d.getMonth() + 1;
		if (month < 10) {
			month = '0' + month;
		}
		var date = d.getDate();
		if (date < 10) {
			date = '0' + date;
		}
		return year + "-" + month + "-" + date;
	},

	// 将时间ms值转化为'HH:mm'格式的字符串
	formatTime : function(ms) {
		var d = new Date(ms);
		var hour = d.getHours();
		var minute = d.getMinutes();
		return hour + ":" + minute;
	},

	// 将时间ms值转化为'yyyy-MM-dd HH:mm:ss'格式的字符串
	formatDateTime : function(ms) {
		var d = new Date(ms);
		var year = d.getFullYear();
		var month = d.getMonth() + 1;
		if (month < 10) {
			month = '0' + month;
		}
		var date = d.getDate();
		if (date < 10) {
			date = '0' + date;
		}
		var hour = d.getHours();
		if (hour < 10) {
			hour = '0' + hour;
		}
		var minute = d.getMinutes();
		if (minute < 10) {
			minute = '0' + minute;
		}
		var second = d.getSeconds();
		if (second < 10) {
			second = '0' + second;
		}
		return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
	},

	formatPreMonth : function(ms) {
		var d = new Date(ms);
		var year = d.getFullYear();
		var month = d.getMonth() + 1;
		if (month == 1) {
			month = 12;
			year--;
		} else {
			month--;
		}
		if (month < 10) {
			month = '0' + month;
		}
		var date = d.getDate();
		if (date < 10) {
			date = '0' + date;
		}
		return year + "-" + month + "-" + date;
	},
	// 将单位为分的金额格式化为2个小数
	formatMoney2 : function(money) {
		return (money / 100).toFixed(2);
	},

	formatMoney : function(money) {
		var re = /^[0-9]+.?[0-9]*$/; // 判断字符串是否为数字 //判断正整数 /^[1-9]+[0-9]*]*$/
		if (!re.test(money)) {
			return 0;
		}
		// 判断商品价格
		var reg = /(^[-+]?[1-9]\d*(\.\d{1,2})?$)|(^[-+]?[0]{1}(\.\d{1,2})?$)/;
		if (!reg.test(money)) {
			return (money / 1).toFixed(2);
		} else {
			return money;
		}

	}

};

FormUtil = {
	createSelectItem : function(items, selected) {
		var str = '';
		for ( var i in items) {
			if (items[i].value == selected) {
				str += '<option value="' + items[i].value + '" selected>' + items[i].name + '</option>';
			} else {
				str += '<option value="' + items[i].value + '">' + items[i].name + '</option>';
			}
		}
		return str;
	}
};