delimiter //
drop procedure if exists selectProdottoByCodiceABarre;

create procedure selectProdottoByCodiceABarre(
    in codiceABarreIn char(12)
)
begin

     select CODICEBARRE, PREZZO, DESCRIZIONE, SPECIFICHE, IMMAGINE,
            QUANTITA, MARCA, MODELLO, DATAINSERIMENTO from prodotto where CODICEBARRE=codiceABarreIn;

end //
delimiter ;