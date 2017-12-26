<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div style="padding:5px 5px">
	<!-- 1.列表 -->
	<table id="dg-menu" class="easyui-treegrid" style="width: 700px; height: 500px"
		url='sys/menu/list' toolbar='#toolbar-menu' idField='id' treeField='name'
		singleSelect="true" fitColumns="true" rownumbers="true">
		<thead>
			<tr>
				<th field="id" width="100">ID</th>
				<th field="name" width="100">名称</th>
				<th field="icon" width="50">图标</th>
				<th field="resource" width="100">资源</th>
				<th field="resourceType" width="60">资源类型</th>
				<th field="parentName" width="60">上级</th>
			</tr>
		</thead>
	</table>
	<!-- 列表上的按钮 -->
	<div id="toolbar-menu">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="loadCreateMenuForm()">创建</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="loadUpdateMenuForm()">修改</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteMenu()">删除</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#dg-menu').treegrid('unselectAll')">清除</a>
	</div>
	
	<!-- 2.加载创建表单的对话框 -->
	<div id="dlg-create-menu" class="easyui-dialog" title="创建菜单"
		style="width: 450px; height: 300px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons-create-menu">
		<div class="ftitle">请输入菜单信息</div>
		<!-- 创建表单 -->
		<form id="fm-create-menu" method="post">
			<div class="fitem">
				<label>名称:</label>
				<input name="name" class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label>资源:</label>
				<input name="resource" class="easyui-textbox"/>
			</div>
			<div class="fitem">
				<label>资源类型:</label>
				<input name="resourceType" class="easyui-combobox"
					data-options="
						valueField:'val',
						textField:'name',
						url:'sys/dictionary/getItems?dicType=menutype'
				" />
			</div>
			<div class="fitem">
				<label>图标:</label>
				<input name="icon" class="easyui-textbox" />
			</div>
			<div class="fitem">
				<label>上级:</label>
				<select id="select-menu-parent" name="parentId" class="easyui-combotree" url='sys/menu/getChildren'>
				</select>
			</div>
		</form>
	</div>
	<!-- 创建表单的按钮 -->
	<div id="dlg-buttons-create-menu">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" 
			onclick="createMenu()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg-create-menu').dialog('close')" style="width: 90px">取消</a>
	</div>
	
	<!-- 3.加载修改表单的对话框 -->
	<div id="dlg-update-menu" class="easyui-dialog" title="修改菜单"
		style="width:550px; height:320px; padding:10px 20px" closed="true"
		buttons="#dlg-buttons-update-menu">
		<div class="ftitle">请输入菜单信息</div>
		<!-- 修改表单 -->
		<form id="fm-update-menu" method="post" novalidate>
			<div class="fitem">
				<label>ID:</label>
				<input name="id" class="easyui-textbox" readonly="true" />
			</div>
			<div class="fitem">
				<label>名称:</label>
				<input name="name" class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label>资源:</label>
				<input name="resource" class="easyui-textbox"/>
			</div>
			<div class="fitem">
				<label>资源类型:</label>
				<input name="resourceType" class="easyui-combobox"
					data-options="
						valueField:'val',
						textField:'name',
						url:'sys/dictionary/getItems?dicType=menutype'
				" />
			</div>
			<div class="fitem">
				<label>图标:</label>
				<input name="icon" class="easyui-textbox" />
			</div>
			<div class="fitem">
				<label>上级:</label>
				<select name="parentId" class="easyui-combotree" url='sys/menu/getChildren'>
				</select>
			</div>
		</form>
	</div>
	<!-- 修改表单的按钮 -->
	<div id="dlg-buttons-update-menu">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" 
			onclick="updateMenu()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg-update-menu').dialog('close')" style="width: 90px">取消</a>
	</div>
</div>
<script type="text/javascript">
	// 响应列表上的创建按钮点击事件
	function loadCreateMenuForm () {
		$('#dlg-create-menu').dialog('open');
		$('#fm-create-menu').form('clear');
		$('#dlg-create-menu').dialog('open');
		var row = $('#dg-menu').treegrid ('getSelected');
		if (row) {
			$('#select-menu-parent').combotree ('setValue', {
				id : row.id,
				text : row.name
			});
		}
	}
	
	// 响应列表上的修改按钮点击事件
	function loadUpdateMenuForm () {
		var row = $('#dg-menu').treegrid('getSelected');
		if (row) {
			$('#dlg-update-menu').dialog('open');
			$('#fm-update-menu').form('load', row);
		} else {
			$.messager.alert({
				icon : 'warning',
				title : '警告',
				msg : '请选择一行记录！'
			});
		}
	}
	
	// 响应创建表单的保存按钮点击事件
	function createMenu() {
		$('#fm-create-menu').form('submit', {
			url : 'sys/menu/create',
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success == false) {
					$.messager.alert({ // show error message
						title : '错误',
						icon : 'error',
						msg : result.message
					});
				} else {
					$('#dlg-create-menu').dialog('close'); // close the dialog
					$('#dg-menu').treegrid('reload'); // reload the grid data
				}
			}
		});
	}
	
	// 响应修改表单的保存按钮点击事件
	function updateMenu() {
		$('#fm-update-menu').form('submit', {
			url : 'sys/menu/update',
			onSubmit : function() {
				return $(this).form('validate');
			},
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.success == false) {
					$.messager.alert({ // show error message
						title : '错误',
						icon : 'error',
						msg : result.message
					});
				} else {
					$('#dlg-update-menu').dialog('close'); // close the dialog
					$('#dg-menu').datagrid('reload'); // reload the grid data
				}
			}
		});
	}
	
	// 响应列表上的删除按钮点击事件
	function deleteMenu() {
		var row = $('#dg-menu').treegrid('getSelected');
		if (row) {
			$.messager.confirm('请确认', '您确定要删除选中的用户吗？', function(r) {
				if (r) {
					$.post('sys/menu/delete', {woSelectedIds : row.id}, function(result) {
						if (result.success) {
							// var index = $('#dg-menu').treegrid('getRowIndex', row);
							// $('#dg-menu').treegrid('deleteRow', index);
							// $('#dg-menu').treegrid('unselectAll');
							$('#dg-menu').treegrid('reload'); // reload grid data
						} else {
							$.messager.alert({ // show error message
								title : '错误',
								icon : 'error',
								msg : result.message
							});
						}
					});
				}
			});
		}
	}
</script>