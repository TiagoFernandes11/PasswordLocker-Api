package edu.senac.demo.controller;

import java.text.ParseException;
import java.util.List;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<SenhaModel>> listarSenhasUsuario(@RequestHeader String idUser) {
        List<SenhaModel> senhas = passwordService.findUserPasswords(idUser);
        return ResponseEntity.status(200).body(senhas);
    }

    // @ApiOperation(value="Cadastrar Senha")
    @PostMapping
    public ResponseEntity<?> cadastrarSenha(@RequestBody SenhaModel senha) throws ParseException {
        return ResponseEntity.status(201).body(passwordService.insert(senha));
    }

    @ApiOperation(value = "Deletar Senha")
    @DeleteMapping
    public ResponseEntity<?> deletarSenha(@RequestHeader String idUser) {
        return ResponseEntity.status(200).body(passwordService.deleteById(idUser));
    }

    @ApiOperation(value = "Alterar Senha")
    @PutMapping
    public ResponseEntity<?> AlterarSenha(@RequestHeader String idSenha, @RequestBody UpdatePasswordModel data)
            throws ParseException {
        return ResponseEntity.status(201).body(passwordService.updatePassword(idSenha, data));
    }
}
