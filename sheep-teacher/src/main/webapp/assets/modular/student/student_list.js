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

});