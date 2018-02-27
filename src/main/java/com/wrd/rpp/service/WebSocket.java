package com.wrd.rpp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/*
@Component
@ServerEndpoint("/websocket")
@Slf4j
public class WebSocket {
    private Session session;
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        log.info("【websocket消息】 有新的连接， 总数：{}", webSocketSet.size());
    }
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        log.info("【websocket消息】 断开连接， 总数：{}", webSocketSet.size());
    }
    @OnMessage
    public void onMessage(String msg){
        log.info("【websocket消息】 收到客户端发来的消息：{}", msg);
    }
    public void sendMsg(String msg){
        for (WebSocket webSocket : webSocketSet){
            try {
                webSocket.session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
*/
