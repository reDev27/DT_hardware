delimiter //
drop procedure if exists selectProdotto;


create procedure selectProdotto(
    in codiceABarreIn char(12),
    out codiceABarreOut char(12),
    out prezzoOut double,
    out descrizioneOut varchar(1000),
    out specificheOut varchar(1000),
    out immagineOut longblob,
    out quantitaOut int,
    out marcaOut varchar(50),
    out modelloOut varchar(50)
)
begin

    set codiceABarreOut = (select CODICEBARRE from prodotto where CODICEBARRE=codiceABarreIn);
    set prezzoOut = (select PREZZO from prodotto where CODICEBARRE=codiceABarreIn);
    set descrizioneOut = (select DESCRIZIONE from prodotto where CODICEBARRE=codiceABarreIn);
    set specificheOut = (select SPECIFICHE from prodotto where CODICEBARRE=codiceABarreIn);
    set immagineOut = (select IMMAGINE from prodotto where CODICEBARRE=codiceABarreIn);
    set quantitaOut = (select QUANTITA from prodotto where CODICEBARRE=codiceABarreIn);
    set marcaOut = (select MARCA from prodotto where CODICEBARRE=codiceABarreIn);
    set modelloOut = (select MODELLO from prodotto where CODICEBARRE=codiceABarreIn);
end //
delimiter ;