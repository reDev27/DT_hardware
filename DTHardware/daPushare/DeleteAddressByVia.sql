delimiter //
drop procedure if EXISTS DeleteAddressByVia;
create procedure DeleteAddressByVia(
    in viaIn varchar(50),
    in nCivicoIn int,
    in usernameIn varchar(30)
)

begin

    delete from risiede where VIA=viaIn and NCIVICO=nCivicoIn and USERNAME=usernameIn;
    delete from indirizzo where VIA=viaIn and NCIVICO=nCivicoIn;

end //

delimiter ;