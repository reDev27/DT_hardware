delimiter //
drop procedure if EXISTS DeleteComponeByCodiceABarre;
create procedure DeleteComponeByCodiceABarre(
    IN codiceabarreIn char(12)
)

begin

    delete from compone
    where CODICEaBARRE=codiceabarreIn;

end //

delimiter ;