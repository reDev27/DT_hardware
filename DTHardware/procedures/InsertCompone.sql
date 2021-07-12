DELIMITER //
drop procedure if exists InsertCompone;
create procedure InsertCompone(
    in nprodottiIn int,
    in codiceABarreIn char(12)
)

begin
    declare idIn int;
    declare totale int;
start transaction;

    set autocommit = OFF;

    set idIn = (select max(ID) from ordine);

    set totale = (select QUANTITA from prodotto where codiceABarreIn=CODICEBARRE)-nprodottiIn;

    if(totale>=0) then
        update prodotto
        set
            QUANTITA=totale
        where CODICEBARRE=codiceABarreIn;
        insert into COMPONE values
        (codiceABarreIn, idIn, nprodottiIn);
    else
        select codiceABarreIn;
        rollback;
    end if;


commit;
end //
DELIMITER ;