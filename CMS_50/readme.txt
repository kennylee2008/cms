�ṩ���¹��ܵĲο�ʵ�֣�

�����¹�����ʵ���ļ��ϴ���
	- ������һ��Attachmentʵ���࣬������¼���¶�Ӧ�ĸ�����Ϣ

Ϊ�˴���multipart�������͵����ݣ���дMultipartRequestWrapper�࣬��ԭʼ��
request������а�װ����װ��Ŀ���ǣ�
	- ��ʹ��multipart���͵����󣬵���request.getParameter(String)��Ȼ����
	  �����ֵ
	- ����ϴ������ļ�����ͨ������request.getParameterMap(String)�����Եõ�
	  һ��Attachment[]���͵Ķ���
	- ��ο���BaseServlet��MultipartRequestWrapper��
		* ��BaseServlet�ж�request������MultipartRequestWrapper���а�װ
		* ��ArticleServlet�д�request�������ó��ϴ������������ļ�����Ϣ
	- ������apache��commons-fileupload.jar��commons-io.jar������