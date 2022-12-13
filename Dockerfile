FROM tomcat:9.0
ADD target/freemarker.war /usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]