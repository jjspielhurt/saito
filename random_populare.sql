DROP TABLE searches CASCADE CONSTRAINTS
/
DROP TABLE downloads CASCADE CONSTRAINTS
/
--DROP TABLE genres CASCADE CONSTRAINTS
--/
--DROP TABLE books CASCADE CONSTRAINTS
--/
--DROP TABLE author CASCADE CONSTRAINTS
--/
--DROP TABLE users CASCADE CONSTRAINTS
--/
DROP TABLE ratings CASCADE CONSTRAINTS
/
DROP TABLE followers CASCADE CONSTRAINTS
/
DROP TABLE wishlist CASCADE CONSTRAINTS
/
--CREATE TABLE genres(
--  genre_id INTEGER NOT NULL PRIMARY KEY,
--  genre_name VARCHAR2(100),
--  parent_id INTEGER NOT NULL
--)
--/
--CREATE TABLE author(
--  author_id VARCHAR2(100) NOT NULL PRIMARY KEY,
--  name VARCHAR2(101) NOT NULL,
--  genre_id INTEGER REFERENCES genres(genre_id)
--)
--/
--CREATE TABLE books(
-- book_id INTEGER NOT NULL PRIMARY KEY,
-- genre_id INTEGER REFERENCES genres(genre_id) ,
-- author_id VARCHAR2(100) REFERENCES author(author_id),
-- title VARCHAR2(2500) NOT NULL,
-- publish_year INTEGER NOT NULL,
-- average_rating FLOAT(5) DEFAULT 2.5,
-- download_link VARCHAR2(100) NOT NULL
--)
--/
--CREATE TABLE users(
-- user_id INTEGER NOT NULL PRIMARY KEY,
-- username VARCHAR2(25) NOT NULL,
-- password VARCHAR2(15) NOT NULL
--)
--/
CREATE TABLE searches (
  search_id VARCHAR2(6) NOT NULL PRIMARY KEY,
  user_id INTEGER NOT NULL REFERENCES users(user_id),
  book_id INTEGER NOT NULL REFERENCES books(book_id)
)
/
CREATE TABLE downloads(
  download_id VARCHAR2(6) NOT NULL PRIMARY KEY,
  user_id INTEGER NOT NULL REFERENCES users(user_id),
  book_id INTEGER NOT NULL REFERENCES books(book_id)
)
/
CREATE TABLE followers(
  follow_id VARCHAR2(6) NOT NULL PRIMARY KEY,
  user_id INTEGER NOT NULL REFERENCES users(user_id),
  user_id_followed INTEGER NOT NULL REFERENCES users(user_id)
)
/
CREATE TABLE wishlist(
user_id INTEGER NOT NULL REFERENCES users(user_id),
book_id INTEGER NOT NULL REFERENCES books(book_id)
)
/
CREATE TABLE ratings(
review_id INTEGER NOT NULL PRIMARY KEY,
book_id INTEGER NOT NULL REFERENCES books(book_id),
rating INTEGER NOT NULL,
user_id INTEGER NOT NULL REFERENCES users(user_id),
review_date DATE,
review VARCHAR2(250)
)
/
SET SERVEROUTPUT ON;

DECLARE
  TYPE varr IS VARRAY(2000) OF varchar2(255);
  lista_prenume_fete varr := varr('Ababei','Acasandrei','Adascalitei','Afanasie','Agafitei','Agape','Aioanei','Alexandrescu','Alexandru','Alexe','Alexii','Amarghioalei','Ambroci','Andonesei','Andrei','Andrian','Andrici','Andronic','Andros','Anghelina','Anita','Antochi','Antonie','Apetrei','Apostol','Arhip','Arhire','Arteni','Arvinte','Asaftei','Asofiei','Aungurenci','Avadanei','Avram','Babei','Baciu','Baetu','Balan','Balica','Banu','Barbieru','Barzu','Bazgan','Bejan','Bejenaru','Belcescu','Belciuganu','Benchea','Bilan','Birsanu','Bivol','Bizu','Boca','Bodnar','Boistean','Borcan','Bordeianu','Botezatu','Bradea','Braescu','Budaca','Bulai','Bulbuc-aioanei','Burlacu','Burloiu','Bursuc','Butacu','Bute','Buza','Calancea','Calinescu','Capusneanu','Caraiman','Carbune','Carp','Catana','Catiru','Catonoiu','Cazacu','Cazamir','Cebere','Cehan','Cernescu','Chelaru','Chelmu','Chelmus','Chibici','Chicos','Chilaboc','Chile','Chiriac','Chirila','Chistol','Chitic','Chmilevski','Cimpoesu','Ciobanu','Ciobotaru','Ciocoiu','Ciofu','Ciornei','Citea','Ciucanu','Clatinici','Clim','Cobuz','Coca','Cojocariu','Cojocaru','Condurache','Corciu','Corduneanu','Corfu','Corneanu','Corodescu','Coseru','Cosnita','Catan','Covatariu','Cozma','Cozmiuc','Craciunas','Crainiceanu','Creanga','Cretu','Cristea','Crucerescu','Cumpata','Curca','Cusmuliuc','Damian','Damoc','Daneliuc','Daniel','Danila','Darie','Dascalescu','Dascalu','Diaconu','Dima','Dimache','Dinu','Dobos','Dochitei','Dochitoiu','Dodan','Dogaru','Domnaru','Dorneanu','Dragan','Dragoman','Dragomir','Dragomirescu','Duceac','Dudau','Durnea','Edu','Eduard','Eusebiu','Fedeles','Ferestraoaru','Filibiu','Filimon','Filip','Florescu','Folvaiter','Frumosu','Frunza','Galatanu','Gavrilita','Gavriliuc','Gavrilovici','Gherase','Gherca','Ghergu','Gherman','Ghibirdic','Giosanu','Gitlan','Giurgila','Glodeanu','Goldan','Gorgan','Grama','Grigore','Grigoriu','Grosu','Grozavu','Gurau','Haba','Harabula','Hardon','Harpa','Herdes','Herscovici','Hociung','Hodoreanu','Hostiuc','Huma','Hutanu','Huzum','Iacob','Iacobuta','Iancu','Ichim','Iftimesei','Ilie','Insuratelu','Ionesei','Ionesi','Ionita','Iordache','Iordache-tiroiu','Iordan','Iosub','Iovu','Irimia','Ivascu','Jecu','Jitariuc','Jitca','Joldescu','Juravle','Larion','Lates','Latu','Lazar','Leleu','Leon','Leonte','Leuciuc','Leustean','Luca','Lucaci','Lucasi','Luncasu','Lungeanu','Lungu','Lupascu','Lupu','Macariu','Macoveschi','Maftei','Maganu','Mangalagiu','Manolache','Manole','Marcu','Marinov','Martinas','Marton','Mataca','Matcovici','Matei','Maties','Matrana','Maxim','Mazareanu','Mazilu','Mazur','Melniciuc-puica','Micu','Mihaela','Mihai','Mihaila','Mihailescu','Mihalachi','Mihalcea','Mihociu','Milut','Minea','Minghel','Minuti','Miron','Mitan','Moisa','Moniry-abyaneh','Morarescu','Morosanu','Moscu','Motrescu','Motroi','Munteanu','Murarasu','Musca','Mutescu','Nastaca','Nechita','Neghina','Negrus','Negruser','Negrutu','Nemtoc','Netedu','Nica','Nicu','Oana','Olanuta','Olarasu','Olariu','Olaru','Onu','Opariuc','Oprea','Ostafe','Otrocol','Palihovici','Pantiru','Pantiruc','Paparuz','Pascaru','Patachi','Patras','Patriche','Perciun','Perju','Petcu','Pila','Pintilie','Piriu','Platon','Plugariu','Podaru','Poenariu','Pojar','Popa','Popescu','Popovici','Poputoaia','Postolache','Predoaia','Prisecaru','Procop','Prodan','Puiu','Purice','Rachieru','Razvan','Reut','Riscanu','Riza','Robu','Roman','Romanescu','Romaniuc','Rosca','Rusu','Samson','Sandu','Sandulache','Sava','Savescu','Schifirnet','Scortanu','Scurtu','Sfarghiu','Silitra','Simiganoschi','Simion','Simionescu','Simionesei','Simon','Sitaru','Sleghel','Sofian','Soficu','Sparhat','Spiridon','Stan','Stavarache','Stefan','Stefanita','Stingaciu','Stiufliuc','Stoian','Stoica','Stoleru','Stolniceanu','Stolnicu','Strainu','Strimtu','Suhani','Tabusca','Talif','Tanasa','Teclici','Teodorescu','Tesu','Tifrea','Timofte','Tincu','Tirpescu','Toader','Tofan','Toma','Toncu','Trifan','Tudosa','Tudose','Tuduri','Tuiu','Turcu','Ulinici','Unghianu','Ungureanu','Ursache','Ursachi','Urse','Ursu','Varlan','Varteniuc','Varvaroi','Vasilache','Vasiliu','Ventaniuc','Vicol','Vidru','Vinatoru','Vlad','Voaides','Vrabie','Vulpescu','Zamosteanu','Zazuleac','Adina','Alexandra','Alina','Ana','Anca','Anda','Andra','Andreea','Andreia','Antonia','Bianca','Camelia','Claudia','Codrina','Cristina','Daniela','Daria','Delia','Denisa','Diana','Ecaterina','Elena','Eleonora','Elisa','Ema','Emanuela','Emma','Gabriela','Georgiana','Ileana','Ilona','Ioana','Iolanda','Irina','Iulia','Iuliana','Larisa','Laura','Loredana','Madalina','Malina','Manuela','Maria','Mihaela','Mirela','Monica','Oana','Paula','Petruta','Raluca','Sabina','Sanziana','Simina','Simona','Stefana','Stefania','Tamara','Teodora','Theodora','Vasilica','Xena');
  lista_prenume_baieti varr := varr('Ababei','Acasandrei','Adascalitei','Afanasie','Agafitei','Agape','Aioanei','Alexandrescu','Alexandru','Alexe','Alexii','Amarghioalei','Ambroci','Andonesei','Andrei','Andrian','Andrici','Andronic','Andros','Anghelina','Anita','Antochi','Antonie','Apetrei','Apostol','Arhip','Arhire','Arteni','Arvinte','Asaftei','Asofiei','Aungurenci','Avadanei','Avram','Babei','Baciu','Baetu','Balan','Balica','Banu','Barbieru','Barzu','Bazgan','Bejan','Bejenaru','Belcescu','Belciuganu','Benchea','Bilan','Birsanu','Bivol','Bizu','Boca','Bodnar','Boistean','Borcan','Bordeianu','Botezatu','Bradea','Braescu','Budaca','Bulai','Bulbuc-aioanei','Burlacu','Burloiu','Bursuc','Butacu','Bute','Buza','Calancea','Calinescu','Capusneanu','Caraiman','Carbune','Carp','Catana','Catiru','Catonoiu','Cazacu','Cazamir','Cebere','Cehan','Cernescu','Chelaru','Chelmu','Chelmus','Chibici','Chicos','Chilaboc','Chile','Chiriac','Chirila','Chistol','Chitic','Chmilevski','Cimpoesu','Ciobanu','Ciobotaru','Ciocoiu','Ciofu','Ciornei','Citea','Ciucanu','Clatinici','Clim','Cobuz','Coca','Cojocariu','Cojocaru','Condurache','Corciu','Corduneanu','Corfu','Corneanu','Corodescu','Coseru','Cosnita','Catan','Covatariu','Cozma','Cozmiuc','Craciunas','Crainiceanu','Creanga','Cretu','Cristea','Crucerescu','Cumpata','Curca','Cusmuliuc','Damian','Damoc','Daneliuc','Daniel','Danila','Darie','Dascalescu','Dascalu','Diaconu','Dima','Dimache','Dinu','Dobos','Dochitei','Dochitoiu','Dodan','Dogaru','Domnaru','Dorneanu','Dragan','Dragoman','Dragomir','Dragomirescu','Duceac','Dudau','Durnea','Edu','Eduard','Eusebiu','Fedeles','Ferestraoaru','Filibiu','Filimon','Filip','Florescu','Folvaiter','Frumosu','Frunza','Galatanu','Gavrilita','Gavriliuc','Gavrilovici','Gherase','Gherca','Ghergu','Gherman','Ghibirdic','Giosanu','Gitlan','Giurgila','Glodeanu','Goldan','Gorgan','Grama','Grigore','Grigoriu','Grosu','Grozavu','Gurau','Haba','Harabula','Hardon','Harpa','Herdes','Herscovici','Hociung','Hodoreanu','Hostiuc','Huma','Hutanu','Huzum','Iacob','Iacobuta','Iancu','Ichim','Iftimesei','Ilie','Insuratelu','Ionesei','Ionesi','Ionita','Iordache','Iordache-tiroiu','Iordan','Iosub','Iovu','Irimia','Ivascu','Jecu','Jitariuc','Jitca','Joldescu','Juravle','Larion','Lates','Latu','Lazar','Leleu','Leon','Leonte','Leuciuc','Leustean','Luca','Lucaci','Lucasi','Luncasu','Lungeanu','Lungu','Lupascu','Lupu','Macariu','Macoveschi','Maftei','Maganu','Mangalagiu','Manolache','Manole','Marcu','Marinov','Martinas','Marton','Mataca','Matcovici','Matei','Maties','Matrana','Maxim','Mazareanu','Mazilu','Mazur','Melniciuc-puica','Micu','Mihaela','Mihai','Mihaila','Mihailescu','Mihalachi','Mihalcea','Mihociu','Milut','Minea','Minghel','Minuti','Miron','Mitan','Moisa','Moniry-abyaneh','Morarescu','Morosanu','Moscu','Motrescu','Motroi','Munteanu','Murarasu','Musca','Mutescu','Nastaca','Nechita','Neghina','Negrus','Negruser','Negrutu','Nemtoc','Netedu','Nica','Nicu','Oana','Olanuta','Olarasu','Olariu','Olaru','Onu','Opariuc','Oprea','Ostafe','Otrocol','Palihovici','Pantiru','Pantiruc','Paparuz','Pascaru','Patachi','Patras','Patriche','Perciun','Perju','Petcu','Pila','Pintilie','Piriu','Platon','Plugariu','Podaru','Poenariu','Pojar','Popa','Popescu','Popovici','Poputoaia','Postolache','Predoaia','Prisecaru','Procop','Prodan','Puiu','Purice','Rachieru','Razvan','Reut','Riscanu','Riza','Robu','Roman','Romanescu','Romaniuc','Rosca','Rusu','Samson','Sandu','Sandulache','Sava','Savescu','Schifirnet','Scortanu','Scurtu','Sfarghiu','Silitra','Simiganoschi','Simion','Simionescu','Simionesei','Simon','Sitaru','Sleghel','Sofian','Soficu','Sparhat','Spiridon','Stan','Stavarache','Stefan','Stefanita','Stingaciu','Stiufliuc','Stoian','Stoica','Stoleru','Stolniceanu','Stolnicu','Strainu','Strimtu','Suhani','Tabusca','Talif','Tanasa','Teclici','Teodorescu','Tesu','Tifrea','Timofte','Tincu','Tirpescu','Toader','Tofan','Toma','Toncu','Trifan','Tudosa','Tudose','Tuduri','Tuiu','Turcu','Ulinici','Unghianu','Ungureanu','Ursache','Ursachi','Urse','Ursu','Varlan','Varteniuc','Varvaroi','Vasilache','Vasiliu','Ventaniuc','Vicol','Vidru','Vinatoru','Vlad','Voaides','Vrabie','Vulpescu','Zamosteanu','Zazuleac','Adrian','Alex','Alexandru','Alin','Andreas','Andrei','Aurelian','Beniamin','Bogdan','Camil','Catalin','Cezar','Ciprian','Claudiu','Codrin','Constantin','Corneliu','Cosmin','Costel','Cristian','Damian','Dan','Daniel','Danut','Darius','Denise','Dimitrie','Dorian','Dorin','Dragos','Dumitru','Eduard','Elvis','Emil','Ervin','Eugen','Eusebiu','Fabian','Filip','Florian','Florin','Gabriel','George','Gheorghe','Giani','Giulio','Iaroslav','Ilie','Ioan','Ion','Ionel','Ionut','Iosif','Irinel','Iulian','Iustin','Laurentiu','Liviu','Lucian','Marian','Marius','Matei','Mihai','Mihail','Nicolae','Nicu','Nicusor','Octavian','Ovidiu','Paul','Petru','Petrut','Radu','Rares','Razvan','Richard','Robert','Roland','Rolland','Romanescu','Sabin','Samuel','Sebastian','Sergiu','Silviu','Stefan','Teodor','Teofil','Theodor','Tudor','Vadim','Valentin','Valeriu','Vasile','Victor','Vlad','Vladimir','Vladut');
      
  v_nume VARCHAR2(255);
  v_username VARCHAR2(255);
  v_prenume1 VARCHAR2(255);
  v_prenume2 VARCHAR2(255);
  v_password VARCHAR2(15);
  v_user_id int;
  v_book_id int;
  v_temp int;
  v_num_books int;
  v_num_users int;
  v_temp1 int;
  v_temp2 int;
  v_date date;
  v_review VARCHAR2(250);
  v_rating int;
  v_count1 int;
  v_count2 int;
BEGIN
--  DBMS_OUTPUT.PUT_LINE('Inserarea a 100 000 useri...');
--  FOR v_i IN 1..10000 LOOP
--      IF (DBMS_RANDOM.VALUE(0,100)<50) THEN 
--        v_prenume1 := lista_prenume_fete(TRUNC(DBMS_RANDOM.VALUE(0,lista_prenume_fete.count))+1);
--           LOOP
--              v_prenume2 := lista_prenume_fete(TRUNC(DBMS_RANDOM.VALUE(0,lista_prenume_fete.count))+1);
--              exit when v_prenume1<>v_prenume2;
--           END LOOP;
--      ELSE
--        v_prenume1 := lista_prenume_baieti(TRUNC(DBMS_RANDOM.VALUE(0,lista_prenume_baieti.count))+1);
--        LOOP
--          v_prenume2 := lista_prenume_baieti(TRUNC(DBMS_RANDOM.VALUE(0,lista_prenume_baieti.count))+1);
--          exit when v_prenume1<>v_prenume2;
--        END LOOP;       
--      END IF;
--      v_temp1:=0;
--      LOOP
--        v_temp1:=v_temp1+1;
--      IF LENGTH(v_prenume1|| v_prenume2) <= 15 THEN
--        v_username := LOWER(v_prenume1) || LOWER(v_prenume2)||v_temp1;
--      else
--        v_username:=v_prenume1||v_temp1;
--      END IF;
--      select count(*) into v_temp2 from users where username like v_username;
--        exit when v_temp2=0;
--      END LOOP;
--      
--    LOOP
--      v_user_id:=FLOOR(DBMS_RANDOM.VALUE(0,100000));
--      select count(*) into v_temp2 from users where user_id=v_user_id;
--    exit when v_temp2=0;
--    END LOOP;
--    
--    v_password:=(DBMS_RANDOM.string('x',15));
--    
--    insert into users values(v_user_id,v_username,v_password);
--  END LOOP;
--  END;
--/
--FOLLOWERS

select count(*) into v_num_users from users;
select count(*) into v_num_books from books;
DBMS_OUTPUT.PUT_LINE(v_num_users);
DBMS_OUTPUT.PUT_LINE(v_num_books);
FOR v_i IN 1..200000 LOOP
 
       LOOP
          v_temp1 :=  TRUNC(DBMS_RANDOM.VALUE(0,v_num_users-1))+1;
          v_temp2 :=  TRUNC(DBMS_RANDOM.VALUE(0,v_num_users-1))+1;
          select count(*) into v_count1 from users where user_id=v_temp1;
          select count(*) into v_count2 from users where user_id=v_temp2;
          EXIT WHEN v_temp1<>v_temp2 and v_count1!=0 and v_count2!=0;
       END LOOP;
       insert into followers values(v_i,v_temp1,v_temp2);
END LOOP;
--WISHLIST
FOR v_i IN 1..200000 LOOP
 
       LOOP
          v_temp1 :=  TRUNC(DBMS_RANDOM.VALUE(0,v_num_users-1))+1;
          v_temp2 :=  TRUNC(DBMS_RANDOM.VALUE(0,v_num_books-1))+1;
          
          select count(*) into v_count1 from users where user_id=v_temp1;
          select count(*) into v_count2 from books where book_id=v_temp2;
          EXIT WHEN v_count1!=0 and v_count2!=0;
          
       END LOOP;
       insert into wishlist values(v_temp1,v_temp2);
END LOOP;       
--DOWNLOADS
FOR v_i IN 1..200000 LOOP
 
       LOOP
          v_temp1 :=  TRUNC(DBMS_RANDOM.VALUE(0,v_num_users-1))+1;
          v_temp2 :=  TRUNC(DBMS_RANDOM.VALUE(0,v_num_books-1))+1;
          select count(*) into v_count1 from users where user_id=v_temp1;
          select count(*) into v_count2 from books where book_id=v_temp2;
          EXIT WHEN v_count1!=0 and v_count2!=0;
       END LOOP;
       insert into downloads values(v_i,v_temp1,v_temp2);
END LOOP;       
--SEARCHES       
FOR v_i IN 1..200000 LOOP
 
       LOOP
          v_temp1 :=  TRUNC(DBMS_RANDOM.VALUE(0,v_num_users-1))+1;
          v_temp2 :=  TRUNC(DBMS_RANDOM.VALUE(0,v_num_books-1))+1;
          
          select count(*) into v_count1 from users where user_id=v_temp1;
          select count(*) into v_count2 from books where book_id=v_temp2;
          EXIT WHEN v_count1!=0 and v_count2!=0;
       END LOOP;
       insert into searches values(v_i,v_temp1,v_temp2);
END LOOP;       
--RATINGS
FOR v_i IN 1..200000 LOOP
       LOOP
          v_temp1 :=  TRUNC(DBMS_RANDOM.VALUE(0,v_num_users-1))+1;
          v_temp2 :=  TRUNC(DBMS_RANDOM.VALUE(0,v_num_books-1))+1;

          select count(*) into v_count1 from users where user_id=v_temp1;
          select count(*) into v_count2 from books where book_id=v_temp2;
          EXIT WHEN v_count1!=0 and v_count2!=0;
       END LOOP;
       v_rating:=FLOOR(DBMS_RANDOM.VALUE(0,4))+1;
       v_date:=(sysdate-TRUNC(DBMS_RANDOM.VALUE(0,100000)));
       v_review:=(DBMS_RANDOM.string('a',250));
       insert into ratings values(v_i,v_temp2,v_rating,v_temp1,v_date,v_review);
END LOOP;       
END;
/
select * from ratings;
select * from followers;
select * from wishlist;
select * from downloads;
select * from searches;
