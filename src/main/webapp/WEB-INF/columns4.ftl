<!doctype html>
<html>
<head>
  <title>${title}</title>
</head>
<body>
  <h1>${title}</h1>

<table width="100%" border="4px">
    <#list systems as system>
      <tr><td>${system_index + 1}</td>
      <td border="1px">${system.col0}</td>
      <td border="1px">${system.col1}</td>
      <td border="1px">${system.col2}</td>
      <td border="1px">${system.col3}</td>
      </tr>
    </#list>
</table>
</body>
</html>