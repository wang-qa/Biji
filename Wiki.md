### **OkHttp** 
使用4.x版本会报错
语言由java改为kotlin，包名保持不变，通过 japicmp实现二进制兼容，在不更改.kt和.java文件的基础上，从3.x迁移到4.X
[报错原因](https://blog.csdn.net/u014543264/article/details/102652637) 

### **目录结构**
1. 类与类（网络）之间object使用 `dto` 传输
2. 类与类（数据库）之间object使用 `model` 传输