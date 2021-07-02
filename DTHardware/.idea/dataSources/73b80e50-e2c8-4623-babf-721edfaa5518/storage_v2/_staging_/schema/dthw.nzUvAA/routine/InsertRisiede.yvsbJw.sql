create
    definer = root@localhost procedure InsertRisiede(IN viaIn varchar(50), IN nCivicoIn int, IN usernameIn varchar(30))
begin

    insert into risiede (USERNAME, VIA, NCIVICO) values
    (usernameIn, viaIn, nCivicoIn);

end;

