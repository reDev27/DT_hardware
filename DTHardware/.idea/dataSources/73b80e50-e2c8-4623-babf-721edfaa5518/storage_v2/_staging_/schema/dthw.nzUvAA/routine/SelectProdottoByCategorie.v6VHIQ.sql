create
    definer = root@localhost procedure SelectProdottoByCategorie(IN categoriaIn varchar(50))
begin

    select CODICEBARRE,PREZZO,DESCRIZIONE,SPECIFICHE,IMMAGINE, QUANTITA,MARCA,MODELLO from PRODOTTO,CATEGORIA
        where prodotto.NOME = categoriaIn;

end;

