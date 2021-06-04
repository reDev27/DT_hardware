delimiter //
drop procedure if exists SelectProdottoByCategoria;
create procedure SelectProdottoByCategoria(
    in categoriaIn varchar(50)
)
begin

    select CODICEBARRE,PREZZO,DESCRIZIONE,SPECIFICHE,IMMAGINE, PRODOTTO.QUANTITA,MARCA,MODELLO,DATAINSERIMENTO from PRODOTTO,CATEGORIA
        where prodotto.NOME = categoriaIn;

end //
delimiter ;