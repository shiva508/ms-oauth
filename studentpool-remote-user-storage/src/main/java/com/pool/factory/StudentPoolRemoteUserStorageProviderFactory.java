package com.pool.factory;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

import com.pool.provider.StudentPoolRemoteUserStorageProvider;

public class StudentPoolRemoteUserStorageProviderFactory implements UserStorageProviderFactory<StudentPoolRemoteUserStorageProvider>{

	public static final String PROVIDER_NAME="studentpool-remote-user-storage";
	@Override
	public StudentPoolRemoteUserStorageProvider create(KeycloakSession session, ComponentModel model) {
		return new StudentPoolRemoteUserStorageProvider(session,model);
	}

	@Override
	public String getId() {
		return PROVIDER_NAME;
	}

}
