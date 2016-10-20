package com.br.unipe.tccmeetings.docente;

import com.br.unipe.tccmeetings.reuniao.ReuniaoEntity;
import com.br.unipe.tccmeetings.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import com.br.unipe.tccmeetings.discente.DiscenteEntity;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * Created by jezreel on 10/10/16.
 */
@Entity
@Table(name ="tb_docente")
@Data
public class DocenteEntity extends UserEntity {

    @Null
    @JsonIgnore
    @OneToMany(mappedBy = "docente")
    private List<DiscenteEntity> discentes;

    @Null
    @JsonIgnore
    @OneToMany(mappedBy = "id_docente")
    private List<ReuniaoEntity> reunioes;


}
