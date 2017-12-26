$(document).ready(function() {
	// 初始化菜单树
	function initTree () {
		// 设置左侧菜单树参数
		$('#woMenuTreeUl').tree({
			url : 'sys/menu/getChildren', // 设置菜单数据URL
			onClick: function(node) {// 菜单树节点点击事件
				if ($('#woContentTabPanel').tabs('exists', node.text)) {// 对应的tab是否已经存在
					$('#woContentTabPanel').tabs('select', node.text); // 选择已有tab
				} else if (node.attributes.resource) {
					if (node.attributes.resourceType == 'url') {
						$('#woContentTabPanel').tabs ('add', {
							href : node.attributes.resource,
							// content : '<iframe width=95% height=95% src="' + node.attributes.resource + '" style="margin:10px 10px"/>',
							closable : true,
							title : node.text
						});
					} else {
						// eval调用代码：Sys.MenuGrid({woNodeId:'', woTabId:'', woNodeText:''});
						// eval(node.attributes.resource + "({woNodeId:'" + node.id + "', woTabId:'woContentTabPanel', woNodeText:'" + node.text + "'})");
					}
				}
			}
		});
		// tabpanel上增加按钮，实现退出系统功能
		$('#woContentTabPanel').panel ({
			tools : [{
				iconCls : 'icon-no',
				handler : function () {
					$.messager.confirm('确认', '您确定要退出吗？', function(r){
						if (r){
							$.post('logout', function(result){
								if (result.success){
									window.location = "";
								}
							},'json');
						}
					});
				}
			}]
		});
	}
	// 判断是否登录，如果没有登录：
	if (!woLogin) {
		// 弹出登录对话框
		$('#woDlgLogin').dialog ('open');
		// 定义登录按钮点击事件
		$('#woButtonsLogin').find ('a').linkbutton({
			onClick : function () {
				$('#woFormLogin').form('submit',{
					url: 'authentication',
					onSubmit: function(){
						return $(this).form('validate');
					},
					success: function(result) {
						var result = eval('(' + result + ')');
						if (result.success == false){
							$.messager.alert({
								title: '错误',
								icon : 'error',
								msg: result.message
							});
						} else {
							// $('#woDlgLogin').dialog('close');		// close the dialog
							// initTree ();
							window.location = "";
						}
					}
				});
			}
		});
	} else {// 判断是否登录，如果已经登录：
		initTree ();
	}
});