package com.br.unipe.tccmeetings.discente;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jezreel on 10/10/16.
 */
public interface DiscenteRepository extends JpaRepository<DiscenteEntity,Long> {

    DiscenteEntity findByEmail(String email);
}
