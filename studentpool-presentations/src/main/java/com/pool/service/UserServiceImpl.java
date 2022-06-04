package com.pool.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pool.entity.UserEntity;
import com.pool.model.UserModel;
import com.pool.model.exception.NoRecardsFoundException;
import com.pool.repository.UserRepository;
import com.pool.util.EpoolConstants;
import com.pool.util.PresentationUtil;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private PresentationUtil presentationUtil;
	
	BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@Autowired
	public UserServiceImpl(
			UserRepository userRepository, 
			PresentationUtil presentationUtil,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.presentationUtil = presentationUtil;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}

	@Transactional
	@Override
	public UserModel createUser(UserModel userModel) {
		String userId = presentationUtil.userIdGenerator(userModel);
		String encodedPassword=bCryptPasswordEncoder.encode(userModel.getPassword());
		userModel.setUserId(userId);
		userModel.setPassword(encodedPassword);
		UserEntity entity = new UserEntity();
		BeanUtils.copyProperties(userModel, entity);
		userRepository.save(entity);
		return userModel;
	}

	@Override
	public UserModel getUserById(String userId) {
		Optional<UserEntity> optionalUserEntity = userRepository.findByUserId(userId);
		UserModel userModel = new UserModel();
		optionalUserEntity.ifPresentOrElse((data) -> {

			BeanUtils.copyProperties(optionalUserEntity.get(), userModel);
		}, () -> {
			throw new NoRecardsFoundException(EpoolConstants.NO_RECORDS_FOUND);
		});
		return userModel;
	}

}
