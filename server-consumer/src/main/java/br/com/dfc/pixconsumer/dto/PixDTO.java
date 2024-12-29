package br.com.dfc.pixconsumer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PixDTO {
    private String identifier;
    private String keyFrom;
    private String keyTo;
    private Double amount;
    private LocalDateTime transferDate;
    private PixStatus status;
}
