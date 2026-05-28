package br.com.serratec.projetoapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {
    @Value("${USUARIO_EMAIL}")
    private String email;
    
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String para, String assunto, String texto) {
        SimpleMailMessage mensagem= new SimpleMailMessage();
        mensagem.setFrom(email);
        mensagem.setTo(para);
        mensagem.setSubject(assunto);
        mensagem.setText(texto);
        javaMailSender.send(mensagem);
    }
}
