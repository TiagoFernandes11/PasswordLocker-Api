package edu.senac.demo.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import edu.senac.demo.model.SenhaModel;
import edu.senac.demo.model.UpdatePasswordModel;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import edu.senac.demo.repository.PasswordRepository;
import edu.senac.demo.tools.DateAdministrator;

@Service
public class PasswordService {

    private PasswordRepository passwordRepository;
    private PasswordEncoder passwordEncoder;

    public PasswordService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<SenhaModel> findUserPasswords(String idUser) {
        List<SenhaModel> senhas = passwordRepository.findSenhasByIdUser(idUser);
        return senhas;
    }

    public SenhaModel insert(SenhaModel senha) throws ParseException {
        String titulo = senha.getTitulo().toUpperCase();
        senha.setTitulo(titulo);

        String encoder = this.passwordEncoder.encode(senha.getSenha());
        senha.setSenha(senha.getSenha());

        Date currentDate = DateAdministrator.currentDate();
        senha.setDataCriacao(currentDate);

        passwordRepository.save(senha);
        return senha;
    }

    public SenhaModel findByGuidId(String idPass) {
        return passwordRepository.findByGuidId(idPass);
    }

    public SenhaModel deleteById(String idPass) {
        SenhaModel senhaDelete = findByGuidId(idPass);
        passwordRepository.deleteById(idPass);
        return senhaDelete;
    }

    public SenhaModel updatePassword(String idSenha, UpdatePasswordModel data) throws ParseException {
        SenhaModel passwordAtt = findByGuidId(idSenha);

        Date dateNow = DateAdministrator.currentDate();

        if (!(data.getTitulo().isBlank() || data.getTitulo().isEmpty())) {
            String upperTitulo = data.getTitulo().toUpperCase();
            passwordAtt.setTitulo(upperTitulo);
            passwordAtt.setDataAlteracao(dateNow);
        }

        if (!(data.getSenha().isBlank() || data.getSenha().isEmpty())) {
            // String encoder = passwordEncoder.encode(data.getSenha());
            passwordAtt.setSenha(data.getSenha());
            passwordAtt.setDataAlteracao(dateNow);
        }

        passwordRepository.save(passwordAtt);
        return passwordAtt;
    }

    public boolean deletePassword(String idSenha) {
        boolean deletePassword = PasswordRepository.deleteByGuidId(idSenha);
        return deletePassword;
    }
}
