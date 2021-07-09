DELIMITER //
drop procedure if exists SelectOrders;
create procedure SelectOrders()

begin

    select id, FATTURA, totale, DATAACQUISTO, USERNAME
    from ordine;

end //
DELIMITER ;