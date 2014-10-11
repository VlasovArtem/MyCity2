package org.hillel.it.mycity.service;

import org.hillel.it.mycity.model.entity.Administrator;
import org.hillel.it.mycity.model.entity.Cinema;
import org.hillel.it.mycity.model.entity.PersonGroup;
import org.hillel.it.mycity.persistence.repository.EstablishmentRepository;
import org.hillel.it.mycity.service.impl.ServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MockitServiceTest {
	private ServiceMyCity serviceImpl;
	private EstablishmentRepository establishmentRepository;
	private Administrator administrator;
	
	@Before
	public void setup() {
		establishmentRepository = Mockito.mock(EstablishmentRepository.class);
		serviceImpl = new ServiceImpl();
		serviceImpl.setEstablishmentRepository(establishmentRepository);
	}
	
	@Test
	public void testAddCinemaOK() {
		Cinema cinema = new Cinema();
		cinema.setId(1);
		administrator = Mockito.mock(Administrator.class);
		cinema.setCreatedBy(administrator);
		Mockito.when(administrator.inGroup(PersonGroup.Administrator)).thenReturn(true);
		Mockito.when(administrator.getId()).thenReturn(1);
		serviceImpl.setLoggedUser(administrator);
		Mockito.doAnswer((invocation) -> {
			Cinema cinema2 = (Cinema) invocation.getArguments()[0];
			Assert.assertTrue(cinema.equals(cinema2));
			return null;
		}).when(establishmentRepository).addCinema(cinema);
		serviceImpl.addCinema(cinema);
	}
	
}
