package br.com.lib.custom.auditoria;

import br.com.lib.custom.util.UsuarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuditorAwareImpl implements AuditorAware<String> {
    @Value("${seguranca.admin.email}")
    public String adminEmail;

    @Autowired
    public Optional<String> getCurrentAuditor() {
        String logado = UsuarioUtil.usuariologado();
        return Optional.of( logado!= null ? logado : adminEmail );
    }

}
