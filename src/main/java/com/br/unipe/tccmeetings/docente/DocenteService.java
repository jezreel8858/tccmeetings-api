package com.br.unipe.tccmeetings.docente;

import com.br.unipe.tccmeetings.discente.DiscenteEntity;
import com.br.unipe.tccmeetings.permission.PermissionEntity;
import com.br.unipe.tccmeetings.permission.PermissionRepository;
import com.br.unipe.tccmeetings.security.CurrentUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jezreel on 22/10/16.
 */
@Service
public class DocenteService {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @Autowired
    CurrentUser currentUser;
    @Autowired
    DocenteRepository docenteRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PermissionRepository permissionRepository;


    public DocenteEntity save(DocenteEntity docente) {
        docente.setPassword(this.passwordEncoder.encode(docente.getPassword()));

        if (this.LOGGER.isDebugEnabled()) {
            this.LOGGER.debug(String.format("Saving the entity [%s].", docente));
        }

        List<PermissionEntity> permissoes = permissionRepository.findPermissionByDocente();
        docente.setPermissions(permissoes);
        return this.docenteRepository.save(docente);
    }

    public List<DiscenteEntity> findDiscentesByDocente() {
        DocenteEntity docente = this.docenteRepository.findByEmail(currentUser.getActiveUser().getEmail());

        return this.docenteRepository.findAllDiscentes(docente.getId());
    }

    public List<DocenteEntity> findAll() {
        return this.docenteRepository.findAll();
    }

    public List<DiscenteEntity> findAllDiscentesByDocente(){
        DocenteEntity docente = this.docenteRepository.findByEmail(currentUser.getActiveUser().getEmail());
        return this.docenteRepository.findAllDiscentesReunioes(docente.getId());
    }

    public DocenteEntity findByEmail(String email){
        return this.docenteRepository.findByEmail(email);
    }
}
