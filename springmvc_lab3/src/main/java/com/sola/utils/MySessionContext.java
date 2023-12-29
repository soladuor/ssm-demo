package com.sola.utils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class MySessionContext {
    private static MySessionContext instance;
    private final HashMap<String, HttpSession> sessionMap;

    private MySessionContext() {
        sessionMap = new HashMap<String, HttpSession>();
    }

    public static MySessionContext getInstance() {
        if (instance == null) {
            instance = new MySessionContext();
        }
        return instance;
    }

    // 用 synchronized 既保证共享变量的可见性和有序性，又保证原子性
    public synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String sessionID) {
        if (sessionID == null) {
            return null;
        }
        return sessionMap.get(sessionID);
    }

}

