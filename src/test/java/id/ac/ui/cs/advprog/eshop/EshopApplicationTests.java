package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class EshopApplicationTest {

	@Test
	void contextLoads() {
		// Ensures the Spring Boot application context loads successfully
	}

	@Test
	void testMainMethod() {
		// Ensures that the main() method runs without throwing exceptions
		assertDoesNotThrow(() -> EshopApplication.main(new String[]{}));
	}
}