create database ProyectoResidencia

create table Usuarios
(
IDusuario int identity(100,1), 
nombre varchar(50) not null unique,
password varchar (30) not null, 
tipo int not null
)


Insert into Usuarios (nombre, password, tipo) values ('administrador', 'administrador', 1)
Insert into Usuarios (nombre, password, tipo) values ('usuario', 'usuario', 2)


create table Tipos (IDtipo int identity, nombreT varchar(20) not null)

Insert into Tipos (nombreT) values ('Administrador')
Insert into Tipos (nombreT) values ('Usuario')

select * from Alumno
create table Alumno
(
IdAlumno int not null primary key, 
NombreAlum varchar(50)not null,
ApePatAlum varchar(50)not null,
ApeMatAlum varchar(50)null,
EdadAlum int not null,
SexoAlum nvarchar(1) constraint Csexo check(SexoAlum in('M','F')) not null, 
NombreCalle char(50)  null default 'Docimicilio Conocido',
NumExt int null default 0,
NumInt int null default 0,
Colonia char(50) not null,
Municipio char(50) not null,
Telefono char(10) null  Default 'S/N',
CE char(50) null
)
select * from Materia
Create table Materia
(
IdMateria int not null primary key identity(1,1), 
NombreMateria varchar(50)not null
)

drop table HorarioClase

create table HorarioClase
(
HoraInicio time not null,
HoraFinal time not null,
IdMateria int not null foreign key references Materia(IdMateria),
IdAlumno int not null foreign key references Alumno(IdAlumno)
)

select* from HorarioClase

Create TAble Periodo
(
IdAlumno int not null foreign key references Alumno(IdAlumno),
IdPago int not null foreign key references pagos(IdPago),
FechaPeriodo datetime not null
)
drop table pagos
Create table Documentacion
(
IdDocumentacion int not null primary key identity(1,1),
Acta varbinary(max) not null,
certificado varbinary(max) not null,
INE varbinary (max) not null,
ComprobanteD varbinary (max) not null,
IdAlumno int not null foreign key references Alumno(IdAlumno)
)

create table pagos
(
IdPago int not null primary key identity(1,1),
Comprobante varbinary(MAX) not null,
FechaPago date not null,
RazonPago varchar(30) null, 
IdAlumno int foreign key references Alumno(IdAlumno)
)
drop table pagos
select * from Usuarios
select * from pagos