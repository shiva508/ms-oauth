package com.pool.provider;

import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;

public class StudentPoolRemoteUserStorageProvider
		implements UserStorageProvider, UserLookupProvider, CredentialInputValidator {
	private KeycloakSession session;
	private ComponentModel model;

	public StudentPoolRemoteUserStorageProvider(KeycloakSession session, ComponentModel model) {
		super();
		this.session = session;
		this.model = model;
	}

	@Override
	public void close() {

	}

	@Override
	public boolean supportsCredentialType(String credentialType) {
		return false;
	}

	@Override
	public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
		return false;
	}

	@Override
	public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
		return false;
	}

	@Override
	public UserModel getUserById(String id, RealmModel realm) {
		return null;
	}

	@Override
	public UserModel getUserByUsername(String username, RealmModel realm) {
		return null;
	}

	@Override
	public UserModel getUserByEmail(String email, RealmModel realm) {
		return null;
	}

}