create table User_Table (
	Id serial primary key,
	First_Name varchar ( 50 ) not null,
	Last_Name varchar ( 50 ) not null,
	Email varchar ( 50 ) unique not null
);

create table User_Login (
	User_Id int primary key references User_Table(Id),
	Username varchar ( 15 ) unique not null,
	Password varchar ( 72 ) not null	
);

create table Account_Type (
	Id serial primary key,
	Acct_Type varchar ( 20 ) unique not null,
	Interest double precision not null,
	Monthly_Fee double precision not null
);

create table Account (
	Id serial primary key,
	acct_name varchar(50) not null,
	User_Id int not null,
	Joint_User_Id int,
	Type_Id int not null,
	foreign key (User_Id)
		references User_Table (Id),
	foreign key (Joint_User_Id)
		references User_Table (Id),
	foreign key (Type_Id)
		references Account_Type (Id)
);

create table Account_Balance (
	Account_Id int primary key references Account (Id),
	Balance double precision not null check (Balance >= 0)
);

create table Account_Transaction (
	Id serial primary key,
	Account_Id int not null,
	Transaction_Amt double precision not null,
	Description text not null,
	foreign key (Account_Id)
		references Account (Id)
);

create table Transfer (
	Id serial primary key,
	Sender int not null,
	Reciever int not null,
	Amount float not null,
	foreign key (Sender)
		references Account (Id),
	foreign key (Reciever)
		references Account (Id)
);

