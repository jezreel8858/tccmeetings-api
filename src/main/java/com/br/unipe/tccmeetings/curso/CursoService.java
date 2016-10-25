package com.br.unipe.tccmeetings.curso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jezreel on 24/10/16.
 */
@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<CursoEntity> findAll() {
        return this.cursoRepository.findAll();
    }

    public CursoEntity findById(Long id){ return  this.cursoRepository.findById(id);}

    public CursoEntity insert(CursoEntity curso) {
        return this.cursoRepository.save(curso);
    }

}

