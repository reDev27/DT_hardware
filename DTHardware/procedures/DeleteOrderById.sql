DELIMITER //
drop procedure if exists DeleteOrderById;
create procedure DeleteOrderById(
    IN idIn int
)

begin

    delete from ordine
    where ID=idIn;

end //
DELIMITER ;