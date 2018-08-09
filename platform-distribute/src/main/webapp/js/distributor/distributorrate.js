$(function () {
    $("#jqGrid").jqGrid({
        url: '../distributorrate/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '标准率', name: 'standarRate', index: 'standar_rate', width: 80},
			{label: '销售占比率', name: 'slaerRate', index: 'slaer_rate', width: 80},
			{label: '省级代理率', name: 'provinceRate', index: 'province_rate', width: 80},
			{label: '市级代理率', name: 'cityRate', index: 'city_rate', width: 80},
			{label: '一级分销商比例1', name: 'distributorLv1S1', index: 'distributor_lv1_s1', width: 120},
			{label: '一级分销商比例2', name: 'distributorLv1S2', index: 'distributor_lv1_s2', width: 120},
			{label: '一级分销商比例3', name: 'distributorLv1S3', index: 'distributor_lv1_s3', width: 120},
			{label: '二级分销商比例1', name: 'distributorLv2S1', index: 'distributor_lv2_s1', width: 120},
			{label: '二级分销商比例2', name: 'distributorLv2S2', index: 'distributor_lv2_s2', width: 120},
			{label: '二级分销商比例3', name: 'distributorLv2S3', index: 'distributor_lv2_s3', width: 120},
			{label: '三级分销商比例1', name: 'distributorLv3S1', index: 'distributor_lv3_s1', width: 120},
			{label: '三级分销商比例2', name: 'distributorLv3S2', index: 'distributor_lv3_s2', width: 120},
			{label: '三级分销商比例3', name: 'distributorLv3S3', index: 'distributor_lv3_s3', width: 120}],
		viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        shrinkToFit: false,
        autoScroll: true,          //shrinkToFit: false,autoScroll: true,这两个属性产生水平滚动条
        autowidth: true,          //必须要,否则没有水平滚动条
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
           // $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		distributorRate: {},
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
		add: function () {
			vm.showList = false;
			vm.title = "新增";
			vm.distributorRate = {};
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
            var url = vm.distributorRate.id == null ? "../distributorrate/save" : "../distributorrate/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.distributorRate),
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
				    url: "../distributorrate/delete",
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
			$.get("../distributorrate/info/"+id, function (r) {
                vm.distributorRate = r.distributorRate;
            });
		},
		reload: function (event) {
			vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'name': vm.q.name},
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