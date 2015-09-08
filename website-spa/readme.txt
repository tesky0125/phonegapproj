Set up environment:
1.install mysql-5.1.51-win32.msi by default and set user/pwd to 'root'
2.install Navicat_for_MySQL_10.0.11.0.exe, connet to MySQL and create db 'data', import data.sql
3.install apache-tomcat-7.0.54.exe
3.put backend.war in Tomcat/webapps and restart Tomcat
4.install httpd-2.2.25-win32-x86-openssl-0.9.8y.msi
5.extra front.rar and set alias for front in httpd.conf
6.start apache server and access http://localhost/front