<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String woWelcomeMsg = (String)request.getAttribute ("woWelcomeMsg");
	Boolean woLogin = (Boolean)request.getAttribute ("woLogin");
	if (woLogin == null) {
		woLogin = false;
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>系统管理</title>
	<link rel="stylesheet" type="text/css" href="js/easyui/themes/default/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="js/easyui/themes/icon.css"/>
	<script type="text/javascript" src="js/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="js/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/easyui/datagrid-detailview.js"></script>
	<script type="text/javascript" src="js/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/easyui/jquery.form.min.js"></script>
	<script type="text/javascript" src="js/app/main.js"></script>
	<script type="text/javascript" src="js/app/sys.js"></script>
	<script type="text/javascript">
		var woLogin = <%=woLogin%>;
	</script>
	<style type="text/css">
		.ftitle{
			font-size:14px;
			font-weight:bold;
			padding:5px 0;
			margin-bottom:10px;
			border-bottom:1px solid #ccc;
		}
		.fitem{
			margin-bottom:5px;
		}
		.fitem label {
			display:inline-block;
			width:60px;
		}
		.fitem input{
			width:200px;
		}
		.fitem select{
			width:200px;
		}
	</style>
</head>
<!-- 请参考：http://www.jeasyui.com/documentation/index.php -->
<!-- layout -->
<body class="easyui-layout">
	<!-- panel -->
	<div id="woMenuTreePanel" data-options="region:'west',split:true, title:'菜单'" style="width:200px;padding:10px;" class="easyui-panel">
		<!-- tree -->
		<ul id="woMenuTreeUl">
		</ul>
	</div>
	<!-- tabs -->
	<div id="woContentTabPanel" data-options="region:'center',title:'<%=woWelcomeMsg%>'" class="easyui-tabs" style="width:700px;height:250px">
		<div title="主页" style="padding:10px">
			<p style="font-size:14px">欢迎来到景区管理系统！</p>
			<ul>
			<li><p>如果您是游客，您可以：</p></li>
			<ul>
				<li><p>维护自身的信息</p></li>
				<li><p>管理自己的评论</p></li>
			</ul>
			<li><p>如果您是工作人员，您可以：</p></li>
			<ul>
				<li><p>维护景区信息，包括景区、景点、住宿、美食</p></li>
				<li><p>管理游客对景区的评论</p></li>
				<li><p>管理自己的售票记录</p></li>
			</ul>
			<li><p>如果您是管理者，您可以：</p></li>
			<ul>
				<li><p>维护景区信息，包括景区、景点、住宿、美食、门票等</p></li>
				<li><p>管理游客对景区的评论</p></li>
				<li><p>管理自己的售票记录</p></li>
				<li><p>维护景区所有人员信息</p></li>
				<li><p>查看景区所有的售票记录</p></li>
			</ul>
			</ul>
		</div>
	</div>
	<div id="woDlgLogin" class="easyui-dialog" style="width:400px;height:230px;padding:10px 20px"
			closed="true" buttons="#woButtonsLogin" modal="true" title="登录">
		<div class="ftitle">请输入登录信息：</div>
		<form id="woFormLogin" method="post" novalidate>
			<div class="fitem">
				<label>登录名:</label>
				<input name="user" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>密码:</label>
				<input name="password" type="password" class="easyui-textbox" required="true">
			</div>
		</form>
		<div id="woButtonsLogin">
			<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" style="width:90px">登录</a>
		</div>
	</div>
	
</body>
</html>