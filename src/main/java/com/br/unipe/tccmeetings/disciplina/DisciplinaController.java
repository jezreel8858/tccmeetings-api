package com.br.unipe.tccmeetings.disciplina;

import com.br.unipe.tccmeetings.security.CurrentUser;
import com.br.unipe.tccmeetings.user.UserEntity;
import com.br.unipe.tccmeetings.user.UserRepository;
import com.br.unipe.tccmeetings.utils.GenericService;
import com.br.unipe.tccmeetings.utils.ServicePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jezreel on 13/10/16.
 */
@RestController
@RequestMapping(path = ServicePath.DISCIPLINA_PATH)
public class DisciplinaController extends GenericService<DisciplinaEntity,Long> {

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;


    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = "application/json")
    public DisciplinaEntity findById(@PathVariable("id") Long id) {
        UserEntity user = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());
        return this.disciplinaRepository.findByPk_id(id);
    }

    @RequestMapping(value = "/filter/{column}/{content}",method = RequestMethod.GET, produces = "application/json")
    public List<DisciplinaEntity> findByFilter(@PathVariable("column") String column, @PathVariable("content") String content) {
        UserEntity user = this.userRepository.findByEmail(currentUser.getActiveUser().getEmail());
        List<DisciplinaEntity> list = null;
            if(column.toLowerCase().equals("nome")){
            list = this.disciplinaRepository.findByNome(content.toLowerCase());
        }
        return list;
    }

}
