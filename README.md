freemarker
----------
<p>in git bash
git clone https://github.com/CharlieSwires/freemarker</p>

<p>This contains both the java and node</p>

build
-----
<p>cd freemarker</p>
<p>mvn package</p>

<p>produces freemarker.war in target</p>

Compass
-------
<p>Collection names</p>
FTLFile, General, General2, HTML, Tables

deploy
------
<p>docker build --tag website:latest .</p>
<p>docker run -d -p 8888:8080 website:latest</p>


postman
-------
<p>http://localhost:8888/freemarker/saveTemplate</p>
{
  "name": "Template1",
  "who": "Charlie Swires",
  "headerHTML": "<html><body>",
  "footerHTML": "</body></html>",
  "insideBodyFTL": "<table width=\"100%\"><tr><td align=\"left\"><h2>${who}</h2></td><td align=\"right\"><h2>${datePrinted}</h2></td></tr></table><br><h1>${one}</h1><br><h1>${two}</h1><br><table width=\"100%\" border=\"4px\"><#list systems as system><tr><td border=\"1px\" width=\"5%\">${system_index + 1}</td><td border=\"1px\"><#if system.type??>${system.type}<#else>null</#if></td><td border=\"1px\"><#if system.note??>${system.note}<#else>null</#if></td></tr></#list></table>"
}

<p>http://localhost:8888/freemarker/loadTemplate/Template1</p>

<p>http://localhost:8888/freemarker/TabularToPDFAndDownload/columns/3</p>
<p>http://localhost:8888/freemarker/tabularToPDF/columns/3</p>
{
    "title": "The Title",
    "printedby": "Charles Swires",
   "headingsCSV":"one,two,three",
    "fileCSV": "hello,there,friend\n1,2,3\n"
 }
<p>Supports 1-5 columns can be extended simply.</p>

<p>http://localhost:8888/freemarker/GeneralToPDFAndDownload</p>
<p>http://localhost:8888/freemarker/generalToPDF</p>
{
    "who": "Charlie Swires",
    "inputHTML" : ".......",
    "replacementStringsCSV" : "title,my title1\nnext,my next2\n"
}

<p>http://localhost:8888/freemarker/GeneralArrayToPDFAndDownload</p>
<p>http://localhost:8888/freemarker/generalArrayToPDF</p>
{
    "who": "Charlie Swires",
"headerHTML": ".........",
"bodyFTL":".........",
"footerHTML": "...........",
"arrayOfItems": [{
"inputCSV": "one,1\ntwo,2\n",
"findingsText": [
{
"type": "Type1",
"note": "Findings...."
},
{
"type": "Type1",
"note": "Findings...."
}]
},
{
"inputCSV": "one,1\ntwo,2\n",
"findingsText": [
{
"type": "Type1",
"note": "Findings...."
},
{
"type": "Type1",
"note": "Findings...."
}]
}
]
}

<p>http://localhost:8888/freemarker/isTamperedWith.</p>
{
    "fileB64": ".......",
    "resultFilename": "result04FE1FCBB58C1DF293706A76B660102E1FA11AE0.pdf"
}

<p>http://localhost:8888/freemarker/ToPDFAndDownload.</p>
<p>http://localhost:8888/freemarker/toPDF.</p>
{
    "who": "Charlie",
    "inputHTML": "..........."
}


