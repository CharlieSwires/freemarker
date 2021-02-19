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
<p>http://localhost:8888/freemarker/PDF/columns/3</p>
{ "file" : "hello,there,finally"
}
<p>Supports 1-5 columns can be extended simply.</p>

browser
-------
<p> If you have had a successful call to the PDF URL you can download the file here although the reply JSON contains the characters that make up the test file so all you have to do is convert them to bytes then a file.</p>
<p>http://localhost:8888/freemarker/DownloadFileServlet</p>

