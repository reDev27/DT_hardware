DELIMITER //
drop procedure if exists UpdateOrder;
create procedure UpdateOrder(
    IN idIn int,
    IN fatturaIn varchar(1000),
    IN totaleIn double,
    IN dataacquistoIn timestamp,
    IN usernameIn varchar(50)
)

begin

    update ordine
    set
        FATTURA=fatturaIn,
        totale=totaleIn,
        DATAACQUISTO=dataacquistoIn,
        USERNAME=usernameIn
    where ID=idIn;

end //
DELIMITER ;