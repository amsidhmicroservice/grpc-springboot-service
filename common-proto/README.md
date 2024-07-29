mvn clean install

mvn install:install-file -Dfile=C:\Users\amsid\IdeaProjects\grpc-springboot-service\common-proto\target\common-proto-1.0-SNAPSHOT.jar -DgroupId=com.amsidh.mvc -DartifactId=common-proto -Dversion=1.0.0 -Dpackaging=jar


Now you can use this as like below
<dependency>
    <groupId>com.amsidh.mvc</groupId>
    <artifactId>common-proto</artifactId>
    <version>1.0.0</version>
</dependency>
