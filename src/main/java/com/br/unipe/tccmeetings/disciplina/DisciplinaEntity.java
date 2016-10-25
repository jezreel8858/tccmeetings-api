package com.br.unipe.tccmeetings.disciplina;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import com.br.unipe.tccmeetings.utils.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by jezreel on 13/10/16.
 */
@Entity
@Table(name = "tb_disciplina")
@AttributeOverride(name = "id",column = @Column(name = "pk_id"))
@Data
public class DisciplinaEntity extends BaseEntity<Long>{

    @NotNull
    @Column(name = "nome",length = 50,nullable = false,unique = true)
    private String nome;

}
