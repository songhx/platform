$(function () {
    $("#jqGrid").jqGrid({
        url: '../withdraw/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '会员名字', name: 'userName', index: 'user_name', width: 80},
            {label: '申请金额', name: 'withdrawMoney', index: 'withdraw_money', width: 80},
             {
                label: '状态', name: 'status', index: 'status', width: 40, formatter: function (value) {
                    var str = "申请中";
                    if (value == 1){str = "已发放";}
                 if (value == 2){str = "拒绝";}
                    return str;
                }
            },
            {label: '微信昵称', name: 'nickName', index: 'nick_name', width: 80},
            {label: '手机号码', name: 'mobile', index: 'mobile', width: 80},
            {label: 'openid', name: 'weixinOpenid', index: 'weixin_openid', width: 80},
            {
                label: '操作', width: 70, sortable: false, formatter: function (value, col, row) {
                var returnStr = "";
                if (row.status == 0) {
                    returnStr += '<button class="ivu-btn ivu-btn-primary ivu-btn-circle ivu-btn-small" onclick="vm.pass(' + row.id  + ')"><i class="ivu-icon ivu-icon-android-send"></i>发放</button>';
                    returnStr += '<button class="ivu-btn ivu-btn-primary ivu-btn-circle ivu-btn-small" onclick="vm.refuse(' + row.id  + ')"><i class="ivu-icon ivu-icon-android-folder-open"></i>拒绝</button>';
                }
                return 'returnStr';
             }
            }
            ],
        viewrecords: true,
        height: 385,
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
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        title: null,
        q: {
            name: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {'userName': vm.q.name},
                page: page
            }).trigger("reloadGrid");
        },
        pass : function(id){
            confirm('确定要发放选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: "../withdraw/update",
                    contentType: "application/json",
                    data: JSON.stringify({
                        id : id,
                        status : 1
                    }),
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
        refuse : function(id){
            confirm('确定要拒绝选中的记录？', function () {
                $.ajax({
                    type: "POST",
                    url: "../withdraw/update",
                    contentType: "application/json",
                    data: JSON.stringify({
                        id : id,
                        status : 2
                    }),
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
    }
});