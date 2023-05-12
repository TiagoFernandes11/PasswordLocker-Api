package edu.senac.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.senac.demo.model.UserModel;
import edu.senac.demo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserModel> findById(int id) {
        Optional<UserModel> obj = userRepository.findById(id);
        return obj;
    }

    public UserModel insert(UserModel obj) {
        return userRepository.save(obj);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public UserModel update(int id, UserModel user) {
        UserModel entity = userRepository.getReferenceById(id);
        updateData(entity, user);
        return userRepository.save(entity);
    }

    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email.toUpperCase());
    }

    public boolean login(String email, String senha) {
        UserModel user = userRepository.findByEmail(email.toUpperCase());
        String senhaUser = user.getSenha();
        boolean isValido = passwordEncoder.matches(senha, senhaUser);

        return isValido;
    }

    private void updateData(UserModel entity, UserModel obj) {
        entity.setNome(obj.getNome());
        entity.setEmail(obj.getEmail());
        entity.setTelefone(obj.getTelefone());
    }

}
