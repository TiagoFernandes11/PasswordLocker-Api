package edu.senac.demo.controller;

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
<<<<<<< HEAD
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
=======
>>>>>>> main
import edu.senac.demo.model.SenhaModel;
import edu.senac.demo.model.UpdatePasswordModel;
import edu.senac.demo.service.PasswordService;

@RestController
@CrossOrigin(origins = "*")
@Api(value = "API REST Senha")
@RequestMapping(value = "/senhas")
public class PasswordController {

    @Autowired
    private PasswordService passwordService;

    public PasswordController(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    // @ApiOperation(value="Retorna uma lista de Senha")
    @GetMapping("/senhasuser")
    public ResponseEntity<?> listarSenhasUsuario(@RequestHeader String idUser, @RequestHeader String token)
            throws Exception {
        try {
            return ResponseEntity.status(200).body(passwordService.findUserPasswords(idUser, token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }

    // @ApiOperation(value="Cadastrar Senha")
    @PostMapping
    public ResponseEntity<?> cadastrarSenha(@RequestBody SenhaModel senha, @RequestHeader String token)
            throws Exception {
        try {
            return ResponseEntity.status(201).body(passwordService.insert(senha, token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

    }

    @ApiOperation(value = "Deletar Senha")
    @DeleteMapping
    public ResponseEntity<?> deletarSenha(@RequestHeader String idSenha) {
        try {
            return ResponseEntity.status(200).body(passwordService.deletePassword(idSenha));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @ApiOperation(value = "Alterar Senha")
    @PutMapping
    public ResponseEntity<?> AlterarSenha(@RequestHeader String idSenha, @RequestBody UpdatePasswordModel data,
            @RequestHeader String token)
            throws Exception {
        try {
            return ResponseEntity.status(201).body(passwordService.updatePassword(idSenha, data, token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
