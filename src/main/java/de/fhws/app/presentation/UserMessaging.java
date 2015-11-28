package de.fhws.app.presentation;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@Singleton
@ServerEndpoint("/websocket")
public class UserMessaging {

    private final static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void incomingMessage(String message, Session session) throws IOException {
        System.out.println("message: " + message);
        System.out.println("session id : " + session.getId());

        System.out.println("number of clients " + clients.size());
        synchronized (clients) {
            for (Session s : clients) {
                if (!s.getId().equals(session.getId())) {
                    System.out.println("send message to " + session.getId());
                    s.getBasicRemote().sendText(message);
                }
            }
        }
    }

    @OnOpen
    public void open(Session session) {
        System.out.println("websocket session is opened for id " + session.getId());
        System.out.println("size: " + clients.size());
        clients.add(session);
        System.out.println("size: " + clients.size());
    }

    @OnClose
    public void close(Session session) {
        System.out.println("websocket session is closed");
        clients.remove(session);
    }
}
