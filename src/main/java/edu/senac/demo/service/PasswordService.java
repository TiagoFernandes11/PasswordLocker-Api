package edu.senac.demo.service;

import java.util.List;
import edu.senac.demo.model.SenhaModel;
import org.springframework.stereotype.Service;
import edu.senac.demo.repository.PasswordRepository;

@Service
public class PasswordService {

    private PasswordRepository passwordRepository;

    public PasswordService(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    public List<SenhaModel> buscarSenharDeUsuario(int idUser) {
        return passwordRepository.findSenhasByIdUser(idUser);
    }
}
