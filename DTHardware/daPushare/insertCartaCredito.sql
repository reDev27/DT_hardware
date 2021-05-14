DELIMITER //
create procedure insertCartaDiCredito(
    in ncartaIn char(12),
    in scadenzaIn timestamp,
    in cvvIn int
)

begin
    insert into cartadicredito values
    (ncartaIn, scadenzaIn, cvvIn);

end //
DELIMITER ;