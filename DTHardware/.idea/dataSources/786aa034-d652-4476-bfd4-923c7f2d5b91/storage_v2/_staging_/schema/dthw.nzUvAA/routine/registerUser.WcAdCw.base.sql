create
    definer = root@localhost procedure registerUser(IN usernameIn varchar(30), IN emailIn varchar(320),
                                                    IN passwIn varbinary(100), IN nomeIn varchar(30),
                                                    IN cognomeIn varchar(30), IN ntelefonoIn int,
                                                    IN dataacquistoIn date, IN ncartaIn int, IN scadenzaIn timestamp(4),
                                                    IN cvvIn int)
begin
    insert into cartadicredito values
    (ncartaIn, scadenzaIn, cvvIn);

    insert into cliente values
    (usernameIn, emailIn, passwIn, nomeIn, cognomeIn, ntelefonoIn, dataacquistoIn, ncartaIn);
end;
