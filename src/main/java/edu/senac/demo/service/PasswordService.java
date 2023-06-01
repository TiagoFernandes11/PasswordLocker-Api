package edu.senac.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.senac.demo.model.PasswordModel;
import edu.senac.demo.model.UpdatePasswordModel;
import edu.senac.demo.model.UserModel;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import edu.senac.demo.repository.PasswordRepository;
import edu.senac.demo.repository.UserRepository;
import edu.senac.demo.tools.EncryptionUtils;
import edu.senac.demo.tools.TextTools;

@Service
public class PasswordService {

    private PasswordRepository passwordRepository;
    private UserRepository userRepository;

    public PasswordService(PasswordRepository passwordRepository, UserRepository userRepository) {
        this.passwordRepository = passwordRepository;
        this.userRepository = userRepository;
        new BCryptPasswordEncoder();
    }

    public ArrayList<PasswordModel> findUserPasswords(String idUser, UserModel user) throws Exception {
        try {
            List<PasswordModel> senhas = passwordRepository.findSenhasByIdUser(idUser);

            ArrayList<PasswordModel> senhasDescript = new ArrayList<PasswordModel>();
            for (PasswordModel senha : senhas) {
                String senhaDescript = senha.getSenha();
                senhaDescript = EncryptionUtils.decryptData(senhaDescript, user.getKey().toString());
                String semespacoNulo = TextTools.CheckNullField(senhaDescript);
                senha.setSenha(semespacoNulo);
                senhasDescript.add(senha);
            }

            return senhasDescript;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public PasswordModel insert(PasswordModel senha, String token, String idUser) throws Exception {
        try {

            UserModel user = userRepository.findByGuidId(idUser);
            String titulo = senha.getTitulo().toUpperCase();
            senha.setTitulo(titulo);

            if (senha.getUserSite() != null) {
                String upperUserSite = senha.getUserSite().toUpperCase();
                senha.setUserSite(upperUserSite);
            }

            String senhaEncri = EncryptionUtils.encryptData(senha.getSenha(), user.getKey().toString());
            senha.setSenha(senhaEncri);

            LocalDateTime dataHoraAtual = LocalDateTime.now();
            senha.setDataCriacao(dataHoraAtual);
            senha.setFk_idUser(idUser);

            passwordRepository.save(senha);
            return senha;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public PasswordModel findByGuidId(String idPass, String token) throws Exception {
        return passwordRepository.findByGuidId(idPass);
    }

    public PasswordModel findByGuidId(UserModel user, String idPass, String token) throws Exception {

        PasswordModel senha = passwordRepository.findByGuidId(idPass);
        String senhaDescript = senha.getSenha();
        senhaDescript = EncryptionUtils.decryptData(senhaDescript, user.getKey().toString());

        senha.setSenha(TextTools.CheckNullField(senhaDescript));

        return senha;
    }

    public PasswordModel deleteById(String idPass, String token) throws Exception {
        PasswordModel senhaDelete = findByGuidId(idPass, token);
        passwordRepository.deleteById(idPass);
        return senhaDelete;
    }

    public PasswordModel updatePassword(String idSenha, UpdatePasswordModel data, String token) throws Exception {
        try {
            PasswordModel passwordAtt = findByGuidId(idSenha, token);

            LocalDateTime dataHoraAtual = LocalDateTime.now();

            if (!(data.getTitulo() == null || data.getTitulo().isBlank() || data.getTitulo().isEmpty())) {
                String upperTitulo = data.getTitulo().toUpperCase();
                passwordAtt.setTitulo(upperTitulo);
                passwordAtt.setDataAlteracao(dataHoraAtual);
            }

            if (!(data.getUserSite() == null || data.getUserSite().isBlank() || data.getUserSite().isEmpty())) {
                String upperUserSite = data.getUserSite().toUpperCase();
                passwordAtt.setUserSite(upperUserSite);
                passwordAtt.setDataAlteracao(dataHoraAtual);
            }

            if (!(data.getSenha() == null || data.getSenha().isBlank() || data.getSenha().isEmpty())) {
                String senhaEncri = EncryptionUtils.encryptData(data.getSenha(), token);
                passwordAtt.setSenha(senhaEncri);
                passwordAtt.setDataAlteracao(dataHoraAtual);
            }

            passwordRepository.save(passwordAtt);
            return passwordAtt;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public PasswordModel deletePassword(String id, String token) throws Exception {

        PasswordModel senhaDeletada = passwordRepository.findByGuidId(id);
        if (senhaDeletada == null)
            throw new NullPointerException("Senha inexistente");

        try {
            passwordRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Algo deu errado ao deletar senha: " + e.getMessage());
        }

        return senhaDeletada;
    }

}
