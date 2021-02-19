FROM tomcat:9.0
ADD target/freemarker.war /usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]
# install dependencies
RUN apt-get update && apt-get install -y \
      curl \
      npm \
      nodejs \
      git;
RUN npm install npm@latest -g
RUN npm install fs
RUN npm install puppeteer