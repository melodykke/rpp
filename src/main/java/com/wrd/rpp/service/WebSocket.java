package com.wrd.rpp.service;

import com.wrd.rpp.shiro.bean.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket/{username}")
@Slf4j
public class WebSocket {

    @Autowired
    private UserService userService;
    private String username;
    private Session session;
    private static ConcurrentHashMap<String, WebSocket> webSocketSet = new ConcurrentHashMap<String, WebSocket>();

    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session){
        System.out.println("onOpen username:"+username);
        this.username = username;
        this.session = session;
        webSocketSet.put(username, this);
        log.info("【websocket消息】 有新的连接: {}", username);
        log.info("【websocket消息】 有新的连接， 总数：{}", webSocketSet.size());
    }
    @OnClose
    public void onClose(){
        webSocketSet.remove(this.username);
        log.info("【websocket消息】 断开连接， 总数：{}", webSocketSet.size());
    }
    @OnMessage
    public void onMessage(String msg){
        log.info("【websocket消息】 收到客户端发来的消息：{}", msg);
        String[] ht = msg.split(":");
        if("HeartBeat".equals(ht[0])) {
            try {
                webSocketSet.get(ht[1]).session.getBasicRemote().sendText("echo:"+ht[1]+":alive");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void sendMsg(String msg, UserInfo userInfo){
        UserInfo parent = userService.findParentByUsername(userInfo.getUsername());
        //遍历webSocketSet，确定上级是否在线，如果在线则提示。
        if(CollectionUtils.contains(webSocketSet.keySet().iterator(), parent.getUsername())){
            try {
                webSocketSet.get(parent.getUsername()).session.getBasicRemote().sendText(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("oops");
        }
    }
}
