package Model.DAO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Model.CrdGiver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletContext;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	void setup() throws SQLException {
		MockitoAnnotations.openMocks(this);

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DTHW", "root", "M0T1f4cc10Un4pwd!")) {
			try (
					PreparedStatement deleteCartaCredito = connection.prepareStatement("DELETE FROM CARTADICREDITO WHERE USERNAME = ?");
					PreparedStatement deleteCliente = connection.prepareStatement("DELETE FROM CLIENTE WHERE USERNAME = ?")
			) {
				// Username da eliminare (dati di test)
				String testUsername = "testUser";

				// Elimina i record collegati a testUser dalla tabella CARTADICREDITO
				deleteCartaCredito.setString(1, testUsername);
				deleteCartaCredito.executeUpdate();

				// Elimina il record collegato a testUser dalla tabella CLIENTE
				deleteCliente.setString(1, testUsername);
				deleteCliente.executeUpdate();
			}
		}
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

		// Simula il contenuto del file crd.json
		String fakeJson = """
        {
          "credentials": {
            "admin": { "username": "root", "password": "M0T1f4cc10Un4pwd!" },
            "user": { "username": "user", "password": "Tav0l1n0" },
            "userNotLogged": { "username": "userNotLogged", "password": "S3di0l1n4" }
          }
        }
        """;
		InputStream fakeInputStream = new ByteArrayInputStream(fakeJson.getBytes());

		// Configura il mock di ServletContext
		when(mockContext.getResourceAsStream("/WEB-INF/crd.json")).thenReturn(fakeInputStream);

		// Configura il comportamento del metodo statico UserNotLoggedBean.callRegister
		try (MockedStatic<UserNotLoggedBean> mockedStatic = mockStatic(UserNotLoggedBean.class)) {
			mockedStatic.when(() -> UserNotLoggedBean.callRegister(
					mockContext, username, email, password, nome, cognome, nTelefono, nCarta, scadenza, cvv
			)).then(invocation -> {
				// Simula le chiamate a UserNotLoggedDAO
				mockUserNotLoggedDAO.register(username, email, password, nome, cognome, nTelefono, "guestUser", "guestPass");
				mockUserNotLoggedDAO.insertCartaCredito(nCarta, scadenza, cvv, username, "guestUser", "guestPass");
				mockUserNotLoggedDAO.destroy();
				return null;
			});

			// Act
			UserNotLoggedBean.callRegister(mockContext, username, email, password, nome, cognome, nTelefono, nCarta, scadenza, cvv);

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

		// Simula il contenuto del file crd.json
		String fakeJson = """
        {
          "credentials": {
            "admin": { "username": "root", "password": "M0T1f4cc10Un4pwd!" },
            "user": { "username": "user", "password": "Tav0l1n0" },
            "userNotLogged": { "username": "userNotLogged", "password": "S3di0l1n4" }
          }
        }
        """;
		InputStream fakeInputStream = new ByteArrayInputStream(fakeJson.getBytes());

		// Configura il mock di ServletContext
		when(mockContext.getResourceAsStream("/WEB-INF/crd.json")).thenReturn(fakeInputStream);

		// Configura il comportamento del metodo statico UserNotLoggedBean.callRegister
		try (MockedStatic<UserNotLoggedBean> mockedStatic = mockStatic(UserNotLoggedBean.class)) {
			mockedStatic.when(() -> UserNotLoggedBean.callRegister(
					mockContext, username, email, password, nome, cognome, nTelefono, null, null, null
			)).then(invocation -> {
				// Simula le chiamate a UserNotLoggedDAO
				mockUserNotLoggedDAO.register(username, email, password, nome, cognome, nTelefono, "guestUser", "guestPass");
				mockUserNotLoggedDAO.destroy();
				return null;
			});

			// Act
			UserNotLoggedBean.callRegister(mockContext, username, email, password, nome, cognome, nTelefono, null, null, null);

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

		// Simula il contenuto del file crd.json
		String fakeJson = """
        {
          "credentials": {
            "admin": { "username": "root", "password": "M0T1f4cc10Un4pwd!" },
            "user": { "username": "user", "password": "Tav0l1n0" },
            "userNotLogged": { "username": "userNotLogged", "password": "S3di0l1n4" }
          }
        }
        """;
		InputStream fakeInputStream = new ByteArrayInputStream(fakeJson.getBytes());

		// Configura il mock di ServletContext
		when(mockContext.getResourceAsStream("/WEB-INF/crd.json")).thenReturn(fakeInputStream);

		// Configura il comportamento del metodo statico UserNotLoggedBean.callRegister
		try (MockedStatic<UserNotLoggedBean> mockedStatic = mockStatic(UserNotLoggedBean.class)) {
			mockedStatic.when(() -> UserNotLoggedBean.callRegister(
					mockContext, username, email, password, nome, cognome, nTelefono, null, null, null
			)).then(invocation -> {
				// Simula il comportamento che genera SQLException
				doThrow(new SQLException("Database error")).when(mockUserNotLoggedDAO)
						.register(username, email, password, nome, cognome, nTelefono, "guestUser", "guestPass");
				return null;
			});

			// Act & Assert
			assertThrows(SQLException.class, () ->
					UserNotLoggedBean.callRegister(mockContext, username, email, password, nome, cognome, nTelefono, null, null, null));

			// Verifiche
			verify(mockUserNotLoggedDAO, times(1)).register(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyString());
			verify(mockUserNotLoggedDAO, never()).insertCartaCredito(anyString(), any(Calendar.class), anyInt(), anyString(), anyString(), anyString());
			verify(mockUserNotLoggedDAO, times(1)).destroy();
		}
	}

}