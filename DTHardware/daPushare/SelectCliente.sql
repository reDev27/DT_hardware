delimiter //
drop procedure if exists SelectCliente;
create procedure SelectCliente()
begin

    select USERNAME,EMAIL,PASSW,NOME,COGNOME,NTELEFONO from cliente;

end //
delimiter ;