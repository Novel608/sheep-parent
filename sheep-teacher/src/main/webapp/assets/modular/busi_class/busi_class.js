layui.use(['table', 'admin', 'ax', 'ztree'], function () {
    var $ = layui.$;
    var table = layui.table;
    var $ax = layui.ax;
    var admin = layui.admin;
    var $ZTree = layui.ztree;

    var BusiClass = {
        tableId: "busiClassTable",
        condition: {
            teacherId: "",
            name: ""
        }
    };

    BusiClass.initColumn = function(){
        return [[
            {type: 'checkbox'},
            {field: 'id', hide: true, sort: true, title: 'id'},
            {field: 'name', sort: true, title: '班级名称'},
            {field: 'short_name', sort: true, title: '班级简称'},
            {field: 'head_master', sort: true, title: '班主任'},
            {field: 'remark', sort: true, title: '备注'},
            {align: 'center', toolbar: '#tableBar', title: '操作', minWidth: 200}
        ]]
    };

    /**
     * 弹出添加
     */
    BusiClass.openAddDept = function () {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '添加教师',
            content: Feng.ctxPath + '/busiClass/add',
            end: function () {
                admin.getTempData('formOk') && table.reload(BusiClass.tableId);
            }
        });
    };

    /**
     * 点击编辑用户按钮时
     *
     * @param data 点击按钮时候的行数据
     */
    BusiClass.onEditTeacher = function (data) {
        admin.putTempData('formOk', false);
        top.layui.admin.open({
            type: 2,
            title: '教师信息编辑',
            content: Feng.ctxPath + '/busiClass/edit?id=' + data.id,
            end: function () {
                admin.getTempData('formOk') && table.reload(BusiClass.tableId);
            }
        });
    };

    /**
     * 点击删除用户按钮
     *
     * @param data 点击按钮时候的行数据
     */
    BusiClass.onDeleteTeacher = function (data) {
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/busiClass/deleteClass", function () {
                table.reload(BusiClass.tableId);
                Feng.success("删除成功!");
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("classId", data.id);
            ajax.start();
        };
        Feng.confirm("是否删除用户" + data.name + "?", operation);
    };

    /**
     * 点击查询按钮
     */
    BusiClass.search = function () {
        var queryData = {};
        queryData['name'] = $("#name").val();
        queryData['teacherId'] = BusiClass.condition.teacherId;
        table.reload(BusiClass.tableId, {where: queryData});
    };

    /**
     * 导出excel按钮
     */
    BusiClass.exportExcel = function () {
        var checkRows = table.checkStatus(BusiClass.tableId);
        if (checkRows.data.length === 0) {
            Feng.error("请选择要导出的数据");
        } else {
            table.exportFile(tableResult.config.id, checkRows.data, 'xls');
        }
    };


    // 渲染表格
    var tableResult = table.render({
        elem: '#' + BusiClass.tableId,
        url: Feng.ctxPath + '/busiClass/getListInfo',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: BusiClass.initColumn()
    });

    $('#btnAdd').click(function(){
       BusiClass.openAddDept()
    });

    $('#btnSearch').click(function () {
        BusiClass.search()
    });

    $('#btnExp').click(function(){
        BusiClass.exportExcel()
    });

    // 工具条点击事件
    table.on('tool(' + BusiClass.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;
        if (layEvent === 'edit') {
            BusiClass.onEditTeacher(data);
        } else if (layEvent === 'delete') {
            BusiClass.onDeleteTeacher(data);
        }
    });

});