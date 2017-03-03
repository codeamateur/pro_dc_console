<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<body>
	<h2>Done</h2>
	<h2>用户id:${userId}</h2>
	<shiro:hasPermission name="test:add">
	<a href="${ctx}/add"><h3>我有新增权限</h3></a>		
	 </shiro:hasPermission>
	 <shiro:hasPermission name="test:edit">
		<h3>我有修改权限</h3>
	 </shiro:hasPermission>
</body>
</html>