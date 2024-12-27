package Model.DAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Model.CrdGiver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Calendar;

public class CallRegisterTest
{

	@Mock
	private ServletContext mockContext;

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
		String nCarta = "1234567812345678";
		Calendar scadenza = Calendar.getInstance();
		scadenza.set(2025, Calendar.DECEMBER, 31);
		Integer cvv = 123;

		// Simula l'aggiornamento delle credenziali senza accesso al file
		doNothing().when(mockCrdGiver).aggiornaCrd(anyInt());
		when(mockCrdGiver.getUsername()).thenReturn("crdUser");
		when(mockCrdGiver.getPass()).thenReturn("crdPass");
		doNothing().when(mockUserNotLoggedDAO).register(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
		doNothing().when(mockUserNotLoggedDAO).insertCartaCredito(anyString(), any(Calendar.class), anyInt(), anyString(), anyString(), anyString());
		doNothing().when(mockUserNotLoggedDAO).destroy();

		// Act
		UserNotLoggedBean.callRegister(mockContext, username, email, password, nome, cognome, nTelefono, nCarta, scadenza, cvv);

		// Assert
		verify(mockCrdGiver, times(1)).aggiornaCrd(2);
		verify(mockUserNotLoggedDAO, times(1)).register(eq(username), eq(email), anyString(), eq(nome), eq(cognome), eq(nTelefono), eq("crdUser"), eq("crdPass"));
		verify(mockUserNotLoggedDAO, times(1)).insertCartaCredito(eq(nCarta), eq(scadenza), eq(cvv), eq(username), eq("crdUser"), eq("crdPass"));
		verify(mockUserNotLoggedDAO, times(1)).destroy();
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

		doNothing().when(mockCrdGiver).aggiornaCrd(2);
		when(mockCrdGiver.getUsername()).thenReturn("crdUser");
		when(mockCrdGiver.getPass()).thenReturn("crdPass");
		doNothing().when(mockUserNotLoggedDAO).register(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
		doNothing().when(mockUserNotLoggedDAO).destroy();

		// Act
		UserNotLoggedBean.callRegister(mockContext, username, email, password, nome, cognome, nTelefono, null, null, null);

		// Assert
		verify(mockCrdGiver, times(1)).aggiornaCrd(2);
		verify(mockUserNotLoggedDAO, times(1)).register(eq(username), eq(email), anyString(), eq(nome), eq(cognome), eq(nTelefono), eq("crdUser"), eq("crdPass"));
		verify(mockUserNotLoggedDAO, never()).insertCartaCredito(anyString(), any(Calendar.class), anyInt(), anyString(), anyString(), anyString());
		verify(mockUserNotLoggedDAO, times(1)).destroy();
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

		doNothing().when(mockCrdGiver).aggiornaCrd(2);
		when(mockCrdGiver.getUsername()).thenReturn("crdUser");
		when(mockCrdGiver.getPass()).thenReturn("crdPass");
		doThrow(new SQLException("Database error")).when(mockUserNotLoggedDAO)
				.register(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString());

		// Act & Assert
		assertThrows(SQLException.class, () ->
				UserNotLoggedBean.callRegister(mockContext, username, email, password, nome, cognome, nTelefono, null, null, null));

		verify(mockCrdGiver, times(1)).aggiornaCrd(2);
		verify(mockUserNotLoggedDAO, times(1)).register(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
		verify(mockUserNotLoggedDAO, never()).insertCartaCredito(anyString(), any(Calendar.class), anyInt(), anyString(), anyString(), anyString());
		verify(mockUserNotLoggedDAO, times(1)).destroy();
	}
}