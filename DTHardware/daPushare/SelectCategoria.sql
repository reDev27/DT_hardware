delimiter //
drop procedure if exists selectCategoria;
create procedure selectCategoria(
    out nomeOut varchar(50),
    out quantitaOut int
)
begin

    select NOME, QUANTITA from categoria;

end //
delimiter ;