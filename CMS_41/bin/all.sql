-- ���CMS���ݿ��Ѿ����ڣ���ɾ��
drop database if exists cms;

-- �������ݿ�CMS
create database cms;

-- ʹ��CMS���ݿ�
use cms;

-- ����admin.sql�Ƚű�
source admin.sql;
source article.sql;
source channel.sql;
source channel_article.sql;
source comment.sql;
source member.sql;
source topic.sql;