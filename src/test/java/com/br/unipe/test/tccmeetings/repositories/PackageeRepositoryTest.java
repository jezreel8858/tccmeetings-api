//package org.wpattern.test.mutrack.simple.repositories;
//
//import org.apache.log4j.Logger;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.wpattern.mutrack.simple.packagee.PackageeEntity;
//import org.wpattern.mutrack.simple.packagee.PackageeRepository;
//import AbstractTest;
//
//import java.util.List;
//
//public class PackageeRepositoryTest extends AbstractTest {
//
//	private static final Logger LOGGER = Logger.getLogger(UserRepositoryTest.class);
//
//	@Autowired
//	private PackageeRepository packageeRepository;
//
//	@Test
//	public void findAllTest() {
//		List<PackageeEntity> packagees = this.packageeRepository.findAll();
//
//		if (LOGGER.isInfoEnabled()) {
//			LOGGER.info("Test FindAll(): " + packagees);
//		}
//	}
//
//}
