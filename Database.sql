create database sewa_mobil;
use sewa_mobil;

create table sewa(
	id int primary key not null auto_increment,
	kode_sewa varchar(30) not null,
	nomor_plat varchar(30) not null,
	jenis_mobil varchar(30) not null,
	nama_penyewa varchar(30) not null,
	lama_sewa varchar(30) not null
);

insert into sewa values
('','A1','1211','Daihatsu','Azis','1 Tahun'),
('','A2','1212','Xenia','Ramdhan','1 Tahun'),
('','A3','1213','Avanza','Arda','1 Tahun'),
('','A4','1214','F1','Zaki','1 Tahun'),
('','A5','1215','Tamiya','Ridwan','1 Tahun'),
('','A6','1216','Bus','Eagan','1 Tahun'),
('','A7','1217','Truck','Isal','1 Tahun');
