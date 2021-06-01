delimiter //
drop procedure if exists SelectClienteByUsername;
create procedure SelectClienteByUsername(
    in usernameIn varchar(30)
)
begin

    select USERNAME,EMAIL,PASSW,NOME,COGNOME,NTELEFONO from CLIENTE
    where cliente.USERNAME=usernameIn;

end //
delimiter ;