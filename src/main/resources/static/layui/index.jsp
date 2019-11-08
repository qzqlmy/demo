<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/30 0030
  Time: 下午 2:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="css/layui.css">

    <!-- 公共样式 开始 -->
    <link rel="stylesheet" type="text/css" href="ordingcount/css/base.css">
    <link rel="stylesheet" type="text/css" href="ordingcount/css/iconfont.css">
    <script type="text/javascript" src="ordingcount/framework/jquery-1.11.3.min.js" ></script>
    <link rel="stylesheet" type="text/css" href="ordingcount/layui/css/layui.css">
    <script type="text/javascript" src="ordingcount/layui/layui.js" ></script>
    <!-- 滚动条插件 -->
    <link rel="stylesheet" type="text/css" href="ordingcount/css/jquery.mCustomScrollbar.css">
    <script src="ordingcount/framework/jquery-ui-1.10.4.min.js"></script>
    <script src="ordingcount/framework/jquery.mousewheel.min.js"></script>
    <script src="ordingcount/framework/jquery.mCustomScrollbar.min.js"></script>
    <script src="ordingcount/framework/cframe.js"></script><!-- 仅供所有子页面使用 -->
    <!-- 公共样式 结束 -->

    <!--报表-->
    <script type="text/javascript" src="ordingcount/framework/echarts.min.js" ></script>
    <link rel="stylesheet" type="text/css" href="ordingcount/css/backHome.css">
    <link rel="stylesheet" href="ordingcount/css/layui.css">
    <script type="text/javascript" src="ordingcount/js/backHome.js" ></script>
    <script type="text/javascript"  >

        $.ajax({
            url: "/Ordering/ordinginfo",
            type: 'post',
            dataType: "json",
            success: function (result) {
                if (result.ordingcount >0) {
                    $("#ordingcount").text(result.ordingcount)
                    $("#ordingprice").text(result.ordingprice)
                    //
                } else {
                    $("#ordingcount").text(0)
                    $("#ordingprice").text(0)
                }
                if(result.ordingcount2>0){
                    $("#ordingcount2").text(result.ordingcount2)
                    $("#ordingprice2").text(result.ordingprice2)
                }else{
                    $("#ordingcount2").text(0)
                    $("#ordingprice2").text(0)
                }
            },error: function (error) {

            }

        });
    </script>

</head>
<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">
            <div class="layui-logo-toggle" kit-toggle="side" data-toggle="on">
                <img style="margin-top: 15px" width="20px" height="20px" src="images/fy.png">
            </div>
            <div class="layui-logo-brand">
                <a href="#/">后台管理系统</a>
            </div>
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left" class="layui-bg-green">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="/goodsManage/goodslist.jsp">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right" class="layui-bg-green">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">所有商品</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="javascript:;">列表三</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">解决方案</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">列表一</a></dd>
                        <dd><a href="javascript:;">列表二</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">云市场</a></li>
                <li class="layui-nav-item"><a href="/goodsManage/goodslist.jsp">发布商品</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div class="row">
            <div class="col-md-2">
                <div class="module" >



                    <div class="title"><span></span><font>今日数据统计</font></div>
                    <div class="content">
                        <div class="today_data">
                            <ul>
                                <li class="new_bt">
                                    <div class="state"><i class="iconfont icon-xiangxiajiantoucuxiao"></i></div>
                                    <div class="data1">
                                        <p>今日订单数量</p>
                                        <h1 id="ordingcount">2</h1>
                                    </div>
                                    <div class="data2">
                                        <p>昨日订单量</p>
                                        <h1 id="ordingcount2">100</h1>
                                    </div>
                                </li>
                                <li>
                                    <div class="state"><i class="iconfont icon-xiangshangjiantoucuxiao"></i></div>
                                    <div class="data1">
                                        <p>今日订单总金额</p>
                                        <h1 id="ordingprice">2</h1>
                                    </div>
                                    <div class="data2">
                                        <p>昨日订单总金额</p>
                                        <h1 id="ordingprice2">5000</h1>
                                    </div>
                                </li>

                                <li>
                                    <div class="state">- - </div>
                                    <div class="data1">
                                        <p><a href="/goodsManage/goodslist.jsp">商品管理</a></p>

                                    </div>
                                    <div class="data2">
                                        <p><a href="/goodsManage/ordinglist.jsp">订单管理</a></p>

                                    </div>
                                </li>

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-5">
                    <div class="module">
                        <div class="title"><span></span><font>订单总量统计</font></div>
                        <%--<span onclick="orderMapDataRefresha()" style="top: 2%;right:14% ;float: right;color: #21c667;margin: 4px;font-size: 6px">近24小时</span>--%>
                        <span onclick="orderMapDataRefreshb()" style="top: 2%;right:14% ;float: right;color: #2d64fb;margin: 4px;font-size: 6px">近一周</span>
                        <%--<span onclick="orderMapDataRefreshc()" style="top: 2%;right:10% ;float: right;color: #8f1311;margin: 4px;font-size: 6px">近一月</span>--%>
                        <div class="content">
                            <div id="echartsOrderNum" style="width: 100%;height:325px;"></div>
                        </div>
                    </div>
                </div>
                <div class="col-md-5 no_mr">
                    <div class="module">
                        <div class="title"><span></span><font>交易金额总量统计</font></div>
                        <div class="content">
                            <div class="today_data">
                                <%--<span style="top: 2%;right:14% ;float: right;color: #21c667;margin: 4px;font-size: 6px">近24小时</span>--%>
                                <span style="top: 2%;right:14% ;float: right;color: #2d64fb;margin: 4px;font-size: 6px">近一周</span>
                                <%--<span style="top: 2%;right:10% ;float: right;color: #8f1311;margin: 4px;font-size: 6px">近一月</span>--%>
                                <div id="echartsMoney" style="width: 100%;height:325px;"></div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md_5" >
                <div class="module">
                    <div class="title"><span></span><font>销量统计</font></div>
                    <div class="content">
                        <div id="echartsMap" style="width: 100%;height:325px;"></div>
                    </div>
                </div>
            </div>

        </div>


</div>
<script src="layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</div>

</body>
<script>
    var dom = document.getElementById("echartsMap");
    var myChart = echarts.init(dom);
    var app = {};

    $.ajax({
        url: "/Ordering/querylistxl",
        type: 'post',
        dataType: "json",
        success: function (result) {
            var arr1 = new Array();
            var arr2 = new Array();
            var len = result.length;

            for (var i = 0; i < len; i++) {
                arr1.push(result[i].foodname)
                arr2.push(result[i].countt)



            }


            adddata(arr1,arr2)

        }

    })


    // echartsMap("echartsMap",BJData);
    option = null;
    app.title = '世界人口总量 - 条形图';
    function adddata(arr1,arr2) {

        option = {
            title: {
                text: '本月销量TOP10'

            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            // legend: {
            //     data: ['2011年', '2012年']
            // },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '1%',
                containLabel: true
            },
            xAxis: {
                type: 'value',
                axisLine: {
                    show: false,
                    lineStyle: {
                        color: '#9ac0cd'
                    }
                },
                axisLabel: {
                    color: '#9ac0cd'
                },

                boundaryGap: [0, 0.01]
            },
            yAxis: {
                type: 'category',
                axisLine: {
                    show: false,
                    lineStyle: {
                        color: '#21c667'
                    }
                },
                axisLabel: {
                    color: '#9ac0cd'
                },

                data:
                arr1
                //["黑椒可乐牛排", "黑椒可乐牛排", "黑椒可乐牛排"]
                // [" 黑椒可乐牛排"," 黄金香酥鸡块"," E"]
            },

            series: [
                // {
                //     name: '2011年',
                //     type: 'bar',
                //     data: [50, 40, 30, 20, 10, 100]
                // },
                {

                    type: 'bar',
                    itemStyle: {
                        normal: {
                            //好，这里就是重头戏了，定义一个list，然后根据所以取得不同的值，这样就实现了，
                            color: function (params) {
                                // build a color map as your need.
                                var colorList = [
                                    '#21c667', '#21c667', '#FCCE10', '#E87C25', '#27727B',
                                    '#FE8463', '#9BCA63', '#FAD860', '#F3A43B', '#60C0DD',
                                    '#D7504B', '#C6E579', '#F4E001', '#F0805A', '#26C0C0'
                                ];
                                return colorList[params.dataIndex]
                            },
                            //以下为是否显示，显示位置和显示格式的设置了
                            label: {
                                show: false,
                                position: 'top',
//                             formatter: '{c}'
                                formatter: '{b}\n{c}'
                            }
                        }
                    },
                    barGap: '5',
                    barWidth: 20,
                    data: arr2
                }
            ]
        };
        myChart.setOption(option, true);
    }
    function adddata1(arr1,arr2) {
        alert(arr1)
        echartsOrderNum("echartsOrderNum",arr2,arr1);
    }
    // var data = [820, 932, 901, 934, 1290, 1330, 1320];
    // var title = ['今天', '昨天', '1月15日', '1月16日', '1月17日', '1月18日', '1月19日'];
    function orderMapDataRefresh() {

        $.ajax({
            url: "/Ordering/weeklist",
            type: 'post',
            dataType: "json",
            success: function (result) {

                var arr1 = new Array();
                var arr2 = new Array();
                var arr3 = new Array();
                var len = result.length;
                for (var i = 0; i < len; i++) {
                    var str = result[i].listdate.replace(/-/g,"").substr(4, 7);
                    // var aa=result[i].listprice.replace(/ /g,"0");
                    var aa ="";
                    if(result[i].listprice=="undefined"){
                        aa==0;
                    }else{
                        aa=result[i].listprice;
                    }
                    arr1.push(str)
                    arr2.push(result[i].listcount)
                    arr3.push(aa)

                }

                echartsOrderNum("echartsOrderNum",arr2,arr1);
                echartsMoney("echartsMoney",arr3,arr1)
            }
        });

    }
    function orderMapDataRefreshb() {
        var data = [820, 932, 101, 934, 1290, 1330, 820];
        var title = ['一', '二', '三', '四', '五', '六', '天'];
        echartsOrderNum("echartsOrderNum",data,title);
    }
    function orderMapDataRefreshc() {
        var data= [820, 932, 101, 934, 1290, 1330, 820, 820, 932, 101, 934, 1290, 1330, 820];
        var title= ['1日', '2日', '3日', '4日', '4日', '6日', '7日', '8日', '9日', '10日', '11日', '12日', '13日', '14日'];
        echartsOrderNum("echartsOrderNum",data,title);


    }


    var data1 = [1120000, 15268423, 8729600, 1012500, 13220000, 1012500, 11320000, 15268423, 8729600, 1012500, 13220000, 1012500, 11320000];
    var title1 = ['1日', '2日', '3日', '4日', '4日', '6日', '7日', '8日', '9日', '10日', '11日', '12日', '13日'];
    echartsMoney("echartsMoney",data1,title1);
    orderMapDataRefresh();
</script>
</html>
