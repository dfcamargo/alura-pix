package br.com.dfc.pixconsumer.model;

import br.com.dfc.pixconsumer.dto.PixDTO;
import br.com.dfc.pixconsumer.dto.PixStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Pix {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String identifier;
    private String keyFrom;
    private String keyTo;
    private Double amount;
    private LocalDateTime transferDate;
    @Enumerated(EnumType.STRING)
    private PixStatus status;

    public static Pix toEntity(PixDTO pixDTO) {
        Pix pix = new Pix();
        pix.setIdentifier(pixDTO.getIdentifier());
        pix.setKeyFrom(pixDTO.getKeyFrom());
        pix.setKeyTo(pixDTO.getKeyTo());
        pix.setAmount(pixDTO.getAmount());
        pix.setTransferDate(pixDTO.getTransferDate());
        pix.setStatus(pixDTO.getStatus());
        return pix;
    }
}