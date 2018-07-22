$(function () {
    id = getQueryString("id");
    $("#jqGrid").jqGrid({
        url: '../sccomment/list?infoId='+id,
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '头像', name: 'avatar', index: 'avatar', align: 'center',width: "80px",formatter:formatAvatar},
            {label: '微信昵称', name: 'name', index: 'name', align: 'center',width: "120px"},
            {label: '评论内容', name: 'content', index: 'content',align: 'center', width: "180px",formatter:formatWord},
			{label: '点赞数', name: 'agreeNum', index: 'agree_num',align: 'center', width: "80px"},
			{label: '创建时间', name: 'createTime', index: 'create_time',align: 'center',}],
		viewrecords: true,
        height: 550,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});
function formatWord(item) {
    var title = "<div style='width: 160px;display:block;word-break: break-all;word-wrap: break-word;'>"+item+"</div>";
    return  title;
}
///格式化头像
function formatAvatar(t) {
    return '<img alt="image" class="img-circle" style="height: 64px; width: 64px;" src="'+t+'">';
}
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}
var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		scComment: {},
		ruleValidate: {
			name: [
				{required: true, message: '名称不能为空', trigger: 'blur'}
			]
		},
		q: {
		    name: ''
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
        toInfo:function () {
			window.location.href = rootPath + "/sc/scinfo.html";
        },
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.scComment = {};
		},
		update: function (event) {
            var id = getSelectedRow();
			if (id == null) {
				return;
			}
			vm.showList = false;
            vm.title = "修改";

            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
            var url = vm.scComment.id == null ? "../sccomment/save" : "../sccomment/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.scComment),
                success: function (r) {
                    if (r.code === 0) {
                        alert('操作成功', function (index) {
                            vm.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
			});
		},
		del: function (event) {
            var ids = getSelectedRows();
			if (ids == null){
				return;
			}

			confirm('确定要删除选中的记录？', function () {
				$.ajax({
					type: "POST",
				    url: "../sccomment/delete",
				    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function (r) {
						if (r.code == 0) {
							alert('操作成功', function (index) {
								$("#jqGrid").trigger("reloadGrid");
							});
						} else {
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(id){
			$.get("../sccomment/info/"+id, function (r) {
                vm.scComment = r.scComment;
            });
		},
		reload: function (event) {
			vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'content': vm.q.name},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		},
        handleSubmit: function (name) {
            handleSubmitValidate(this, name, function () {
                vm.saveOrUpdate()
            });
        },
        handleReset: function (name) {
            handleResetForm(this, name);
        }
	}
});