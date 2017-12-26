<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div style="padding:5px 5px">
	<!-- 1.列表 http://www.jeasyui.com/documentation/index.php# datagrid -->
	<table id="dg-staff" class="easyui-datagrid" style="width: 1000px; height: 500px"
		url="sys/staff/list" toolbar="#toolbar-staff" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true">
		<thead>
			<tr>
				<th field="id" width="100">ID</th>
				<th field="name" width="50">姓名</th>
				<th field="sex" width="50">性别</th>
				<th field="birthday" width="30">生日</th>
				<th field="headImage" width="50">头像</th>
				<th field="mobile" width="50">手机</th>
				<th field="email" width="50">邮箱</th>
				<th field="position" width="50">职务</th>
			</tr>
		</thead>
	</table>
	<!-- 列表上的按钮 linkbutton -->
	<div id="toolbar-staff">
	<form id="fm-search-staff">
	<table><tr>
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="loadCreateStaffForm()">创建</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="loadUpdateStaffForm()">修改</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteStaff()">删除</a>
		<input name="searchContent" class="easyui-textbox" iconCls='icon-search'/>
	</tr></table>
	</form>
	</div>
	
	<!-- 2.加载创建表单的对话框 dialog -->
	<div id="dlg-create-staff" class="easyui-dialog" title="创建员工"
		style="width: 450px; height: 600px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons-create-staff">
		<div class="ftitle">请输入员工信息</div>
		<!-- 创建表单 textbox、filebox、combogrid -->
		<form id="fm-create-staff" method="post" enctype="multipart/form-data">
		
		
			<div class="fitem">
				<label>姓名:</label>
				<input name="name" class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label>性别:</label>
				<!-- idField:提交到后端的ID字段，textField:显示在下拉框中的名称 -->
				<input name="sex" class="easyui-combobox"
					data-options="
						valueField:'val',
						textField:'name',
						url:'sys/dictionary/getItems?dicType=sex'
				" />
			</div>
			<div class="fitem">
				<label>生日:</label>
				<input name="birthday" class="easyui-datebox"  />
			</div>
			<div class="fitem">
				<label>头像:</label>
				<input name="headImage" class="easyui-filebox"  />
			</div>
			<div class="fitem">
				<label>手机:</label>
				<input name="mobile" class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label>邮箱:</label>
				<input name="email" class="easyui-textbox" buttonText="请输入邮箱" />
			</div>
			<div class="fitem">
				<label>职务:</label>
				<input name="position" class="easyui-combobox"
					data-options="
						valueField:'val',
						textField:'name',
						url:'sys/dictionary/getItems?dicType=position'
				" />
			</div>
			<div class="fitem">
				<label>景区:</label>
				<input name="scine" class="easyui-combobox" 
					data-options="
						valueField:'val',
						textField:'name',
						url:'sys/dictionary/getItems?dicType=senic'
				"/>
			</div>
			
			<div class="fitem">
				<label>登录名:</label>
				<input name="loginName" class="easyui-textbox" data-options="iconCls:'icon-man'"/>
			</div>
			<div class="fitem">
				<label>密码:</label>
				<input name="password" class="easyui-passwordbox" data-options="iconCls:'icon-lock'"/>
			</div>
		</form>
	</div>
	
	
	
	
	
	<!-- 创建表单的按钮 linkbutton -->
	<div id="dlg-buttons-create-staff">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" 
			onclick="createStaff()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg-create-staff').dialog('close')" style="width: 90px">取消</a>
	</div>
	
	<!-- 3.加载修改表单的对话框 -->
	<div id="dlg-update-staff" class="easyui-dialog" title="修改用户"
		style="width:550px; height:320px; padding:10px 20px" closed="true"
		buttons="#dlg-buttons-update-staff">
		<div class="ftitle">请输入员工信息</div>
		<!-- 修改表单 -->
		<form id="fm-update-staff" method="post" novalidate>
			<table>
				<tr>
					<td>
					
					
					
					
			<div class="fitem">
				<label>ID:</label>
				<input name="name" class="easyui-textbox" readonly="true" />
			</div>			
			<div class="fitem">
				<label>姓名:</label>
				<input name="name" class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label>性别:</label>
				<!-- idField:提交到后端的ID字段，textField:显示在下拉框中的名称 -->
				<input name="sex" class="easyui-combobox"
					data-options="
						valueField:'val',
						textField:'name',
						url:'sys/dictionary/getItems?dicType=sex'
				" />
			</div>
			<div class="fitem">
				<label>生日:</label>
				<input name="birthday" class="easyui-datebox"  />
			</div>
			<div class="fitem">
				<label>头像:</label>
				<input name="headImage" class="easyui-filebox"  />
			</div>
			<div class="fitem">
				<label>手机:</label>
				<input name="mobile" class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label>邮箱:</label>
				<input name="email" class="easyui-emailbox" buttonText="请输入邮箱" />
			</div>
			<div class="fitem">
				<label>职务:</label>
				<input name="position" class="easyui-combobox"
					data-options="
						valueField:'val',
						textField:'name',
						url:'sys/dictionary/getItems?dicType=position'
				" />
			</div>
			<div class="fitem">
				<label>景区:</label>
				<input name="scine" class="easyui-combobox" readonly="true" />
			</div>
			<div class="fitem">
				<label>登录名:</label>
				<input name="loginName" class="easyui-textbox" readonly="true" data-options="iconCls:'icon-man' "/>
			</div>
			<div class="fitem">
				<label>密码:</label>
				<input name="password" class="easyui-passwordbox" data-options="iconCls:'icon-lock'"/>
			</div>
						
									
					</td>
					<td style="padding:10px">
						<img id="woStaffUpdateFormImage" width=120 height=120/>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 修改表单的按钮 -->
	<div id="dlg-buttons-update-staff">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" 
			onclick="updateStaff()" style="width: 90px">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg-update-staff').dialog('close')" style="width: 90px">取消</a>
	</div>
</div>


<!-- 4.所有按钮的点击响应事件 -->
<script type="text/javascript">
	// 为datagrid增加onBeforeLoad事件
	$('#dg-staff').datagrid({
		onBeforeLoad : function (param) {// onBeforeLoad在datagrid请求列表数据之前被调用，param是请求的参数对象
			var t = $('#fm-search-staff').serializeArray();
	        $.each(t, function() {
	        	if (this.value != '' && this.value != undefined) {
	        		// 使用form中的name和value设置param属性值
	        		param[this.name] = this.value;
	        	}
	        });
	        return true;
		}
	});
	
	// 响应列表上的创建按钮点击事件
	function loadCreateStaffForm () {
		// 弹出修改对话框
		$('#dlg-create-staff').dialog('open');
		// 清除form数据
		$('#fm-create-staff').form('clear');
	}
	
	// 响应列表上的修改按钮点击事件
	function loadUpdateStaffForm () {
		// 获取列表上选中的行数据对象
		var row = $('#dg-staff').datagrid('getSelected');
		if (row) {
			// 弹出修改对话框
			$('#dlg-update-staff').dialog('open');
			// 加载form数据
			$('#fm-update-staff').form('load', row);
			// 设置头像src的值
			$('#woStaffUpdateFormImage').attr ('src', row.headImage);
		} else {
			$.messager.alert({
				icon : 'warning',
				title : '警告',
				msg : '请选择一行记录！'
			});
		}
	}
	
	// 响应创建表单的保存按钮点击事件 form
	function createStaff() {
		$('#fm-create-staff').form('submit', {
			url : 'sys/staff/create',
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
					$('#dlg-create-staff').dialog('close'); // close the dialog
					$('#dg-staff').datagrid('reload'); // reload the grid data
				}
			}
		});
	}
	
	// 响应修改表单的保存按钮点击事件 form
	function updateStaff() {
		$('#fm-update-staff').form('submit', {
			url : 'sys/staff/update',
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
					$('#dlg-update-staff').dialog('close'); // close the dialog
					$('#dg-staff').datagrid('reload'); // reload the grid data
				}
			}
		});
	}
	
	// 响应列表上的删除按钮点击事件
	function deleteStaff() {
		// 获取列表中的选中行数据对象
		var row = $('#dg-staff').datagrid('getSelected');
		if (row) {
			$.messager.confirm('请确认', '您确定要删除选中的用户吗？', function(r) {
				// r为true表示点击了确定按钮，否则表示点击了取消按钮
				if (r) {
					$.post('sys/staff/delete', {woSelectedIds : row.id}, function(result) {
						if (result.success) {
							$('#dg-staff').datagrid('reload'); // reload grid data
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