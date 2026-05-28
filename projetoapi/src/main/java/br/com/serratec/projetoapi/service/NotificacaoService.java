package br.com.serratec.projetoapi.service;

import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {
    private final EmailService service;

    public NotificacaoService(EmailService service) {
        this.service = service;
    }
    
    public void notificar(String email, String status) {
        String mensagem= "O status da ordem de serviço foi atualizado. Favor entrar em contato para mais detalhes.";

        service.enviarEmail(email, status, mensagem);
    }
}
