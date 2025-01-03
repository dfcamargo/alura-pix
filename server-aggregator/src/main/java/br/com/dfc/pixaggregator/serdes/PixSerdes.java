package br.com.dfc.pixaggregator.serdes;

import br.com.dfc.pixaggregator.dto.PixDTO;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class PixSerdes extends Serdes.WrapperSerde<PixDTO> {

    public PixSerdes(Serializer<PixDTO> serializer, Deserializer<PixDTO> deserializer) {
        super(serializer, deserializer);
    }

    public static Serde<PixDTO> serdes() {
        JsonSerializer<PixDTO> serializer = new JsonSerializer<>();
        JsonDeserializer<PixDTO> deserializer = new JsonDeserializer<>(PixDTO.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
