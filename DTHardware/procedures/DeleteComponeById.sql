delimiter //
drop procedure if EXISTS DeleteComponeById;
create procedure DeleteComponeById(
    IN idIn int
)

begin

    delete from compone
    where IDORDINE=idIn;

end //

delimiter ;