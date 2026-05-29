package br.com.serratec.projetoapi.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message= "Preencha a nota.")
    @Min(value = 1, message = "A nota mínima é 1")
    @Max(value = 5, message = "A nota máxima é 5")
    private Integer nota;

    @Size(min = 3, max = 255, message = "O comentário deve ter entre 3 e 255 caracteres.")
    private String comentario;
    
    @NotNull(message= "Preencha a data.")
    @PastOrPresent(message = "A data não pode ser no futuro")
    private LocalDate data;
    
    @NotNull(message= "Preencha o ID da ordem de serviço.")
    @ManyToOne
    @JoinColumn(name = "id_ordem")
    private OrdemServico ordem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public OrdemServico getOrdem() {
        return ordem;
    }

    public void setOrdem(OrdemServico ordem) {
        this.ordem = ordem;
    }

    
}
