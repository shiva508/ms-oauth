package com.pool.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties("studentpool-presentations")
@Component
@Data
@RefreshScope
public class EpoolEnvConfiguration {
	@Value("token.expiration_time")
	private String tokenExpirationTime;
	@Value("token.secret")
	private String tokenSecret;
	@Value("login.url.path")
	private String loginUrlPath;
}
