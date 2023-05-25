package edu.senac.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.senac.demo.model.SenhaModel;
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

    public ArrayList<SenhaModel> findUserPasswords(String idUser, String token) throws Exception {
        try {
            List<SenhaModel> senhas = passwordRepository.findSenhasByIdUser(idUser);

            ArrayList<SenhaModel> senhasDescript = new ArrayList<SenhaModel>();
            for (SenhaModel senha : senhas) {
                String senhaDescript = senha.getSenha();
                senhaDescript = EncryptionUtils.decryptData(senhaDescript, token);
                String semespacoNulo = TextTools.CheckNullField(senhaDescript);
                senha.setSenha(semespacoNulo);
                senhasDescript.add(senha);
            }

            return senhasDescript;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public SenhaModel insert(SenhaModel senha, String token, String idUser) throws Exception {
        try {

            UserModel user = userRepository.findByGuidId(idUser);
            String titulo = senha.getTitulo().toUpperCase();
            senha.setTitulo(titulo);

            String upperUserSite = senha.getUserSite().toUpperCase();
            senha.setUserSite(upperUserSite);

            String senhaEncri = EncryptionUtils.encryptData(senha.getSenha(), user.getKey());
            senha.setSenha(senhaEncri);

            LocalDateTime dataHoraAtual = LocalDateTime.now();
            senha.setDataCriacao(dataHoraAtual);

            passwordRepository.save(senha);
            return senha;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public SenhaModel findByGuidId(String idPass, String token) throws Exception {
        return passwordRepository.findByGuidId(idPass);
    }

    public SenhaModel deleteById(String idPass, String token) throws Exception {
        SenhaModel senhaDelete = findByGuidId(idPass, token);
        passwordRepository.deleteById(idPass);
        return senhaDelete;
    }

    public SenhaModel updatePassword(String idSenha, UpdatePasswordModel data, String token) throws Exception {
        try {
            SenhaModel passwordAtt = findByGuidId(idSenha, token);

            LocalDateTime dataHoraAtual = LocalDateTime.now();

            if (!(data.getTitulo().isBlank() || data.getTitulo().isEmpty())) {
                String upperTitulo = data.getTitulo().toUpperCase();
                passwordAtt.setTitulo(upperTitulo);
                passwordAtt.setDataAlteracao(dataHoraAtual);
            }

            if (!(data.getUserSite().isBlank() || data.getUserSite().isEmpty())) {
                String upperUserSite = data.getUserSite().toUpperCase();
                passwordAtt.setUserSite(upperUserSite);
                passwordAtt.setDataAlteracao(dataHoraAtual);
            }

            if (!(data.getSenha().isBlank() || data.getSenha().isEmpty())) {
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

    public SenhaModel deletePassword(String id, String token) throws Exception {
        SenhaModel senhaDeletado = passwordRepository.findByGuidId(id);
        boolean deletado = passwordRepository.deleteByGuidId(id);

        if (!deletado)
            throw new Exception("Algo deu errado ao deletar senha");

        return senhaDeletado;
    }

}
