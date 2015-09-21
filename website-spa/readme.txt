Set up running environment:
1.install mysql-5.1.51-win32.msi by default and set user/pwd to 'root'
2.install Navicat_for_MySQL_10.0.11.0.exe, connet to MySQL and create db 'data', import data.sql
3.install apache-tomcat-7.0.54.exe
4.put backend.war/front.war in Tomcat/webapps and restart Tomcat
5.access http://localhost/front

Set up development environment:
1.install mysql-5.1.51-win32.msi by default and set user/pwd to 'root'
2.install Navicat_for_MySQL_10.0.11.0.exe, connet to MySQL and create db 'data', import data.sql
3.install apache-tomcat-7.0.54.exe
4.install eclipse and set tomcat server
5.import source code for front/backend
6.set backend project build path
7.Run as Run on Server for backend project
8.Debug backend with open http://localhost:8080/backend/user_info.html
9.Run as Run on Server for front project
10.access http://localhost/front