delimiter //
drop procedure if exists SelectCartadicreditoByUsername;
create procedure SelectCartadicreditoByUsername(
    in usernameIn varchar(30)
)
begin

    select (NCARTA,SCADENZA,CVV,USERNAME) from cartadicredito
    where cartadicredito.USERNAME=usernameIn;

end //
delimiter ;