### pdf 转图片 icepdf 以及 pdfbox 方式

##### 手动导入打印所需jar包
```
idea 需要调整内存大小，不然在tomcat 服务上运行时pdf 转图片会卡
```

##### 手动导入打印所需jar包
###### 1.core-renderer-0.2.jar
```
mvn install:install-file -Dfile=I:\core-renderer-0.2.jar -DgroupId=core-renderer -DartifactId=core-renderer -Dversion=0.2 -Dpackaging=jar
```
###### 2.jbarcode-0.2.8.jar
```
mvn install:install-file -Dfile=I:\jbarcode-0.2.8.jar -DgroupId=org.jbarcode -DartifactId=jbarcode -Dversion=0.2.8 -Dpackaging=jar
```
###### 3.lgl-itext-2.0.8.jar
```
mvn install:install-file -Dfile=I:\lgl-itext-2.0.8.jar -DgroupId=lgl-itext -DartifactId=lgl-itext -Dversion=2.0.8 -Dpackaging=jar
```
###### 4.ojdbc6.jar
```
mvn install:install-file -Dfile=D:/myJar/Oracle/ojdbc6.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3.0 -Dpackaging=jar
```
##### 手动导入打印所需jar包（pdf 转图片）
###### 1.icepdf-core.jar
```
mvn install:install-file -Dfile=D:\workspace\pdfToImage\src\icepdf-5.0.6\icepdf-core.jar -DgroupId=org.icepdf -DartifactId=icepdf-core -Dversion=5.0.6 -Dpackaging=jar
```
###### 2.icepdf-extra.jar
```
mvn install:install-file -Dfile=D:\workspace\pdfToImage\src\icepdf-5.0.6\icepdf-extra.jar -DgroupId=org.icepdf -DartifactId=icepdf-extra -Dversion=5.0.6 -Dpackaging=jar
```
###### 3.icepdf-pro.jar
```
mvn install:install-file -Dfile=D:\workspace\pdfToImage\src\icepdf-5.0.6\icepdf-pro.jar -DgroupId=org.icepdf -DartifactId=icepdf-pro -Dversion=5.0.6 -Dpackaging=jar
```
###### 4.icepdf-pro-intl.jar
```
mvn install:install-file -Dfile=D:\workspace\pdfToImage\src\icepdf-5.0.6\icepdf-pro-intl.jar -DgroupId=org.icepdf -DartifactId=icepdf-pro-intl -Dversion=5.0.6 -Dpackaging=jar
```

###### 5.icepdf-viewer.jar
```
mvn install:install-file -Dfile=D:\workspace\pdfToImage\src\icepdf-5.0.6\icepdf-viewer.jar -DgroupId=org.icepdf -DartifactId=icepdf-viewer -Dversion=5.0.6 -Dpackaging=jar
```
