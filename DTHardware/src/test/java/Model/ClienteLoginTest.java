package Model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import Model.DAO.UserNotLoggedBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
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
        // Mock del comportamento
        when(UserNotLoggedBean.callLogin(context, "validUser", "validPassword")).thenReturn(true);

        // Test
        boolean result = cliente.authUser("validUser", "validPassword", context, session);

        // Verifiche
        assertTrue(result, "Login con username e password validi dovrebbe avere successo");
        verify(session).setAttribute("isLogged", "l");
    }

    @Test
    public void testAuthUser_AdminLogin() throws SQLException, NoSuchAlgorithmException, IOException {
        // Mock del comportamento
        when(UserNotLoggedBean.callLogin(context, "admin", "adminPassword")).thenReturn(true);

        // Test
        boolean result = cliente.authUser("admin", "adminPassword", context, session);

        // Verifiche
        assertTrue(result, "Login come admin dovrebbe avere successo");
        verify(session).setAttribute("isLogged", "a");
    }

    @Test
    public void testAuthUser_InvalidPassword() throws SQLException, NoSuchAlgorithmException, IOException {
        // Mock del comportamento
        when(UserNotLoggedBean.callLogin(context, "validUser", "wrongPassword")).thenReturn(false);

        // Test
        boolean result = cliente.authUser("validUser", "wrongPassword", context, session);

        // Verifiche
        assertFalse(result, "Login con password non valida dovrebbe fallire");
        verify(session).setAttribute("isLogged", "n");
    }

    @Test
    public void testAuthUser_EmptyPassword() throws SQLException, NoSuchAlgorithmException, IOException {
        // Test
        boolean result = cliente.authUser("validUser", "", context, session);

        // Verifiche
        assertFalse(result, "Login con password vuota dovrebbe fallire");
        verify(session).setAttribute("isLogged", "n");
    }

    @Test
    public void testAuthUser_NullUsername() throws SQLException, NoSuchAlgorithmException, IOException {
        // Test
        boolean result = cliente.authUser(null, "validPassword", context, session);

        // Verifiche
        assertFalse(result, "Login con username nullo dovrebbe fallire");
        verify(session).setAttribute("isLogged", "n");
    }

    @Test
    public void testAuthUser_UnregisteredUser() throws SQLException, NoSuchAlgorithmException, IOException {
        // Mock del comportamento
        when(UserNotLoggedBean.callLogin(context, "unregisteredUser", "validPassword")).thenReturn(false);

        // Test
        boolean result = cliente.authUser("unregisteredUser", "validPassword", context, session);

        // Verifiche
        assertFalse(result, "Login per un utente non registrato dovrebbe fallire");
        verify(session).setAttribute("isLogged", "n");
    }
}
