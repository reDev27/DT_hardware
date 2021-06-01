delimiter //
drop procedure if exists SelectIndirizzoByUsername;
create procedure SelectIndirizzoByUsername(
    in usernameIn varchar(30)
)
begin

    select VIA,NCIVICO,CITTA,CAP,FLAG,USERNAME from indirizzo
    where indirizzo.USERNAME=usernameIn;

end //
delimiter ;