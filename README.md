freemarker
----------
<p>in git bash
git clone https://github.com/scubetech-dev/freemarker</p>

<p>This contains both the java and node</p>

build
-----
<p>cd freemarker</p>
<p>mvn package</p>

<p>produces freemarker.war in target</p>



deploy
------
<p>docker build --tag website:latest .</p>
<p>docker run -d -p 8888:8080 website:latest</p>


postman
-------
<p>http://localhost:8888/freemarker/PDF/columns/3</p>
{ "file" : "1,2,3"
}
<p>http://localhost:8888/freemarker/DownloadFileServlet</p>

