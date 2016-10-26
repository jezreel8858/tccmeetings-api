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

/**
 * Created by jezreel on 11/10/16.
 */
@RestController
@RequestMapping(value = ServicePath.REUNIAO_PATH)
public class ReuniaoController extends GenericService<ReuniaoEntity,Long> {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiscenteRepository discenteRepository;

    @Autowired
    private DocenteRepository docenteRepository;

    @Override
    @JsonView(ReuniaoEntity.Views.Public.class)
    public ReuniaoEntity insert(@RequestBody ReuniaoEntity reuniao) {

        UserEntity user = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());

        if (user instanceof DocenteEntity) {
            reuniao.setValidado(true);
            reuniao = genericRepository.save(reuniao);
            user.getReunioes().add(reuniao);
            this.userRepository.save(user);

        } else if(user instanceof DiscenteEntity){
            reuniao.setValidado(false);
            reuniao.setDiscente(null);
            reuniao = genericRepository.save(reuniao);
            user.getReunioes().add(reuniao);
            this.discenteRepository.save((DiscenteEntity) user);
        }
        return null;
    }

    @Override
    @JsonView(ReuniaoEntity.Views.Docente.class)
    public List<ReuniaoEntity> findAll() {
        UserEntity user = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());

        if (user != null) {
            if (user instanceof DocenteEntity) {
                return user.getReunioes();
            } else if (user instanceof DiscenteEntity) {
                return user.getReunioes();
            }
        }
        return null;
    }

    @RequestMapping(path = "findMyAllDiscentes")
    @JsonView(DiscenteEntity.Views.Internal.class)
    public List<DiscenteEntity> findAllDiscenteReunioes(){
        UserEntity user = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());
        return this.docenteRepository.findAllMyDiscentes(user.getId());
    }
}
