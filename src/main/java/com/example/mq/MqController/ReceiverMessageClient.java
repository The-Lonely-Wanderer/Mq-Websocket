package com.example.mq.MqController;

import com.example.mq.po.MessageTemplate;
import com.example.mq.util.FinalParmarConfig;
import org.json.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.io.*;
import java.lang.reflect.Constructor;

@Component
public class ReceiverMessageClient {

    private WebScoketController webScoketController;

    //@RabbitListener(queues = FinalParmarConfig.MQ_NAME_ONE)
    public void recevierMessage(Message message) {
        byte[] messageBody = null;
        try {
            messageBody = message.getBody();
            if (messageBody != null) {
                //messageByte = messageBody.toString();
                webScoketController = new WebScoketController();
                MessageTemplate messageTemplate = (MessageTemplate) toPlay(message.getBody(), MessageTemplate.class);
                webScoketController.onMessage(new JSONObject(messageTemplate).toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /*
     * Title: toPlay
     * Description:反序列化对象
     */
    public static Object toPlay(byte[] bs, Class c) throws Exception {
        Constructor cl = c.getConstructor();
        Object readObject = cl.newInstance();
        //创建存放二进制数据的API
        ByteArrayInputStream byteArrayInputStream = null;
        //创建反序列化对象
        ObjectInputStream objectInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bs);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            //校验测试
            readObject = objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            objectInputStream.close();
        }
        return readObject;
    }
}
