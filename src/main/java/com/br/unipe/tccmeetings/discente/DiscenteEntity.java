package com.br.unipe.tccmeetings.discente;

import com.br.unipe.tccmeetings.curso.CursoEntity;
import com.br.unipe.tccmeetings.docente.DocenteEntity;
import com.br.unipe.tccmeetings.permission.PermissionEntity;
import com.br.unipe.tccmeetings.reuniao.ReuniaoEntity;
import com.br.unipe.tccmeetings.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(DiscenteEntity.Views.Internal.class)
    private CursoEntity curso;

    @NotNull
    @JsonView(DocenteEntity.Views.Internal.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docente")
    private DocenteEntity docente;

    @Override
    @JsonView(DiscenteEntity.Views.Internal.class)
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    @JsonView(DiscenteEntity.Views.Internal.class)
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    @JsonView(DiscenteEntity.Views.Internal.class)
    public List<PermissionEntity> getPermissions() {
        return super.getPermissions();
    }

    @Override
    @JsonView(DiscenteEntity.Views.Public.class)
    public Long getId() {
        return super.getId();
    }
}
