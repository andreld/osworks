package com.andrepires.osworks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrepires.osworks.domain.model.Comentario;

public interface ComentarioRepository extends JpaRepository <Comentario, Long> {

}
