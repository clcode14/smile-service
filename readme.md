# Smile 后端部署步骤
1. 在IDEA中利用maven打包，或者直接用cmd maven命令进行打包，分别打包smile_server、smile_admin、smile_app_server项目（打成jar包）
```
mvn clean package -Dmaven.test.skip=true
```
2. 将项目上传至服务器，如果为linux服务器，可以执行如下sh脚本来启动项目
```
nohup java -jar server-0.0.1-SNAPSHOT.jar  > log_server.txt 2>&1 &
nohup java -jar admin-0.0.1-SNAPSHOT.jar  > log_admin.txt 2>&1 &
nohup java -jar appserver-0.0.1-SNAPSHOT.jar  > log_appserver.txt 2>&1 &
```
3.数据库为smile.sql，该项目完全启动需要占用服务器3个端口，如果要停止程序用命令 ps -ef | grep java 获取程序进程之后执行kill -9 xxxx杀死进程即可
