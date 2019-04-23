layui.use(['table', 'admin', 'ax', 'ztree'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;

    var Teacher = {
        tableId: "teacherTable",
        condition: {
            teacherId: "",
            name: ""
        }
    };

    Teacher.initColumn = function(){
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'name', sort: true, title: '姓名'},
            {field: 'career', sort: true, title: '从事职业'},
            {field: 'mobile', sort: true, title: '联系电话'},
            {field: 'remark', sort: true, title: '备注'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]]
    };

    /**
     * 弹出添加
     */
    Teacher.openAddDept = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加教师',
            content: Feng.ctxPath + '/teacher/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Teacher.tableId);
            }
        });
    };

    /**
     * 点击编辑用户按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    Teacher.onEditTeacher = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '教师信息编辑',
            content: Feng.ctxPath + '/teacher/teacher_edit?teacherId=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Teacher.tableId);
            }
        });
    };

    /**
     * 点击删除用户按钮
     *
     * @param data 点击按钮时候的行数据
     */
    Teacher.onDeleteTeacher = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/teacher/deleteTeacher", function () {
                table.reload(Teacher.tableId);
                Feng.success("删除成功!");
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("teacherId", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除用户" + data.name + "?", operation);
    };

    /**
     * 点击查询按钮
     */
    Teacher.search = function () {
        var queryData = {};
        queryData['name'] = $("#name").val();
        queryData['teacherId'] = Teacher.condition.teacherId;
        table.reload(Teacher.tableId, {where: queryData});
    };

    /**
     * 导出excel按钮
     */
    Teacher.exportExcel = function () {
        var checkRows = table.checkStatus(Teacher.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };


    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Teacher.tableId,
        url: Feng.ctxPath + '/teacher/list',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Teacher.initColumn()
    });

    $('#btnAdd').click(function(){
       Teacher.openAddDept()
    });

    $('#btnSearch').click(function () {
        Teacher.search()
    });

    $('#btnExp').click(function(){
        Teacher.exportExcel()
    });

    // 工具条点击事件
    table.on('tool(' + Teacher.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'edit') {
            Teacher.onEditTeacher(data);
        } else if (layEvent === 'delete') {
            Teacher.onDeleteTeacher(data);
        }
    });

});