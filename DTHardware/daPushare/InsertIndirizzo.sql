DELIMITER //
drop procedure if exists InsertIndirizzo;
create procedure InsertIndirizzo(
    in viaIn varchar(50),
    in ncivicoIn int,
    in cittaIn varchar(20),
    in capIn int,
    in flagIn boolean,
    in usernameIn varchar(30)
)

begin

    insert into INDIRIZZO values
    (viaIn,ncivicoIn,cittaIn,capIn,flagIn,usernameIn);

end //
DELIMITER ;