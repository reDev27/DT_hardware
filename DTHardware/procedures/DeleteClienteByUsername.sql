delimiter //
drop procedure if EXISTS DeleteClienteByUsername;
create procedure DeleteClienteByUsername(
    IN usernameIn varchar(30)
)

begin

    delete from cliente
    where username=usernameIn;

end //

delimiter ;