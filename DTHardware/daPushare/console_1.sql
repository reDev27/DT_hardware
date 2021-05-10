delete from cliente where cliente.NCARTA='0';
delete from cartadicredito where cartadicredito.ncarta='0';

select * from cliente, cartadicredito