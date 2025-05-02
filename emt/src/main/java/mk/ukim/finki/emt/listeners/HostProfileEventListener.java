package mk.ukim.finki.emt.listeners;

import mk.ukim.finki.emt.events.HostProfileEvent;
import mk.ukim.finki.emt.service.domain.HostProfileService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostProfileEventListener {

    private final HostProfileService hostProfileService;

    public HostProfileEventListener(HostProfileService hostProfileService) {
        this.hostProfileService = hostProfileService;
    }

    @EventListener
    public void onHostProfileChanged(HostProfileEvent event) {
        this.hostProfileService.refreshMaterializedView();
    }
}
