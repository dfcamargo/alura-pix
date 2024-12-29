package br.com.dfc.pixaggregator.service;

import br.com.dfc.pixaggregator.serdes.PixSerdes;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PixAggregatorService {

    @Autowired
    public void execute(StreamsBuilder streamsBuilder) {

//        KStream<String, PixDTO> messageStream = streamsBuilder
//                .stream("PIX_TRANSFER_VALIDATION", Consumed.with(Serdes.String(), PixSerdes.serdes()))
//                .peek((key, value) -> System.out.printf("Pix received: %s", value.getIdentifier()))
//                .filter((key, value) -> value.getAmount() > 1000);

//        messageStream.print(Printed.toSysOut());
//        messageStream.to("PIX_TRANSFER_VALIDATION_FRAUD", Produced.with(Serdes.String(), PixSerdes.serdes()));

        KTable<String, Double> messageStream = streamsBuilder
                .stream("PIX_TRANSFER_VALIDATION", Consumed.with(Serdes.String(), PixSerdes.serdes()))
                .peek((key, value) -> System.out.printf("Pix received: %s", value.getIdentifier()))
                .groupBy((key, value) -> value.getKeyFrom())
                .aggregate(
                        () -> 0.0,
                        (key, value, aggregate) -> (aggregate + value.getAmount()),
                        Materialized.with(Serdes.String(), Serdes.Double())
                );

        messageStream.toStream().print(Printed.toSysOut());
        messageStream.toStream().to("PIX_TRANSFER_VALIDATION_AGGREGATION", Produced.with(Serdes.String(), Serdes.Double()));
    }
}
