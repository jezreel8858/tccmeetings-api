package com.br.unipe.tccmeetings.discente;

import com.br.unipe.tccmeetings.curso.CursoEntity;
import com.br.unipe.tccmeetings.curso.CursoRepository;
import com.br.unipe.tccmeetings.curso.CursoService;
import com.br.unipe.tccmeetings.docente.DocenteEntity;
import com.br.unipe.tccmeetings.docente.DocenteRepository;
import com.br.unipe.tccmeetings.permission.PermissionEntity;
import com.br.unipe.tccmeetings.permission.PermissionRepository;
import com.br.unipe.tccmeetings.security.CurrentUser;
import com.br.unipe.tccmeetings.utils.GenericService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jezreel on 22/10/16.
 */
@Service
public class DiscenteService {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @Autowired
    private DiscenteRepository discenteRepository;
    @Autowired
    private CurrentUser currentUser;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private CursoService cursoService;

    public DiscenteEntity insert(DiscenteEntity discente){
        discente.setPassword(this.passwordEncoder.encode(discente.getPassword()));
        if (this.LOGGER.isDebugEnabled()) {
            this.LOGGER.debug(String.format("Saving the entity [%s].", discente));
        }
        List<PermissionEntity> permissoes = permissionRepository.findPermissionByDiscente();
        CursoEntity curso = cursoService.findById(discente.getCurso().getId());
        discente.setCurso(curso);
        discente.setPermissions(permissoes);
        return this.discenteRepository.save(discente);
    }

    public List<DiscenteEntity> findAll(){
        return this.findAll();
    }


}
