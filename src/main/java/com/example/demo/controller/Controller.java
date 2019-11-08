package com.example.demo.controller;


import com.example.demo.DemoApplication;
import com.example.demo.Model.Book;
import com.example.demo.Model.CommonResult;
import com.example.demo.Model.CommonRseult;
import com.example.demo.Model.result;
import com.example.demo.service.UserService;
import io.swagger.annotations.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.lang.Nullable;

import org.springframework.web.bind.annotation.*;

import javax.jms.*;
import javax.jms.Queue;
import java.io.Serializable;
import java.util.*;

import static com.example.demo.HttpClientUtil.doPostJson;

@Api(tags = "Springboot学习")
@RestController
@RequestMapping("/index") //在类上使用RequestMapping，里面设置的value就是方法的父路径
public class Controller extends BaseController{

    @Autowired
    private UserService userService;



    RedisTemplateService redisTemplateService;
    @Autowired
    RedisTemplateService redisTemplate;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;
    @Autowired
    private JmsTemplate jmsTemplate;
    /**
     * 消息生产者
     */
    @RequestMapping("/send")
    public void send() {
        //指定消息发送的目的地及内容
        HashMap map=new HashMap();
        map.put("url", "2222");
        map.put("code", 0);
        this.jmsMessagingTemplate.convertAndSend(this.queue,"新发送的消息！");

    }



    public static void main(String[] args) {
        String serinumbe="11398277";
        String queryFlag="1";
        String billQueryType="0";
        String destinationAttr="2";
        String url="http://135.202.130.66:8099/billing/acct/GetAcctOwe";
        String json="{\n" +
                "\t\t\t\t\t\t\t\"systemId\":\"100001\" ,\n" +
                "                            \"stdCcrBillQueryOwe\": {\n" +
                "                            \"billQuery\": {\n" +
                "                            \"queryFlag\": "+queryFlag+","  +
                "                            \"billQueryType\": "+billQueryType+","  +
                "                            \"destinationAttr\":"+destinationAttr+"," +
                "                            \"feeQueryFlag\": \"0\", \"destinationAccount\": " +serinumbe+
                "                          }\n" +
                "                         }\n" +
                "\t\t\t\t\t\t}";
       String aa= doPostJson(url, json);
        JSONObject resJson = JSONObject.fromObject(aa);
//
        String errorMsg=resJson.getString("errorMsg");
        int bb=resJson.getInt("errorCode");
        System.out.println(errorMsg);
        if(bb==0) {

            JSONObject stdCcaBillQueryOwe = JSONObject.fromObject(JSONObject.fromObject(resJson.getString("stdCcaBillQueryOwe")));
            String acctID = stdCcaBillQueryOwe.getString("acctID");
            String feeBillingCycl=stdCcaBillQueryOwe.getString("feeBillingCycle");
            System.out.println(feeBillingCycl);
          if(!feeBillingCycl.equals("null")) {
              JSONArray feeBillingCycle = stdCcaBillQueryOwe.getJSONArray("feeBillingCycle");
              System.out.println(acctID);
              int chargePaye = 0;
              int sumCharg = 0;
              for (int i = 0; i < feeBillingCycle.size(); i++) {
                  int chargePayed = Integer.parseInt(JSONObject.fromObject(feeBillingCycle.getString(i)).getString("chargePayed"));
                  int sumCharge = Integer.parseInt(JSONObject.fromObject(feeBillingCycle.getString(i)).getString("sumCharge"));
                  chargePaye = chargePaye + chargePayed;
                  sumCharg = sumCharg + sumCharge;
                }
              System.out.println(sumCharg);
              System.out.println(chargePaye);
              System.out.println(sumCharg - chargePaye);
          }else{
              System.out.println("账户欠费信息为空！");
          }
        }else{
            System.out.println("查询失败！");
        }



    }
    @RequestMapping("/sendd")
    public void sendd() {
        Book user = new Book();
        user.setId(11);
        user.setTitle("aaaadddd");
        user.setpublish("helloredis");
        user.setAuthor("aaaaa");
        jmsTemplate.send("smsDestination", session -> {
            ObjectMessage objectMessage = session.createObjectMessage((Serializable) user);
            return objectMessage;
        });
        //{
//            @Override
//            public Message createMessage(Session session) throws JMSException {
//                MapMessage mapMessage = session.createMapMessage();
//
//                mapMessage.setString("phoneNumbers", "123456r");//电话号
////                mapMessage.setString("templateId", "22222");//模板id
////                mapMessage.setString("smsSign", "333333");//签名id
////                mapMessage.setString("params", "1111" + " " + "1");
//                return mapMessage;
//            }
       // }


    }
    @RequestMapping("/senda")
    @ApiOperation(value="使用activemq发送消息",notes = "用activemq发送消息,后台监听发送过去的消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNumbers", value = "电话号码" ),
            @ApiImplicitParam(name = "templateId", value = "模板ID" )
    })

    public void senda(@RequestParam(value="phoneNumbers",required = false) String phoneNumbers,@RequestParam(value="templateId",required = false) String templateId) {
        jmsTemplate.send("sms", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                MapMessage mapMessage = session.createMapMessage();
                mapMessage.setString("phoneNumbers", phoneNumbers);//电话号
                mapMessage.setString("templateId", templateId);//模板id
                mapMessage.setString("smsSign", "ccccc");//签名id
                mapMessage.setString("params", "1111" + " " + "1");
                return mapMessage;
            }
        });
    }
    @GetMapping("/hi")  //如果方法上的RequestMapping没有value，则此方法默认被父路径调用
    public String index(){
        Book user = new Book();
        user.setId(11);
        user.setTitle("aaaadddd");
        user.setpublish("11111");
        user.setAuthor("aaaaa");


        redisTemplateService.set("key1",user);
        stringRedisTemplate.opsForValue().set("bbbbb", "222222222222");

        Book usaa = redisTemplateService.get("key1",Book.class);
       // System.out.println( stringRedisTemplate.opsForValue().get("bbbbb"));
        System.out.println(usaa);


        return "hello spring boot";
    }
    @RequestMapping("/redis")
    public String index2() {
        Long start= System.currentTimeMillis() ;
        List List = stringRedisTemplate.executePipelined(new RedisCallback<Long>() {
            @Nullable
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                for (int i = 0; i < 10; i++) {
                    String key = "pipeline_" + i;
                    String value = "value_" + i;
                    connection.set(key.getBytes(), value.getBytes());
                }
                return null;
            }
        });

        Long end = System.currentTimeMillis();
        System.out.println("Pipeline插入1000000条记录耗时：" + (end - start) + "毫秒。");
        return null;
    }

    @RequestMapping("/usersa/{userIda}")
    @ApiOperation("根据id获取用户ces")
    @ApiImplicitParam(name = "userIda", value = "模板ID" )

    @ApiResponses({ @ApiResponse(code=200,message="请求成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public CommonResult getUsera(@PathVariable("userIda") int userIda){
        Book user1 = new Book();
        user1.setId(11);
        user1.setTitle("aaaadddd");
        user1.setpublish("11111");
        user1.setAuthor("aaaaa");

        Book user2 = new Book();
        user2.setId(13);
        user2.setTitle("aaaaaaaa");
        user2.setpublish("11111");
        user2.setAuthor("aaaaa");
        List<Book> aa=new ArrayList<>();
        aa.add(user1);
        aa.add(user2);
        HashMap<String,Object> map2=new HashMap<String,Object>();
        map2.put("code",0);
        map2.put("msg","请求成功");
        map2.put("data",aa);
//        CommonRseult ab=new CommonRseult();
//        ab.setMsg("请求成功");
//        ab.setCode(0);
//        ab.setAa(aa);
        //return CommonResult.failed("请求失败");
        return CommonResult.success(aa);
    }
    @RequestMapping("/users/{userId}")
    @ApiOperation("根据id获取用户")
    @ApiImplicitParam(name = "userId", value = "模板ID" )

    @ApiResponses({ @ApiResponse(code=200,message="请求成功"),
            @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
    })
    public result getUser(@PathVariable("userId") int userId){
        Book user1 = new Book();
        user1.setId(11);
        user1.setTitle("aaaadddd");
        user1.setpublish("11111");
        user1.setAuthor("aaaaa");

        Book user2 = new Book();
        user2.setId(13);
        user2.setTitle("aaaaaaaa");
        user2.setpublish("11111");
        user2.setAuthor("aaaaa");
        List<Book> aa=new ArrayList<>();
        aa.add(user1);
        aa.add(user2);
        HashMap<String,Object> map2=new HashMap<String,Object>();
        map2.put("code",0);
        map2.put("msg","请求成功");
        map2.put("data",aa);
        result ab=new result();
        ab.setMsg("请求成功");
        ab.setCode(0);
        ab.setAa(aa);
        return ab;
    }


    @RequestMapping("/queryfoodlista")
    @ApiOperation(value="分页查询后台数据",notes = "分页查询数据并给前台返回一个json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "每页显示条数" ),
            @ApiImplicitParam(name = "page", value = "页数" )
    })
    public HashMap<String, Object> queryfoodlista(@RequestParam(value="limit",required = false) String limit, @RequestParam(value="page",required = false) String page){
        String foodmodel= request.getParameter("key[foodmodel]");
        String foodname = request.getParameter("key[foodname]");
        String price = request.getParameter("key[price]");
        System.out.println(foodname);
//        String page = request.getParameter("page");
//        String limit = request.getParameter("limit");
//        int cc=(Integer.parseInt( request.getParameter("limit")));
//        int pagecruent =(Integer.parseInt( request.getParameter("page"))-1)*cc;

        int cc=Integer.parseInt( limit);
        int pagecruent =(Integer.parseInt( page)-1)*cc;
        String aa= String.valueOf(pagecruent);
        HashMap<String,Object> map=new HashMap<String,Object>();
        map.put("foodmodel",foodmodel);
        map.put("foodname", foodname);
        map.put("start", aa);
        map.put("end", limit);
        map.put("price", price);
        HashMap<String,Object> map2=new HashMap<String,Object>();
        map.put("foodmodel",foodmodel);
        map2.put("foodname", foodname);
        map2.put("price", price);
        List<Map> inforList = userService.selectList(map);
        List<Map> inforLis= userService.selectList(map2);
        HashMap<String,Object> ma=new HashMap<String,Object>();
        ma.put("code",0);
        ma.put("count",inforLis.size());
        ma.put("msg","");

        //JSONArray rea=JSONArray.fromObject(inforList);
        ma.put("data",inforList);
        //JSONObject re=JSONObject.fromObject(ma);
       return ma;

}
}
