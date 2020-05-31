package com.example.demo.controller;

import com.example.demo.Model.Book;
import net.sf.json.JSONObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.demo.HttpClientUtil.doPostJson;

@RestController
public class CustomerController {
    /**
     * 客户控制器
     */
    @JmsListener(destination = "active.queue")
    public void readActiveQueue(String message){
        System.out.println("接收到"+message);
    }
    @JmsListener(destination="smsDestination")
    public void receiveQueue2(ObjectMessage objectMessage){
        try {
            Book user = (Book) objectMessage.getObject();
            System.out.println(user);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
//    public void onMessage(Message message, Session session) {
//        ActiveMQObjectMessage objectMessage=(ActiveMQObjectMessage)message;
//        try {
//            Book book=(Book)objectMessage.getObject();
//            System.out.println(book);
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }

//————————————————
//版权声明：本文为CSDN博主「你所有承诺」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
//原文链接：https://blog.csdn.net/stronglyh/article/details/81024588
//
//
//
//    }
    @JmsListener(destination="sms")
    public void onMessag(Map map) {
        String phoneNumbers = map.get("phoneNumbers").toString();
        String templateId = map.get("templateId").toString();

        System.out.println("aaaa:"+map);
    }

    public static void main(String[] args) {
        String val = "";
         Random random = new Random();
          for (int i = 0; i < 10; i++) {
          val += String.valueOf(random.nextInt(10));
            }
        String date=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String ExchangeId="BLSDXT"+date+val;
        System.out.println(ExchangeId);
        String main_number="18909718131";
        String slstaff="avdsfasfdsf";
        String xxstaf="avdsfasfdsf";
        String remake=main_number+"订购月租优惠（测试测试测试 数据啊 别信）";
        System.out.println(remake);
        String url="http://135.192.93.101:8000/api/http/InsertITipNsInfo/1.0?access_token=MGFkODUxMGE3YWU1NGNhMGE2YjliNzQzZjM5MjgxMjk=";
//        String json="{\n" +
//                "\"Root\":{\n" +
//                "\t\"Header\":{\n" +
//                "\t\"ExchangeId\":\""+ExchangeId+"\",\n" +
//                "\t\"BizCode\":\"InsertITipNsInfo\",\n" +
//                "\t\"ClientId\":\"BLSDXT\",\n" +
//                "\t\"Password\":\"BLSDXT\"\n" +
//                "\t},\n" +
//                "\"OrderRequest\":{\n" +
//                "\"accNum\":\""+main_number+"\",\n" +
//                "\"staffcode\":\""+slstaff+"\",\n" +
//                "\"partyStaffCode\":\""+xxstaf+"\",\n" +
//                "\"orderSource\":\"4003\",\n" +
//                "\"remark\":\""+remake+"\",\n" +
//                "}\n" +
//                "}\n" +
//                "}\n";
//        String json="{\"Root\":{\n" +
//                "\t\"Header\":{\n" +
//                "\t\"ExchangeId\":\""+ExchangeId+"\",\n" +
//                "\t\"BizCode\":\"InsertITipNsInfo\",\n" +
//                "\t\"ClientId\":\"BLSDXT\",\n" +
//                "\t\"Password\":\"BLSDXT\"\n" +
//                "\t},\n" +
//                "\t\"OrderRequest\":{\n" +
//                "\t\"accNum\":\""+main_number+"\",\n" +
//                "\t\"staffcode\":\""+slstaff+"\",\n" +
//                "\t\"partyStaffCode\":\""+xxstaf+"\",\n" +
//                "\t\"orderSource\":\"4000\",\n" +
//                "\t\"remark\"::\""+remake+"\",\n" +
//                "\t}\n" +
//                "\t}\n" +
//                "}\n";
        String json="{\"Root\":{\n" +
                "\t\"Header\":{\n" +
                "\t\"ExchangeId\":\""+ExchangeId+"\",\n" +
                "\t\"BizCode\":\"InsertITipNsInfo\",\n" +
                "\t\"ClientId\":\"BLSDXT\",\n" +
                "\t\"Password\":\"BLSDXT\"\n" +
                "\t},\n" +
                "\t\"OrderRequest\":{\n" +
                "\t\"accNum\":\""+main_number+"\",\n" +
                "\t\"staffcode\":\""+slstaff+"\",\n" +
                "\t\"partyStaffCode\":\""+xxstaf+"\",\n" +
                "\t\"orderSource\":\"4004\",\n" +
                "\t\"remark\":\""+remake+"\"\n" +
                "\t}\n" +
                "\t}\n" +
                "}";
        String aa= doPostJson(url, json);
       System.out.println(aa);
        JSONObject resJson = JSONObject.fromObject(aa);
        String Root=resJson.getString("Root");
        JSONObject Header = JSONObject.fromObject(Root);
        String Result=Header.getString("Result");
        JSONObject Header2 = JSONObject.fromObject(Result);
        String Response1=Header2.getString("data");
        JSONObject Codee = JSONObject.fromObject(Response1);
       // System.out.println(Codee);
        String resultCode=Codee.getString("resultCode");
        String ns_order_no=Codee.getString("ns_order_no");
        //System.out.println("Code"+Code);
        if(resultCode.equals("000")){
            System.out.println("受理成功！");
            Map<String, Object> parama=new HashMap<String, Object>();
            parama.put("ExchangeId",ExchangeId);
            parama.put("main_number",main_number);
            parama.put("slstaff",slstaff);
            parama.put("xxstaf",xxstaf);
            parama.put("ns_order_no",remake);
            parama.put("ns_order_no",ns_order_no);
        }

    }
}
