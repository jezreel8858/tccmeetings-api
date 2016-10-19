package com.br.unipe.tccmeetings.reuniao;

import com.br.unipe.tccmeetings.utils.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.br.unipe.tccmeetings.utils.ServicePath;

/**
 * Created by jezreel on 11/10/16.
 */
@Controller
@RequestMapping(value = ServicePath.REUNIAO_PATH)
public class ReuniaoController extends GenericService<ReuniaoEntity,Long> {

    @Autowired
    private ReuniaoRepository reuniaoRepository;



}
