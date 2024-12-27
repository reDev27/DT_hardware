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
        // Arrange: codice a barre vuoto o errato
        String codiceaBarre = ""; // Formato non valido
        double prezzo = 10.0;
        String descrizione = "Descrizione valida";
        String specifiche = "Specifiche valide";
        InputStream image = mock(InputStream.class);
        int quantita = 5;
        String marca = "MarcaValida";
        String modello = "ModelloValido";
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Elettronica";
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
        // Arrange: prezzo negativo
        String codiceaBarre = "12345";
        double prezzo = -5.0; // Formato non valido
        String descrizione = "Descrizione valida";
        String specifiche = "Specifiche valide";
        InputStream image = mock(InputStream.class);
        int quantita = 5;
        String marca = "MarcaValida";
        String modello = "ModelloValido";
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Elettronica";
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
        // Arrange: descrizione nulla
        String codiceaBarre = "12345";
        double prezzo = 10.0;
        String descrizione = null; // Formato non valido
        String specifiche = "Specifiche valide";
        InputStream image = mock(InputStream.class);
        int quantita = 5;
        String marca = "MarcaValida";
        String modello = "ModelloValido";
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Elettronica";
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
        // Arrange: specifiche nulle
        String codiceaBarre = "12345";
        double prezzo = 10.0;
        String descrizione = "Descrizione valida";
        String specifiche = null; // Formato non valido
        InputStream image = mock(InputStream.class);
        int quantita = 5;
        String marca = "MarcaValida";
        String modello = "ModelloValido";
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Elettronica";
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
        // Arrange: immagine nulla
        String codiceaBarre = "12345";
        double prezzo = 10.0;
        String descrizione = "Descrizione valida";
        String specifiche = "Specifiche valide";
        InputStream image = null; // Formato non valido
        int quantita = 5;
        String marca = "MarcaValida";
        String modello = "ModelloValido";
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Elettronica";
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
        // Arrange: quantità negativa
        String codiceaBarre = "12345";
        double prezzo = 10.0;
        String descrizione = "Descrizione valida";
        String specifiche = "Specifiche valide";
        InputStream image = mock(InputStream.class);
        int quantita = -1; // Formato non valido
        String marca = "MarcaValida";
        String modello = "ModelloValido";
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Elettronica";
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
        // Arrange: marca nulla
        String codiceaBarre = "12345";
        double prezzo = 10.0;
        String descrizione = "Descrizione valida";
        String specifiche = "Specifiche valide";
        InputStream image = mock(InputStream.class);
        int quantita = 5;
        String marca = null; // Formato non valido
        String modello = "ModelloValido";
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Elettronica";
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
        // Arrange: modello nullo
        String codiceaBarre = "12345";
        double prezzo = 10.0;
        String descrizione = "Descrizione valida";
        String specifiche = "Specifiche valide";
        InputStream image = mock(InputStream.class);
        int quantita = 5;
        String marca = "MarcaValida";
        String modello = null; // Formato non valido
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Elettronica";
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
        // Arrange: data nulla
        String codiceaBarre = "12345";
        double prezzo = 10.0;
        String descrizione = "Descrizione valida";
        String specifiche = "Specifiche valide";
        InputStream image = mock(InputStream.class);
        int quantita = 5;
        String marca = "MarcaValida";
        String modello = "ModelloValido";
        String userType = "admin";
        String passData = "password";
        String nomeCategoria = "Elettronica";
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
        // Arrange: tutti i dati corretti
        String codiceaBarre = "12345ABC"; // Formato corretto
        double prezzo = 19.99; // Formato corretto
        String descrizione = "Prodotto di test"; // Formato corretto
        String specifiche = "Specifiche tecniche valide"; // Formato corretto
        InputStream image = mock(InputStream.class); // Simulazione di un'immagine valida
        int quantita = 10; // Valore positivo, formato corretto
        String marca = "MarcaValida"; // Formato corretto
        String modello = "Modello2023"; // Formato corretto
        String userType = "admin"; // Utente valido
        String passData = "password"; // Password corretta
        String nomeCategoria = "Elettronica"; // Nome categoria valido
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