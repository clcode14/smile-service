# Smile 后端部署步骤
1. 在IDEA中利用maven打包，或者直接用cmd maven命令进行打包，分别打包smile_server、smile_admin、smile_app_server项目（打成jar包）
2. 将项目上传至服务器，如果为linux服务器，可以执行如下sh脚本来启动项目
```
cd `dirname $0`
nohup /usr/java/jdk1.8.0_121/bin/java -jar smile-0.0.1-SNAPSHOT.jar  > mylog1.txt 2>&1 &
```
3.数据库为smile.sql，该项目完全启动需要占用服务器3个端口，如果要停止程序用命令 ps -ef | grep smile 获取程序进程之后执行kill -9 xxxx杀死进程即可
