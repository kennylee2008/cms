-- 如果CMS数据库已经存在，则删除
drop database if exists cms;

-- 创建数据库CMS
create database cms;

-- 使用CMS数据库
use cms;

-- 调用admin.sql等脚本
source admin.sql;
source article.sql;
source channel.sql;
source channel_article.sql;
source comment.sql;
source member.sql;
source topic.sql;