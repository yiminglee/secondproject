<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style >
 .ft {
            width: 400px;
            border: 1px solid black;
            background-color: rgb(212, 246, 248);
            border-radius: 10px;
            padding: 10px;
              
        }
</style>
</head>
<body>
<h2>資料新增成功!!!!!!</h2>
<div class="ft">
公司代號: ${Lcsvbean.cmdid}<br>
公司名稱: ${Lcsvbean.cmdname}<br>
產業別: ${Lcsvbean.indcat}<br>
地址: ${Lcsvbean.addr}<br>
董事長名稱: ${Lcsvbean.chairname}<br>
收益: ${Lcsvbean.income}<br>
建立日期: ${Lcsvbean.createdate}<br>
</div>
<a href="index.jsp">返回</a>
</body>
</html>