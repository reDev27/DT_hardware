DELIMITER //
drop procedure if exists InsertCompone;
create procedure InsertCompone(
    in nprodottiIn int,
    in codiceABarreIn char(12)
)

begin
    declare idIn int;
start transaction;

    set idIn = (select max(ID) from ordine);

    insert into COMPONE values
    (codiceABarreIn, idIn, nprodottiIn);
commit;
end //
DELIMITER ;