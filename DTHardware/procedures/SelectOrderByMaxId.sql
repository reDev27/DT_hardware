DELIMITER //
drop procedure if exists SelectOrderByMaxId;
create procedure SelectOrderByMaxId(
    in usernameIn varchar(30)
)

begin

    declare maxId int;

    set maxId=(select max(ID) from ordine where USERNAME=usernameIn);

    select maxId;

end //
DELIMITER ;