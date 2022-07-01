package br.com.moneyapi.events;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class EventResourceListener implements ApplicationListener<EventResource> {

    @Override
    public void onApplicationEvent(EventResource event) {
        HttpServletResponse response = event.getResponse();
        Long code = event.getCode();
        addHeaderLocation(response, code);
    }

    private void addHeaderLocation(HttpServletResponse response, Long code) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{code}")
			.buildAndExpand(code).toUri();
		response.setHeader("Location", uri.toASCIIString());
    }
    
}
