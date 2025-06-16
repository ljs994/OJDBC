-- 부모(member) 테이블 생성

create table member (
	mno number(5) primary key,
	mname nvarchar2(10) not null,
	id nvarchar2(10) unique,
	pw nvarchar2(20) not null,
	email nvarchar2(20) not null,
	regidate date default sysdate not null
)

create sequence member_seq increment by 1 start with 1 nocycle nocache

insert into member (mno, mname, id, pw, email, regidate)
values (member_seq.nextval, '이재상', 'ljs', '1234', 'ljs1234@naver.com', sysdate)

insert into member (mno, mname, id, pw, email, regidate)
values (member_seq.nextval, '김기원', 'kkw', '9876', 'kkw9876@naver.com', sysdate)

select * from member


drop table member -- member 테이블 삭제용

-- 자식(board) 테이블 생성
-- 회원 탈퇴를 할 때 게시글도 같이 삭제 된다.
create table board(
	mno number(5) not null,
	bno number(5) primary key,
	title nvarchar2(20) not null,
	content nvarchar2(50) not null,
	writedate date default sysdate not null,
	constraint fk_board_member foreign key (mno)
		references member(mno) on delete cascade
)
create sequence board_seq increment by 1 start with 1 nocycle nocache

drop table board