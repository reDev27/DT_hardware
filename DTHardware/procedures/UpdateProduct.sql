delimiter //
drop procedure if EXISTS UpdateProduct;
create procedure UpdateProduct(
    IN codiceabarreIn char(12),
    IN prezzoIn double,
    IN descrizioneIn varchar(1000),
    IN specificheIn varchar(1000),
    IN immagineIn longblob,
    IN quantitaIn int,
    IN marcaIn varchar(50),
    IN modelloIn varchar(50),
    IN nomeIn varchar(50)
)

begin

    if(immagineIn is not null) then
        update prodotto
        set
            PREZZO = prezzoIn,
            DESCRIZIONE = descrizioneIn,
            SPECIFICHE = specificheIn,
            IMMAGINE = immagineIn,
            QUANTITA = quantitaIn,
            MARCA = marcaIn,
            MODELLO = modelloIn,
            NOME = nomeIn
        where CODICEBARRE=codiceabarreIn;
    else
        update prodotto
        set
            PREZZO = prezzoIn,
            DESCRIZIONE = descrizioneIn,
            SPECIFICHE = specificheIn,
            QUANTITA = quantitaIn,
            MARCA = marcaIn,
            MODELLO = modelloIn,
            NOME = nomeIn
        where CODICEBARRE=codiceabarreIn;
    end if;

end //

delimiter ;