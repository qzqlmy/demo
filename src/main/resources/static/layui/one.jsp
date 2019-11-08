<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<div class="layui-inline" style="margin-top: 10px">
    <label class="layui-form-label">订单号：</label>
    <div class="layui-input-inline">
        <input type="tel" name="phone"  autocomplete="off" id="ording" placeholder="输入订单号" class="layui-input">
    </div>
</div>
<div class="layui-inline" style="margin-top: 10px">
    <label class="layui-form-label">商品名称：</label>
    <div class="layui-input-inline">
        <input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" id="foodname" placeholder="输入商品名称"class="layui-input">
    </div>
</div>

<div class="layui-inline" style="margin-top: 10px">
    <label class="layui-form-label">开始时间：</label>
    <div class="layui-input-inline">
        <input type="text" class="layui-input" id="starttime" placeholder="开始时间">
    </div>
</div>
<div class="layui-inline" style="margin-top: 10px">
    <label class="layui-form-label">商品名称：</label>
    <div class="layui-input-inline">
        <input type="text" class="layui-input" id="endttime" placeholder="结束时间">
    </div>
</div>


<div style="margin-left: 6%;margin-top: 8px">
<button class="layui-btn" id="aa" data-type="reload">订单查询</button>
</div>
<table class="layui-hide" id="test"></table>


<script src="layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'

            ,url:'/Ordering/queryordinglista'
            ,cols: [[
                /*{field:'id', width:80, title: 'ID', sort: true}*/
               {field:'ordingid', width:120, title: '订单号'}
                ,{field:'foodname', width:120, title: '商品名称',sort: true}
                ,{field:'foodprice', width:120, title: '商品价格', style:' color: #2F4056;'}
                ,{field:'foodcount',width:120, title: '商品数量'}
                ,{field:'listdate', width:160, title: '下单时间', sort: true}

            ]]
            ,id: 'testReload'
            ,page: true
        });
        var $ = layui.$, active = {
            reload: function(){
                var demoReload = $('#ording');


                //执行重载
                table.reload('testReload', {
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                    ,where: {
                        key: {
                            ordingid: demoReload.val(),
                            foodname:$('#foodname').val(),
                            starttime:$('#starttime').val(),
                            endttime:$('#endttime').val()
                        }
                    }
                }, 'data');
            }
        };

        $('#aa').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>
<script>
    layui.use('laydate', function(){
        var laydate = layui.laydate;


        //开始日期时间选择器
        laydate.render({
            elem: '#starttime'
            ,type: 'datetime'
        });

         //结束日期时间选择器
        laydate.render({
            elem: '#endttime'
            ,type: 'datetime'
        });



    });
</script>

</body>
</html>