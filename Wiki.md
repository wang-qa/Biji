### **OkHttp** 
使用4.x版本会报错
语言由java改为kotlin，包名保持不变，通过 japicmp实现二进制兼容，在不更改.kt和.java文件的基础上，从3.x迁移到4.X
[报错原因](https://blog.csdn.net/u014543264/article/details/102652637) 

### **目录结构**
1. 类与类（网络）之间object使用 `dto` 传输
2. 类与类（数据库）之间object使用 `model` 传输

### **HTML**
`<input>` 用 `th:value`方式赋值（回显）
`<textarea>` 用 `th:value`方式赋值（回显）

### **分页**
```sql
select * from QUESTION limit 10, 5; -- 从索引0 开始  每次显示5条
```
在有索引情况下`count(1)`效率高于`count(*)`