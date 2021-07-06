delimiter //
drop procedure if EXISTS SelectProductsByOrderId;
create procedure SelectProductsByOrderId(
    in idIn int
)

begin

    select CODICEBARRE, PREZZO, IMMAGINE, QUANTITA, MARCA, MODELLO
    from prodotto, compone
    where IDORDINE=idIn and CODICEBARRE=CODICEABARRE;

end //

delimiter ;