DELIMITER //
drop procedure if exists InsertCategoria;
create procedure InsertCategoria(
    in nomeIn varchar(50),
    in quantitaIn int,
    in codiceabarreIn int
)

begin

    insert into CATEGORIA values
    (nomeIn,quantitaIn,codiceabarreIn);

end //
DELIMITER ;