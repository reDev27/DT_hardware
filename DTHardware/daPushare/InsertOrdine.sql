DELIMITER //
drop procedure if exists InsertOrdine;
create procedure InsertOrdine(
    in idIn int,
    in scontoIn int,
    in totaleIn double
)

begin

    insert into ORDINE values
    (idIn,scontoIn,totaleIn);

end //
DELIMITER ;