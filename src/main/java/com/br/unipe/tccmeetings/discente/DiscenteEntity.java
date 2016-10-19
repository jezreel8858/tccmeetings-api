package com.br.unipe.tccmeetings.discente;

import com.br.unipe.tccmeetings.curso.CursoEntity;
import com.br.unipe.tccmeetings.docente.DocenteEntity;
import com.br.unipe.tccmeetings.user.UserEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_docente")
    private DocenteEntity docente;

}
