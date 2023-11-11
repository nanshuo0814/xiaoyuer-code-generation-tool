- 代码自动生成器(基于mybatis生成基本的crud增删改查,用于springboot项目等等)

JavaMain模块
执行生成JavaDemo模块(是个已经生成好的例子)
执行JavaMainApplication时,默认是生成在JavaDemo模块里的(可以自行查看修改配置文件继续修改application.properties)
生成前可以先清空JavaDemo里的内容,方便查看
只需要删除生成的文件即可(如下所示的包)
-- controller
-- mapper
--- mapper.xml
-- service
--- impl
-- enums
-- entity
-- utils
-- exception
-- ...

- db目录
有一个xiaoyuer_code_demo.sql,用于测试使用的(包括创建表和数据,需要自己创建数据库),执行就行了

- 项目的技术
-- JavaDemo模块
<springboot.version>2.2.6.RELEASE</springboot.version>
<mybatis.version>1.3.2</mybatis.version>
<mysql.version>8.0.33</mysql.version>

-- JavaMain模块(没有使用到springboot技术,但是生成的代码适用于springboot项目)
<mysql.version>8.0.29</mysql.version>
<apache.version>3.4</apache.version>
<logback.version>1.2.10</logback.version>
<sl4fj.version>1.7.7</sl4fj.version>
<fastjson.version>1.1.42</fastjson.version>


--- xiaoyuer (2023/10/28 22:22)
