delimiter //
drop procedure if EXISTS SelectOrdiniByUsername;
create procedure SelectOrdiniByUsername(
    in usernameIn varchar(30)
)

begin

    select id, fattura, TOTALE, DATAACQUISTO
    from ordine
    where USERNAME=usernameIn;

end //

delimiter ;