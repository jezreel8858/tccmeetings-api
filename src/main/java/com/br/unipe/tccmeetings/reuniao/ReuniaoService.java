package com.br.unipe.tccmeetings.reuniao;

import com.br.unipe.tccmeetings.discente.DiscenteEntity;
import com.br.unipe.tccmeetings.discente.DiscenteRepository;
import com.br.unipe.tccmeetings.docente.DocenteEntity;
import com.br.unipe.tccmeetings.docente.DocenteRepository;
import com.br.unipe.tccmeetings.docente.DocenteService;
import com.br.unipe.tccmeetings.security.CurrentUser;
import com.br.unipe.tccmeetings.user.UserEntity;
import com.br.unipe.tccmeetings.user.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by jezreel on 21/10/16.
 */
@Service
public class ReuniaoService {

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReuniaoRepository reuniaoRepository;

    @Autowired
    private DiscenteRepository discenteRepository;

    @Autowired
    private DocenteService docenteService;

    public ReuniaoService() {
    }

    public List<ReuniaoEntity> findAll() {
        UserEntity user = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());

        if(user != null){
            if(user instanceof DocenteEntity){
                return reuniaoRepository.findByUserId(user.getId());
            } else if (user instanceof DiscenteEntity){
                return reuniaoRepository.findAllByUserId(user.getId());
            }
        }
        return this.reuniaoRepository.findAll();

    }

    public ReuniaoEntity insert(@JsonView(ReuniaoEntity.Views.Public.class)ReuniaoEntity reuniao) {

        DocenteEntity docente = this.docenteService.findByEmail(currentUser.getActiveUser().getEmail());

        if (docente != null) {
            reuniao.setUser(docente);
            reuniao.setValidado(true);
        } else {
            DiscenteEntity discente = this.discenteRepository.findByEmail(currentUser.getActiveUser().getEmail());
            if(discente != null){
                reuniao.setUser(discente);
                reuniao.setValidado(false);
            }

        }
        return this.reuniaoRepository.save(reuniao);
    }

    public List<DiscenteEntity> findAllDiscentesReunioes(){
        return this.docenteService.findDiscentesByDocente();
    }

}
