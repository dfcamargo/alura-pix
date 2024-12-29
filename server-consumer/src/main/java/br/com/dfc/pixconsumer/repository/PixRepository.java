package br.com.dfc.pixconsumer.repository;

import br.com.dfc.pixconsumer.model.Pix;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PixRepository extends JpaRepository<Pix, Integer> {
    Pix findByIdentifier(String identifier);
}
