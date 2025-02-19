package com.bing.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("login.aad")
public class AADLoginProperties {
    private static final String SLASH = "/";
    private String filingClientId;
    private String filingAuthority;
    private String filingSecretKey;
    private String filingRedirectUriSignin;
    private String filingSsoLoginUri;
    private String legalDBClientId;
    private String legalDBAuthority;
    private String legalDBSecretKey;
    private String legalDBRedirectUriSignin;
    private String legalDBSsoLoginUri;

    public String getFilingAuthority() {
        if (!filingAuthority.endsWith(SLASH)) {
            filingAuthority += SLASH;
        }
        return filingAuthority;
    }

    public String getFilingClientId() {
        return filingClientId;
    }

    public void setFilingClientId(String filingClientId) {
        this.filingClientId = filingClientId;
    }

    public void setFilingAuthority(String filingAuthority) {
        this.filingAuthority = filingAuthority;
    }

    public String getFilingSecretKey() {
        return filingSecretKey;
    }

    public void setFilingSecretKey(String filingSecretKey) {
        this.filingSecretKey = filingSecretKey;
    }

    public String getFilingRedirectUriSignin() {
        return filingRedirectUriSignin;
    }

    public void setFilingRedirectUriSignin(String filingRedirectUriSignin) {
        this.filingRedirectUriSignin = filingRedirectUriSignin;
    }

    public String getFilingSsoLoginUri() {
        return filingSsoLoginUri;
    }

    public void setFilingSsoLoginUri(String filingSsoLoginUri) {
        this.filingSsoLoginUri = filingSsoLoginUri;
    }

    public String getLegalDBClientId() {
        return legalDBClientId;
    }

    public void setLegalDBClientId(String legalDBClientId) {
        this.legalDBClientId = legalDBClientId;
    }

    public String getLegalDBAuthority() {
        if (!legalDBAuthority.endsWith(SLASH)) {
            legalDBAuthority += SLASH;
        }
        return legalDBAuthority;
    }

    public void setLegalDBAuthority(String legalDBAuthority) {
        this.legalDBAuthority = legalDBAuthority;
    }

    public String getLegalDBSecretKey() {
        return legalDBSecretKey;
    }

    public void setLegalDBSecretKey(String legalDBSecretKey) {
        this.legalDBSecretKey = legalDBSecretKey;
    }

    public String getLegalDBRedirectUriSignin() {
        return legalDBRedirectUriSignin;
    }

    public void setLegalDBRedirectUriSignin(String legalDBRedirectUriSignin) {
        this.legalDBRedirectUriSignin = legalDBRedirectUriSignin;
    }

    public String getLegalDBSsoLoginUri() {
        return legalDBSsoLoginUri;
    }

    public void setLegalDBSsoLoginUri(String legalDBSsoLoginUri) {
        this.legalDBSsoLoginUri = legalDBSsoLoginUri;
    }
}
