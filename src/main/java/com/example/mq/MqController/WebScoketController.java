package com.example.mq.MqController;

import com.example.mq.po.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.rabbitmq.tools.json.JSONUtil;
import org.json.JSONObject;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
@ServerEndpoint("/websocket/")
public class WebScoketController {
    private static CopyOnWriteArraySet<Session> webSocketSet;
    private static Map<String, CopyOnWriteArraySet<Session>> sessionMap = new HashMap<String, CopyOnWriteArraySet<Session>>();

    @OnOpen
    public void onOpen(Session session) {
        webSocketSet = new CopyOnWriteArraySet<Session>();
        webSocketSet.add(session);
        sessionMap.put(session.getId(), webSocketSet);
    }

    @OnMessage
    public void onMessage(String message) {
        sessionMap.forEach((id, sessions) -> {
            for (Session session : sessions) {
                if (session.isOpen()) {
                    try {
                        session.getAsyncRemote().sendText(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @OnClose
    public void onClose(Session session) {
        sessionMap.forEach((id, sessions) -> {
            if (id.equals(session.getId())) {
                sessionMap.remove(id);
            }
        });
    }


}
