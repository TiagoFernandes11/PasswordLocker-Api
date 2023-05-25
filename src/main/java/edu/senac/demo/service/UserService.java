package edu.senac.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.senac.demo.model.LoginModel;
import edu.senac.demo.model.UpdateUserModel;
import edu.senac.demo.model.UserModel;
import edu.senac.demo.repository.UserRepository;
import edu.senac.demo.tools.DateStringConverter;
import edu.senac.demo.tools.EncryptionUtils;

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
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        String key = EncryptionUtils.generateBase64EncodedKey();

        obj.setEmail(upperEmail);
        obj.setNome(upperNome);
        obj.setTelefone(obj.getTelefone());
        obj.setSenha(enconder);
        obj.setDataCriacao(dataHoraAtual);
        obj.setKey(key);

        return userRepository.save(obj);
    }

    public UserModel findByEmail(String email) {
        String emailUpper = email.toUpperCase();
        return userRepository.findByEmail(emailUpper);
    }

    public LoginModel login(String email, String senha) throws Exception {
        UserModel user = userRepository.findByEmail(email.toUpperCase());
        if (user == null)
            throw new NullPointerException("Usuario inexistente");

        LocalDateTime dataHoraAtual = LocalDateTime.now();
        String dataString = DateStringConverter.convert(dataHoraAtual);
        String token = EncryptionUtils.encryptData(user.getKey(), dataString);

        String senhaUser = user.getSenha();
        boolean isValido = passwordEncoder.matches(senha, senhaUser);

        LoginModel infoLogin = new LoginModel();

        infoLogin.setIdUser(user.getId());
        infoLogin.setToken(token);
        infoLogin.setValido(isValido);

        return infoLogin;
    }

    public UserModel update(String id, UpdateUserModel user, String token) throws Exception {
        try {
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

    public UserModel deleteByGuidId(String idUser, String token) throws Exception {
        UserModel userDeletado = userRepository.findByGuidId(idUser);
        boolean deltedUser = userRepository.deleteByGuidId(idUser);
        if (!deltedUser)
            throw new Exception("Algo deu errado a deletar usuario");

        return userDeletado;
    }
}
