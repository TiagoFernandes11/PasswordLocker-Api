package edu.senac.demo.controller;

import java.security.NoSuchAlgorithmException;

import edu.senac.demo.tools.EncryptionUtils;
import edu.senac.demo.tools.TimerUtils;

public class Session {
    public static TimerUtils timer = new TimerUtils();
    private static String token;

    public static void startSession() throws NoSuchAlgorithmException {
        setToken();
        timer.startTimer();
    }

    public static void setToken() throws NoSuchAlgorithmException {
        String newToken = EncryptionUtils.generateBase64EncodedKey();
        token = newToken;
    }

    public static String getToken() throws Exception {
        return token;
    }

    public static void verifyToken() throws Exception {
        if (!timer.isTimerCounting()) {
            EncryptionUtils.clearKey(token);
            token = null;
            throw new Exception("Token invalido");
        }
    }

}
