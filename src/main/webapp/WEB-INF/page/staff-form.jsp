<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div style="padding:20px 20px">
		<div class="ftitle">请输入人员信息</div>
		<!-- 创建表单 textbox、filebox、combogrid -->
		<form id="fm-update-staff" method="post" enctype="multipart/form-data">
			<div class="fitem">
				<label>姓名:</label>
				<input name="name" class="easyui-textbox" required="true" />
			</div>
			<div class="fitem">
				<label>性别:</label>
				<input name="sex" class="easyui-combo" required="true" />
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
				<label>email:</label>
				<input name="email" class="easyui-emailbox" buttonText="请输入邮箱" />
			</div>
			<div class="fitem">
				<label>职务:</label>
				<input name="position" class="easyui-combobox" required="true" />
			</div>
			<div class="fitem">
				<label>景区:</label>
				<input name="scine" class="easyui-combobox" required="true" />
			</div>
			<div class="fitem">
				<label>登录名:</label>
				<input name="loginName" class="easyui-textbox" data-options="iconCls:'icon-man'"/>
			</div>
			<div class="fitem">
				<label>密码:</label>
				<input name="password" class="easyui-passwordbox" data-options="iconCls:'icon-lock'"/>
			</div>
			
			<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" 
			onclick="saveStaff()" style="width: 90px">保存</a>
		</form>
</div>



<script type="text/javascript">


</script>

