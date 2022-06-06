package com.pool.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
	public UserServiceImpl(UserRepository userRepository, PresentationUtil presentationUtil,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.presentationUtil = presentationUtil;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Transactional
	@Override
	public UserModel createUser(UserModel userModel) {
		String userId = presentationUtil.userIdGenerator(userModel);
		String encodedPassword = bCryptPasswordEncoder.encode(userModel.getPassword());
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(username);
		if (optionalUserEntity.isPresent()) {
			var data = optionalUserEntity.get();
			return new User(data.getEmail(), data.getPassword(), true, true, true, true, new ArrayList<>());
		} else {
			throw new UsernameNotFoundException(username);
		}

	}

	@Override
	public UserModel getUserByEmail(String username) {
		Optional<UserEntity> optionalUserEntity = userRepository.findByEmail(username);
		if (optionalUserEntity.isPresent()) {
			UserModel userModel = new UserModel();
			BeanUtils.copyProperties(optionalUserEntity.get(), userModel);
			return userModel;
		} else {
			throw new UsernameNotFoundException(username);
		}
	}

}
