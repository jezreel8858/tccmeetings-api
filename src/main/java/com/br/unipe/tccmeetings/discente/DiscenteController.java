package com.br.unipe.tccmeetings.discente;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.br.unipe.tccmeetings.permission.PermissionEntity;
import com.br.unipe.tccmeetings.permission.PermissionRepository;
import com.br.unipe.tccmeetings.utils.GenericService;
import com.br.unipe.tccmeetings.utils.ServicePath;

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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionRepository permissionRepository;

    @RequestMapping(method = RequestMethod.POST)
    public DiscenteEntity insert(@RequestBody DiscenteEntity user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        if (this.LOGGER.isDebugEnabled()) {
            this.LOGGER.debug(String.format("Saving the entity [%s].", user));
        }
        List<PermissionEntity> permissoes = permissionRepository.findPermissionByDiscente();
        user.setPermissions(permissoes);

        return this.discenteRepository.save(user);
    }

    // Get para usuarios Anonymous
    @RequestMapping(value = "/findAll",method = RequestMethod.GET, produces = "application/json")
    public List<DiscenteEntity> findAllAnonymous() {
        //        UserEntity user = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());
        List<DiscenteEntity> list = this.discenteRepository.findAll();
        for (DiscenteEntity docente : list){
            docente.setPassword(null);
            docente.setEmail(null);
//            docente.setReunioes(null);
            docente.setPermissions(null);
        }

        return list;
    }
}
