package com.example.mq.MqController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.util.concurrent.TimeoutException;

import com.example.mq.factory.RabbitMqFactory;
import com.example.mq.po.MessageTemplate;
import com.example.mq.po.User;
import com.example.mq.util.FinalParmarConfig;
import com.rabbitmq.client.*;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MqController {

    @Autowired
    private SendMesageClient sendMesageClient;
    @Autowired
    private WebScoketController  webScoketController = new WebScoketController();

    /**
     * 登陆
     * 
     * @return html
     */
    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    /**
     * 登陆成功，跳转首页
     *
     * @param modelAndView
     * @param user
     * @return html
     */
    @RequestMapping("/loginSuccess")
    public ModelAndView loginSuccess(ModelAndView modelAndView, @ModelAttribute("user") User user) {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("message/index");
        return modelAndView;
    }

    /**
     * 跳转消息接收页面
     *
     * @param modelAndView
     * @return html
     */
    @RequestMapping("/receiverMessagePage")
    @ResponseBody
    public String receiverMessagePage() {
        // 获取消息
        ConnectionFactory connectionFactory;
        MessageTemplate messageTemplate =null;
        try {
            connectionFactory = RabbitMqFactory.linkRabbitMq();
            Connection con = connectionFactory.newConnection();
            Channel channel = con.createChannel();
            GetResponse getResponse = channel.basicGet(FinalParmarConfig.MQ_NAME_ONE,false);
            Envelope envelope = getResponse.getEnvelope();





            if (getResponse != null){
                messageTemplate = (MessageTemplate) toPlay(getResponse.getBody(), MessageTemplate.class);
                messageTemplate.setDeliveryTag(String.valueOf(envelope.getDeliveryTag()));
                webScoketController.onMessage(new JSONObject(messageTemplate).toString());
            }
            channel.close();
            con.close();
        } catch (KeyManagementException e) {
            System.out.println(e);
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e);
        } catch (URISyntaxException e) {
            System.out.println(e);
        }catch (IOException e) {
            System.out.println(e);
        } catch (TimeoutException e) {
            System.out.println(e);
        }catch (Exception e){
            System.out.println(e);
        }
        return "SUCCESS";
    }

    /**
     * 跳转消息发送页面
     * @param modelAndView
     * @param user
     * @return
     */
    @RequestMapping("/toSendMessagePage")
    public ModelAndView toSendMessagePage(ModelAndView modelAndView,@ModelAttribute("user") User user){
        modelAndView.addObject("user",user);
        modelAndView.setViewName("message/sendMessage");
        return modelAndView;
    }

    /**
     * 发送消息
     * @param template
     * @return String
     */
    @RequestMapping("/sendMessage")
    @ResponseBody
    public String sendMessage(@ModelAttribute("messageTemplate") MessageTemplate template) {
        return sendMesageClient.sendMessage(template);
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
