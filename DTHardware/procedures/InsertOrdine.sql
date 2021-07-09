DELIMITER //
drop procedure if exists InsertOrdine;
create procedure InsertOrdine(
    in fatturaIn varchar(1000),
    in totaleIn double,
    in dataacquistoIn timestamp,
    in usernameIn varchar(50)
)

begin
start transaction;

    insert into ORDINE (fattura, totale, dataacquisto, username) values
    (fatturaIn,totaleIn,dataacquistoIn,usernameIn);

commit;
end //
DELIMITER ;