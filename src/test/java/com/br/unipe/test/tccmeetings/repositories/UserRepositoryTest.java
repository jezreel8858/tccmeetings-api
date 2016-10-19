package com.br.unipe.test.tccmeetings.repositories;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.br.unipe.tccmeetings.user.UserEntity;
import com.br.unipe.tccmeetings.user.UserRepository;
import com.br.unipe.test.tccmeetings.utils.AbstractTest;

public class UserRepositoryTest extends AbstractTest {

	private static final Logger LOGGER = Logger.getLogger(UserRepositoryTest.class);

	@Autowired
	private UserRepository userRepository;

	@Test
	public void findAllTest() {
		List<UserEntity> users = this.userRepository.findAll();

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Test FindAll(): " + users);
		}
	}

	@Test
	public void find() {
		List<UserEntity> users = this.userRepository.findByEmailOrName("", "");

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Test FindAll(): " + users);
		}
	}

}
