delimiter //
drop procedure if exists SelectMostRecentProducts;
create procedure SelectMostRecentProducts()
begin

    select * from prodotto
    order by DATAINSERIMENTO desc
    limit 0,10;

end //
delimiter ;