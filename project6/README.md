# 实现增删查改

##运行结果显示
1. 点击运行后开始页面

![images](https://github.com/Ceaull/2017267216_android/blob/master/project6/images/1.1.PNG)

2. 添加页面

![images](https://github.com/Ceaull/2017267216_android/blob/master/project6/images/1.2.PNG)

![images](https://github.com/Ceaull/2017267216_android/blob/master/project6/images/1.3.PNG)

![images](https://github.com/Ceaull/2017267216_android/blob/master/project6/images/1.4.PNG)

3.左键长按内容，会出现删除和更新
  
![images](https://github.com/Ceaull/2017267216_android/blob/master/project6/images/1.5.PNG)

4.更新页面

![images](https://github.com/Ceaull/2017267216_android/blob/master/project6/images/1.6.PNG)

![images](https://github.com/Ceaull/2017267216_android/blob/master/project6/images/1.7.PNG)

5.删除页面
  
![images](https://github.com/Ceaull/2017267216_android/blob/master/project6/images/1.8.PNG)

##代码显示
1.界面设计，activity_main.xml
```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <ListView
            android:id="@android:id/list"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1" >

        </ListView>

        <TextView
            android:id="@android:id/empty"
            android:layout_width="match_parent"
            android:layout_height="0dp"
            android:layout_weight="1"
            android:text="空白"
            android:gravity="center"/>

        <Button
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:onClick="add"
            android:text="添加备忘录" />
    </LinearLayout>

</androidx.constraintlayout.widget.ConstraintLayout>
```
