<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<style type="text/css">
  table.hovertable {
    font-family: verdana,arial,sans-serif;
    font-size:11px;
    color:#333333;
    border-width: 1px;
    border-color: #999999;
    border-collapse: collapse;
  }
  table.hovertable th {
    background-color:#c3dde0;
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #a9c6c9;
  }
  table.hovertable tr {
    background-color:#d4e3e5;
  }
  table.hovertable td {
    border-width: 1px;
    padding: 8px;
    border-style: solid;
    border-color: #a9c6c9;
  }
</style>
</head>
<body>
<table class="hovertable" style="width:100%;height:99%">
  <tr>
    <c:forEach items="${headsList}" var="r" varStatus="vs">
      <th>${r}</th>
    </c:forEach>
  </tr>
 <c:forEach items="${rowsList}" var="r" varStatus="ivs">
    <tr onmouseover="this.style.backgroundColor='#ffff66';" onmouseout="this.style.backgroundColor='#d4e3e5';">
     <c:forEach items="${headsList}" var="head"  varStatus="jvs">
          <td>
              <c:forEach items="${seatList}" var="seat">
                <c:if test="${seat.seatNo==(cells-jvs.count+1)+(ivs.count-1)*cells}">
                  ${seat.name}
                </c:if>
              </c:forEach>
          </td>
      </c:forEach>
    </tr>
  </c:forEach>
</table>
</body>
</html>
