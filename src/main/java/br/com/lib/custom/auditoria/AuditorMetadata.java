package br.com.lib.custom.auditoria;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;

public class AuditorMetadata {

    private static final AuditorMetadata instance = new AuditorMetadata();

    private String schema;

    public static AuditorMetadata getInstance() {
        return instance;
    }

    public void filFrom(AnnotationMetadata classMetadata) {
        MultiValueMap<String, Object> allAnnotationAttributes = classMetadata
                .getAllAnnotationAttributes(EnableAuditor.class.getName());
        schema = (String) allAnnotationAttributes.getFirst("schema");
        if (StringUtils.isEmpty(schema)) {
            throw new IllegalArgumentException("You must provide schema for revision entity table!");
        }
    }

    public String getSchema() {
        return this.schema;
    }
}
