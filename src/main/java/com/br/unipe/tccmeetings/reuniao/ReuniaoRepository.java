package com.br.unipe.tccmeetings.reuniao;

import com.br.unipe.tccmeetings.docente.DocenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by jezreel on 10/10/16.
 */
public interface ReuniaoRepository extends JpaRepository<ReuniaoEntity,Long> {



//    List<ReuniaoEntity> find


}
