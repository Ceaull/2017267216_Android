# 在AS当中导入别人的项目
1. 从github上下载郭霖的代码
2. 将HelloWorld程序导入AS当中
3. 打开build.gradle文件，更改gradle版本并添加goole（），如图
4. 打开gradle-wrapper.properties文件，更改版本，如图
5. 启动运行，运行成功
- 总结：
-当出现下图的错误时，原因是build.gradle中allprojects的函数内没有加goole()
