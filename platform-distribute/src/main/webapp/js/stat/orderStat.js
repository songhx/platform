$(function () {

    $("#jqGrid").jqGrid({
        url: '../orderStat/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
			{label: '订单编号', name: 'ordersn', index: 'ordersn', width: '180px'},
			{label: '商户号', name: 'agentid', index: 'agentid', width: '60px'},
            {label: '商品名称', name: 'orderGoodsVoList', index: 'price', width: '193px',formatter:formatTitle},
            {label: '商品编码', name: 'orderGoodsVoList', index: 'price', width: '180px',formatter:formatSN},
            {label: '订单金额', name: 'price', index: 'price', width: '80px',formatter:formatDoubleNum},
			{label: '会员姓名', name: 'realname', index: 'realname', width: '65px'},
            {label: '纯利润', name: 'pureProfit', index: 'price', width: '70px',formatter:formatDoubleNum},
			{label: '平台佣金', name: 'platformCommission', index: 'price', width: '70px',formatter:formatDoubleNum},
            {label: '一级分销商姓名', name: 'agentLv1Name', index: '', width: '100px'},
            {label: '一级分销商', name: 'commission1', index: 'commission1', width: '90px',formatter:formatDoubleNum},
            {label: '一级分销商等级', name: 'agentLv1', index: '', width: '120px',formatter:formatLevel},

            {label: '二级分销商姓名', name: 'agentLv2Name', index: '', width: '100px'},
            {label: '二级分销商', name: 'commission2', index: 'commission2', width: '90px',formatter:formatDoubleNum},
            {label: '二级分销商等级', name: 'agentLv2', index: '', width: '120px',formatter:formatLevel},

            {label: '三级分销商姓名', name: 'agentLv3Name', index: '', width: '100px'},
            {label: '三级分销商', name: 'commission3', index: 'commission3', width: '90px',formatter:formatDoubleNum},
            {label: '三级分销商等级', name: 'agentLv3', index: '', width: '120px',formatter:formatLevel},

			{label: '销售', name: 'salerCommission', index: 'commission1', width: '70px',formatter:formatDoubleNum},
			{label: '省分销商', name: 'provinceCommission', index: 'commission2', width: '70px',formatter:formatDoubleNum},
			{label: '市分销商', name: 'cityCommission', index: 'commission3', width: '70px',formatter:formatDoubleNum},
			{label: '分销商等级', name: 'level', index: 'level', width: '65px',formatter:formatLevel},
            {label: '交易时间', name: 'createtime', index: 'createtime', width: '139px',formatter:formatTime},
            {label: '成交时间', name: 'finishtime', index: 'finishtime', width: '139px',formatter:formatTime},
            {label: '支付方式', name: 'paytype', index: 'paytype', width: '80px',formatter:formatPayType },
            {label: '订单状态', name: 'status', index: 'status', width: '80px',formatter:formatOrderStatus},
			],
		viewrecords: true,
        height: 566,
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
            // $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "scroll"});
        }
    });
});
///格式化double数字
function formatDoubleNum(t) {
    if (null == t) {return "";}
    return t.toFixed(2);
}
//格式化商品标题
function formatTitle(item) {
    var title = "<div style='width: 188px;display:block;word-break: break-all;word-wrap: break-word;'>";
    if(item != null & item.length > 0){
        for (var  i = 0; i < item.length; i++){
            title += item[i].title + " /" +  item[i].total + "件 /  零售价 " + item[i].marketprice + "  / 成本价 " + item[i].costprice + "<br>";
        }
    }
    title += "</div>";
    return  title;
}
///商品编码
function formatSN(item) {
    var productsn = "";
    if(item != null & item.length > 0){
        for (var  i = 0; i < item.length; i++){
            productsn += item[i].productsn + "<br>";
        }
    }
    return  productsn;
}
///格式化时间
function formatTime(v) {
	if (v == 0){return "";}
	var t = v +"000";
	//console.log("t----" + t)
	return DateUtils.long2String(parseInt(t),11);
}
const  Levels = ['消费者', '门店店长', '超市店主']
///格式化
function formatLevel(t) {
    console.log("t---------"+t)
    if(t == null){ return "";}
    return '<span>'+Levels[t]+'</span>';
}
///订单状态
/*  已关闭 -1
	待付款  0
	待发货 1
	待收货 2
	已完成  3
	维权申请 4
	维权完成 5*/
const OrderStatus = ['待付款', '待发货', '待收货','已完成','维权申请','维权完成']

function formatOrderStatus(t) {
    if(t == -1) { return '<span>已关闭</span>';}
    return '<span>'+OrderStatus[t]+'</span>';
}

///支付类型
const PayType = ['未支付', '余额支付', '在线支付','货到付款','后台付款','微信支付','支付宝支付','银联支付']
///格式化支付类型
function formatPayType(t) {
	if(t == 11) {t = 4;}
    if(t == 21) {t = 5;}
    if(t == 22) {t = 6;}
    if(t == 23) {t = 7;}
	return '<span>'+PayType[t]+'</span>';
}

var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		imsEweiShopOrder: {},
		ruleValidate: {
			name: [
				{required: true, message: '订单编号不能为空', trigger: 'blur'}
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
		getInfo: function(id){
			$.get("../imseweishoporder/info/"+id, function (r) {
                vm.imsEweiShopOrder = r.imsEweiShopOrder;
            });
		},
		reload: function (event) {
			vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
			$("#jqGrid").jqGrid('setGridParam', {
                postData: {'ordersn': vm.q.name},
                page: page
            }).trigger("reloadGrid");
            vm.handleReset('formValidate');
		}
	}
});