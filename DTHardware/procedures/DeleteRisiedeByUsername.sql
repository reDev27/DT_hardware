delimiter //
drop procedure if EXISTS DeleteRisiedeByUsername;
create procedure DeleteRisiedeByUsername(
    IN usernameIn varchar(30)
)

begin

    delete from risiede
    where username=usernameIn;

end //

delimiter ;