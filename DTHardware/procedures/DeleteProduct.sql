delimiter //
drop procedure if EXISTS DeleteProduct;
create procedure DeleteProduct(
    IN codiceabarreIn char(12)
)

begin

    delete from prodotto
    where CODICEBARRE=codiceabarreIn;

end //

delimiter ;