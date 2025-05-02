package mk.ukim.finki.emt.events;

import lombok.Getter;
import mk.ukim.finki.emt.model.domain.HostProfile;
import mk.ukim.finki.emt.model.enumerations.HostProfileChangeType;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostProfileEvent extends ApplicationEvent {

    private final HostProfile hostProfile;
    private final LocalDateTime when;
    private final HostProfileChangeType hostProfileChangeType;

    public HostProfileEvent(HostProfile hostProfile, HostProfileChangeType hostProfileChangeType) {
        super(hostProfile);
        this.hostProfile = hostProfile;
        this.hostProfileChangeType = hostProfileChangeType;
        this.when = LocalDateTime.now();
    }
}