delimiter //
drop procedure if exists SelectCliente;
create procedure SelectCliente()
begin

    select USERNAME,EMAIL,NOME,COGNOME,NTELEFONO from cliente;

end //
delimiter ;