DELIMITER //
drop procedure if exists registerUser;
create procedure registerUser(
    in usernameIn varchar(30),
    in emailIn varchar(320),
    in passwIn varbinary(100),
    in nomeIn varchar(30),
    in cognomeIn varchar(30),
    in ntelefonoIn int,
    in dataacquistoIn date,
    in ncartaIn int,
    in scadenzaIn timestamp(4),
    in cvvIn int
)

begin
    insert into cartadicredito values
    (ncartaIn, scadenzaIn, cvvIn);

    insert into cliente values
    (usernameIn, emailIn, passwIn, nomeIn, cognomeIn, ntelefonoIn, dataacquistoIn, ncartaIn);
end //
DELIMITER ;