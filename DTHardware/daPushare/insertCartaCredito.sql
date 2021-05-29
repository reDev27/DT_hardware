DELIMITER //
drop procedure if exists insertCartaDiCredito;
create procedure insertCartaDiCredito(
    in ncartaIn char(12),
    in scadenzaIn timestamp,
    in cvvIn int,
    in usernameIn varchar(30)
)

begin
    insert into cartadicredito values
    (ncartaIn, scadenzaIn, cvvIn,usernameIn);

end //
DELIMITER ;
