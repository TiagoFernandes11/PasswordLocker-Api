package edu.senac.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.senac.model.UserModel;
import edu.senac.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll() {
        List<UserModel> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

}
