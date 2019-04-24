var TeacherInfoDlg = {
    data: {
        id: "",
        name: ""
    }
};

layui.use(['layer', 'form', 'admin', 'ax'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;
    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    $("#headMasterName").click(function(){
        top.layui.admin.open({
            type: 2,
            title: '班主任',
            area: ['700px', '600px'],
            content: Feng.ctxPath + '/teacher/teacherDlg',
            btn: ['确认'],
            yes: function(index, layero){
                var iframeWin = top[layero.find('iframe')[0]['name']];
                var res = iframeWin.TeacherInfo;
                $("#headMaster").val(res.id);
                $("#headMasterName").val(res.name);
                parent.layer.close(index);
            }
        });
    });

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/busiClass/addClass", function (data) {
            Feng.success("添加成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });
        ajax.set(data.field);
        ajax.start();
    });
});