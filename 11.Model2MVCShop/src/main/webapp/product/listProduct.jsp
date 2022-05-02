<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR" isELIgnored = "false" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%--	/////////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////////////
	<%
	<%@ page import="java.util.*" %>
	<%@ page import="com.model2.mvc.service.domain.*" %>
	<%@ page import="com.model2.mvc.common.*" %>
	<%@ page import="com.model2.mvc.common.util.CommonUtil" %>

	Map<String,Object> map = (Map<String,Object>)request.getAttribute("map");

	Page resultPage=(Page)request.getAttribute("resultPage");

	Search search = (Search)request.getAttribute("search");
	
	String menu = request.getParameter("menu");
	
	
	List<Product> list= (List<Product>)request.getAttribute("list");
	
	
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
	%>
 /////////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////////////	--%>    








<!DOCTYPE html>

<html lang="ko">

<head>
	<meta charset="EUC-KR">
	<title>상품 목록조회</title>
	
	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>


    <!-- Bootstrap Dropdown Hover CSS -->
    <link href="/css/animate.min.css" rel="stylesheet">
    <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
 	<!-- Bootstrap Dropdown Hover JS -->
 	<script src="/javascript/bootstrap-dropdownhover.min.js"></script>
  
  
 	<!-- jQuery UI toolTip 사용 CSS-->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
	<!-- jQuery UI toolTip 사용 JS-->
  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
	  body {
            padding-top : 50px;
        }
    </style>
	
	<link rel="stylesheet" href="/css/admin.css" type="text/css">
	
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">
		//검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScript 이용  
		function fncGetUserList(currentPage) {
			//document.getElementById("currentPage").value = currentPage;
			$("#currentPage").val(currentPage)
		   	//document.detailForm.submit();
			$("form").attr("method","POST").attr("action","/product/listProduct?menu=${param.menu}").submit();
		}
		
		$(function() {
			$( "button.btn.btn-default:contains('검색')" ).on("click", function() {
				fncGetUserList('1');		
			});
			
			
			$( ".ct_list_pop td:nth-child(3)" ).on("click", function() {
				
				if(${param.menu == 'search'}){
					
					//////////////////////////AJAX 추가 변경//////////////////////////////////////////
					//self.location ="/product/getProduct?prodNo="+$(this).attr('prodNo');
					///////////////////////////////////////////////////////////////////
					var prodNo = $(this).attr('prodNo');
					
					$.ajax(
							{
								url : "/product/json/getProduct/"+prodNo ,
								method : "GET" ,
								dataType : "json" ,
								headers : {
									"Accept" : "application/json",
									"Content-Type" : "application/json"
								},
								success : function(JSONData , status) {
									//alert(status);
									//alert(JSONData);
									var displayValue = "<h3>"
															+"상품번호 : "+JSONData.prodNo+"<br/>"
															+"상품명 : "+JSONData.prodName+"<br/>"
															+"상품이미지 : "+JSONData.fileName+"<br/>"
															+"상품상세정보 : "+JSONData.prodDetail+"<br/>"
															+"제조일자 : "+JSONData.manuDate+"<br/>"
															+"가격 : "+JSONData.price+"<br/>"
															+"등록일자 : "+JSONData.regDateString+"<br/>"
															+"</h3>";
									$("h3").remove();
									$("#"+prodNo+"").html(displayValue);
								}
							}
					);
				}else if(${param.menu == 'manage'}){
					self.location ="/product/updateProduct?prodNo="+$(this).attr('prodNo');
				}
			});
			
			
			$( ".ct_list_pop td:nth-child(3)").css("color","green");
			$("h7").css("color","blue");
			
			$(".ct_list_pop:nth-child(4n+6)").css("background-color" , "powderblue");
			
		});
		
	</script>
</head>

<body bgcolor="#ffffff" text="#000000">
	
	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
	
	<div style="width:98%; margin-left:10px;">
	<!-- 
	<form name="detailForm" action="/product/listProduct?menu=${param.menu}" method="post">
	
	 -->
		<form name="detailForm">
		
		<!-- /////////////////////11 Refactoring title////////////////
		<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
			<tr>
				<td width="15" height="37">
					<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
				</td>
				<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="93%" class="ct_ttl01">
		<%-- /////////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////////////
								<% if(menu.equals("search")) {%>
									상품 목록조회
								<%}else {%>
									상품 관리
								<%} %>	
		
		 /////////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////////// --%>					
								
								<c:if test="${param.menu == 'search'}">
									상품 목록조회
								</c:if>
															
								<c:if test="${param.menu == 'manage' }">
									상품관리
								</c:if>							
								
							</td>
						</tr>
					</table>
				</td>
				<td width="12" height="37">
					<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
				</td>
			</tr>
		</table>
		 //////////////////////////////////////////////////////////////-->
		 
		 <!--  화면구성 div Start /////////////////////////////////////-->
			<div class="container">
			
				<!-- table 위쪽 검색 Start /////////////////////////////////////-->
				<div class="page-header text-info">
	      			<c:if test="${param.menu == 'search'}">
						<h3>상품 목록조회</h3>
					</c:if>
												
					<c:if test="${param.menu == 'manage' }">
						<h3>상품 관리</h3>
					</c:if>	
	    		</div>
				
				
				
			    <div class="row">
			    
				    <div class="col-md-6 text-left">
				    	<p class="text-primary">
				    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
				    	</p>
				    </div>
				    
				    <div class="col-md-6 text-right">
					    <form class="form-inline" name="detailForm">
					    
						  <div class="form-group">
						    <select class="form-control" name="searchCondition" >
								<option value="0" ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : ""}>상품번호</option>
								<option value="1" ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : ""}>상품명</option>
								<option value="2" ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : ""}>상품가격</option>
							</select>
						  </div>
						  
						  <div class="form-group">
						    <label class="sr-only" for="searchKeyword">검색어</label>
						    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="검색어"
						    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
						  </div>
						  
						  <button type="button" class="btn btn-default">검색</button>
						  
						  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
						  <input type="hidden" id="currentPage" name="currentPage" value=""/>
						  
						</form>
			    	</div>
			    	
				</div>	    		
	    			
			</div>
		
<!-- //////////////////////////////////////11Refactoring 검색 수정 ///////////////////////////////////
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
			<tr>
				<td align="right">
					<select name="searchCondition" class="ct_input_g" style="width:200px; height:19px" >
					<%-- /////////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////////////
						<option value="0" <%= (searchCondition.equals("0") ? "selected" : "")%>>상품번호</option>
						<option value="1" <%= (searchCondition.equals("1") ? "selected" : "")%>>상품명</option>
						<option value="2" <%= (searchCondition.equals("2") ? "selected" : "")%>>상품가격</option>
		
		 /////////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////////// --%>	
						<option value="0" ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : ""}>상품번호</option>
						<option value="1" ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : ""}>상품명</option>
						<option value="2" ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : ""}>상품가격</option>
						
					</select>
					<%-- /////////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////////////
						<input type="text" name="searchKeyword" value="<%= searchKeyword %>"
									class="ct_input_g" style="width:200px; height:19px" />
		
		 /////////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////////// --%>	
					<input type="text" name="searchKeyword" 
									value="${! empty search.searchKeyword ? search.searchKeyword : "" }"
									class="ct_input_g" style="width:200px; height:19px" />
				</td>
			
		
			
				
				<td align="right" width="70">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="17" height="23">
								<img src="/images/ct_btnbg01.gif" width="17" height="23">
							</td>
							<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
								 /////////////////////////////////////////////////////////
								<a href="javascript:fncGetUserList('1');">검색</a>						
								///////////////////////////////////////////////////////////
								검색
							</td>
							<td width="14" height="23">
								<img src="/images/ct_btnbg03.gif" width="14" height="23">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
//////////////////////////////////////////////////////////////////////////////////////////////////// -->		

		
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
			<tr>
				<%-- /////////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////////////
				<td colspan="11" >전체 <%= resultPage.getTotalCount() %> 건수, 현재 <%= resultPage.getCurrentPage() %> 페이지</td>
		
		 /////////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////////// --%>
				<td colspan="11" >전체 ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage } 페이지</td>
			</tr>
			<tr>
				<td class="ct_list_b" width="100">No</td>
				<td class="ct_line02"></td>
				<!-- ///////////////////////////////////////////////////
				<td class="ct_list_b" width="150">상품명</td>		
				/////////////////////////////////////////////////////// -->
				<td class="ct_list_b" width="150">
					click<br>
					<h7>상품 상세정보 보기</h7>		
				</td>	
				<td class="ct_line02"></td>
				<td class="ct_list_b" width="150">가격</td>
				<td class="ct_line02"></td>
				<td class="ct_list_b">등록일</td>	
				<td class="ct_line02"></td>
				<td class="ct_list_b">현재상태</td>	
			</tr>
			<tr>
				<td colspan="11" bgcolor="808285" height="1"></td>
			</tr>
			<%-- /////////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////////////
				<%
				
				for(int i=0; i<list.size(); i++){
					Product vo = list.get(i);		
				
				%>
					
				<tr class="ct_list_pop">
					<td align="center"><%= i+1 %></td>
					<td></td>
							<% if(menu.equals("search")) {%>
								<td align="left"><a href="/getProduct.do?prodNo=<%=vo.getProdNo() %>"><%=vo.getProdName() %></a></td>
							<%}else {%>
								<td align="left"><a href="/updateProductView.do?prodNo=<%=vo.getProdNo() %>"><%=vo.getProdName() %></a></td>
							<%} %>
					<td></td>
					<td align="left"><%= vo.getPrice() %></td>
					<td></td>
					<td align="left"><%= vo.getRegDate() %></td>
					<td></td>
					<td align="left">
					
						판매중
					
					</td>	
				</tr>
				<tr>
					<td colspan="11" bgcolor="D6D7D6" height="1"></td>
				</tr>	
				
				<%
					}
				%>
		
		 /////////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////////// --%>
			
			<c:set var="i" value="0"/>
			<c:forEach var="product" items="${list }">
				<c:set var="i" value="${i+1 }"/>
				<tr class="ct_list_pop">
					<td align="center">${i }</td>
					<td></td>
						<!-- /////////////////////////////////////////////////////////////////////////////////////
						<c:choose>
							<c:when test="${param.menu == 'search'}">
								<td align="left"><a href="/product/getProduct?prodNo=${product.prodNo }">${product.prodName }</a></td>
							</c:when>
							<c:when test="${param.menu == 'manage'}">
								<td align="left"><a href="/product/updateProduct?prodNo=${product.prodNo }">${product.prodName }</a></td>
							</c:when>
						</c:choose>
						 ////////////////////////////////////////////////////////////////////////////////////////-->
					<td align="left" prodNo="${product.prodNo }">${product.prodName }</td>
					<td></td>
					<td align="left">${product.price }</td>
					<td></td>
					<td align="left">${product.regDate }</td>
				</tr>
				<tr>
					<!-- /////////////////////////////////////////////////////////////
					<td colspan="11" bgcolor="D6D7D6" height="1"></td>			
					///////////////////////////////////////////////////////////////-->
					<td id="${product.prodNo }" colspan="11" bgcolor="D6D7D6" height="1"></td> 
				</tr>	
			</c:forEach>
			
				
		</table>
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
			<tr>
				<td align="center">
				<input type="hidden" id="currentPage" name="currentPage" value=""/>
				<%-- /////////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////////////
					<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
							◀ 이전
					<% }else{ %>
							<a href="javascript:fncGetProductList('<%=resultPage.getCurrentPage()-1%>')">◀ 이전</a>
					<% } %>
		
					<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
							<a href="javascript:fncGetProductList('<%=i %>');"><%=i %></a>
					<% 	}  %>
			
					<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
							이후 ▶
					<% }else{ %>
							<a href="javascript:fncGetProductList('<%=resultPage.getEndUnitPage()+1%>')">이후 ▶</a>
					<% } %>
		
		 /////////////////////////// EL / JSTL 적용으로 주석 처리 ////////////////////////////// --%>
				<jsp:include page="../common/pageNavigator.jsp"/>
				
		    	</td>
			</tr>
		</table>
		<!--  페이지 Navigator 끝 -->
		
		</form>
	
	</div>
</body>
</html>
