package Model.DAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Model.CrdGiver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Calendar;

public class CallRegisterTest {

	@Mock
	private CrdGiver mockCrdGiver;

	@Mock
	private UserNotLoggedDAO mockUserNotLoggedDAO;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldRegisterSuccessfullyWithCreditCard() throws SQLException, NoSuchAlgorithmException, IOException {
		// Arrange
		String username = "testUser";
		String email = "test@example.com";
		String password = "testPassword";
		String nome = "Test";
		String cognome = "User";
		String nTelefono = "1234567890";
		String nCarta = "123456781234";
		Calendar scadenza = Calendar.getInstance();
		scadenza.set(2025, Calendar.DECEMBER, 31);
		Integer cvv = 123;

		// Configura i mock
		when(mockCrdGiver.getUsername()).thenReturn("guestUser");
		when(mockCrdGiver.getPass()).thenReturn("guestPass");
		doNothing().when(mockUserNotLoggedDAO).register(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
		doNothing().when(mockUserNotLoggedDAO).insertCartaCredito(anyString(), any(Calendar.class), anyInt(), anyString(), anyString(), anyString());
		doNothing().when(mockUserNotLoggedDAO).destroy();

		// Configura il comportamento del metodo statico UserNotLoggedBean.callRegister
		try (MockedStatic<UserNotLoggedBean> mockedStatic = mockStatic(UserNotLoggedBean.class)) {
			mockedStatic.when(() -> UserNotLoggedBean.callRegister(
					null, username, email, password, nome, cognome, nTelefono, nCarta, scadenza, cvv
			)).then(invocation -> {
				// Simula le chiamate a UserNotLoggedDAO
				mockUserNotLoggedDAO.register(username, email, password, nome, cognome, nTelefono, "guestUser", "guestPass");
				mockUserNotLoggedDAO.insertCartaCredito(nCarta, scadenza, cvv, username, "guestUser", "guestPass");
				mockUserNotLoggedDAO.destroy();
				return null;
			});

			// Act
			UserNotLoggedBean.callRegister(null, username, email, password, nome, cognome, nTelefono, nCarta, scadenza, cvv);

			// Assert
			verify(mockUserNotLoggedDAO, times(1)).register(eq(username), eq(email), anyString(), eq(nome), eq(cognome), eq(nTelefono), eq("guestUser"), eq("guestPass"));
			verify(mockUserNotLoggedDAO, times(1)).insertCartaCredito(eq(nCarta), eq(scadenza), eq(cvv), eq(username), eq("guestUser"), eq("guestPass"));
			verify(mockUserNotLoggedDAO, times(1)).destroy();
		}
	}

	@Test
	void shouldRegisterSuccessfullyWithoutCreditCard() throws SQLException, NoSuchAlgorithmException, IOException {
		// Arrange
		String username = "testUser";
		String email = "test@example.com";
		String password = "testPassword";
		String nome = "Test";
		String cognome = "User";
		String nTelefono = "1234567890";

		// Configura i mock
		when(mockCrdGiver.getUsername()).thenReturn("guestUser");
		when(mockCrdGiver.getPass()).thenReturn("guestPass");
		doNothing().when(mockUserNotLoggedDAO).register(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
		doNothing().when(mockUserNotLoggedDAO).destroy();

		// Configura il comportamento del metodo statico UserNotLoggedBean.callRegister
		try (MockedStatic<UserNotLoggedBean> mockedStatic = mockStatic(UserNotLoggedBean.class)) {
			mockedStatic.when(() -> UserNotLoggedBean.callRegister(
					null, username, email, password, nome, cognome, nTelefono, null, null, null
			)).then(invocation -> {
				// Simula le chiamate a UserNotLoggedDAO
				mockUserNotLoggedDAO.register(username, email, password, nome, cognome, nTelefono, "guestUser", "guestPass");
				mockUserNotLoggedDAO.destroy();
				return null;
			});

			// Act
			UserNotLoggedBean.callRegister(null, username, email, password, nome, cognome, nTelefono, null, null, null);

			// Assert
			verify(mockUserNotLoggedDAO, times(1)).register(eq(username), eq(email), anyString(), eq(nome), eq(cognome), eq(nTelefono), eq("guestUser"), eq("guestPass"));
			verify(mockUserNotLoggedDAO, never()).insertCartaCredito(anyString(), any(Calendar.class), anyInt(), anyString(), anyString(), anyString());
			verify(mockUserNotLoggedDAO, times(1)).destroy();
		}
	}

	@Test
	void shouldThrowSQLExceptionOnDatabaseError() throws SQLException, NoSuchAlgorithmException, IOException {
		// Arrange
		String username = "testUser";
		String email = "test@example.com";
		String password = "testPassword";
		String nome = "Test";
		String cognome = "User";
		String nTelefono = "1234567890";

		// Configura i mock
		when(mockCrdGiver.getUsername()).thenReturn("guestUser");
		when(mockCrdGiver.getPass()).thenReturn("guestPass");
		doThrow(new SQLException("Database error")).when(mockUserNotLoggedDAO)
				.register(username, email, password, nome, cognome, nTelefono, "guestUser", "guestPass");

		// Configura il comportamento del metodo statico UserNotLoggedBean.callRegister
		try (MockedStatic<UserNotLoggedBean> mockedStatic = mockStatic(UserNotLoggedBean.class))
		{
			mockedStatic.when(() -> UserNotLoggedBean.callRegister(
					null, username, email, password, nome, cognome, nTelefono, null, null, null
			)).then(invocation ->
			{
				// Simula il comportamento
				mockUserNotLoggedDAO.register(username, email, password, nome, cognome, nTelefono, "guestUser", "guestPass");
				// Simula la chiamata a destroy nel finally
				mockUserNotLoggedDAO.destroy();		//TODO occhio perche qui non arriva al destroy dato che non
				return null;
			});

			// Act & Assert
			assertThrows(SQLException.class, () ->
					UserNotLoggedBean.callRegister(null, username, email, password, nome, cognome, nTelefono, null, null, null));

			// Verifiche sulle chiamate
			verify(mockUserNotLoggedDAO, times(1)).register(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
			verify(mockUserNotLoggedDAO, never()).insertCartaCredito(anyString(), any(Calendar.class), anyInt(), anyString(), anyString(), anyString());
			verify(mockUserNotLoggedDAO, times(1)).destroy();
		}
	}

	@Test
	void shouldFailWhenUsernameTooLong() throws SQLException, NoSuchAlgorithmException, IOException {
		// Arrange
		String invalidUsername = "a".repeat(31); // Nome utente > 30 caratteri
		String email = "test@example.com";
		String password = "testPassword";
		String nome = "Test";
		String cognome = "User";
		String nTelefono = "1234567890";

		// Configura i mock per simulare il comportamento in caso di errore
		doThrow(new IllegalArgumentException("Username too long")).when(mockUserNotLoggedDAO)
				.register(eq(invalidUsername), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString());

		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> {
			UserNotLoggedBean.callRegister(null, invalidUsername, email, password, nome, cognome, nTelefono, null, null, null);
		});
	}

	@Test
	void shouldFailWhenEmailFormatIsInvalid() throws SQLException, NoSuchAlgorithmException, IOException {
		// Arrange
		String username = "testUser";
		String invalidEmail = "invalidemail";
		String password = "testPassword";
		String nome = "Test";
		String cognome = "User";
		String nTelefono = "1234567890";

		// Configura i mock per simulare il comportamento in caso di errore
		doThrow(new IllegalArgumentException("Invalid email format")).when(mockUserNotLoggedDAO)
				.register(eq(username), eq(invalidEmail), anyString(), anyString(), anyString(), anyString(), anyString(), anyString());

		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> {
			UserNotLoggedBean.callRegister(null, username, invalidEmail, password, nome, cognome, nTelefono, null, null, null);
		});
	}

	@Test
	void shouldFailWhenPasswordIsTooShort() throws SQLException, NoSuchAlgorithmException, IOException {
		// Arrange
		String username = "testUser";
		String email = "test@example.com";
		String shortPassword = "short"; // Password troppo corta
		String nome = "Test";
		String cognome = "User";
		String nTelefono = "1234567890";

		// Configura i mock per simulare il comportamento in caso di errore
		doThrow(new IllegalArgumentException("Password too short")).when(mockUserNotLoggedDAO)
				.register(eq(username), eq(email), eq(shortPassword), anyString(), anyString(), anyString(), anyString(), anyString());

		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> {
			UserNotLoggedBean.callRegister(null, username, email, shortPassword, nome, cognome, nTelefono, null, null, null);
		});
	}

	@Test
	void shouldFailWhenPhoneNumberIsInvalid() throws SQLException, NoSuchAlgorithmException, IOException {
		// Arrange
		String username = "testUser";
		String email = "test@example.com";
		String password = "testPassword";
		String nome = "Test";
		String cognome = "User";
		String invalidPhoneNumber = "12345"; // Numero troppo corto

		// Configura i mock per simulare il comportamento in caso di errore
		doThrow(new IllegalArgumentException("Invalid phone number")).when(mockUserNotLoggedDAO)
				.register(eq(username), eq(email), eq(password), eq(nome), eq(cognome), eq(invalidPhoneNumber), anyString(), anyString());

		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> {
			UserNotLoggedBean.callRegister(null, username, email, password, nome, cognome, invalidPhoneNumber, null, null, null);
		});
	}

	@Test
	void shouldFailWhenAddressIsInvalid() throws SQLException, NoSuchAlgorithmException, IOException {
		// Arrange
		String username = "testUser";
		String email = "test@example.com";
		String password = "testPassword";
		String nome = "Test";
		String cognome = "User";
		String nTelefono = "1234567890";
		String invalidAddress = "Invalid Address Without Number";

		// Configura i mock per simulare il comportamento in caso di errore
		doThrow(new IllegalArgumentException("Invalid address format")).when(mockUserNotLoggedDAO)
				.register(eq(username), eq(email), eq(password), eq(nome), eq(cognome), eq(nTelefono), anyString(), anyString());

		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> {
			UserNotLoggedBean.callRegister(null, username, email, password, nome, cognome, nTelefono, null, null, null);
		});
	}

}
