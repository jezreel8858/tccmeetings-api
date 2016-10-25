package com.br.unipe.tccmeetings.docente;

import com.br.unipe.tccmeetings.discente.DiscenteEntity;
import com.br.unipe.tccmeetings.permission.PermissionEntity;
import com.br.unipe.tccmeetings.permission.PermissionRepository;
import com.fasterxml.jackson.annotation.JsonView;
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
    private DocenteService docenteService;

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public DocenteEntity insert(@RequestBody DocenteEntity user) {
        return this.docenteService.save(user);
    }

    // Get para usuarios Anonymous
    @Override
    @JsonView(DocenteEntity.Views.Public.class)
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<DocenteEntity> findAll() {

        return this.docenteService.findAll();

    }

    @JsonView(DiscenteEntity.Views.Public.class)
    @RequestMapping(path = "/discentes", method = RequestMethod.GET, produces = {"application/json"})
    private List<DiscenteEntity> findMyDiscentes() {
        return this.docenteService.findDiscentesByDocente();
    }


}
