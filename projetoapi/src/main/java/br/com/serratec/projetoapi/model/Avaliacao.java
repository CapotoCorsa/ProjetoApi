package br.com.serratec.projetoapi.model;

import java.time.LocalDate;

import jakarta.persistence.*;
<<<<<<< HEAD
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
=======
import jakarta.validation.constraints.NotNull;
>>>>>>> 0e4588a1852e388c0e6e0e071657c048c318dc8d

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

<<<<<<< HEAD
    @NotNull(message = "A nota é obrigatória")
    @Min(value = 1, message = "A nota mínima é 1")
    @Max(value = 5, message = "A nota máxima é 5")
    private Integer nota;

    @NotBlank(message = "O comentário não pode estar vazio")
    @Size(min = 3, max = 255, message = "O comentário deve ter entre 3 e 255 caracteres")
    private String comentario;

    @NotNull(message = "A data é obrigatória")
    @PastOrPresent(message = "A data não pode ser no futuro")
=======
    @NotNull(message= "Preencha a nota.")
    private Integer nota;
    
    private String comentario;
    
    @NotNull(message= "Preencha a nota.")
>>>>>>> 0e4588a1852e388c0e6e0e071657c048c318dc8d
    private LocalDate data;
    
    @NotNull(message= "Preencha o ID do cliente.")
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
