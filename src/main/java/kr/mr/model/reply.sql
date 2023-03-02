create table tbl_reply(
	rno int auto_increment primary key,
    bid int,
    reply_contents varchar(1000) not null,
    replyer varchar(20) not null,
    reply_date datetime default now(),
    update_date datetime default now(),
    
    foreign key(bid) references tbl_board(bid)
);

insert into tbl_reply(bid, reply_contents, replyer)
values(10, '댓글 테스트 입니다', '홍길동');

select * from tbl_reply;
drop table tbl_replay;