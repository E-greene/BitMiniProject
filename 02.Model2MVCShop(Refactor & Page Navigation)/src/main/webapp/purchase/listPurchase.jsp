<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model2.mvc.common.Search" %>
<%@ page import="com.model2.mvc.common.Page" %>
<%@ page import="com.model2.mvc.service.domain.User" %>
<%@ page import="com.model2.mvc.service.domain.Purchase" %>


<%
	Map<String,Object> map = (Map<String,Object>)request.getAttribute("map");
	/* System.out.println("�ʰ�ī��Ʈ"+map.get("count")); */
	
	Search search = (Search)request.getAttribute("searchVO");
	
	Page resultPage=(Page)request.getAttribute("resultPage");
	
	User user = (User)session.getAttribute("user");
	
	List<Purchase> list = (List<Purchase>)request.getAttribute("list");

/* 	int total = 0;
	List<Purchase> list = null;
	if(map != null){
		total = ((Integer)map.get("count")).intValue();
		list = (List<Purchase>)map.get("list");
		System.out.println("total��"+total);
		System.out.println("list"+((List<Purchase>)map.get("list")));
	} */
	
/* 	int currentPage = search.getPage();
	
	int totalPage = 0;
	if(total > 0) {
			totalPage = total / search.getPageUnit();
			if(total%search.getPageUnit() > 0)
				totalPage += 1;
	} */
	
%>





<html>
<head>
<title>���� �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
/* 	function fncGetUserList() {
		document.detailForm.submit();
	} */
	
	// �˻� / page �ΰ��� ��� ��� Form ������ ���� JavaScrpt �̿�  
	function fncGetUserList(currentPage) {
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}	
	
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listUser.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">���� �����ȸ</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">��ü <%= resultPage.getTotalCount() %> �Ǽ�, ���� <%= resultPage.getCurrentPage() %> ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ��ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��ȭ��ȣ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�����Ȳ</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">��������</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<%
		int no = list.size();
		for(int i=0; i<list.size(); i++){
			Purchase purchase = (Purchase)list.get(i);
	%>
	
	
	<tr class="ct_list_pop">
		<td align="center">
			<a href="/getPurchase.do?tranNo=<%=purchase.getTranNo() %>"><%=no-- %></a>
		</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=<%=purchase.getBuyer().getUserId()%>"><%=purchase.getBuyer().getUserId()%></a>
		</td>
		<td></td>
		<td align="left"><%=purchase.getReceiverName() %></td>
		<td></td>
		<td align="left"><%=purchase.getReceiverPhone() %></td>
		<td></td>
		<td align="left">
			<%if(purchase.getTranCode().equals("1  ")) {%>
				���� ���ſϷ���� �Դϴ�.
			<%}else if(purchase.getTranCode().equals("2  ")) {%>
				���� ����߻��� �Դϴ�.
			<%}else {%>
				���� ��ۿϷ���� �Դϴ�.
			<%} %>
		</td>
		<td></td>
		<td align="left">
			<%if(purchase.getTranCode().equals("2  ")) {%>
				<a href="/updateTranCode.do?prodNo=<%=purchase.getPurchaseProd().getProdNo() %>&tranCode=3&user=user">���ǵ���</a>
			<%} %>
		</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<% } %>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
<%-- 		
		<% for(int i=1; i<=totalPage; i++) {%>
			<a href="/listPurchase.do?page=<%=i%>"><%=i%></a>
		<%} %>
 --%>
		 <input type="hidden" id="currentPage" name="currentPage" value=""/>
			<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					�� ����
			<% }else{ %>
					<a href="javascript:fncGetUserList('<%=resultPage.getCurrentPage()-1%>')">�� ����</a>
			<% } %>

			<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
					<a href="javascript:fncGetUserList('<%=i %>');"><%=i %></a>
			<% 	}  %>
	
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					���� ��
			<% }else{ %>
					<a href="javascript:fncGetUserList('<%=resultPage.getEndUnitPage()+1%>')">���� ��</a>
			<% } %>
		</td>
	</tr>
</table>

<!--  ������ Navigator �� -->
</form>

</div>

</body>
</html>