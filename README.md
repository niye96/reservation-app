# 简介

+ 医院预约后台管理系统后端;

+ 使用了spring boot + mybatis框架进行开发;

+ ide使用了IntelliJ IDEA，jdk为1.8;

# 使用说明

1. 使用maven构建项目

2. 导入项目根目录下的reservation.sql进数据库

3. 修改manager模块下application.yml配置文件中的数据库账号密码以及url

4. 如果有fastdfs文件服务器以及redis，修改filesystem模块中的tracker的ip以及redis所在服务器的ip和权限

5. 由于配置了拦截器，需要在头部中传入token与redis中的数据相比较，因此刚开始需要把InterceptorConfig中的@Configuration注解删除

6. 执行ManagerApplication的main函数

7. 访问[swagger](http://localhost:8080/swagger-ui.html#/)，进行接口的测试使用
