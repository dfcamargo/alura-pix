package br.com.dfc.pixproducer.controller;

import br.com.dfc.pixproducer.dto.PixDTO;
import br.com.dfc.pixproducer.dto.PixStatus;
import br.com.dfc.pixproducer.service.CreatePixService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/pix")
@RequiredArgsConstructor
public class PixController {

    private final CreatePixService createPixService;

    @GetMapping
    public String IndexPix() {
        return "Hello World";
    }

    @PostMapping
    public PixDTO PostPix(@RequestBody PixDTO pixDTO) {
        pixDTO.setIdentifier(UUID.randomUUID().toString());
        pixDTO.setTransferDate(LocalDateTime.now());
        pixDTO.setStatus(PixStatus.PROCESSING);

        return createPixService.execute(pixDTO);
    }
}
