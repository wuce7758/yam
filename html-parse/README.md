yam
===

通过简单的配置，提取html页面中的指定内容，并转换为java对象，是垂直领域爬虫的很简单实用的辅助工具。 
例如，以下为解析某应用商店页面的配置，目的是爬取app的名称、包名、分类、标签、图片等信息，并将对应信息保存到map中
（你也可以定义一个对象，直接将信息解析为一个对象）
```
<?xml version="1.0" encoding="UTF-8"?>
<html-beans>
	<bean-info>
		<tag name='app-info-map'/>
		<class name='map'/>
		<method name='name' expression='class:app-info->class:title->text' ignore-error='false'/>
		<method name='packageName' expression='class:app-info->class:download-wp->tag:a->attr:data-pn' ignore-error='false'/>
		<method name='categoryName' expression='class:second->tag:span->text' ignore-error='true'/>
		<method name='tag' expression='class:app-info->class:tagline->text' />
		<method name='picList' expression='class:j-scrollbar-wrap->tag:img->attr:src' result-type='list' />
	</bean-info>
</html-beans>
```
通过以下代码即可将一个url中对应的信息提取出来：
```
    // 读取配置文件
		InputStream inputStream = XmlBeanConfigReaderTest.class
				.getResourceAsStream("/com/yam/base/html/parse/apps.xml");
		XmlBeanConfigReader configReader = new XmlBeanConfigReader(inputStream);
		
		// 获取指定配置
		ParseBeanConfig beanConfig = configReader.getConfig("app-info-map");
		HtmlBeanParser beanParser = new HtmlBeanParser(beanConfig);
		try {
      // 解析url对应信息
			Object result = beanParser.parseUrl("http://www.wandoujia.com/apps/com.tencent.mobileqq", 3);
			TestCase.assertNotNull(result);
		
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
```

解析到的信息如下：
```
{picList=[http://img.wdjimg.com/mms/screenshot/1/c1/0bba124cf313a3993f16ed3213033c11_320_535.jpeg, http://img.wdjimg.com/mms/screenshot/0/5c/b0591b5fc95d0885208d14ca154135c0_320_535.jpeg, http://img.wdjimg.com/mms/screenshot/b/2f/81a03f8c4b9619d94e40254b823ac2fb_320_535.jpeg, http://img.wdjimg.com/mms/screenshot/3/b3/574e81bbbdc26c3c9c318822534d0b33_320_535.jpeg], name=QQ, packageName=com.tencent.mobileqq, tag=乐在沟通聊天欢乐, categoryName=聊天社交}
```

