layui.use(['table', 'admin', 'ax', 'ztree'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;

    var Student = {
        tableId: 'studentTable',
        condition: {

        }
    };

    Student.initColumn = function(){
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'name', sort: true, title: '学生名称'},
            {field: 'before_name', sort: true, title: '曾用名'},
            {field: 'age', sort: true, title: '年龄'},
            {field: 'className', sort: true, title: '班级名称'},
            {field: 'headMasterName', sort: true, title: '班主任'},
            {field: 'remark', sort: true, title: '备注'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]]
    };

    /**
     * 弹出添加
     */
    Student.openAddStudent = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加学生信息',
            area:['800px','500px'],
            content: Feng.ctxPath + '/studentController/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(Student.tableId);
            }
        });
    };

    /**
     * 点击编辑用户按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    Student.onEditStudent = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '学生信息编辑',
            area:['800px','500px'],
            content: Feng.ctxPath + '/studentController/edit?studentId=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(Student.tableId);
            }
        });
    };
    /**
     * 删除学生数据信息
     * @param data
     */
    Student.onDeleteStudent = function (data) {
        console.log('1111111111111')
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/studentController/deleteStudent", function () {
                table.reload(Student.tableId);
                Feng.success("删除成功!");
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("studentId", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除学生" + data.name + "?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Student.tableId,
        url: Feng.ctxPath + '/studentController/getListInfo',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Student.initColumn()
    });

    $("#btnAdd").click(function(){
       Student.openAddStudent();
    });

    // 工具条点击事件
    table.on('tool(' + Student.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'edit') {
            Student.onEditStudent(data);
        } else if (layEvent === 'delete') {
            Student.onDeleteStudent(data);
        }
    });

});