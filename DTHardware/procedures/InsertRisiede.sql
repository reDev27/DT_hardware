delimiter //
drop procedure if EXISTS InsertRisiede;
create procedure InsertRisiede(
    in usernameIn varchar(30),
    in viaIn varchar(50),
    in nCivicoIn int
)

begin

    insert into risiede (USERNAME, VIA, NCIVICO) values
    (usernameIn, viaIn, nCivicoIn);

end //

delimiter ;