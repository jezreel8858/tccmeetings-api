package com.br.unipe.tccmeetings.docente;

import com.br.unipe.tccmeetings.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import com.br.unipe.tccmeetings.discente.DiscenteEntity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by jezreel on 10/10/16.
 */
@Entity
@Table(name ="tb_docente")
@Data
public class DocenteEntity extends UserEntity {

    @JsonIgnore
    @OneToMany(mappedBy = "docente",fetch = FetchType.EAGER)
    private List<DiscenteEntity> discentes;


}
