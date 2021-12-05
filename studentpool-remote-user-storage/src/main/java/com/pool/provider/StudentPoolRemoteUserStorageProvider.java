package com.pool.provider;

import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.UserCredentialStore;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.user.UserLookupProvider;

import com.pool.model.User;
import com.pool.model.VerifyPasswordResponse;
import com.pool.restclient.UserApiClient;

public class StudentPoolRemoteUserStorageProvider
		implements UserStorageProvider, UserLookupProvider, CredentialInputValidator {
	private KeycloakSession session;
	private ComponentModel model;
	private UserApiClient userApiClient; 

	public StudentPoolRemoteUserStorageProvider(KeycloakSession session, ComponentModel model,UserApiClient userApiClient) {
		super();
		this.session = session;
		this.model = model;
		this.userApiClient=userApiClient;
	}

	@Override
	public void close() {

	}

	@Override
	public boolean supportsCredentialType(String credentialType) {
		return PasswordCredentialModel.TYPE.equals(credentialType);
	}

	@Override
	public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
		if(!supportsCredentialType(credentialType)) {
			return false;
		}
		return !getUserCredentialStore().getStoredCredentialsByType(realm, user, credentialType).isEmpty() ;
	}

	private UserCredentialStore getUserCredentialStore() {
		return session.userCredentialManager();
	}
	
	@Override
	public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
		VerifyPasswordResponse verifyPasswordResponse=userApiClient.verifyUserPassword(user.getUsername(), credentialInput.getChallengeResponse());
		if(null !=verifyPasswordResponse) {
			return verifyPasswordResponse.getResult();
		}
		return false;
	}

	@Override
	public UserModel getUserById(String id, RealmModel realm) {
		return null;
	}

	@Override
	public UserModel getUserByUsername(String username, RealmModel realm) {
		UserModel userModel=null;
		User user=userApiClient.getUser(username);
		if(null !=user) {
			userModel=createUserModel(user,realm);
		}
		
		return null;
	}

	private UserModel createUserModel(User user, RealmModel realm) {
		return new AbstractUserAdapter(session,realm,model) {
			@Override
			public String getUsername() {
				return user.getUserName();
			}
		};
	}

	@Override
	public UserModel getUserByEmail(String email, RealmModel realm) {
		return null;
	}

}
