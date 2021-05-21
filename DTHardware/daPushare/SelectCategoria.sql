delimiter //
drop procedure if exists selectCategoria;
create procedure selectCategoria()
begin

    select NOME, QUANTITA from categoria;

end //
delimiter ;