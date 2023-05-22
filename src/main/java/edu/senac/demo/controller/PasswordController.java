package edu.senac.demo.controller;

import java.text.ParseException;
import java.util.List;

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

import edu.senac.demo.model.SenhaModel;
import edu.senac.demo.model.UpdatePasswordModel;
import edu.senac.demo.service.PasswordService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/senhas")
public class PasswordController {

    private PasswordService passwordService;

    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @GetMapping("/senhasuser")
    public ResponseEntity<List<SenhaModel>> listarSenhasUsuario(@RequestHeader String idUser) {
        List<SenhaModel> senhas = passwordService.findUserPasswords(idUser);
        return ResponseEntity.status(200).body(senhas);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarSenha(@RequestBody SenhaModel senha) throws ParseException {
        return ResponseEntity.status(201).body(passwordService.insert(senha));
    }

    @DeleteMapping
    public ResponseEntity<?> deletarSenha(@RequestHeader String idSenha) {
        try {
            return ResponseEntity.status(200).body(passwordService.deletePassword(idSenha));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> AlterarSenha(@RequestHeader String idSenha, @RequestBody UpdatePasswordModel data)
            throws ParseException {
        return ResponseEntity.status(201).body(passwordService.updatePassword(idSenha, data));
    }
}
