DELIMITER //
drop procedure if exists InsertProdotto;
create procedure InsertProdotto(
    in codiceabarreIn int,
    in prezzoIn double,
    in descrizioneIn varchar(1000),
    in specificheIn varchar(1000),
    in immagineIn longblob,
    in quantitaIn int,
    in marcaIn varchar(50),
    in modelloIn varchar(50),
    in nomeIn varchar(50)
)

begin

    insert into PRODOTTO values
    (codiceabarreIn,prezzoIn,descrizioneIn,specificheIn, immagineIn, quantitaIn,marcaIn,modelloIn,nomeIn);

end //
DELIMITER ;