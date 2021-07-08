delimiter //
drop procedure if EXISTS DeleteOrdineByUsername;
create procedure DeleteOrdineByUsername(
    IN usernameIn varchar(30)
)

begin

    delete from ordine
    where username=usernameIn;

end //

delimiter ;