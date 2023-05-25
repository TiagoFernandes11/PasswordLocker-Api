package edu.senac.demo.controller;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.swing.plaf.nimbus.State;

import edu.senac.demo.model.UserModel;
import edu.senac.demo.tools.TimerUtils;

// Criar token no login e ficar verificando ele em toda requisição
public class Session {
    public static TimerUtils timer = new TimerUtils();
    private static String token;
    private static UserModel userLogado;
    private static final int TOKEN_LENGTH = 24; // Tamanho do token em bytes

    public static void startSession() throws NoSuchAlgorithmException {
        setToken();
        timer.startTimer();
    }

    public static String getToken() throws Exception {
        return token;
    }

    public static void setToken() {
        token = generateToken();
    }

    public static String generateToken() {
        byte[] tokenBytes = new byte[TOKEN_LENGTH];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    public static void verifyToken(String key) throws Exception {
        if (!timer.isTimerCounting()) {
            token = null;
            throw new Exception("Token Expirado");
        }
        if (!key.equals(token)) {
            throw new Exception("Token invalido");
        }
    }

}
