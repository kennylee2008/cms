第二步：

用户从登录页面向系统提交用户名、密码和验证码
系统判断验证码是否正确
系统判断用户名是否存在，判断密码是否正确

如果验证码不正确，返回登录页面，显示提示信息“验证码不正确”
如果用户名不存在，返回登录页面，显示提示信息“用户名不存在”
如果密码不正确，返回登录页面，显示提示信息“密码不正确”


------------
访问数据库：

注册数据库驱动
获取Connection对象(url,用户名,密码)
通过Connection对象和一条SQL语句创建Statement/PreparedStatement对象
执行SQL语句
关闭ResultSet
关闭Statement
关闭Connection