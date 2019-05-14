create table if not exists busi_class
(
	id int auto_increment
		primary key,
	name varchar(50) null comment '班级名称',
	short_name varchar(20) null comment '班级简称',
	status int default 1 null comment '班级状态
1 正常
0 删除',
	head_master varchar(20) null comment '班主任',
	create_time timestamp null,
	update_time timestamp null comment '更新时间',
	remark varchar(2000) null
)
comment '班级数据表';

create table if not exists busi_student
(
	id bigint auto_increment
		primary key,
	name varchar(100) null,
	before_name varchar(50) null,
	age int null,
	class_id int null,
	address varchar(1024) null,
	remark varchar(4000) null,
	create_time timestamp null,
	update_time timestamp null,
	status int default 1 null,
	pic_url varchar(512) null comment '照片地址
'
)
comment '学生表';

create table if not exists busi_student_contact
(
	id bigint auto_increment
		primary key,
	contact_name varchar(500) null comment '联系人姓名',
	contact_type_name varchar(50) null comment '联系类型',
	contact_mobile varchar(20) null comment '联系电话',
	create_time timestamp null,
	update_time timestamp null,
	status int default 1 null,
	student_id bigint not null,
	remark varchar(2000) null
)
comment '学生联系信息';

create table if not exists busi_teacher
(
	id int auto_increment
		primary key,
	name varchar(200) null,
	career varchar(512) null,
	remark varchar(1024) null,
	mobile varchar(20) null,
	status int default 1 null
)
comment '教师数据表';

