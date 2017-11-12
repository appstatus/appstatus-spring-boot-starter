# appstatus-spring-boot-demo


## java application

    java -jar appstatus-spring-boot-demo.war

## web application

    cp appstatus-spring-boot-demo.war /<tomcat>/webapps/appstatus-spring-boot-demo.war

## container

    # build image
    docker build -t sample-app .
    # create and run the container
    docker run --name appstatus-spring-boot-demo -p 8080:8080 sample-app 
