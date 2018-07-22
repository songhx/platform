$(function () {
    $("#jqGrid").jqGrid({
        url: '../scinfo/list',
        datatype: "json",
        colModel: [
			{label: 'id', name: 'id', index: 'id', key: true, hidden: true},
            {label: '操作', name: 'id', index: '', align: 'center',width: "80x",frozen: true, sortable:false,formatter:toComment},
			{label: '头像', name: 'avatar', index: 'avatar', width: "75px",align: 'center',formatter:formatAvatar},
			{label: '微信昵称', name: 'name', index: 'name', align: 'center',width: "120px"},
			{label: '信息类型', name: 'type', index: 'type', align: 'center',width: "80px",formatter:formatType},
			{label: '文本内容', name: 'content', index: 'content',align: 'center', width: "180px",formatter:formatWord},
			{label: '图片', name: 'imgUrl', index: 'img_url', align: 'center',width: "200px",formatter:formatImgs},
			{label: '视频', name: 'videoUrl', index: 'video_url',align: 'center', width: "200px",formatter:formatVedio},
            {label: '评论数', name: 'commentNum', index: 'comment_num',align: 'center', width: "80x"},
			{label: '点赞数', name: 'agreeNum', index: 'agree_num', align: 'center',width: "80x"},
			{label: '踩数', name: 'dissNum', index: 'diss_num', align: 'center',width: "80x"},
			{label: '转发数', name: 'repostNum', index: 'repost_num', align: 'center',width: "80x"},
			{label: '创建时间', name: 'createTime', index: 'create_time', align: 'center',width: "100x"}
           ],
		viewrecords: true,
        height: 550,
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
            //$("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});

        }
    });
    jQuery("#jqGrid").jqGrid('setFrozenColumns');
});
function toComment(id) {
	 return '<a href="'+rootPath+'/sc/sccomment.html?id='+id+'" target="_self">管理评论</a>' ;
}
function formatWord(item) {
    var title = "<div style='width: 160px;display:block;word-break: break-all;word-wrap: break-word;'>"+item+"</div>";
    return  title;
}
function formatVedio(t) {
    if (t == null || t == "" ){return "";}
	return ' <video class="video-js vjs-default-skin" controls preload="none" width="200" height="65"  data-setup="{}">' +
        '<source src="'+t+'" type="video/mp4">' +
        '<track kind="subtitles" src="/statics/plugins/video-js-6/examples/shared/example-captions.vtt" srclang="en" label="English"></track>'+
		'<p class="vjs-no-js">To view this video please enable JavaScript, and consider upgrading to a web browser that <a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a></p>'+
        '</video>';
}
//格式化图片
function formatImgs(t) {
	if (t == null || t == "" ){return "";}
	var imgs = t.split(",");
	var imgStr = "<div style='width: 195px; '> <ul>";
	for(var i = 0 ; i < imgs.length ; i++){
        imgStr += '<li style="float:left;margin-left:1px;"><img alt="image" style="height: 64px; width: 64px;" src="'+imgs[i]+'"></li>';
	}
    imgStr +="</ul></div>";
    return imgStr;
}
///格式化头像
function formatAvatar(t) {
	return '<img alt="image" class="img-circle" style="height: 64px; width: 64px;" src="'+t+'">';
}

//1->全部;41->视频;10->图片;29->文本;31->声音
const Type = ['视频', '图片', '文本','声音']
///格式化支付类型
function formatType(t) {
    if(t == 41) {t = 0;}
    if(t == 10) {t = 1;}
    if(t == 29) {t = 2;}
    if(t == 31) {t = 3;}
    return '<span>'+Type[t]+'</span>';
}

var vm = new Vue({
	el: '#rrapp',
	data: {
        showList: true,
        title: null,
		scInfo: {},
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
			vm.scInfo = {};
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
            var url = vm.scInfo.id == null ? "../scinfo/save" : "../scinfo/update";
			$.ajax({
				type: "POST",
			    url: url,
			    contentType: "application/json",
			    data: JSON.stringify(vm.scInfo),
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
				    url: "../scinfo/delete",
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
			$.get("../scinfo/info/"+id, function (r) {
                vm.scInfo = r.scInfo;
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