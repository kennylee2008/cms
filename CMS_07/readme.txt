定义一个LoginFilter，并配置它匹配/backend/*，即在/backend/下面的所有请求，均经过这个LoginFilter

url-pattern的定义只有三种形式：
1、精确匹配
如：
/backend/main.jsp
/backend/left.jsp
/backend/ArticleServlet
等等
2、目录匹配
如：
/backend/*
3、扩展名匹配
如：
*.jsp

url-pattern不支持以下方式，如：
/backend/*.jsp 
使用这个表达式的意图是匹配/backend/下面的所有的jsp，url-pattern不支持！
------------------------
利用正则表达式，对/backend/下面的请求，进行二次过滤，以便只对Servlet和JSP进行过滤