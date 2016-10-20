package com.br.unipe.tccmeetings.reuniao;

import com.br.unipe.tccmeetings.utils.GenericService;
import com.br.unipe.tccmeetings.utils.ServicePath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jezreel on 11/10/16.
 */
@RestController
@RequestMapping(value = ServicePath.REUNIAO_PATH)
public class ReuniaoController extends GenericService<ReuniaoEntity,Long> {


}
