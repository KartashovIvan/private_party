# Для запуска проекта private_party
#### 1 - создать бд с необходимыми таблицами и view
```
create database private_party

create table guests(id serial primary key, name varchar(100) not null, email varchar(100)not null, phone varchar(11) not null);

create view guest_name as (
	select name
	from guests
);
```
#### 2 - создать пользователей в БД и выдать им необходимые гранты
```
create user manager with password '1234';
create user guard with password '5678';

grant insert on guests to manager;
GRANT USAGE ON SEQUENCE guests_id_seq TO manager;

grant select on guest_name to manager;
grant select on guest_name to guard;
```
#### 3 - запрос добавления пользователя в таблицу guests
```
curl -H "Content-Type:application/json" -H "login: manager" -H "pass: 1234" -X POST --data '{"name":"ivan","email":"123@ya.ru","phone":"12345"}' "http://localhost:8080/add-guest"
```
#### 4 - запрос на просмотра списка гостей из view от пользователя manager и guard
```
curl -H "login: manager" -H "pass: 1234" http://localhost:8080/all-guests
curl -H "login: guard" -H "pass: 5678" http://localhost:8080/all-guests
```
