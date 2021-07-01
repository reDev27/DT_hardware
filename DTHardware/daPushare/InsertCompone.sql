DELIMITER //
drop procedure if exists InsertCompone;
create procedure InsertCompone(
    in nprodottiIn int,
    in codiceABarreIn char(12)
)

begin
    declare idIn int;

    set idIn = (select max(ID) from ordine);

    insert into COMPONE values
    (codiceABarreIn, idIn, nprodottiIn);

end //
DELIMITER ;