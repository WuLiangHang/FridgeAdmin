$(function() {
	AjaxUtil.isLogin(user.token);
	user.init();
});

var user = {
	token : window.localStorage.token,
	currentStatusSelect : 11,
	init : function() {
		$('#user_table').bootstrapTable({
			url : AjaxUtil.url + '/userManager/list?token=' + this.token, // 请求后台的URL（*）
			responseHandler : function(res) {
				return res.obj;
			},
			method : 'get', // 请求方式（*）
			toolbar : '#user_toolbar', // 工具按钮用哪个容器
			striped : true, // 是否显示行间隔色,默认为false
			cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
			pagination : true, // 是否显示分页（*）
			sidePagination : "client", // 分页方式：client客户端分页，server服务端分页（*）
			pageNumber : 1, // 初始化加载第一页，默认第一页
			pageSize : 10, // 每页的记录行数（*）
			pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
			search : true, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端
			showColumns : true, // 是否显示内容列下拉框
			showRefresh : true, // 是否显示刷新按钮
			clickToSelect : true, // 是否启用点击选中行
			smartDisplay : true,// 智能显示分页按钮
			columns : [ {
				radio : true
			}, {
				field : 'idNum',
				title : '身份证号码',
				align : 'center'
			}, {
				field : 'realname',
				title : '姓名',
				align : 'center'
			}, {
				field : 'phone',
				title : '手机号码',
				align : 'center'
			}, {
				field : 'job',
				title : '职业',
				align : 'center'
			}, {
				field : 'openId',
				title : '微信公众号Id',
				align : 'center'
			}, {
				field : 'status',
				title : '状态',
				align : 'center',
				formatter : function(row, value, index) {
					if (value.status == 0) {
						return "未绑定";
					} else if (value.status == 1) {
						return "等待审核";
					} else if (value.status == 2){
						return "已审核";
					}else if(value.status == 3){
						return "冻结";
					}
				}
			} ],
			onDblClickRow : function(row) { // 双击修改
				$('#user_form_edit').autofill(row);
				$('#user_modal_edit').modal('show');
			}
		});

		var select_str = '<div class="search pull-right" style="margin-right:5px;"> <select onchange="user.statusChange(this.value)" class="form-control">';
		select_str += '<option value="11">全部</option>';// 拼接下拉选项
		select_str += '<option value="0">未绑定</option>';// 拼接下拉选项
		select_str += '<option value="1">等待审核</option>';// 拼接下拉选项
		select_str += '<option value="2">已审核</option>';// 拼接下拉选项
		select_str += '<option value="3">冻结</option>';// 拼接下拉选项
		select_str += '</select></div>';
		$('.fixed-table-toolbar').append(select_str);

	},
	addUI : function() {
		$('#user_modal_add').modal('show');
	},
	editUI : function() {
		var rows = $('#user_table').bootstrapTable('getSelections');
		if (rows.length == 0) {
            SwalUtil.error("请选择要修改的用户!");
			return;
		} else if (rows.length > 1) {
            SwalUtil.error("一次只能修改一个用户!");
			return;
		} else {
			var row = rows[0];
			$('#user_form_edit').autofill(row);
			$('#user_modal_edit').modal('show');
			var resp = AjaxUtil.getJson(AjaxUtil.url + "/userManager/list?token=" + user.token + "&userId=" + row.userId);
		}
	},
	add : function() {
		var model = $('#user_form_add').serialize();
		var resp = AjaxUtil.postJson(AjaxUtil.url + '/userManager/add', model += '&token=' + this.token);
		if (resp) {
            SwalUtil.success(resp.msg);
			$('#user_table').bootstrapTable('refresh');
			$('#user_form_add')[0].reset();
			$('#user_modal_add').modal('hide');
		}
	},
	edit : function() {
		var model = $('#user_form_edit').serialize();
		var resp = AjaxUtil.postJson(AjaxUtil.url + '/userManager/edit', model += '&token=' + this.token);
		if (resp) {
            SwalUtil.success(resp.msg);
			$('#user_table').bootstrapTable('refresh');
			$('#user_form_edit')[0].reset();
			$('#user_modal_edit').modal('hide');
		}
	},
	remove : function() {
		var rows = $('#user_table').bootstrapTable('getSelections');
		if (rows.length == 0) {
            SwalUtil.error("请选择要删除的用户!");
			return;
		} else if (rows.length > 1) {
            SwalUtil.error("一次只能删除一个用户!");
			return;
		}
		var row = rows[0];
		swal({
			title : "您确定要删除？",
			type : 'warning',
			showCancelButton : true,
			confirmButtonColor : '#DD6B55',
			cancelButtonText : '取消',
			closeOnConfirm : true,

		}, function() {
			var userId = row.userId;
			var resp = AjaxUtil.postJson(AjaxUtil.url + '/userManager/delete', 'userId=' + userId + '&token=' + user.token);
			if (resp) {
                SwalUtil.success(resp.msg);
				$('#user_table').bootstrapTable('refresh');
			}
		});

	},
	statusChange : function(status) {
		user.currentStatusSelect = status;
		$('#user_table').bootstrapTable('refresh', {
			'query' : {
				status : status
			}
		});
	},
	comfirm : function () {
		var rows = $('#user_table').bootstrapTable('getSelections');
		if (rows.length == 0) {
            SwalUtil.error("请选择要通过审核的会员!");
			return;
		} else if (rows.length > 1) {
            SwalUtil.error("一次只能通过审核一个会员!");
			return;
		}
		var row = rows[0];
		if(row.status == 2){
            SwalUtil.error("该用户已经通过审核,请不要重复操作!");
			return;
		}
		var resp = AjaxUtil.postJson(AjaxUtil.url + '/userManager/confirm', {
			userId : row.userId,
			token : user.token
		});
		if (resp) {
            SwalUtil.success(resp.msg);
			$('#user_table').bootstrapTable('refresh', {
				'query' : {
					status : user.currentStatusSelect
				}
			});
		}
	}

}