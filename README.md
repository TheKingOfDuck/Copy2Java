
# 这是一份垃圾代码,不推荐使用。

# Copy2Java
一键生成Java代码的burp插件/Generate Java script for fuzzing in Burp。

Download:[releases](https://github.com/TheKingOfDuck/Copy2Java/releases/download/1.1/copy2Java-1.1.jar)

More:[在Burp中一键生成Java代码](https://blog.gzsec.org/post/Copy2Java/)

### 使用


1.加载插件：


![image-20200413021026557](https://thekingofduck.github.io/post-images/copy2java//image-20200413021026557.png)

2.在burp的repeater中点击Copy2Java菜单：


![image-20200413021159116](https://thekingofduck.github.io/post-images/copy2java//image-20200413021159116.png)

3.设置是否开启代理【可选】：


![image-20200413021244692](https://thekingofduck.github.io/post-images/copy2java//image-20200413021244692.png)


默认是不开代理的，没需要的话这部可以跳过。

4.点击步骤3中弹出的窗口中的Copy to clipboard按钮复制代码并粘贴到ide:

添加插件lib目录下的依赖包

![image-20200413021640508](https://thekingofduck.github.io/post-images/copy2java//image-20200413021640508.png)

完成

![image-20200413022210153](https://thekingofduck.github.io/post-images/copy2java//image-20200413022210153.png)



如何高效的写一个web相关漏洞的利用工具？

1.新建mvn项目，并将生成的代码复制进来。

2.修改传入的参数，mvn package快速打包。

比用Python写慢？不存在的！



![image-20200413022944759](https://thekingofduck.github.io/post-images/copy2java//image-20200413022944759.png)
