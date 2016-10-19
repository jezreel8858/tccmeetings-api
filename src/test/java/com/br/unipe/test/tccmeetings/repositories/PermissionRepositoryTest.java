package com.br.unipe.test.tccmeetings.repositories;

import java.util.List;

import com.br.unipe.test.tccmeetings.utils.AbstractTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.br.unipe.tccmeetings.permission.PermissionEntity;
import com.br.unipe.tccmeetings.permission.PermissionRepository;

public class PermissionRepositoryTest extends AbstractTest {

	private static final Logger LOGGER = Logger.getLogger(UserRepositoryTest.class);

	@Autowired
	private PermissionRepository permissionRepository;

	@Test
	public void findAllTest() {
		List<PermissionEntity> permissions = this.permissionRepository.findAll();

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("Test FindAll(): " + permissions);
		}
	}

}
