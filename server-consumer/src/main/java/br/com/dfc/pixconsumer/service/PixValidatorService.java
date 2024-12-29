package br.com.dfc.pixconsumer.service;

import br.com.dfc.pixconsumer.dto.PixDTO;
import br.com.dfc.pixconsumer.dto.PixStatus;
import br.com.dfc.pixconsumer.exception.KeyNotFounException;
import br.com.dfc.pixconsumer.model.Key;
import br.com.dfc.pixconsumer.model.Pix;
import br.com.dfc.pixconsumer.repository.KeyRepository;
import br.com.dfc.pixconsumer.repository.PixRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class PixValidatorService {

    @Autowired
    private KeyRepository  keyRepository;

    @Autowired
    private PixRepository pixRepository;



    @KafkaListener(topics = "PIX_TRANSFER_VALIDATION", groupId = "pix-validator-service")
    @RetryableTopic(
            backoff = @Backoff(value = 15000L),
            attempts = "3",
            autoCreateTopics = "true",
            include = KeyNotFounException.class
    )
    public void execute(PixDTO pixDTO) {
        System.out.printf("Pix received: %s", pixDTO.getIdentifier());

        Pix pix = pixRepository.findByIdentifier(pixDTO.getIdentifier());
        Key keyFrom = keyRepository.findByKey(pixDTO.getKeyFrom());
        Key keyTo = keyRepository.findByKey(pixDTO.getKeyTo());

        if (keyFrom == null || keyTo == null) {
            pix.setStatus(PixStatus.ERROR);
            throw new KeyNotFounException();
        }
        else
            pix.setStatus(PixStatus.PROCESSED);

        pixRepository.save(pix);
    }
}
