package br.com.dfc.pixconsumer.repository;

import br.com.dfc.pixconsumer.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyRepository extends JpaRepository<Key, Integer> {
    Key findByKey(String key);
}
