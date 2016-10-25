package com.br.unipe.tccmeetings.curso;

import com.br.unipe.tccmeetings.utils.GenericService;
import com.br.unipe.tccmeetings.utils.ServicePath;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jezreel on 13/10/16.
 */
@RestController
@RequestMapping(path = ServicePath.CURSO_PATH)
public class CursoController extends GenericService<CursoEntity,Long> {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    @Autowired
    private CursoRepository cursoRepository;


}
