package edu.senac.demo.controller;

import java.security.NoSuchAlgorithmException;

import edu.senac.demo.tools.EncryptionUtils;
import edu.senac.demo.tools.TimerUtils;

// Criar token no login e ficar verificando ele em toda requisição
public class Session {
    public static TimerUtils timer = new TimerUtils();
    private static String token;
    public static EncryptionUtils criptgrafia;

    public static void startSession() throws NoSuchAlgorithmException {
        criptgrafia = new EncryptionUtils();
        timer.startTimer();
    }

    public static String getToken() throws Exception {
        return token;
    }

    public static void verifyToken() throws Exception {
        if (!timer.isTimerCounting()) {
            criptgrafia = null;
            token = null;
            throw new Exception("Token invalido");
        }
    }

}
