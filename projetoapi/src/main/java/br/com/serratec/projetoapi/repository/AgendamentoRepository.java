package br.com.serratec.projetoapi.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.projetoapi.model.Agendamento;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long>{
    Boolean existsByDataAndHora(LocalDate data, LocalTime hora);
    List<Agendamento> findByData(LocalDate data);
}
