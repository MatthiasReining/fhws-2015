package de.fhws.app.presentation;

import de.fhws.app.business.logmanager.boundary.LogEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.event.Observes;
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
        sendBroadcast(message);
    }

    protected void sendBroadcast(String message) throws IOException {
        synchronized (clients) {
            for (Session s : clients) {
                s.getBasicRemote().sendText(message);
            }
        }
    }

    public void manageLogEventsAtConsole(@Observes @LogEvent String message) throws IOException {
        sendBroadcast(message);
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
