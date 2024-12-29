package br.com.dfc.pixproducer.repository;

import br.com.dfc.pixproducer.model.Pix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PixRepository extends JpaRepository<Pix, Integer> {
}
