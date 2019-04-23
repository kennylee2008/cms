提供如下功能的参考实现：

在文章管理中实现文件上传！
	- 增加了一个Attachment实体类，用来记录文章对应的附件信息

为了处理multipart编码类型的数据，编写MultipartRequestWrapper类，对原始的
request对象进行包装！包装的目的是：
	- 即使是multipart类型的请求，调用request.getParameter(String)仍然可以
	  获得其值
	- 如果上传的是文件，则通过调用request.getParameterMap(String)，可以得到
	  一个Attachment[]类型的对象
	- 请参考【BaseServlet和MultipartRequestWrapper】
		* 在BaseServlet中对request对象用MultipartRequestWrapper进行包装
		* 在ArticleServlet中从request对象中拿出上传到服务器的文件的信息
	- 需引入apache的commons-fileupload.jar和commons-io.jar两个包