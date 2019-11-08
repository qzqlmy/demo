package com.example.demo.controller;

import com.example.demo.Model.Book;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.Map;

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
}
