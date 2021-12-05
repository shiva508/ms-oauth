package com.pool.factory;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;
import com.pool.provider.StudentPoolRemoteUserStorageProvider;
import com.pool.restclient.UserApiClient;

public class StudentPoolRemoteUserStorageProviderFactory
		implements UserStorageProviderFactory<StudentPoolRemoteUserStorageProvider> {

	public static final String PROVIDER_NAME = "studentpool-remote-user-storage";

	@Override
	public StudentPoolRemoteUserStorageProvider create(KeycloakSession session, ComponentModel model) {
		return new StudentPoolRemoteUserStorageProvider(session, model, getUserApiClient("http://localhost:8099"));
	}

	@Override
	public String getId() {
		return PROVIDER_NAME;
	}

	public UserApiClient getUserApiClient(String uri) {
		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(uri);
		return target.proxyBuilder(UserApiClient.class).classloader(UserApiClient.class.getClassLoader()).build();
	}
}
