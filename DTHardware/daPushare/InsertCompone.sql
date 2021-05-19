DELIMITER //
drop procedure if exists InsertCompone;
create procedure InsertCompone(
    in nprodottiIn int,
    in idIn int,
    in codiceABarreIn char(12)
)

begin

    insert into COMPONE values
    (nprodottiIn, idIn, codiceABarreIn);

end //
DELIMITER ;