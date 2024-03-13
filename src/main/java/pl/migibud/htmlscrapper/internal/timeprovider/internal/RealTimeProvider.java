package pl.migibud.htmlscrapper.internal.timeprovider.internal;

import org.springframework.stereotype.Component;
import pl.migibud.htmlscrapper.internal.timeprovider.api.TimeProvider;

import java.time.Instant;

@Component
class RealTimeProvider implements TimeProvider {

    @Override
    public Instant now() {
        return Instant.now();
    }

}
