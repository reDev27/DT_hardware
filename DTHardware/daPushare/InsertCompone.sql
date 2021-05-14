DELIMITER //
drop procedure if exists InsertCompone;
create procedure InsertCompone(
    in nprodottiIn int
)

begin

    insert into COMPONE values
    (nprodottiIn);

end //
DELIMITER ;