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

import static com.example.demo.HttpClientUtil.*;

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

//    public static void main(String[] args) {
//        String url="http://localhost:8087/iop/login";
//        String json="";
//        String aa= doPostJson(url, json);
//        System.out.println(aa);
//    }
    public static void main(String[] args) {
        String url="http://localhost:8085/iop";
        Map<String, String> param=new HashMap<String, String>();
        param.put("method","QHAI_UNHQ_MarketingaddSubitemmgdService");
        param.put("content","{\n" +
                "\t\n" +
                "\t\"approveFlow\": {\n" +
                "\t\t\"operatorId\": \"1\"\n" +
                "\t\t\n" +
                "\t},\n" +
                "\t\"planId\":\"2164\",\n" +
                "\t\n" +
                "\t\"operatorId\":\"1\",\n" +
                "\t\"operatorName\":\"admin\",\n" +
                "\t\"orgId\":\"3\"\n" +
                "}");
        String aa= doPost(url,param);
        System.out.println(aa);
      JSONObject resJson = JSONObject.fromObject(aa);
      String result=resJson.getString("result");
      String SVC_CONTENT = JSONObject.fromObject(result).getString("SVC_CONTENT");
      String X_RESULTCODE = JSONObject.fromObject(result).getString("X_RESULTCODE");
      String X_RESULTINFO = JSONObject.fromObject(result).getString("X_RESULTINFO");
      if(X_RESULTCODE.equals("0")){
          System.out.println(SVC_CONTENT);
      }else{
          System.out.println("请求失败");
      }



    }
}
