delimiter //
drop procedure if EXISTS SelectComponeById;
create procedure SelectComponeById(
    in idIn int
)

begin

    select CODICEABARRE, IDORDINE, NPRODOTTI
    from compone
    where IDORDINE=idIn;

end //

delimiter ;