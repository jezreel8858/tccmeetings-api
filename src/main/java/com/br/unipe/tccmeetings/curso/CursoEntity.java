package com.br.unipe.tccmeetings.curso;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import com.br.unipe.tccmeetings.discente.DiscenteEntity;
import com.br.unipe.tccmeetings.disciplina.DisciplinaEntity;
import com.br.unipe.tccmeetings.utils.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by jezreel on 13/10/16.
 */
@Entity
@Table(name = "tb_curso")
@AttributeOverride(name = "id",column = @Column(name = "pk_id"))
@Data
public class CursoEntity extends BaseEntity<Long>{

    @NotNull
    @Size(max = 50)
    @NotEmpty
    @Column(name = "nome",length = 50,nullable = false,unique = true)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "curso",fetch = FetchType.LAZY)
    private List<DiscenteEntity> discentes;

    @NotNull
    @ManyToMany( cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinTable(name = "td_curso_disciplina",joinColumns = @JoinColumn(name = "id_curso"),inverseJoinColumns = @JoinColumn(name = "id_disciplina"))
    private List<DisciplinaEntity> disciplinas;

}
