package com.br.unipe.tccmeetings.permission;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.unipe.tccmeetings.utils.GenericService;
import com.br.unipe.tccmeetings.utils.ServicePath;

@RestController
@RequestMapping(path = ServicePath.PERMISSION_PATH)
public class PermissionController extends GenericService<PermissionEntity, Long> {

}
