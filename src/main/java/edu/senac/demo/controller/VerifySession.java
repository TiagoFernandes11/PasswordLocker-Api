package edu.senac.demo.controller;

import java.time.LocalDateTime;
import java.time.Duration;
import edu.senac.demo.Exceptions.TokenException;
import edu.senac.demo.service.UserService;
import edu.senac.demo.tools.DateStringConverter;
import edu.senac.demo.tools.EncryptionUtils;

public class VerifySession {

    private UserService userService;

    public VerifySession(UserService userService) {
        this.userService = userService;
    }

    public void verifyToken(String token, String idUser) throws Exception {
        String chaveUser = userService.findByGuidId(idUser).getKey();
        String tokenDescripto = EncryptionUtils.encryptData(token, chaveUser);
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime dataToken = DateStringConverter.convert(tokenDescripto);

        Duration duracao = Duration.between(dataAtual, dataToken);

        boolean passou = duracao.toMinutes() > 15;
        if (passou) {
            throw new TokenException("Token expirado");
        }
    }

}
