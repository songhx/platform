$(function () {
    var shippingStatus = getQueryString("shippingStatus");
    var payStatus = getQueryString("payStatus");
    var orderStatus = getQueryString("orderStatus");
    var orderType = getQueryString("orderType");
    var url = '../order/list';
    if (shippingStatus) {
        url += '?shippingStatus=' + shippingStatus;
    }
    if (payStatus) {
        url += '?payStatus=' + payStatus;
    }
    if (orderStatus) {
        url += '?orderStatus=' + orderStatus;
    }
    if (orderType) {
        url += '?orderType=' + orderType;
    }
    $("#jqGrid").jqGrid({
        url: url,
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '订单号', name: 'orderSn', index: 'order_sn', width: 120},
            {label: '会员', name: 'userName', index: 'user_name', width: 60},
            // {
            //     label: '订单类型', name: 'orderType', index: 'order_type', width: 80, formatter: function (value) {
            //     if (value == '1') {
            //         return '普通订单';
            //     } else if (value == '2') {
            //         return '团购订单';
            //     } else if (value == '3') {
            //         return '砍价订单';
            //     }
            //     return '-';
            // }
            // },
            {
                label: '订单状态', name: 'orderStatus', index: 'order_status', width: 60, formatter: function (value) {
                if (value == '0') {
                    return '待付款';
                } else if (value == '101') {
                    return '订单已取消';
                } else if (value == '102') {
                    return '订单已删除';
                } else if (value == '201') {
                    return '订单已付款';
                } else if (value == '300') {
                    return '订单已发货';
                } else if (value == '301') {
                    return '用户确认收货';
                } else if (value == '401') {
                    return '退款';
                } else if (value == '402') {
                    return '完成';
                }
                return value;
            }
            },
            {
                label: '发货状态',
                name: 'shippingStatus',
                index: 'shipping_status',
                width: 60,
                formatter: function (value) {
                    if (value == '0') {
                        return '未发货';
                    } else if (value == '1') {
                        return '已发货';
                    } else if (value == '2') {
                        return '已收货';
                    } else if (value == '4') {
                        return '退货';
                    }
                    return value;
                }
            },
            {
                label: '付款状态', name: 'payStatus', index: 'pay_status', width: 60,
                formatter: function (value) {
                    if (value == '0') {
                        return '未付款';
                    } else if (value == '1') {
                        return '付款中';
                    } else if (value == '2') {
                        return '已付款';
                    }
                    return value;
                }
            },
            {
                label: '付款方式', name: 'payWays', index: 'pay_ways', width: 60,
                formatter: function (value) {
                    var str = "";
                    if (value != null){
                        value = value + "";
                        if (value.indexOf("1") != -1 ) {
                            str +='账户支付';
                        } else if (value.indexOf("2") != -1 ) {
                            str += ' 微信支付';
                        } else if (value.indexOf("3") != -1 ) {
                            str += ' 置换支付';
                        }
                        console.log(value)
                    }
                    return str;
                }
            },
            // {label: '收货人', name: 'consignee', index: 'consignee', width: 80},
            // {label: '省', name: 'province', index: 'province', width: 20 },
            // {label: '地市', name: 'city', index: 'city', width: 20 },
            // {label: '区县', name: 'district', index: 'district', width: 20 },
            // {label: '收货地址', name: 'address', index: 'address', width: 80},
            {label: '联系电话', name: 'mobile', index: 'mobile', width: 60},
            // {label: '补充说明', name: 'postscript', index: 'postscript', width: 80 },
            // {label: '快递公司Id', name: 'shippingId', index: 'shipping_id', width: 80 },
            {label: '快递公司', name: 'shippingName', index: 'shipping_name', width: 60},
            {label: '快递单号', name: 'shippingNo', index: 'shipping_No', width: 120},
            // {label: '付款方', name: 'payName', index: 'pay_name', width: 80},
            // {label: '快递费用', name: 'shippingFee', index: 'shipping_fee', width: 80},
            {label: '实际支付金额', name: 'actualPrice', index: 'actual_price', width: 60},
            // {label: '', name: 'integral', index: 'integral', width: 80 },
            // {label: '', name: 'integralMoney', index: 'integral_money', width: 80 },
            {label: '订单总价', name: 'orderPrice', index: 'order_price', width: 60},
            // {label: '商品总价', name: 'goodsPrice', index: 'goods_price', width: 60},
            // {
            //     label: '下单时间', name: 'addTime', index: 'add_time', width: 100,
            //     formatter: function (value) {
            //         return transDate(value);
            //     }
            // },
            {
                label: '操作', width: 140, align: 'center', sortable: false, formatter: function (value, col, row) {
                return '<button class="btn btn-outline btn-info" onclick="vm.lookDetail(' + row.id + ')"><i class="fa fa-info-circle"></i>&nbsp;详情</button>' +
                    '<button class="btn btn-outline btn-primary" onclick="vm.printDetail(' + row.id + ')"><i class="fa fa-print"></i>&nbsp;打印</button>';
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
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        detail: false,
        title: null,
        order: {},
        shippings: [],
        q: {
            orderSn: '',
            orderStatus: ''
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        sendGoods: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            vm.showList = false;
            vm.title = "发货";
            $.get("../order/info/" + id, function (r) {
                vm.order = r.order;
            });
        },
        //取消订单
        refund : function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            confirm('取消订单？', function () {
                $.ajax({
                    type: "POST",
                    url: "../order/refund",
                    contentType: "application/json",
                    data: JSON.stringify(id),
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
        confirm: function (event) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            confirm('确定收货？', function () {
                $.ajax({
                    type: "POST",
                    url: "../order/confirm",
                    contentType: "application/json",
                    data: JSON.stringify(id),
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
        saveOrUpdate: function (event) {
            $.ajax({
                type: "POST",
                url: '../order/sendGoods',
                contentType: "application/json",
                data: JSON.stringify(vm.order),
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
        reload: function (event) {
            vm.showList = true;
            vm.detail = false;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'orderSn': vm.q.orderSn,
                    'orderStatus': vm.q.orderStatus
                },
                page: page
            }).trigger("reloadGrid");
        },
        lookDetail: function (rowId) { //第三步：定义编辑操作
            vm.detail = true;
            vm.title = "详情";
            $.get("../order/info/" + rowId, function (r) {
                vm.order = r.order;
            });
        },
        printDetail: function (rowId) {
            openWindow({
                type: 2,
                title: '<i class="fa fa-print"></i>打印票据',
                content: '../shop/orderPrint.html?orderId=' + rowId
            })
        }
    },
    created: function () {
        var vue = this;
        $.get("../shipping/queryAll", function (r) {
            vue.shippings = r.list;
        });
    }
});