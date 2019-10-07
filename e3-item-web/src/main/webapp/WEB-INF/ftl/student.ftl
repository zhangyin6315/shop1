<html>
<head>
	<title>student</title>
</head>
<BODY>
	学生信息：<br>
	学号：${student.id}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	姓名：${student.name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	年龄：${student.age}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	地址：${student.addr}<br>\
	学生列表
	<table border="1">
		<tr>
		<th>下标：</th>
			<th>学号：</th>
			<th>姓名：</th>
			<th>年龄：</th>
			<th>地址：</th>
		</tr>
		<#list stulist as stu>
		<#if stu_index%2==0>
		<tr bgcolor="red">
		<#else>
		<tr bgcolor="green">
		</#if>
		<td>${stu_index}</td>
			<td>${stu.id}</td>
			<td>${stu.name}</td>
			<td>${stu.age}</td>
			<td>${stu.addr}</td>
			</tr>
		</#list>
	</table><br>
	<!-- ?date,?time,?datetime,string("yyyy-MM-dd-hh-mm-ss")-->
	当前日期${date?string("yyyy-MM-dd-hh-mm-ss")}<br>
	null处理：${val!"值为null"}
	判断val是否为空：<#if val??>
	有值
	<#else>
	null
	</#if>
	<br>
	引用测试<#include "hello.ftl">
</BODY>
</html>