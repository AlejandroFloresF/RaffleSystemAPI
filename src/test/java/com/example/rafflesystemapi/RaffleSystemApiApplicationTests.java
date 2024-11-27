package com.example.rafflesystemapi;

import com.example.rafflesystemapi.Repository.RaffleRepository;
import com.example.rafflesystemapi.Service.RaffleService;
import com.example.rafflesystemapi.ViewModel.Raffle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RaffleSystemApiApplicationTests {

	@Mock
	private RaffleRepository raffleRepository;

	@InjectMocks
	private RaffleService raffleService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllRaffles_ReturnsListOfRaffles() {
		// Arrange
		Raffle raffle1 = new Raffle(
				1L,
				null,
				null,
				"ACTIVE",
				LocalDateTime.now().plusDays(30),
				LocalDateTime.now(),
				BigDecimal.valueOf(50.0),
				"Car",
				"First Raffle",
				"Raffle 1"
		);

		Raffle raffle2 = new Raffle(
				2L,
				null,
				null,
				"COMPLETED",
				LocalDateTime.now().minusDays(10),
				LocalDateTime.now().minusMonths(1),
				BigDecimal.valueOf(100.0),
				"Bike",
				"Second Raffle",
				"Raffle 2"
		);
		when(raffleRepository.findAll()).thenReturn(Arrays.asList(raffle1, raffle2));

		// Act
		List<Raffle> raffles = raffleService.getAllRaffles();

		// Assert
		assertEquals(2, raffles.size());
		verify(raffleRepository, times(1)).findAll();
	}

	@Test
	void testGetRaffleById_ReturnsRaffle() {
		// Arrange
		Long raffleId = 1L;
		Raffle raffle = new Raffle(
				1L,
				null,
				null,
				"ACTIVE",
				LocalDateTime.now().plusDays(30),
				LocalDateTime.now(),
				BigDecimal.valueOf(50.0),
				"Car",
				"First Raffle",
				"Raffle 1"
		);
		when(raffleRepository.findById(raffleId)).thenReturn(Optional.of(raffle));
		// Act
		Raffle foundRaffle = raffleService.getRaffleById(raffleId);
		// Assert
		assertNotNull(foundRaffle);
		assertEquals("Raffle 1", foundRaffle.getName());
		verify(raffleRepository, times(1)).findById(raffleId);
	}


}
