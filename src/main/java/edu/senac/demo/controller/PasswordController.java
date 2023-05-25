package edu.senac.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.senac.demo.Exceptions.TokenException;
import edu.senac.demo.model.SenhaModel;
import edu.senac.demo.model.UpdatePasswordModel;
import edu.senac.demo.service.PasswordService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/senhas")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;
    private VerifySession verifySession;

    @GetMapping("/senhasuser")
    public ResponseEntity<?> listarSenhasUsuario(@RequestHeader String idUser, @RequestHeader String token)
            throws Exception {
        try {

            verifySession.verifyToken(token, idUser);
            return ResponseEntity.status(200).body(passwordService.findUserPasswords(idUser, token));

        } catch (TokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity<?> cadastrarSenha(@RequestHeader String IdUser, @RequestBody SenhaModel senha,
            @RequestHeader String token,
            @RequestHeader String idUser)
            throws Exception {
        try {

            verifySession.verifyToken(token, IdUser);
            return ResponseEntity.status(201).body(passwordService.insert(senha, token, idUser));

        } catch (TokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @GetMapping("/senha")
    public ResponseEntity<?> listarSenhasPorId(@RequestHeader String IdUser, @RequestHeader String idSenha,
            @RequestHeader String token) {
        try {

            verifySession.verifyToken(token, IdUser);
            return ResponseEntity.status(200).body(passwordService.findByGuidId(idSenha, token));

        } catch (TokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping
    public ResponseEntity<?> deletarSenha(@RequestHeader String IdUser, @RequestHeader String idSenha,
            @RequestHeader String token) {
        try {

            verifySession.verifyToken(token, IdUser);
            return ResponseEntity.status(200).body(passwordService.deletePassword(idSenha, token));

        } catch (TokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> AlterarSenha(@RequestHeader String IdUser, @RequestHeader String idSenha,
            @RequestBody UpdatePasswordModel data,
            @RequestHeader String token)
            throws Exception {
        try {

            verifySession.verifyToken(token, IdUser);
            return ResponseEntity.status(201).body(passwordService.updatePassword(idSenha, data, token));

        } catch (TokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/qntSenhas")
    public ResponseEntity<?> buscarQntSenhas(@RequestHeader String idUser, @RequestHeader String token) {
        try {
            verifySession.verifyToken(token, idUser);
            int qntSenhas = 0;
            return ResponseEntity.status(200).body(qntSenhas);
        } catch (TokenException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }
}
