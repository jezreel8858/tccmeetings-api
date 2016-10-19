package com.br.unipe.test.tccmeetings.repositories;

import java.util.List;

import com.br.unipe.test.tccmeetings.utils.AbstractTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.br.unipe.tccmeetings.userpermission.UserPermissionEntity;
import com.br.unipe.tccmeetings.userpermission.UserPermissionRepository;

public class UserPermissionRepositoryTest extends AbstractTest {

	private static final Logger LOGGER = Logger.getLogger(UserPermissionRepositoryTest.class);

	@Autowired
	private UserPermissionRepository userPermissionRepository;

	@Test
	public void findAllTest() {
		List<UserPermissionEntity> userPermissions = this.userPermissionRepository.findAll();

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Test FindAll(): " + userPermissions);
		}
	}

}
