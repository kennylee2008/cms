/*
����Channel��Article��һ���м��������¼��ƪ���������ĸ�Ƶ����Щ��Ϣ
���У�channelId��articleId���������������ظ���
*/
drop table if exists t_channel_article;
create table t_channel_article(
	channelId integer,
	articleId integer,
	unique key channel_article (channelId,articleId)
);
