delimiter //
drop procedure if EXISTS DeleteCartaDiCreditoByUsername;
create procedure DeleteCartaDiCreditoByUsername(
    IN usernameIn varchar(30)
)

begin

    delete from cartadicredito
    where username=usernameIn;

end //

delimiter ;