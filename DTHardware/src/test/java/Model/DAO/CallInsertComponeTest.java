package Model.DAO;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Model.CrdGiver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;

public class CallInsertComponeTest {

    @Mock
    private CrdGiver mockCrdGiver;

    @Mock
    private UserDAO mockUserDAO;

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldInsertProductSuccessfully() throws SQLException, IOException {
        // Arrange
        int nprodotti = 1;
        String codiceABarre = "123456789";
        String expectedUsername = "guestUser";
        String expectedPassword = "guestPass";

        // Configura il comportamento del mock di CrdGiver
        when(mockCrdGiver.getUsername()).thenReturn(expectedUsername);
        when(mockCrdGiver.getPass()).thenReturn(expectedPassword);

        // Simula il comportamento di UserDAO e ResultSet
        when(mockUserDAO.insertCompone(nprodotti, codiceABarre, expectedUsername, expectedPassword)).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true); // Simula che il ResultSet contenga un risultato
        when(mockResultSet.getString("codiceabarreIn")).thenReturn(codiceABarre);

        // Act
        boolean result = UserBean.callInsertCompone(nprodotti, codiceABarre, null); // Passa null per il contesto, non usato nei mock

        // Assert
        assertTrue(result); // Verifica che il risultato sia true
        verify(mockUserDAO, times(1)).insertCompone(nprodotti, codiceABarre, expectedUsername, expectedPassword); // Verifica che insertCompone sia chiamato con i parametri giusti
        verify(mockResultSet, times(1)).next(); // Verifica che next() venga chiamato
        verify(mockResultSet, times(1)).getString("codiceabarreIn"); // Verifica che getString() venga chiamato con il giusto parametro
    }

    @Test
    void shouldReturnFalseOnNullPointerException() throws SQLException, IOException {
        // Arrange
        int nprodotti = 1;
        String codiceABarre = "123456789";

        // Configura il comportamento del mock di CrdGiver
        when(mockCrdGiver.getUsername()).thenReturn("guestUser");
        when(mockCrdGiver.getPass()).thenReturn("guestPass");

        // Simula che insertCompone lanci un NullPointerException
        when(mockUserDAO.insertCompone(nprodotti, codiceABarre, "guestUser", "guestPass")).thenThrow(NullPointerException.class);

        // Act
        boolean result = UserBean.callInsertCompone(nprodotti, codiceABarre, null); // Passa null per il contesto

        // Assert
        assertFalse(result); // Verifica che il risultato sia false
        verify(mockUserDAO, times(1)).insertCompone(nprodotti, codiceABarre, "guestUser", "guestPass"); // Verifica che insertCompone sia stato chiamato
    }

    @Test
    void shouldHandleSQLExceptionGracefully() throws SQLException, IOException {
        // Arrange
        int nprodotti = 1;
        String codiceABarre = "123456789";

        // Configura il comportamento del mock di CrdGiver
        when(mockCrdGiver.getUsername()).thenReturn("guestUser");
        when(mockCrdGiver.getPass()).thenReturn("guestPass");

        // Simula che insertCompone lanci una SQLException
        when(mockUserDAO.insertCompone(nprodotti, codiceABarre, "guestUser", "guestPass")).thenThrow(SQLException.class);

        // Act
        boolean result = UserBean.callInsertCompone(nprodotti, codiceABarre, null); // Passa null per il contesto

        // Assert
        assertFalse(result); // Verifica che il risultato sia false
        verify(mockUserDAO, times(1)).insertCompone(nprodotti, codiceABarre, "guestUser", "guestPass"); // Verifica che insertCompone sia stato chiamato
    }
}


/*
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import Model.CrdGiver;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.servlet.ServletContext;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CallInsertComponeTest {
    ServletContext context = Mockito.mock(ServletContext.class);
    UserDAO userDAO = Mockito.mock(UserDAO.class);
    ResultSet resultSet = Mockito.mock(ResultSet.class);

    @Test
    public void testValidInputs() throws Exception {
        // Mock del contesto per restituire il file JSON
        String mockedJson = """
        {
            "credentials": {
                "user": {
                    "username": "testuser",
                    "password": "testpass"
                }
            }
        }
    """;
        when(context.getResourceAsStream("/WEB-INF/crd.json"))
                .thenReturn(new ByteArrayInputStream(mockedJson.getBytes()));

        // Mock della connessione al database
        Connection mockConnection = Mockito.mock(Connection.class);
        PreparedStatement mockStatement = Mockito.mock(PreparedStatement.class);
        ResultSet mockResultSet = Mockito.mock(ResultSet.class);

        // Configurazione del comportamento del mock
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);

        // Mock del DAO per restituire la connessione mockata
        UserDAO userDAO = Mockito.mock(UserDAO.class);
        when(userDAO.openConnection()).thenReturn(mockConnection);
        when(userDAO.insertCompone(10, "1234567890123", "testuser", "testpass"))
                .thenReturn(mockResultSet);

        // Mock statico del DAO se necessario
        try (MockedStatic<UserDAO> mockedUserDAO = Mockito.mockStatic(UserDAO.class)) {
            mockedUserDAO.when(() -> UserDAO.openConnection()).thenReturn(mockConnection);
            mockedUserDAO.when(() -> UserDAO.insertCompone(anyInt(), anyString(), anyString(), anyString()))
                    .thenReturn(mockResultSet);

            // Esegui il metodo
            boolean result = UserBean.callInsertCompone(10, "1234567890123", context);

            // Verifica il risultato
            assertTrue(result);

            // Verifica che i metodi siano stati chiamati correttamente
            verify(mockStatement).executeQuery();
            verify(mockResultSet).next();
        }

        // Verifica che la connessione venga chiusa
        verify(mockConnection).close();
    }




    @Test
    public void testNegativeNprodotti() throws Exception {

        boolean result = UserBean.callInsertCompone(-1, "1234567890123", context);

        assertFalse(result);
    }

    @Test
    public void testZeroNprodotti() throws Exception {

        boolean result = UserBean.callInsertCompone(0, "1234567890123", context);

        assertFalse(result);
    }

    @Test
    public void testNullCodiceABarre() throws Exception {

        boolean result = UserBean.callInsertCompone(10, null, context);

        assertFalse(result);
    }

    @Test
    public void testEmptyCodiceABarre() throws Exception {

        boolean result = UserBean.callInsertCompone(10, "", context);

        assertFalse(result);
    }

    @Test
    public void testNullContext() throws Exception {
        boolean result = UserBean.callInsertCompone(10, "1234567890123", null);

        assertFalse(result);
    }

    @Test
    public void testDatabaseException() throws Exception {
        when(userDAO.insertCompone(anyInt(), anyString(), anyString(), anyString()))
                .thenThrow(new SQLException());

        boolean result = UserBean.callInsertCompone(10, "1234567890123", context);

        assertFalse(result);
    }
}*/