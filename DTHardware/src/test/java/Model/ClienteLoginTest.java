package Model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import Model.DAO.UserNotLoggedBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class ClienteLoginTest {

    private Cliente cliente;
    private ServletContext context;
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente();
        context = mock(ServletContext.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void testAuthUser_ValidUserAndPassword() throws SQLException, NoSuchAlgorithmException, IOException {
        // Configura il mock di getResourceAsStream
        // (perche dipende da un path che dovrei avere nel contesto)
        // essendo il context mockato non troverà il path e quindi restituiva null
        // operazioni su null non possono essere fatte

        when(context.getResourceAsStream(anyString()))
                .thenReturn(new ByteArrayInputStream("mocked content".getBytes()));

        // Configura il comportamento di callLogin
        //essendo metodi statici non potrebbero essere mockati ma con mokced static
        //andremo a mockare il comportamento solo per al durata del test
        //la lambda expression viene utilizzata per dire che il metodo restituisce true quando i parametri
        // sono passati nel giusto ordine

        try (MockedStatic<UserNotLoggedBean> mockedStatic = mockStatic(UserNotLoggedBean.class)) {
            mockedStatic.when(() -> UserNotLoggedBean.callLogin(context, "validUser", "validPassword"))
                    .thenReturn(true);

            boolean result = cliente.authUser("validUser", "validPassword", context, session);

            assertTrue(result, "Login con username e password validi dovrebbe avere successo");
            verify(session).setAttribute("isLogged", "l");
        }
    }

    @Test
    public void testAuthUser_InvalidPassword() throws SQLException, NoSuchAlgorithmException, IOException {

        // Mock di getResourceAsStream
        when(context.getResourceAsStream(anyString())).thenReturn(new ByteArrayInputStream("mocked content".getBytes()));

        // Mock del comportamento di callLogin
        try (MockedStatic<UserNotLoggedBean> mockedStatic = mockStatic(UserNotLoggedBean.class)) {
            mockedStatic.when(() -> UserNotLoggedBean.callLogin(context, "validUser", "invalidPassword"))
                    .thenReturn(false);

            // Esegui il test sulla login
            boolean result = cliente.authUser("validUser", "invalidPassword", context, session);

            // Verifica i risultati
            assertFalse(result, "Login con username valida e password invalida dovrebbe fallire");
            verify(session).setAttribute("isLogged", "n");
        }
    }

    @Test
    public void testAuthUser_EmptyPassword() throws SQLException, NoSuchAlgorithmException, IOException {
        // Mock di getResourceAsStream
        when(context.getResourceAsStream(anyString())).thenReturn(new ByteArrayInputStream("mocked content".getBytes()));

        // Mock del comportamento di callLogin
        try (MockedStatic<UserNotLoggedBean> mockedStatic = mockStatic(UserNotLoggedBean.class)) {
            mockedStatic.when(() -> UserNotLoggedBean.callLogin(context, "validUser", ""))
                    .thenReturn(false);

            // Esegui il test sulla login
            boolean result = cliente.authUser("validUser", "", context, session);

            // Verifica i risultati
            assertFalse(result, "Login con username valida e password vuota dovrebbe fallire");
            verify(session).setAttribute("isLogged", "n");
        }
    }

    @Test
    public void testAuthUser_InvalidUsername() throws SQLException, NoSuchAlgorithmException, IOException {
        // Mock di getResourceAsStream
        when(context.getResourceAsStream(anyString())).thenReturn(new ByteArrayInputStream("mocked content".getBytes()));

        // Mock del comportamento di callLogin
        try (MockedStatic<UserNotLoggedBean> mockedStatic = mockStatic(UserNotLoggedBean.class)) {
            mockedStatic.when(() -> UserNotLoggedBean.callLogin(context, "invalidUser", "validPassword"))
                    .thenReturn(false);

            // Esegui il test sulla login
            boolean result = cliente.authUser("invalidUser", "validPassword", context, session);

            // Verifica i risultati
            assertFalse(result, "Login con username non valida dovrebbe fallire");
            verify(session).setAttribute("isLogged", "n");
        }
    }
    @Test
    public void testAuthUser_EmptyUsername() throws SQLException, NoSuchAlgorithmException, IOException {
        // Mock di getResourceAsStream
        when(context.getResourceAsStream(anyString())).thenReturn(new ByteArrayInputStream("mocked content".getBytes()));

        // Mock del comportamento di callLogin
        try (MockedStatic<UserNotLoggedBean> mockedStatic = mockStatic(UserNotLoggedBean.class)) {
            mockedStatic.when(() -> UserNotLoggedBean.callLogin(context, "", "validPassword"))
                    .thenReturn(false);

            // Esegui il test sulla login
            boolean result = cliente.authUser("", "validPassword", context, session);

            // Verifica i risultati
            assertFalse(result, "Login con username valida e password vuota dovrebbe fallire");
            verify(session).setAttribute("isLogged", "n");
        }
    }


    @Test
    public void testAuthUser_UnregisteredUser() throws SQLException, NoSuchAlgorithmException, IOException {
        // Mock di getResourceAsStream
        when(context.getResourceAsStream(anyString())).thenReturn(new ByteArrayInputStream("mocked content".getBytes()));

        // Mock del comportamento di callLogin
        try (MockedStatic<UserNotLoggedBean> mockedStatic = mockStatic(UserNotLoggedBean.class)) {
            mockedStatic.when(() -> UserNotLoggedBean.callLogin(context, "unregisteredUser", "validPassword"))
                    .thenReturn(false);

            // Esegui il test sulla login
            boolean result = cliente.authUser("unregisteredUser", "validPassword", context, session);

            // Verifica i risultati
            assertFalse(result, "Login con username non registrato dovrebbe fallire");
            verify(session).setAttribute("isLogged", "n");
        }
    }
}
