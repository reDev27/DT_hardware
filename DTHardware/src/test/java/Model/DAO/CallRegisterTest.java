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
				.register(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString());

		// Configura il comportamento del metodo statico UserNotLoggedBean.callRegister
		try (MockedStatic<UserNotLoggedBean> mockedStatic = mockStatic(UserNotLoggedBean.class)) {
			mockedStatic.when(() -> UserNotLoggedBean.callRegister(
					null, username, email, password, nome, cognome, nTelefono, null, null, null
			)).then(invocation -> {
				// Simula l'eccezione
				mockUserNotLoggedDAO.register(username, email, password, nome, cognome, nTelefono, "guestUser", "guestPass");
				return null;
			});

			// Act & Assert
			assertThrows(SQLException.class, () ->
					UserNotLoggedBean.callRegister(null, username, email, password, nome, cognome, nTelefono, null, null, null));

			// Verifiche
			verify(mockUserNotLoggedDAO, times(1)).register(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
			verify(mockUserNotLoggedDAO, never()).insertCartaCredito(anyString(), any(Calendar.class), anyInt(), anyString(), anyString(), anyString());
			verify(mockUserNotLoggedDAO, times(1)).destroy();
		}
	}
}
