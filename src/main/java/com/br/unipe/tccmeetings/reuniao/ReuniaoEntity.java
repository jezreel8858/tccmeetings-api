package com.br.unipe.tccmeetings.reuniao;

import com.br.unipe.tccmeetings.discente.DiscenteEntity;
import com.br.unipe.tccmeetings.docente.DocenteEntity;
import com.br.unipe.tccmeetings.user.UserEntity;
import com.br.unipe.tccmeetings.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by jezreel on 10/10/16.
 */
@Entity
@Table(name = "tb_reuniao")
@AttributeOverride(name = "id", column = @Column(name = "pk_id"))
@Data
public class ReuniaoEntity extends BaseEntity<Long> {


    @NotNull
    @Size(max = 150)
    @Column(name = "descricao", length = 150, nullable = false)
    private String descricao;

    @NotNull
    @Column(name = "dataInicial", nullable = false)
    private Date dataInicial;

    @Null
    @Column(name = "dataFinal", nullable = true)
    private Date dataFinal;

    @Null
    @Column(name = "validado")
    private boolean validado;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_docente")
    private DocenteEntity id_docente;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_discente")
    private DiscenteEntity id_discente;

}
