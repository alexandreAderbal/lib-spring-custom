package br.com.lib.custom.auditoria;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(EnableAuditor.EnableAuditoriaImporter.class)
@EnableJpaAuditing
public @interface EnableAuditor {
    String schema() default "public";

    class EnableAuditoriaImporter implements ImportSelector {

        @Override
        public String[] selectImports(AnnotationMetadata classMetadata) {
            AuditorMetadata auditingMetadata = AuditorMetadata.getInstance();
            auditingMetadata.filFrom(classMetadata);
            return new String[]{AuditorConfiguracao.class.getName()};
        }

    }

    class AuditorConfiguracao {

        @Bean
        AuditorAware<String> auditorProvider() {
            return new AuditorAwareImpl();
        }
        @Bean
        AuditReader auditReader(ApplicationContext context) {
            EntityManagerFactory entityManagerFactory = context.getBean(EntityManagerFactory.class);
            return AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        }

    }
}
