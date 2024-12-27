package Model.DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AdminDAOTest {

    private AdminDAO Prodotto;
    private Connection mockConnection;
    private CallableStatement mockCallableStatement;


    @BeforeEach
    public void setUp() throws SQLException {
        // Mock delle dipendenze
        mockConnection = mock(Connection.class);
        mockCallableStatement = mock(CallableStatement.class);

        // Configurazione del comportamento dei mock
        when(mockConnection.prepareCall(anyString())).thenReturn(mockCallableStatement);

        // Classe da testare (usa il mock invece di una vera connessione)
        Prodotto = new AdminDAO() {
            @Override
            protected void openConnection(String userType, String passData) {
                this.user = mockConnection; // Usa la connessione mock
            }
        };
    }

    @Test
    public void testCodiceABarreErrato() throws SQLException {
        // TEST: codice a barre vuoto o errato
        String codiceaBarre = ""; // Formato non valido
        double prezzo = 199.99;
        String descrizione = "Il migliore per qualità/prezzo";
        String specifiche = "Mobile Intel 4 Series Express Chipset Family";
        InputStream image = mock(InputStream.class);
        int quantita = 10;
        String marca = "HP";
        String modello = "EliteBook";
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Computer";
        Calendar dataInserimento = Calendar.getInstance();

        // Act & Assert
        Prodotto.insertProdotto(
                codiceaBarre,
                prezzo,
                descrizione,
                specifiche,
                image,
                quantita,
                marca,
                modello,
                userType,
                passData,
                nomeCategoria,
                dataInserimento
        );

        // Assert: Verifica che venga passato un valore vuoto o errato
        verify(mockCallableStatement).setString("codiceabarreIn", codiceaBarre);
    }


    @Test
    public void testPrezzoErrato() throws SQLException {
        // TEST: prezzo negativo o uguale a 0

        String codiceaBarre = "123456789101";
        double prezzo = -5.0; // Formato non valido
        String descrizione = "Il migliore per qualità/prezzo";
        String specifiche = "Mobile Intel 4 Series Express Chipset Family";
        InputStream image = mock(InputStream.class);
        int quantita = 10;
        String marca = "HP";
        String modello = "EliteBook";
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Computer";
        Calendar dataInserimento = Calendar.getInstance();

        // Act & Assert
        Prodotto.insertProdotto(
                codiceaBarre,
                prezzo,
                descrizione,
                specifiche,
                image,
                quantita,
                marca,
                modello,
                userType,
                passData,
                nomeCategoria,
                dataInserimento
        );

        // Assert
        verify(mockCallableStatement).setString("prezzoIn", String.valueOf(prezzo));
    }

    @Test
    public void testDescrizioneErrata() throws SQLException {
        // TEST: descrizione nulla

        String codiceaBarre = "123456789101";
        double prezzo = 199.99;
        String descrizione = null; // Formato non valido
        String specifiche = "Mobile Intel 4 Series Express Chipset Family";
        InputStream image = mock(InputStream.class);
        int quantita = 10;
        String marca = "HP";
        String modello = "EliteBook";
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Computer";
        Calendar dataInserimento = Calendar.getInstance();

        // Act & Assert
        Prodotto.insertProdotto(
                codiceaBarre,
                prezzo,
                descrizione,
                specifiche,
                image,
                quantita,
                marca,
                modello,
                userType,
                passData,
                nomeCategoria,
                dataInserimento
        );

        // Assert
        verify(mockCallableStatement).setString("descrizioneIn", descrizione);
    }
    @Test
    public void testSpecificheErrate() throws SQLException {
        // TEST: specifiche nulle
        String codiceaBarre = "123456789101";
        double prezzo = 199.99;
        String descrizione = "Il migliore per qualità/prezzo";
        String specifiche = null; // Formato non valido
        InputStream image = mock(InputStream.class);
        int quantita = 10;
        String marca = "HP";
        String modello = "EliteBook";
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Computer";
        Calendar dataInserimento = Calendar.getInstance();

        // Act & Assert
        Prodotto.insertProdotto(
                codiceaBarre,
                prezzo,
                descrizione,
                specifiche,
                image,
                quantita,
                marca,
                modello,
                userType,
                passData,
                nomeCategoria,
                dataInserimento
        );

        // Assert
        verify(mockCallableStatement).setString("specificheIn", specifiche);
    }
    @Test
    public void testImmagineErrata() throws SQLException {
        // Arrange: immagine nulla o errore nell URL
        String codiceaBarre = "123456789101";
        double prezzo = 199.99;
        String descrizione = "Il migliore per qualità/prezzo";
        String specifiche = "Mobile Intel 4 Series Express Chipset Family";
        InputStream image = null; // Formato non valido
        int quantita = 10;
        String marca = "HP";
        String modello = "EliteBook";
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Computer";
        Calendar dataInserimento = Calendar.getInstance();

        // Act & Assert
        Prodotto.insertProdotto(
                codiceaBarre,
                prezzo,
                descrizione,
                specifiche,
                image,
                quantita,
                marca,
                modello,
                userType,
                passData,
                nomeCategoria,
                dataInserimento
        );

        // Assert
        verify(mockCallableStatement).setBinaryStream("immagineIn", image);
    }
    @Test
    public void testQuantitaErrata() throws SQLException {
        // TEST: quantità negativa o pari a 0
        String codiceaBarre = "123456789101";
        double prezzo = 199.99;
        String descrizione = "Il migliore per qualità/prezzo";
        String specifiche = "Mobile Intel 4 Series Express Chipset Family";
        InputStream image = mock(InputStream.class);
        int quantita = -1;
        String marca = "HP";
        String modello = "EliteBook";
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Computer";
        Calendar dataInserimento = Calendar.getInstance();

        // Act & Assert
        Prodotto.insertProdotto(
                codiceaBarre,
                prezzo,
                descrizione,
                specifiche,
                image,
                quantita,
                marca,
                modello,
                userType,
                passData,
                nomeCategoria,
                dataInserimento
        );

        // Assert
        verify(mockCallableStatement).setString("quantitaIn", String.valueOf(quantita));
    }
    @Test
    public void testMarcaErrata() throws SQLException {
        // TEST: marca nulla
        String codiceaBarre = "123456789101";
        double prezzo = 199.99;
        String descrizione = "Il migliore per qualità/prezzo";
        String specifiche = "Mobile Intel 4 Series Express Chipset Family";
        InputStream image = mock(InputStream.class);
        int quantita = 10;
        String marca = null; //Formato non valido
        String modello = "EliteBook";
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Computer";
        Calendar dataInserimento = Calendar.getInstance();

        // Act & Assert
        Prodotto.insertProdotto(
                codiceaBarre,
                prezzo,
                descrizione,
                specifiche,
                image,
                quantita,
                marca,
                modello,
                userType,
                passData,
                nomeCategoria,
                dataInserimento
        );

        // Assert
        verify(mockCallableStatement).setString("marcaIn", marca);
    }
    @Test
    public void testModelloErrato() throws SQLException {
        // TEST: modello nullo
        String codiceaBarre = "123456789101";
        double prezzo = 199.99;
        String descrizione = "Il migliore per qualità/prezzo";
        String specifiche = "Mobile Intel 4 Series Express Chipset Family";
        InputStream image = mock(InputStream.class);
        int quantita = 10;
        String marca = "HP";
        String modello = null; //Formato non valido
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Computer";
        Calendar dataInserimento = Calendar.getInstance();

        // Act & Assert
        Prodotto.insertProdotto(
                codiceaBarre,
                prezzo,
                descrizione,
                specifiche,
                image,
                quantita,
                marca,
                modello,
                userType,
                passData,
                nomeCategoria,
                dataInserimento
        );

        // Assert
        verify(mockCallableStatement).setString("modelloIn", modello);
    }
    @Test
    public void testDataInserimentoErrata() throws SQLException {
        // TEST: data nulla
        String codiceaBarre = "123456789101";
        double prezzo = 199.99;
        String descrizione = "Il migliore per qualità/prezzo";
        String specifiche = "Mobile Intel 4 Series Express Chipset Family";
        InputStream image = mock(InputStream.class);
        int quantita = 10;
        String marca = "HP";
        String modello = "EliteBook";
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Computer";
        Calendar dataInserimento = null; // Formato non valido

        // Act & Assert
        Prodotto.insertProdotto(
                codiceaBarre,
                prezzo,
                descrizione,
                specifiche,
                image,
                quantita,
                marca,
                modello,
                userType,
                passData,
                nomeCategoria,
                dataInserimento
        );

        // Assert
        verify(mockCallableStatement).setString("datainserimentoIn", null);
    }
    @Test
    public void testInserimentoDatiCorretti() throws SQLException {
        // TEST: tutti i dati corretti
        String codiceaBarre = "123456789101"; // Formato corretto
        double prezzo = 199.99; // Formato corretto
        String descrizione = "Il migliore per qualità/prezzo"; // Formato corretto
        String specifiche = "Mobile Intel 4 Series Express Chipset Family"; // Formato corretto
        InputStream image = mock(InputStream.class); // Simulazione di un'immagine valida
        int quantita = 10; // Valore positivo, formato corretto
        String marca = "HP"; // Formato corretto
        String modello = "EliteBook"; // Formato corretto
        String userType = "admin"; // Utente valido
        String passData = "password"; // Password corretta
        String nomeCategoria = "Computer"; // Nome categoria valido
        Calendar dataInserimento = Calendar.getInstance(); // Data corretta

        // Act
        Prodotto.insertProdotto(
                codiceaBarre,
                prezzo,
                descrizione,
                specifiche,
                image,
                quantita,
                marca,
                modello,
                userType,
                passData,
                nomeCategoria,
                dataInserimento
        );

        // Assert: verifica che ogni parametro venga passato correttamente
        verify(mockCallableStatement).setString("codiceabarreIn", codiceaBarre);
        verify(mockCallableStatement).setString("prezzoIn", String.valueOf(prezzo));
        verify(mockCallableStatement).setString("descrizioneIn", descrizione);
        verify(mockCallableStatement).setString("specificheIn", specifiche);
        verify(mockCallableStatement).setBinaryStream("immagineIn", image);
        verify(mockCallableStatement).setString("quantitaIn", String.valueOf(quantita));
        verify(mockCallableStatement).setString("marcaIn", marca);
        verify(mockCallableStatement).setString("modelloIn", modello);
        verify(mockCallableStatement).setString("nomeIn", nomeCategoria);

        // Converte la data nel formato stringa atteso dal metodo
        String dataInserimentoStr = DateUtil.getStringFromCalendar(dataInserimento);
        verify(mockCallableStatement).setString("datainserimentoIn", dataInserimentoStr);

        // Verifica che il metodo venga eseguito
        verify(mockCallableStatement).execute();
    }

}