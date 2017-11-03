package java9test;

import java.time.Duration;
import java.util.stream.DoubleStream;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

@RestController
public class FluxConroller {

	@RequestMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<Number> get() {
		
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
		
        Flux<Number> numberFlux = Flux.fromStream(DoubleStream.generate(() -> Math.random()).mapToObj(number -> new Number(number)));

        return Flux.zip(interval, numberFlux).map(Tuple2::getT2);
	}
}
