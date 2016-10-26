package com.br.unipe.tccmeetings.discente;

import com.br.unipe.tccmeetings.curso.CursoEntity;
import com.br.unipe.tccmeetings.curso.CursoRepository;
import com.br.unipe.tccmeetings.permission.PermissionEntity;
import com.br.unipe.tccmeetings.permission.PermissionRepository;
import com.br.unipe.tccmeetings.security.CurrentUser;
import com.br.unipe.tccmeetings.utils.GenericService;
import com.br.unipe.tccmeetings.utils.ServicePath;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jezreel on 11/10/16.
 */
@RestController
@RequestMapping(path = ServicePath.DISCENTE_PATH)
public class DiscenteController extends GenericService<DiscenteEntity,Long>{

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
    private CursoRepository cursoRepository;

    @RequestMapping(method = RequestMethod.POST)
    public DiscenteEntity insert(@RequestBody DiscenteEntity discente) {
        discente.setPassword(this.passwordEncoder.encode(discente.getPassword()));
        if (this.LOGGER.isDebugEnabled()) {
            this.LOGGER.debug(String.format("Saving the entity [%s].", discente));
        }
        List<PermissionEntity> permissoes = permissionRepository.findPermissionByDiscente();
        CursoEntity curso = cursoRepository.findById(discente.getCurso().getId());
        discente.setCurso(curso);
        discente.setPermissions(permissoes);
        return this.discenteRepository.save(discente);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @JsonView(DiscenteEntity.Views.Public.class)
    public List<DiscenteEntity> findAll() {
        return this.discenteRepository.findAll();
    }

}
