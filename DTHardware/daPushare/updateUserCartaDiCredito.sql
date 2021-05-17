delimiter //
drop procedure if exists updateUserCartaDiCredito;
create procedure updateUserCartaDiCredito(
    in usernameIn varchar(30),
    in ncartaIn char(12)
)

begin
    update cliente set ncarta=ncartaIn where USERNAME=usernameIn;
end //

delimiter ;