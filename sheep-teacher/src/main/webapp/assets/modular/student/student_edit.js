layui.use(['layer', 'form', 'admin', 'ax', 'upload','table'], function () {
    var $ = layui.jquery;
    var $ax = layui.ax;
    var form = layui.form;
    var admin = layui.admin;
    var layer = layui.layer;
    var upload = layui.upload;
    var table = layui.table;
    // 让当前iframe弹层高度适应
    admin.iframeAuto();

    var ajax = new $ax(Feng.ctxPath + "/studentController/getStudentInfo?studentId=" + Feng.getUrlParam("studentId"));
    var result = ajax.start();
    form.val('studentForm', result);

    var StudentContact = {
        tableId: 'studentContactTable'
    };
    // 初始化联系人信息列表
     StudentContact.initColumn = function(){
       return [[
           {field: 'contact_name', sort: true, title: '姓名',edit:'text'},
           {field: 'contact_type_name', sort: true, title: '关系',edit:'text'},
           {field: 'contact_mobile', sort: true, title: '手机号码',edit:'text'},
           {field: 'remark', sort: true, title: '备注',edit:'text'},
           {field: 'id', hide: true, title: '联系人信息ID',edit:'text'},
           {field: 'status', hide: true, title: '状态',edit:'text'},
           {field: 'studentId', hide: true, title: '学生ID',edit:'text'},
           {align: 'center', toolbar: '#contactTableBar', title: '操作', minWidth: 80}
       ]];
     };

     // 初始化渲染联系人信息表
    var tableResult = table.render({
        elem: '#' + StudentContact.tableId,
        page: false,
        height: "full-158",
        cols: StudentContact.initColumn()
    });

    var contactAjax = new  $ax(Feng.ctxPath + '/studentController/getContactList?page=1&limit=10&studentId=' + Feng.getUrlParam("studentId"));
    var contactResult = contactAjax.start();
    tableResult.reload({
        data: contactResult.data
    });

    //普通图片上传
    var uploadInst = upload.render({
        elem: '#btnUploadPicture'
        ,url: Feng.ctxPath + '/fileController/uploadFile'
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#studentPic').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(res.code != 200){
                return layer.msg('上传失败');
            }
            //上传成功
            var demoText = $('#demoText');
            demoText.html('');
            $('#picUrl').val(res.data);
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });

    $("#className").click(function(){
        top.layui.admin.open({
            type: 2,
            title: '选择班级',
            area: ['700px', '600px'],
            content: Feng.ctxPath + '/busiClass/classDlg',
            btn: ['确认'],
            yes: function(index, layero){
                var iframeWin = top[layero.find('iframe')[0]['name']];
                var res = iframeWin.ClassInfo;
                console.log(res)
                $("#classId").val(res.classId);
                $("#className").val(res.className);
                parent.layer.close(index);
            }
        });
    });


    // 添加联系人信息按钮
    $('#addContact').click(function(){
        var oldData =  table.cache[StudentContact.tableId];
        var tempData = {
            contact_name: '',
            contact_type_name: '',
            contact_mobile: '',
            remark: ''
        };
        oldData.push(tempData);
        tableResult.reload({
            data : oldData
        });
    });


    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var ajax = new $ax(Feng.ctxPath + "/studentController/editStudent", function (data) {
            Feng.success("修订成功！");

            //传给上个页面，刷新table用
            admin.putTempData('formOk', true);

            //关掉对话框
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("添加失败！" + data.responseJSON.message)
        });

        var submitData = {
            studentStr: JSON.stringify(data.field),
            contactListStr: JSON.stringify(table.cache[StudentContact.tableId])
        };

        ajax.set(submitData);
        ajax.start();
    });


    table.on("tool(" + StudentContact.tableId + ")", function(obj){
        var layEvent = obj.event;
        var data = obj.data;
        if(layEvent === 'delete'){
            var name = data.contact_name;
            layer.confirm('确定要删除' + name + '的联系方式吗？', function(index){
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
            });
        }
    });

});