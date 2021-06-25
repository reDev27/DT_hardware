delimiter //
drop procedure if EXISTS SelectUsername;
create procedure SelectUsername(
    in usernameIn varchar(30)
)

begin

    select USERNAME from cliente where USERNAME=usernameIn;

end //

delimiter ;