twitter-connect
===============

Twitter api'sini kullanarak sitenize kullanıcıları kayıt veya login yapabilirsiniz.


```xml
  <properties>
		<spring.version>3.0.5.RELEASE</spring.version>
	</properties>
	
	<dependencies>
   <dependency>
       <groupId>org.twitter4j</groupId>
       <artifactId>twitter4j-core</artifactId>
       <version>[4.0,)</version>
   </dependency>
   
   <!-- Spring 3 dependencies -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-core</artifactId>
			<version>${spring.version}</version>
		</dependency>
 
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<version>${spring.version}</version>
		</dependency>
 
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>${spring.version}</version>
		</dependency>
		</dependencies>
 
```
