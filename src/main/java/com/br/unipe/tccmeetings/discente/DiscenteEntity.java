package com.br.unipe.tccmeetings.discente;

import com.br.unipe.tccmeetings.curso.CursoEntity;
import com.br.unipe.tccmeetings.docente.DocenteEntity;
import com.br.unipe.tccmeetings.reuniao.ReuniaoEntity;
import com.br.unipe.tccmeetings.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * Created by jezreel on 10/10/16.
 */
@Entity
@Table(name = "tb_discente")
@Data
public class DiscenteEntity extends UserEntity {


    @NotNull
    @OneToOne
    private CursoEntity curso;

    @Null
    @JsonIgnore
    @OneToMany(mappedBy = "id_discente")
    private List<ReuniaoEntity> reunioes;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_docente")
    private DocenteEntity docente;

}
