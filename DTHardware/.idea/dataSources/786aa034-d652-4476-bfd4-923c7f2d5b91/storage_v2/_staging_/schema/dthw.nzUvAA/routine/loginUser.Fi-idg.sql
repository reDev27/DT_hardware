create
    definer = root@localhost procedure loginUser(IN usernameIn varchar(30), IN passwIn varbinary(100),
                                                 OUT esito tinyint(1))
begin
    if((select username from cliente
        where PASSW=passwIn and USERNAME=usernameIn) IS not NULL) then
        set esito=true;
    else
        set esito=false;
    end if;

end

