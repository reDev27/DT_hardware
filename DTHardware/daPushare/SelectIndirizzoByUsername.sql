delimiter //
drop procedure if EXISTS SelectIndirizzoByUsername;
create procedure SelectIndirizzoByUsername(
    in usernameIn varchar(30)
)

begin

    select indirizzo.via, indirizzo.NCIVICO, CITTA, CAP, FLAG
    from indirizzo, risiede
    where risiede.USERNAME=usernameIn and indirizzo.VIA=risiede.VIA and indirizzo.NCIVICO=risiede.NCIVICO;

end //

delimiter ;