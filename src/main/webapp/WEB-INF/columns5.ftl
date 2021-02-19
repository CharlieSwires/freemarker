<!doctype html>
<html>
<head>
  <title>${title}</title>
</head>
<body>
  <h1>${title}</h1>

<table>
    <#list systems as system>
      <tr>
      <td>${system_index + 1}</td>
      <td>${system.col0}</td>
      <td>${system.col1}</td>
      <td>${system.col2}</td>
      <td>${system.col3}</td>
      <td>${system.col4}</td>
      </tr>
    </#list>
</table>
</body>
</html>