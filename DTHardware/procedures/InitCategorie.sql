DELIMITER //
drop procedure if exists InitCategorie;
create procedure InitCategorie()

begin
    insert into categoria values
    ('Alimentatori',0),
    ('Case',0),
    ('Dissipazione',0),
    ('Hard Disk',0),
    ('Memorie Ram',0),
    ('Monitor',0),
    ('Mouse',0),
    ('Pendrive',0),
    ('Periferiche da gioco',0),
    ('Processori',0),
    ('Schede madri',0),
    ('Schede video',0),
    ('Tastiere',0);

end //
DELIMITER ;
