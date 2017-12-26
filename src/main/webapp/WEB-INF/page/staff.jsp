<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
	<%String pageName="staff"; %>
<div style="padding:5px 5px">
	<table id="<%=pageName%>showTable" class="easyui-datagrid"
		url='sys/<%=pageName%>/list' toolbar='#<%=pageName%>options'
		fitColumns="true" rownumbers="true"
		data-options="
			autoRowHeight:true,
			pagination:true,
			ctrlSelect:true
		">
		<thead>
			<tr>
				<th field="id" hidden="true">id</th>
				<th field="name" >姓名</th>
				<th field="sex" >性别</th>
				<th field="birthday" >生日</th>
				<th field="headImage" >头像</th>
				<th field="mobile" >手机</th>
				<th field="email" >邮箱</th>
				<th field="position" >职务</th>
				<th field="sceneName">景区</th>
				<th field="loginName">登录名</th>
				<th field="password" >密码</th>
			</tr>
		</thead>
	</table>
	<!-- 列表上的按钮 -->
	<div id="<%=pageName%>options">
		<form id="<%=pageName%>search">
			<table>
				<tr>
					<input name="info" class="easyui-textbox"
						iconCls='icon-search' style="width: 150px"
						data-options="prompt:'请输入人员信息...'"></input>
						
					<a class="easyui-linkbutton admin" iconCls="icon-add"
						onclick="<%=pageName%>loadCreateForm()">创建</a>
						
					<a class="easyui-linkbutton" iconCls="icon-edit"
						onclick="<%=pageName%>loadUpdateForm()">修改</a>
						
					<a class="easyui-linkbutton admin" iconCls="icon-remove"
						onclick="<%=pageName%>remove()">删除</a>
						
					<a class="easyui-linkbutton" iconCls="icon-clear"
						onclick="javascript:$('#<%=pageName%>showTable').datagrid('unselectAll')">清除选中</a>
				</tr>
			</table>
		</form>
	</div>
	
	<!-- 2.加载创建表单的对话框 -->
	<div id="<%=pageName%>createDiv" class="easyui-dialog" title="添加员工"
		style="width: 450px; padding: 10px 20px" closed="true"
		buttons="#<%=pageName%>createButtons" data-options="shadow:true,modal:true">
		<div class="ftitle">请输入员工信息</div>
		<!-- 创建表单 -->
		<form id="<%=pageName%>createForm" method="post" enctype="mutipart/form-data">
			<div class="fitem">
				<label>姓名:</label>
				<input name="name" class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label>性别:</label>
				<select name="sex" class="easyui-combobox" required="true" style="width: 205px;"
					data-options="valueField:'val',
								textField:'name',
								editable:false,
								url:'sys/dictionary/getItems?dicType=sex'"></select>
			</div>
			<div class="fitem">
				<label>手机:</label>
				<input name="mobile" class="easyui-textbox" required="true" 
					data-options="validType:'phoneNum'"/>
			</div>
			<div class="fitem">
				<label>职务:</label>
				<select name="position" class="easyui-combobox" style="width: 205px;"
					url="sys/dictionary/getItems?dicType=position" required="true" 
					 data-options="valueField:'val',textField:'name',editable:false"></select>
			</div>
			<div class="fitem">
				<label>景区:</label> 
				<select id="sceneId" class="easyui-combogrid" name="sceneId" style="width: 205px;"
					data-options="
				            panelWidth:450,
				            idField:'id',
				            textField:'name',
				            url:'scene/list',
				            editable:false,
				            columns:[[
				                {field:'id',title:'id',hidden:true,width:60},
				                {field:'name',title:'景区名',width:100},
				                {field:'address',title:'地址',width:120},
				                {field:'open',title:'开放时间',width:100}
				            ]]
       			 "></select>
			</div>
			<div class="fitem">
				<label>生日:</label>
				<input name="birthday" style="width: 205px;" class="easyui-datebox"/>
			</div>
			<div class="fitem">
				<label>头像:</label>
				<input name="headImageFile"  buttonText="选择头像" class="easyui-filebox"/>
			</div>
			<div class="fitem">
				<label>邮箱:</label>
				<input name="email" class="easyui-textbox"
				data-options="validType:'email'"/>
			</div>
		</form>
	</div>
	<!-- 创建表单的按钮 -->
	<div id="<%=pageName%>createButtons">
	 	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" 
			onclick="<%=pageName%>create()" style="width: 90px">添加</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#<%=pageName%>createDiv').dialog('close')" style="width: 90px">取消</a>
	</div>
	
	<!-- 3.加载修改表单的对话框 -->
	<!-- 2.加载创建表单的对话框 -->
	<div id="<%=pageName%>updateDiv" class="easyui-dialog" title="添加景点"
		style="width: 450px; padding: 10px 20px" closed="true"
		buttons="#<%=pageName%>updateButtons" data-options="shadow:true,modal:true">
		<div class="ftitle">请输入景点信息</div>
		<!-- 创建表单 -->
		<form id="<%=pageName%>updateForm">
			<div class="fitem">
				<label>ID:</label>
				<input name="id" class="easyui-textbox" readonly="true" required="true" />
			</div>
			<div class="fitem">
				<label>性别:</label>
				<select name="sex" class="easyui-combobox"
					url="sys/dictionary/getItems?dictype=sex" required="true" ></select>
			</div>
			<div class="fitem">
				<label>生日:</label>
				<input name="name" class="easyui-datebox" required="true" />
			</div>
			<div class="fitem">
				<label>头像:</label>
				<input name="name" buttonText="修改头像" class="easyui-filebox" required="true" />
			</div>
			<div class="fitem">
				<label>手机:</label>
				<input name="name" class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label>邮箱:</label>
				<input name="name" class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label>职务:</label>
				<select name="position" class="easyui-combobox" 
					url="sys/dictionary/list?dictype=position" required="true" ></select>
			</div>
			<div class="fitem">
				<label>景区:</label>
				<input name="sceneName" class="easyui-textbox"/>
			</div>
			<div class="fitem">
				<label>登录名:</label>
				<input name="loginName" class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label>密码:</label>
				<input name="password" class="easyui-passwordbox" iconCls="icon-lock" required="true" />
			</div>
		</form>
	</div>

<div id="<%=pageName%>updateButtons">
	<a class="easyui-linkbutton" iconCls="icon-ok" onclick="<%=pageName%>update()">修改</a>
	<a class="easyui-linkbutton" iconCls="icon-cancel" 
		onclick="javascript:$('#<%=pageName%>updateDiv').dialog('close')">取消</a>
</div>


<script type="text/javascript">

	/* function isAdmin(){
		$.get('scene/staff/isAdmin',{
			success:function(data){
				var val = eval('('+data+')');
				if(!val.isAdmin){
					$('.admin').hide();
				}else{
					$('.admin').show();
				}
			}
		});
		
	} */
	
	$.extend($.fn.validatebox.defaults.rules, {     
        phoneNum: { //验证手机号    
            validator: function(value, param){  
             return /^1[3-8]+\d{9}$/.test(value);  
            },     
            message: '请输入正确的手机号码!'    
        },
        email: { //验证邮箱
            validator: function(value, param){  
                return /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/.test(value);  
               },     
               message: '请输入正确的邮箱!'    
           }
    }); 

//为datagrid增加onBeforeLoad事件
$('#<%=pageName%>showTable').datagrid({
	onBeforeLoad : function (param) {// onBeforeLoad在datagrid请求列表数据之前被调用，param是请求的参数对象
		var t = $('#<%=pageName%>search').serializeArray();
        $.each(t, function() {
        	if (this.value != '' && this.value != undefined) {
        		// 使用form中的name和value设置param属性值
        		param[this.name] = this.value;
        	}
        });
        return true;
	}
});

	function <%=pageName%>loadCreateForm(){
		$('#<%=pageName%>createDiv').dialog('open');
	}
	
	function <%=pageName%>create(){
		$('#<%=pageName%>createForm').form('submit',{
			url:'sys/<%=pageName%>/create',
			onSubmit:function(){
				return $(this).form('validate');				
			},
			success:function(data){
				var val = eval('('+data+')');
				if(val.success){
					$('#<%=pageName%>showTable').datagrid('reload');
					$('#<%=pageName%>createDiv').dialog('close');
				}else{
					$.messager.alert({
						title:'创建失败',
						icon:'icon-error',
						msg:val.msg
					});
				}
			}
		});
	}
	
	function <%=pageName%>loadUpdateForm(){
		var row = $('#<%=pageName%>showTable').datagrid('getSelected');
		if(row){
			$('#<%=pageName%>updateDiv').dialog('open');
			$('#<%=pageName%>updateForm').form('load',row);
		}else{
			$.messager.alert({
				title:'提示',
				msg:'请选择一行',
				icon:'icon-note'
			});
		}
	}
	
	function <%=pageName%>update() {
		$('#<%=pageName%>updateForm').form('submit',{
			url:'sys/<%=pageName%>/update',
			onSubmit:function(){
				return $(this).form('validate');
			},
			success:function(data){
				var val = eval('('+data+')');
				if(val.success){
					$('#<%=pageName%>showTable').datagrid('reload');
					$('#<%=pageName%>updateDiv').dialog('close');
				}else{
					$.messager.alert({
						title:'修改失败',
						icon:'icon-error',
						msg:data.msg
					});
				}
			}
		});
	}
	
	function <%=pageName%>2Comments(node){
		if ($('#woContentTabPanel').tabs('exists', node.text)) {
			$('#woContentTabPanel').tabs('select', node.text); 
		} else {
			$('#woContentTabPanel').tabs ('add', {
				href : 'page/comment',
				closable : true,
				title : node.text
			});
		}
	}
	
	function <%=pageName%>remove(){
		var row = $('#<%=pageName%>showTable').datagrid('getSelected');
		if(row){
			$.messager.confirm('确认','确定要删除吗?',function(result){
				if(result){
					$.post('sys/staff/delete',{ids:row.id},function(data){
						if(data.success){
							$('#<%=pageName%>showTable').datagrid('reload');
						}else{
							$.messager.alert({
								title:'错误',
								icon:'icon_error',
								msg:data.msg
							});
						}					
					});
				}
			});
		}else{
			$.messager.alert({
				title:'提示',
				msg:'请选择一行',
				icon:'icon-note'
			});
		}
	}

</script>