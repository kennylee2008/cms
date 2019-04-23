@echo off
rem 本批处理文件用于登录mysql，并执行all.sql脚本
rem -uroot表示用root用户名登录；-pleadfar表示使用leadfar作为密码登录
rem 注意：-u和root以及-p和leadfar之间不能有空格！！
mysql -uroot -pleadfar < all.sql