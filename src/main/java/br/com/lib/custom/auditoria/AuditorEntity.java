package br.com.lib.custom.auditoria;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Audited
public class AuditorEntity implements Serializable {

    @CreatedBy
    @Column(name = "USUARIO_CRIACAO", nullable = false,length = 255,updatable = false)
    private String usuarioCriacao;

    @CreatedDate
    @Column(name = "DATA_CRIACAO",updatable = false)
    private Instant dataCriacao = Instant.now();

    @LastModifiedBy
    @Column(name = "USUARIO_ALTERACAO",length = 255)
    private String usuarioAlteracao;

    @LastModifiedDate
    @Column(name = "DATA_ALTERACAO",updatable = false)
    private Instant dataAlteracao = Instant.now();

}
