/**
 * Created by maohang on 2017/11/20.
 */
$(function () {
    AjaxUtil.isLogin(depart.token);
    depart.init();
});
var depart = {
    token: window.localStorage.token,
    currentStatusSelect: 11,
    init: function () {
        $('#depart_table').bootstrapTable({
            url: AjaxUtil.url + '/departManager/list?token=' + this.token, // 请求后台的URL（*）
            responseHandler: function (res) {
                if (!res || res.code != '200') {
                    return [];
                }
                return res.obj;
            },
            method: 'get', // 请求方式（*）
            toolbar: '#depart_toolbar', // 工具按钮用哪个容器
            striped: true, // 是否显示行间隔色,默认为false
            cache: false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true, // 是否显示分页（*）
            sidePagination: "client", // 分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1, // 初始化加载第一页，默认第一页
            pageSize: 10, // 每页的记录行数（*）
            pageList: [10, 25, 50, 100], // 可供选择的每页的行数（*）
            search: true, // 是否显示表格搜索，此搜索是客户端搜索，不会进服务端
            showColumns: true, // 是否显示内容列下拉框
            showRefresh: true, // 是否显示刷新按钮
            clickToSelect: true, // 是否启用点击选中行
            smartDisplay: true,// 智能显示分页按钮
            columns: [{
                radio: true
            }, {
                field: 'departmentId',
                title: '部门ID',
                align: 'center'
            }, {
                field: 'name',
                title: '部门名称',
                align: 'center'
            }, {
                field: 'staffId',
                title: '负责人工号',
                align: 'center'
            }, {
                field: 'mobile',
                title: '部门联系电话',
                align: 'center'
            }],
            onDblClickRow: function (row) { // 双击修改
                $('#depart_form_edit').autofill(row);
                $('#depart_modal_edit').modal('show');
            }
        });

        // var select_str = '<div class="search pull-right" style="margin-right:5px;"> <select onchange="depart.statusChange(this.value)" class="form-control">';
        // select_str += '<option value="11">全部</option>';// 拼接下拉选项
        // select_str += '<option value="0">未绑定</option>';// 拼接下拉选项
        // select_str += '<option value="1">等待审核</option>';// 拼接下拉选项
        // select_str += '<option value="2">已审核</option>';// 拼接下拉选项
        // select_str += '<option value="3">冻结</option>';// 拼接下拉选项
        // select_str += '</select></div>';
        // $('.fixed-table-toolbar').append(select_str);

    },
    addUI: function () {
        $('#depart_modal_add').modal('show');
    },
    editUI: function () {

        var rows = $('#depart_table').bootstrapTable('getSelections');
        if (rows.length == 0) {
            SwalUtil.error("请选择要修改的部门!");
            return;
        } else if (rows.length > 1) {
            SwalUtil.error("一次只能修改一个部门!");
            return;
        } else {
            var row = rows[0];
            $('#depart_form_edit').autofill(row);
            $('#depart_modal_edit').modal('show');
            var resp = AjaxUtil.getJson(AjaxUtil.url + "departManager/list?token=" + depart.token + "&departmentId=" + row.departmentId);
        }
    },
    add: function () {
        var model = $('#depart_form_add').serialize();
        var resp = AjaxUtil.postJson(AjaxUtil.url + 'departManager/add', model += '&token=' + this.token);
        $('#depart_form_add').ajaxSubmit({
            type: 'post', // 提交方式 get/post
            url: '/departManager/add', // 需要提交的 url
            // success: function (data) { // data 保存提交后返回的数据，一般为 json 数据
            //     // 此处可对 data 作相关处理
            //     SwalUtil.success('提交成功！');
            // },
    });
        if (resp) {
            SwalUtil.success(resp.msg);
            $('#depart_table').bootstrapTable('refresh');
            $('#depart_form_add')[0].reset();
            $('#depart_modal_add').modal('hide');
        }
},


// uploadPic:function(){
// 上传设置
//     var options = {
//         // 规定把请求发送到那个URL
//         url: "/upload/image",
//         // 请求方式
//         type: "post",
//         // 服务器响应的数据类型
//         dataType: "json",
//         // 请求成功时执行的回调函数
//         success: function(data, status, xhr) {
//             // 图片显示地址
//             $("#allUrl").attr("src", data.path);
//         }
//     };
//     $("#depart_toolbar").ajaxSubmit(options);
// },
//     submit:function(){
//     // var data = new FormData($('#form1')[0]);
//         var data=new FormData($('#pic').val());
//         // console.log(typeof(data));
//     $.ajax({
//         url: AjaxUtil.url+'upload/image',
//         type: 'POST',
//         data: data,
//         dataType: 'JSON',
//         cache: false,
//         processData: false,
//         contentType: false
//     });
//         $('#depart_modal_add').ajaxSubmit(data);
//         SwalUtil.success("成功");
//     return false;
// },

edit : function () {
    var model = $('#depart_form_edit').serialize();
    //model是jQuery的序列化字符串，model+token值
    var resp = AjaxUtil.postJson(AjaxUtil.url + 'departManager/edit', model += '&token=' + this.token);
    if (resp) {
        SwalUtil.success(resp.msg);
        $('#depart_table').bootstrapTable('refresh');
        $('#depart_form_edit')[0].reset();
        $('#depart_modal_edit').modal('hide');
    }
}
,
remove : function () {
    var rows = $('#depart_table').bootstrapTable('getSelections');
    if (rows.length == 0) {
        SwalUtil.error("请选择要删除的部门!");
        return;
    } else if (rows.length > 1) {
        SwalUtil.error("一次只能删除一个部门!");
        return;
    }
    var row = rows[0];
    swal({
            title: "您确定要删除？",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#DD6B55',
            cancelButtonText: '取消',
            confirmButtonText: 'OK',
            // closeOnConfirm : true,
        }
        // , function() {
        //     var departmentId = row.departmentId;
        //     var resp = AjaxUtil.deleteJson(AjaxUtil.url + 'departManager/delete/'+departmentId,  '&token=' + depart.token);//'departmentId=' + departmentId +'&token=' + depart.token
        //     if (resp) {
        //         SwalUtil.success(resp.msg);
        //         $('#depart_table').bootstrapTable('refresh');
        //     }
        // }
    ).then(function (isConfirm) {
        if (isConfirm) {
            var departmentId = row.departmentId;
            var resp = AjaxUtil.deleteJson(AjaxUtil.url + 'departManager/delete/' + departmentId, '&token=' + depart.token);//'departmentId=' + departmentId +'&token=' + depart.token
            if (resp) {
                SwalUtil.success(resp.msg);
                $('#depart_table').bootstrapTable('refresh');
            }
        }
    })
}
,
// statusChange : function(status) {
//     depart.currentStatusSelect = status;
//     $('#depart_table').bootstrapTable('refresh', {
//         'query' : {
//             status : status
//         }
//     });
// },
// comfirm : function () {
//     var rows = $('#depart_table').bootstrapTable('getSelections');
//     if (rows.length == 0) {
//         SwalUtil.error("请选择要通过审核的部门!");
//         return;
//     } else if (rows.length > 1) {
//         SwalUtil.error("一次只能通过审核一个部门!");
//         return;
//     }
//     var row = rows[0];
//     if(row.status == 2){
//         SwalUtil.error("该用户已经通过审核,请不要重复操作!");
//         return;
//     }
//     var resp = AjaxUtil.postJson(AjaxUtil.url + '/departManager/confirm', {
//         departId : row.departId,
//         token : depart.token
//     });
//     if (resp) {
//         SwalUtil.success(resp.msg);
//         $('#depart_table').bootstrapTable('refresh', {
//             'query' : {
//                 status : depart.currentStatusSelect
//             }
//         });
//     }
// }

}