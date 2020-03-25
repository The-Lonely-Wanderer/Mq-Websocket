package com.example.mq.MqController;

import com.example.mq.po.MessageTemplate;
import com.example.mq.po.User;
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

    /**
     * 登陆
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
    public ModelAndView receiverMessagePage(ModelAndView modelAndView) {


        return modelAndView;
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

}
