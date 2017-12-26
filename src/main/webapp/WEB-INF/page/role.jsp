<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<div style="">
    <!-- 1.列表 http://www.jeasyui.com/documentation/index.php# datagrid -->
    <table id="dg-role" class="easyui-datagrid" style="width: 100%; height: 80%"
           url="sys/role/list" toolbar="#toolbar-role" pagination="true" rownumbers="true"
           fitColumns="true" singleSelect="true" idProperty="id">
        <thead>
        <tr>
            <th field="name" width="100">名称</th>
            <th field="type" width="30">类型</th>
            <th field="description" width="50">说明</th>
        </tr>
        </thead>
    </table>
    <!-- 列表上的按钮 linkbutton -->
    <div id="toolbar-role">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="loadCreateRoleForm()">创建</a>
        <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="loadUpdateRoleForm()">修改</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRole()">删除</a>
    </div>

    <!-- 2.加载创建表单的对话框 dialog -->
    <div id="dlg-create-role" class="easyui-dialog" title="创建角色"
         style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
         buttons="#dlg-buttons-create-role">
        <div class="ftitle">请输入角色信息</div>
        <!-- 创建表单 textbox、filebox、combogrid -->
        <form id="fm-create-role" method="post" enctype="multipart/form-data">
            <div class="fitem">
                <label>名称:</label>
                <input name="name" class="easyui-textbox" required="true"/>
            </div>
            <div class="fitem">
                <label>类型:</label>
                <input name="type" class="easyui-textbox"/>
            </div>
            <div class="fitem">
                <label>说明:</label>
                <input name="description" class="easyui-textbox"/>
            </div>
            <div class="fitem">
                <label>菜单:</label>
                <select class="easyui-combotree" style="width:202px;" name="menuIds"
                        data-options="multiple : true, cascadeCheck : false, url:'sys/menu/getChildren'">
                </select>
            </div>
        </form>
    </div>
    <!-- 创建表单的按钮 linkbutton -->
    <div id="dlg-buttons-create-role">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok"
           onclick="createRole()" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
           onclick="javascript:$('#dlg-create-role').dialog('close')" style="width: 90px">取消</a>
    </div>

    <!-- 3.加载修改表单的对话框 -->
    <div id="dlg-update-role" class="easyui-dialog" title="修改角色"
         style="width:550px; height:320px; padding:10px 20px" closed="true"
         buttons="#dlg-buttons-update-role">
        <div class="ftitle">请输入角色信息</div>
        <!-- 修改表单 -->
        <form id="fm-update-role" method="post" enctype="multipart/form-data">
            <div class="fitem">
                <label>名称:</label>
                <input name="name" class="easyui-textbox" required="true"/>
            </div>
            <div class="fitem">
                <label>类型:</label>
                <input name="type" class="easyui-textbox" required="true"/>
            </div>
            <div class="fitem">
                <label>说明:</label>
                <input name="description" class="easyui-textbox" required="true"/>
            </div>
            <div class="fitem">
                <label>菜单:</label>
                <select class="easyui-combotree" style="width:202px;" textField="menuNames" name="menuIds"
                        data-options="multiple : true, cascadeCheck : false, url:'sys/menu/getChildren'">
                </select>
            </div>
        </form>
    </div>
    <!-- 修改表单的按钮 -->
    <div id="dlg-buttons-update-role">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok"
           onclick="updateRole()" style="width: 90px">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
           onclick="javascript:$('#dlg-update-role').dialog('close')" style="width: 90px">取消</a>
    </div>
</div>
<!-- 4.所有按钮的点击响应事件 -->
<script type="text/javascript">
    // 响应列表上的创建按钮点击事件
    function loadCreateRoleForm() {
        // 弹出修改对话框
        $('#dlg-create-role').dialog('open');
        // 清除form数据
        $('#fm-create-role').form('clear');
    }

    // 响应列表上的修改按钮点击事件
    function loadUpdateRoleForm() {
        // 获取列表上选中的行数据对象
        var row = $('#dg-role').datagrid('getSelected');
        if (row) {
            // 弹出修改对话框
            $('#dlg-update-role').dialog('open');
            // 加载form数据
            $('#fm-update-role').form('load', row);
        } else {
            $.messager.alert({
                icon: 'warning',
                title: '警告',
                msg: '请选择一行记录！'
            });
        }
    }

    // 响应创建表单的保存按钮点击事件 form
    function createRole() {
        $('#fm-create-role').form('submit', {
            url: 'sys/role/create',
            onSubmit: function () {
                return $(this).form('validate');
            },
            success: function (result) {
                var result = eval('(' + result + ')');
                if (result.success == false) {
                    $.messager.alert({ // show error message
                        title: '错误',
                        icon: 'error',
                        msg: result.message
                    });
                } else {
                    $('#dlg-create-role').dialog('close'); // close the dialog
                    $('#dg-role').datagrid('reload'); // reload the grid data
                }
            }
        });
    }

    // 响应修改表单的保存按钮点击事件 form
    function updateRole() {
        $('#fm-update-role').form('submit', {
            url: 'sys/role/update',
            onSubmit: function () {
                return $(this).form('validate');
            },
            success: function (result) {
                var result = eval('(' + result + ')');
                if (result.success == false) {
                    $.messager.alert({ // show error message
                        title: '错误',
                        icon: 'error',
                        msg: result.message
                    });
                } else {
                    $('#dlg-update-role').dialog('close'); // close the dialog
                    $('#dg-role').datagrid('reload'); // reload the grid data
                }
            }
        });
    }

    // 响应列表上的删除按钮点击事件
    function deleteRole() {
        // 获取列表中的选中行数据对象
        var row = $('#dg-role').datagrid('getSelected');
        if (row) {
            $.messager.confirm('请确认', '您确定要删除选中的角色吗？', function (r) {
                // r为true表示点击了确定按钮，否则表示点击了取消按钮
                if (r) {
                    $.post('sys/role/delete', {woSelectedIds: row.id}, function (result) {
                        if (result.success) {
                            $('#dg-role').datagrid('reload'); // reload grid data
                        } else {
                            $.messager.alert({ // show error message
                                title: '错误',
                                icon: 'error',
                                msg: result.message
                            });
                        }
                    });
                }
            });
        }
    }

    //响应列表上的导入按钮点击事件
    function loadImportRoleForm() {
        var id = 'woRoleImportDlg';
        $('#' + id).remove();
        $('<div id="' + id + '" style="width:400px;height:200px;padding:10px 20px"/>').appendTo($('body'));
        $('#' + id).dialog({
            title: '导入',
            width: 400,
            height: 200,
            closed: false,
            cache: false,
            href: 'sys/role/importForm', // 加载导入表单
            modal: true,
            buttons: 'woButtonsRoleImport'
        });
    }

    //响应列表上的导出按钮点击事件
    function loadExportRoleForm() {
        window.open('sys/role/export');
    }
</script>