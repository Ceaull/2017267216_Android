# 活动的启动模式

1. 活动有四种启动模式，在AndroidManifest.xml中通过<activity>标签指定，如图所示

![images](https://github.com/Ceaull/2017267216_android/blob/master/project4/images/1.PNG)

2. 在Hello1和Hello2类中设置实例计数，方便观察四种活动的区别，如图所示

![images](https://github.com/Ceaull/2017267216_android/blob/master/project4/images/2.PNG)

![images](https://github.com/Ceaull/2017267216_android/blob/master/project4/images/2.1.PNG)

3.standard模式，系统不会在乎这个活动是否已经在返回栈中，每次启动都会创建该活动的一个新的实例，如图所示开始运行时创建了第一个Hello1对象，
  点击按钮继续跳转到Hello1时创建了第二个Hello1对象，点击按钮跳转到Hello2时创建了第一个Hello2对象。

![images](https://github.com/Ceaull/2017267216_android/blob/master/project4/images/3.1.PNG)

4.singleTop模式，在启动活动时如果发现返回栈的栈顶已经是该活动，则认为可以直接使用它，不会再创建新的活动实例。如图所示开始运行时创建了第一个Hello1对象，
  此时栈顶为Hello1，点击按钮继续跳转到Hello1时不会创建新的实例，继续使用第一个Hello1实例。
  
![images](https://github.com/Ceaull/2017267216_android/blob/master/project4/images/4.PNG)

  若启动活动时栈顶不是该活动，则创建该活动的一个新的实例。如图所示开始运行时创建了第一个Hello1对象，此时栈顶为Hello1，点击按钮继续跳转到Hello2时创建了
  第一个Hello2对象，此时栈顶为Hello2，再次点击按钮跳转到Hello1时，此时栈顶不是Hello1，则创建了第二个Hello1对象。
  
![images](https://github.com/Ceaull/2017267216_android/blob/master/project4/images/4.1.PNG)

5.singleTask模式，每次启动该活动时系统首先会在返回栈中检查是否存在该活动的实例，如果发现已经存在则直接使用该实例，并把在这个活动之上的所有活动统统出栈，
  如果没有发现就会创建一个新的活动实例。如图所示开始运行时创建了第一个Hello1对象，点击按钮继续跳转到Hello2时创建了第一个Hello2实例，再次点击按钮跳转到
  Hello1时，此时系统存在了Hello1的第一个实例，因此直接使用，并把其以上的所有活动（Hello2的第一个实例）出栈，如图Hello2的第一个实例被销毁了。
  
![images](https://github.com/Ceaull/2017267216_android/blob/master/project4/images/5.PNG)

6.singleInstance模式，在整个安卓系统中就只有唯一的实例，如图所示开始运行时创建了第一个Hello1实例，点击按钮跳转到Hello2时创建了第一个Hello2实例，再点击
  按钮跳转到Hello1时，还是使用之前创建的第一个Hello1实例，再点击按钮跳转到Hello2时，使用的也还是第一个Hello2实例。
  
![images](https://github.com/Ceaull/2017267216_android/blob/master/project4/images/6.PNG)
