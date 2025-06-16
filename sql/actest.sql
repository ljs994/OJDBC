-- �θ�(member) ���̺� ����

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
values (member_seq.nextval, '�����', 'ljs', '1234', 'ljs1234@naver.com', sysdate)

insert into member (mno, mname, id, pw, email, regidate)
values (member_seq.nextval, '����', 'kkw', '9876', 'kkw9876@naver.com', sysdate)

select * from member


drop table member -- member ���̺� ������

-- �ڽ�(board) ���̺� ����
-- ȸ�� Ż�� �� �� �Խñ۵� ���� ���� �ȴ�.
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