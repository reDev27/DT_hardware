delimiter //
drop procedure if EXISTS IsAvailableProduct;
create procedure IsAvailableProduct(
    in codiceABarreIn char(12),
    in quantitaIn int
)

begin

    select CODICEBARRE from prodotto where codiceABarreIn=CODICEBARRE and QUANTITA<quantitaIn;

end //

delimiter ;