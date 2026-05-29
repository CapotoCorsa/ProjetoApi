package br.com.serratec.projetoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.projetoapi.model.Checkout;

public interface CheckoutRepository extends JpaRepository<Checkout, Long>{

}
