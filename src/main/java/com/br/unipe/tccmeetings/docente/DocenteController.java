package com.br.unipe.tccmeetings.docente;

import com.br.unipe.tccmeetings.permission.PermissionEntity;
import com.br.unipe.tccmeetings.permission.PermissionRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.br.unipe.tccmeetings.utils.GenericService;
import com.br.unipe.tccmeetings.utils.ServicePath;

import java.util.List;

/**
 * Created by jezreel on 11/10/16.
 */
@RestController
@RequestMapping(path = ServicePath.DOCENTE_PATH)
public class DocenteController extends GenericService<DocenteEntity, Long> {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @Autowired
    private DocenteRepository docenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DocenteEntity insert(@RequestBody DocenteEntity user) {
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        if (this.LOGGER.isDebugEnabled()) {
            this.LOGGER.debug(String.format("Saving the entity [%s].", user));
        }

        List<PermissionEntity> permissoes = permissionRepository.findPermissionByDocente();
        user.setPermissions(permissoes);

        return this.docenteRepository.save(user);
    }

    // Get para usuarios Anonymous
    @RequestMapping(value = "/findAll",method = RequestMethod.GET, produces = "application/json")
    public List<DocenteEntity> findAllAnonymous() {
        //        UserEntity user = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());
        List<DocenteEntity> list = this.docenteRepository.findAll();
        for (DocenteEntity docente : list){
            docente.setPassword(" ");
            docente.setEmail(" ");
//            docente.setRemoved(false);
            docente.setReunioes(null);
            docente.setPermissions(null);
        }

        return list;
    }


}
