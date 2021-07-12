DELIMITER //
drop procedure if exists InsertProdotto;
create procedure InsertProdotto(
    in codiceabarreIn char(12),
    in prezzoIn double,
    in descrizioneIn varchar(1000),
    in specificheIn varchar(1000),
    in immagineIn longblob,
    in quantitaIn int,
    in marcaIn varchar(50),
    in modelloIn varchar(50),
    in nomeIn varchar(50),
    in datainserimentoIn timestamp
)

begin

    if(immagineIn is not null) then
        insert into PRODOTTO values
        (codiceabarreIn,prezzoIn,descrizioneIn,specificheIn, immagineIn, quantitaIn,marcaIn,modelloIn,nomeIn,datainserimentoIn);
    else
        insert into PRODOTTO (CODICEBARRE, PREZZO, DESCRIZIONE, SPECIFICHE, QUANTITA, MARCA, MODELLO, NOME, DATAINSERIMENTO)values
        (codiceabarreIn,prezzoIn,descrizioneIn,specificheIn, quantitaIn, marcaIn, modelloIn, nomeIn, datainserimentoIn);
    end if;

    update categoria
        set QUANTITA=QUANTITA+1
    where nome=nomeIn;

end //
DELIMITER ;