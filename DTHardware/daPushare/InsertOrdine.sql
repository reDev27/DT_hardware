DELIMITER //
drop procedure if exists InsertOrdine;
create procedure InsertOrdine(
    in idIn int,
    in scontoIn int,
    in totaleIn double,
    in dataacquistoIn timestamp,
    in usernameIn varchar(50)
)

begin

    insert into ORDINE values
    (idIn,scontoIn,totaleIn,dataacquistoIn,usernameIn);

end //
DELIMITER ;