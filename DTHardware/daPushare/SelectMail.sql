delimiter //
drop procedure if EXISTS SelectMail;
create procedure SelectMail(
    in mailIn varchar(30)
)

begin

    select EMAIL from cliente where email=mailIn;

end //

delimiter ;