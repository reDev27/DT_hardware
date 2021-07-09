delimiter //
drop procedure if EXISTS UpdateCliente;
create procedure UpdateCliente(
    IN usernameIn varchar(30),
    IN emailIn varchar(320),
    IN nomeIn varchar(30),
    IN cognomeIn varchar(30),
    IN ntelefonoIn int
)

begin

    update cliente
    set EMAIL=emailIn,
        NOME=nomeIn,
        COGNOME=cognomeIn,
        NTELEFONO=ntelefonoIn
    where username=usernameIn;

end //

delimiter ;