package edu.senac.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.senac.demo.model.UserModel;
import edu.senac.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@Api(value = "Usuarios")
@RequestMapping(value = "/usuarios")
public class UserController {

    @Autowired
    private UserService service;

    @ApiOperation(value = "Lista de usuarios")
    @GetMapping
    public ResponseEntity<List<UserModel>> buscarTodosUsuarios() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody UserModel user) {
        return ResponseEntity.status(200).body(service.insert(user));
    }

    @GetMapping("/login")
    public ResponseEntity<?> validarLogin(@RequestHeader String usario, @RequestHeader String senha) {
        boolean isValido = service.login(usario, senha);

        if (!isValido)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return ResponseEntity.status(204).build();
    }

}
