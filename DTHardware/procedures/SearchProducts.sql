delimiter //
drop procedure if EXISTS SearchProducts;
create procedure SearchProducts(
    in toSearchIn varchar(50)
)

begin



    select CODICEBARRE, MARCA, MODELLO from prodotto where MARCA like concat('%', toSearchIn, '%') or MARCA like concat(toSearchIn, '%') or MARCA like concat('%', toSearchIn)
                                                        or MODELLO like concat('%', toSearchIn, '%') or MODELLO like concat(toSearchIn, '%') or MODELLO like concat('%', toSearchIn)
                                                        or concat(MARCA, ' ',MODELLO) like concat('%', toSearchIn, '%') or concat(MARCA, ' ',MODELLO) like concat(toSearchIn, '%') or concat(MARCA, ' ', MODELLO) like concat('%', toSearchIn)
                                                        or concat(MODELLO, ' ',MARCA) like concat('%', toSearchIn, '%') or concat(MODELLO, ' ',MARCA) like concat(toSearchIn, '%') or concat(MODELLO, ' ', MARCA) like concat('%', toSearchIn);

end //

delimiter ;