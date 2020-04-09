package com.example.mq.MqController;

import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.Collection;
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
        //用户断开连接时移除当前用户的session
        //获取用户Map的值
        Collection<CopyOnWriteArraySet<Session>> sessionCollection = sessionMap.values();
        for (CopyOnWriteArraySet<Session> sessionCopyOnWriteArraySet : sessionCollection) {
            sessionCopyOnWriteArraySet.forEach(session1 -> {
                if (session1.equals(session)){
                    sessionMap.remove(session);
                }
            });
        }
    }
}
