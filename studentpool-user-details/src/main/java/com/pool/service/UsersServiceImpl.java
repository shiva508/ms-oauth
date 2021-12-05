package com.pool.service;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.pool.modal.UserEntity;
import com.pool.repository.UsersRepository;
import com.pool.response.UserRest;

@Service
public class UsersServiceImpl implements UsersService {
	private UsersRepository usersRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.usersRepository = usersRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public UserRest getUserDetails(String userName) {
		UserRest returnValue = new UserRest();

		UserEntity userEntity = usersRepository.findByEmail(userName);
		if (userEntity == null) {
			return returnValue;
		}else {
			BeanUtils.copyProperties(userEntity, returnValue);
			returnValue.setUserName(returnValue.getEmail());
		}

		return returnValue;
	}

	@Override
	public UserRest getUserDetails(String userName, String password) {
		UserRest returnValue = null;

		UserEntity userEntity = usersRepository.findByEmail(userName);

		if (userEntity == null) {
			return returnValue;
		}

		if (bCryptPasswordEncoder.matches(password, userEntity.getEncryptedPassword())) {
			System.out.println("password matches!!!");

			returnValue = new UserRest();
			BeanUtils.copyProperties(userEntity, returnValue);
			returnValue.setUserName(returnValue.getEmail());
		}

		return returnValue;
	}

}
