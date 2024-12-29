package br.com.dfc.pixproducer.service;

import br.com.dfc.pixproducer.dto.PixDTO;
import br.com.dfc.pixproducer.model.Pix;
import br.com.dfc.pixproducer.repository.PixRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePixService {

    @Autowired
    private final PixRepository pixRepository;

    @Autowired
    private final KafkaTemplate<String, PixDTO> kafkaTemplate;

    public PixDTO execute(PixDTO pixDTO) {
        pixRepository.save(Pix.toEntity(pixDTO));
        kafkaTemplate.send("PIX_TRANSFER_VALIDATION", pixDTO.getIdentifier(), pixDTO);
        return pixDTO;
    }
}
