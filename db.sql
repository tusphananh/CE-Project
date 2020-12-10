create database hotel;
use hotel;
create table customer(
	cus_ID int auto_increment,
    name varchar(20) not null,
    phone varchar(20) not null unique,
    coin int not null,
    type varchar(20) not null,
    primary key(cus_ID)
    );

create table user(
	user_ID int auto_increment,
    name varchar(20) not null,
    phone varchar(20) not null unique,
    identifier varchar(20) not null unique,
    username varchar(20) not null unique,
    dob date not null,
    password varchar(20) not null,
    role varchar(20) not null,
    image varchar(20),
    status varchar(20) not null,
    primary key(user_ID)
    );
    
create table reservation(
	first_id varchar(2) ,
	last_id int,
    cus_ID int,
    user_ID int,
    status varchar(20) not null,
    payment_status varchar(20) not null,
    total_price float not null,
    note varchar(100),
    primary key(first_id,last_id),
    foreign key(cus_ID) references customer(Cus_ID),
	foreign key(user_ID) references user(user_ID)
    );
    
create table roombooking(
	first_id varchar(2) ,
	last_id int,
    start_date date not null,
    end_date date not null,
    primary key(first_id,last_id),
    foreign key(first_id,last_id) references reservation(first_id,last_id)
    );
    
    create table banquetbooking(
	first_id varchar(2) ,
	last_id int,
    start_date varchar(20) not null,
    start_hour varchar(2) not null,
    primary key(first_id,last_id),
    foreign key(first_id,last_id) references reservation(first_id,last_id)
    );
    
    create table room(
		id int auto_increment,
        name varchar(20) not null,
        status varchar(20) not null,
        type varchar(20) not null,
        capacity long not null,
        price double not null,
        sale long not null,
        image varchar(20),
        primary key(id)
        );
        
	create table service(
		id int auto_increment,
        name varchar(20) not null,
        price double not null,
        permission varchar(2),
        primary key(id)
        );
        
	create table uses(
		first_id varchar(20),
        last_id int,
        ser_id int,
        amount long not null,
        primary key(first_id,last_id,ser_id),
        foreign key(first_id,last_id) references reservation(first_id,last_id),
	    foreign key(ser_id) references service(id)
        );
        
	create table contain(
		first_id varchar(20),
        last_id int,
        room_ID int,
        primary key(first_id,last_id,room_ID),
        foreign key(first_id,last_id) references reservation(first_id,last_id),
	    foreign key(room_ID) references room(id)
        );
        
	insert into room(name,status,type,capacity,price,sale,image)
    values	("A1.101","useable","vip",2,30.0,50,"Room1.png"),
			("A1.202","useable","normal",4,20.5,30,"Room2.png"),
            ("A1.303","useable","vip",4,60.59,20,"Room3.png"),
            ("A1.404","useable","normal",10,50.0,10,"Room4.png"),
            ("A1.505","useable","vip",2,30.0,0,"Room5.png"),
            ("A2.101","useable","normal",4,20.5,0,"Room6.png"),
            ("A2.202","useable","vip",2,30.0,0,"Room7.png"),
            ("A2.303","useable","normal",10,50.0,0,"Room8.png"),
            ("A2.404","useable","vip",2,30.0,0,"Room9.png"),
            ("A2.505","useable","normal",2,7.6,0,"Room10.png");
            
insert into service(name,price,permission)
values	("Swiming",5,"RB"),
		("Buffet",15.5,"RB"),
        ("Massage",5.9,"RB"),
        ("Sky Diving",60,"RB"),
        ("Scuba Diving",40,"RB"),
        ("Swiming",6,"BB"),
		("Buffet",17,"BB"),
        ("Massage",7,"BB");
    
		
insert into user(name,phone,identifier,username,dob,password,role,status,image)
values	("Phan Anh Tu","0932059267","1234567","anhtu","2000-03-06","123","manager","good","mark.jpg"),
		("Nguyen Lam","0932059266","12345678","nguyenlam","2000-03-06","456","manager","good","gate.jpg"),
		("Quyen Huynh","0932059265","123456789","quyen","2000-03-06","123","staff","good","steve.jpg");

