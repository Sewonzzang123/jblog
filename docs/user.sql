use jblog;
show tables;

DESC user;
DESC blog;
DESC category;
DESC post;

-- select
select * from user;
select * from blog;
select * from category;
select * from post;
-- join 
insert into user values('name2','테스터2','1234', now());

-- join 하는 순간 블로그가 만들어져야 하므로... 
insert into blog values('name1','name1의 블로그','/image/dooly.jpg');
update blog
set logo='/image/dooly.jpg'
where id='sewonzzang123';
-- 블로그가 만들어지는 순간 기본 category가 하나 있어야 한다.
insert into category values(null, '기본', '기본 카테고리', sysdate(), 'name1');


insert into post values(null, '글3','내용3',sysdate(),1);

select t2.no, t2.title, date_format(t2.reg_date,'%Y/%m/%d') as regDate
from category t1, post t2
where t1.no = t2.category_no
and t1.user_id='sewonzzang123'
and t1.no = 3
order by t2.reg_date desc;

                            
select a.no, count(*)                        
from(select t1.name, t1.desc, t1.no, ifnull(t2.no,0) as post_no
	from category t1
	left join  post t2
	on t1.no = t2.category_no
	where t1.user_id='name1')a
group by no;

select count(*)
from category t1, post t2
where t1.no = t2.category_no
and t1.no=1;


select t2.no as no
from user t1, category t2
where t1.id = t2.user_id
and t1.id='sewonzzang123';

select min(no)
from category
where user_id='name1';
select * from post;
select no, title, contents, reg_date-- date_format(max(reg_date),'%Y/%m/%d') as regDate
from post
where category_no = 1;
-- and no=1;

select no, title, contents, date_format(reg_date,'%Y/%m/%d') as regDate
from post
where category_no=1
-- and no=1 
order by reg_date desc
limit 0,1;

