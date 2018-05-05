   # Dubbo入门 #

   Dubbo是阿里巴巴在2011年开源的分布式服务框架，是SOA服务法治理方案的核心框架，每天为阿里巴巴内部的2000多个服务提供3000000000多次访问量的支持(目前，在阿里巴巴内部使用的是新一代的RPC框架HSF，全称High Speed Framework,也被称为"好舒服")，并在国内被很多大公司广泛应用于各系统中。Dubbo官方曾停止维护Dubbo很长一段时间（虽然目前有重新开始维护，不过还是还有很长的路要走）,但是国内也有很多热心的团队在更新和维护它，比如当当在Dubbo的基础上开源了Dubbox等。Dubbo的结构如图


![](https://i.imgur.com/z2QT6Rw.jpg)




  Dubbo 主要提供了三方面的功能：**远程接口调用:负载均衡和容错：自动服务注册和发现**.我们可以非常容易地通过Dubbo来构建分布式服务。下面我们就以Dubbo官方提供的demo开始

1.准备环境

  首先，安装zookeeper。zookeeper使用java程序编写的，运行在java环境上，因此需要安装好java环境，jdk1.7及以上。从官网下载 [http://zookeeper.apache.org/](http://zookeeper.apache.org/) 解压后有bin和conf这两个目录

  
- bin 是zookeeper的可执行脚本目录，包括zookeeper的服务进程，客户端脚本。其中,.sh是linux环境下的脚本，
.cmd是Windows环境下的脚本。

- conf是配置文件目录，其中，zoo_sample.cfg是样列配置文件，需要将其修改为自己的名称，一般为zoo.cfg

2.开始编写
官方提供的 [dubbo-demo](https://github.com/apache/incubator-dubbo/tree/master/dubbo-demo)

官方的注册中心不是zookeeper我们需要改成zookeeper.

demo的结构图

![](https://i.imgur.com/FTthjeS.png)

我们重点来看xml文件

**提供方的xml文件的配置**

![](https://i.imgur.com/UBRCmbY.png)


**消费放的xml文件配置**

![](https://i.imgur.com/sg1azpG.png)

需要将注册中该为zookeeper，当然我们本地必须先正常启动zookeeper服务。

如果你的jar包没有正常引入的话，运行项目的时候，就会报dubbo.xsd文件找不到

入口部分，只需要写个ApplicationContext载入这个xml配置文件即可

 ```
public class Provider {

    public static void main(String[] args) throws Exception {
        //Prevent to get IPV6 address,this way only work in debug mode
        //But you can pass use -Djava.net.preferIPv4Stack=true,then it work well whether in debug mode or not
        System.setProperty("java.net.preferIPv4Stack", "true");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-demo-provider.xml"});
        context.start();

        System.in.read(); // press any key to exit
    }

}

```
<br/>
修改后的xml文件

**提供方的XML文件**

![](https://i.imgur.com/zvqpO7g.png)

消费放的XML文件

![](https://i.imgur.com/wQeBLMr.png)


我们先启动提供方的实体类

 **启动提供方**

![](https://i.imgur.com/kBApBlB.png)

 **启动服务方**

![](https://i.imgur.com/jwDxWcE.png)

这样就正常启动项目了，这里只是简单的介绍了Dubbo的入门，后面会逐渐深入和研究源码。

 **[项目地址](https://github.com/coder-PengWenHao/Spring-Boot-demos/tree/master/dubbo/dubbo-demo)**






















