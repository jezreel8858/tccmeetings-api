package com.br.unipe.tccmeetings.discente;

import com.br.unipe.tccmeetings.utils.GenericService;
import com.br.unipe.tccmeetings.utils.ServicePath;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    private DiscenteService discenteService;

    @RequestMapping(method = RequestMethod.POST)
    public DiscenteEntity insert(@RequestBody DiscenteEntity discente) {
        return  this.discenteService.insert(discente);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    @JsonView(DiscenteEntity.Views.Public.class)
    public List<DiscenteEntity> findAll() {
        return this.discenteService.findAll();
    }

}
