package edu.senac.demo.controller;

import java.time.LocalDateTime;

import java.time.Duration;
import edu.senac.demo.Exceptions.TokenException;
import edu.senac.demo.tools.DateStringConverter;
import edu.senac.demo.tools.EncryptionUtils;
import edu.senac.demo.tools.TextTools;

public class VerifySession {

    public static void verifyToken(String token, String chaveUser) throws Exception {
        String descriptografia = EncryptionUtils.decryptData(token, chaveUser);
        String dataSemNull = TextTools.CheckNullField(descriptografia);
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime dataToken = DateStringConverter.convert(dataSemNull);

        Duration duracao = Duration.between(dataAtual, dataToken);

        boolean passou = duracao.toMinutes() < -15;
        if (passou) {
            throw new TokenException("Token expirado");
        }
    }

}
