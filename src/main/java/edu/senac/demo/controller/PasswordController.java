package edu.senac.demo.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import edu.senac.demo.tools.EncryptionUtils;
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
    public ResponseEntity<List<SenhaModel>> listarSenhasUsuario(@RequestHeader String idUser) throws Exception {
        List<SenhaModel> senhas = passwordService.findUserPasswords(idUser);
        String token = "1J7gujuhuUgbuvcf";

        List<SenhaModel> senhasDescript = new ArrayList<SenhaModel>();
        for (SenhaModel senha : senhas) {
            String senhaDescript = senha.getSenha();
            senhaDescript = EncryptionUtils.decryptData(senhaDescript, token);
            senha.setSenha(senhaDescript);
            senhasDescript.add(senha);
        }

        return ResponseEntity.status(200).body(senhasDescript);
    }

    @PostMapping
    public ResponseEntity<?> cadastrarSenha(@RequestBody SenhaModel senha) throws Exception {
        String token = "1J7gujuhuUgbuvcf";
        String senhaEncri = EncryptionUtils.encryptData(senha.getSenha(), token);
        senha.setSenha(senhaEncri);
        return ResponseEntity.status(201).body(passwordService.insert(senha));
    }

    @DeleteMapping
    public ResponseEntity<?> deletarSenha(@RequestHeader String idUser) {
        return ResponseEntity.status(200).body(passwordService.deleteById(idUser));
    }

    @PutMapping
    public ResponseEntity<?> AlterarSenha(@RequestHeader String idSenha, @RequestBody UpdatePasswordModel data)
            throws ParseException {
        return ResponseEntity.status(201).body(passwordService.updatePassword(idSenha, data));
    }
}
