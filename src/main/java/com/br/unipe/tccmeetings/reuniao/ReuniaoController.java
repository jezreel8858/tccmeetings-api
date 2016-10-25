package com.br.unipe.tccmeetings.reuniao;

import com.br.unipe.tccmeetings.discente.DiscenteEntity;
import com.br.unipe.tccmeetings.discente.DiscenteRepository;
import com.br.unipe.tccmeetings.docente.DocenteEntity;
import com.br.unipe.tccmeetings.docente.DocenteRepository;
import com.br.unipe.tccmeetings.security.CurrentUser;
import com.br.unipe.tccmeetings.user.UserEntity;
import com.br.unipe.tccmeetings.user.UserRepository;
import com.br.unipe.tccmeetings.utils.GenericService;
import com.br.unipe.tccmeetings.utils.ServicePath;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * Created by jezreel on 11/10/16.
 */
@RestController
@RequestMapping(value = ServicePath.REUNIAO_PATH)
public class ReuniaoController extends GenericService<ReuniaoEntity,Long> {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @Autowired  private  ReuniaoService reuniaoService;

    @Override
    @JsonView(ReuniaoEntity.Views.Public.class)
    public ReuniaoEntity insert(@RequestBody ReuniaoEntity reuniao) {
        return reuniaoService.insert(reuniao);
    }

    @Override
    @JsonView(ReuniaoEntity.Views.Public.class)
    public List<ReuniaoEntity> findAll() {
        return this.reuniaoService.findAll();
    }

    public List<DiscenteEntity> findAllDiscenteReunioes(){
        return this.reuniaoService.findAllDiscentesReunioes();
    }
}
