package com.br.unipe.tccmeetings.reuniao;

import com.br.unipe.tccmeetings.user.UserEntity;
import com.br.unipe.tccmeetings.utils.BaseEntity;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @Size(max = 150)
    @Column(name = "descricao", length = 150)
    @JsonView(ReuniaoEntity.Views.Public.class)
    private String descricao;

    @Column(name = "dataInicial")
    @JsonView(ReuniaoEntity.Views.Public.class)
    private Date dataInicial;

    @Column(name = "dataFinal")
    @JsonView(ReuniaoEntity.Views.Public.class)
    private Date dataFinal;

    @Column(name = "validado")
    @JsonView(ReuniaoEntity.Views.Public.class)
    private boolean validado;

    @JsonView(ReuniaoEntity.Views.Public.class)
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private UserEntity user;

    public static final class Views {
        // show only public data
        public interface Public {
        }

        // show public and internal data
        public interface Internal extends Public {
        }
    }
}
