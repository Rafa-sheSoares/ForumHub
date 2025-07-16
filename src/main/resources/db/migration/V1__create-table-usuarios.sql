create table usuarios(

      id bigint not null auto_increment,
      autor varchar(100) not null,
      titulo  varchar(100)not null,
      mensagem varchar(300) not null,
      data_criacao varchar(50) not null,
      curso  varchar(100)not null,


      primary key(id)


);
