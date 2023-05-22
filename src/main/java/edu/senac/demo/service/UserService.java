package edu.senac.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.senac.demo.controller.Session;
import edu.senac.demo.model.LoginModel;
import edu.senac.demo.model.UpdateUserModel;
import edu.senac.demo.model.UserModel;
import edu.senac.demo.repository.UserRepository;
import edu.senac.demo.tools.DateAdministrator;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserModel findByGuidId(String idUser) {
        UserModel obj = userRepository.findByGuidId(idUser);
        return obj;
    }

    public UserModel insert(UserModel obj) throws Exception {
        UserModel userExiste = findByEmail(obj.getEmail());
        if (userExiste != null) {
            throw new Exception("Email já cadastrado na base de dados");
        }

        String upperEmail = obj.getEmail().toUpperCase();
        String upperNome = obj.getNome().toUpperCase();
        String enconder = this.passwordEncoder.encode(obj.getSenha());
        Date date = DateAdministrator.currentDate();

        obj.setEmail(upperEmail);
        obj.setNome(upperNome);
        obj.setTelefone(obj.getTelefone());
        obj.setSenha(enconder);
        obj.setDataCriacao(date);

        return userRepository.save(obj);
    }

    public UserModel findByEmail(String email) {
        String emailUpper = email.toUpperCase();
        return userRepository.findByEmail(emailUpper);
    }

    public LoginModel login(String email, String senha) throws Exception {
        UserModel user = userRepository.findByEmail(email.toUpperCase());
        if (user == null)
            throw new Exception("Usuario inexistente");

        String senhaUser = user.getSenha();

        boolean isValido = passwordEncoder.matches(senha, senhaUser);

        LoginModel infoLogin = new LoginModel();

        infoLogin.setIdUser(user.getId());
        infoLogin.setValido(isValido);

        return infoLogin;
    }

    public UserModel update(String id, UpdateUserModel user, String token) throws Exception {
        try {
            Session.verifyToken();
            UserModel entity = userRepository.findByGuidId(id);
            updateData(entity, user);
            return userRepository.save(entity);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    private void updateData(UserModel entity, UpdateUserModel obj) {
        if (!(obj.getNome().isEmpty() || obj.getNome().isBlank()))
            entity.setNome(obj.getNome().toUpperCase());

        if (!(obj.getEmail().isEmpty() || obj.getEmail().isBlank()))
            entity.setEmail(obj.getEmail().toUpperCase());

        if (!(obj.getTelefone().isEmpty() || obj.getTelefone().isBlank()))
            entity.setTelefone(obj.getTelefone());

        if (!(obj.getSenha().isEmpty() || obj.getSenha().isBlank()))
            entity.setSenha(obj.getSenha());
    }

    public boolean deleteByGuidId(String idUser) {
        boolean deltedUser = userRepository.deleteByGuidId(idUser);
        return deltedUser;
    }
}
