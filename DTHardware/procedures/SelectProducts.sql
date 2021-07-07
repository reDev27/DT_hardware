delimiter //
drop procedure if EXISTS SelectProducts;
create procedure SelectProducts()

begin



    select CODICEBARRE, PREZZO, DESCRIZIONE, SPECIFICHE, IMMAGINE,
    QUANTITA, MARCA, MODELLO, NOME, DATAINSERIMENTO
    from prodotto;

end //

delimiter ;