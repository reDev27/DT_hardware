DELIMITER //
drop procedure if exists loginUser;
create procedure loginUser(
    in usernameIn varchar(30),
    in passwIn varbinary(100),
    out esito boolean
)

begin
    if((select username from cliente
        where PASSW=passwIn and USERNAME=usernameIn) IS not NULL) then
        set esito=true;
    else
        set esito=false;
    end if;

end //
DELIMITER ;