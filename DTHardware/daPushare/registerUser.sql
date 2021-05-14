DELIMITER //
drop procedure if exists registerUser;
create procedure registerUser(
    in usernameIn varchar(30),
    in emailIn varchar(320),
    in passwIn varbinary(100),
    in nomeIn varchar(30),
    in cognomeIn varchar(30),
    in ntelefonoIn int,
    in dataacquistoIn timestamp,
    in ncartaIn int,
    in scadenzaIn timestamp,
    in cvvIn int
)

begin

    insert into cliente values
    (usernameIn, emailIn, passwIn, nomeIn, cognomeIn, ntelefonoIn, dataacquistoIn, ncartaIn);

end //
DELIMITER ;