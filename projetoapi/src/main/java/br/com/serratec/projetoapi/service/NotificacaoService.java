package br.com.serratec.projetoapi.service;

import org.springframework.stereotype.Service;

import br.com.serratec.projetoapi.config.MailConfig;

@Service
public class NotificacaoService {
    private final MailConfig mailConfig;

    public NotificacaoService(MailConfig mailConfig) {
        this.mailConfig = mailConfig;
    }
    
    public void notificar(String email, String status) {
        String assunto= "Novo Status: "+ status;
        String texto= "O status da ordem de serviço foi atualizado. Favor entrar em contato para mais detalhes.";

        mailConfig.sendEmail(email, assunto, texto);
    }

}
