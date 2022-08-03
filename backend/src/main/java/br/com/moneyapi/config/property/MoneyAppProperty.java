package br.com.moneyapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;

@ConfigurationProperties("moneyapp")
@Getter
public class MoneyAppProperty {

    private final Security security = new Security();

    private String allowedOrigin = "http://localhost:8080";
    
    public static class Security {
        
        private boolean enableHttps;
        
        public boolean isEnableHttps() { return enableHttps; }

        public void setEnableHttps(boolean enableHttps) { this.enableHttps = enableHttps; }
        
    }
}
