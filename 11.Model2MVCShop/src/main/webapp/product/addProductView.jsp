<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

 <!DOCTYPE html>

<html lang="ko">   



<html>
<head>
	<meta charset="EUC-KR">
	
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
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
 		body {
            padding-top : 50px;
        }
     </style>
     
	<title>상품등록</title>
	
	<link rel="stylesheet" href="/css/admin.css" type="text/css">
	
	<script type="text/javascript" src="../javascript/calendar.js">
	</script>
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	
	<script type="text/javascript">
	
	function fncAddProduct(){
		//Form 유효성 검증
	 	//var name = document.detailForm.prodName.value;
		//var detail = document.detailForm.prodDetail.value;
		//var manuDate = document.detailForm.manuDate.value;
		//var price = document.detailForm.price.value;
		
		var name = $("input[name='prodName']").val();
		var detail = $("input[name='prodDetail']").val();
		var manuDate = $("input[name='manuDate']").val();
		var price = $("input[name='price']").val();
	
		if(name == null || name.length<1){
			alert("상품명은 반드시 입력하여야 합니다.");
			return;
		}
		if(detail == null || detail.length<1){
			alert("상품상세정보는 반드시 입력하여야 합니다.");
			return;
		}
		if(manuDate == null || manuDate.length<1){
			alert("제조일자는 반드시 입력하셔야 합니다.");
			return;
		}
		if(price == null || price.length<1){
			alert("가격은 반드시 입력하셔야 합니다.");
			return;
		}
	
		//document.detailForm.action='/product/addProduct';
		//document.detailForm.submit();
		
		$("form").attr("method","POST").attr("action" , "/product/addProduct").submit();
		
	}
	
	$(function() {
		$( "button.btn.btn-primary:contains('등록')" ).on("click" , function() {
			//alert($("td.ct_btn01:contains('등록')").html());
			fncAddProduct();
		});
	});
	
	//function resetData(){
	//	document.detailForm.reset();
	//}
	
	$(function() {
		$( "button.btn.btn-primary:contains('취소')" ).on("click" , function() {
			//alert($("td.ct_btn01:contains('취소')").html());
			//$("form")[0].reset();
			history.go(-1);
		});
	});
	
	
	</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
	
	<!--//////////////////////////jQuery Event 처리로 변경됨///////////////////////////// 
	<form name="detailForm" method="post">					
	/////////////////////////////////////////////////////////////////////////////////-->
	<form name="detailForm">
	
		<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
	
		<div class="page-header">
	       <h3 class=" text-info">상품등록</h3>
	       <h5 class="text-muted">등록할 <strong class="text-danger">상품정보를</strong> 입력하세요.</h5>
	    </div>
	
		<div class="row">
	  		<div class="col-xs-4 col-md-2"><strong>상 품 명</strong></div>
			<div >
				<input type="text" class="col-xs-8 col-md-4" name="prodName" id="prodName">
			</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>상세정보</strong></div>
			<div >
				<input type="text" class="col-xs-8 col-md-4" name="prodDetail" id="prodDetail">
			</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>제조일자</strong></div>
			<div >
				<input type="text" class="col-xs-8 col-md-4" name="manuDate" id="manuDate">
				&nbsp;<img src="../images/ct_icon_date.gif" width="15" height="15" 
											onclick="show_calendar('document.detailForm.manuDate', document.detailForm.manuDate.value)"/>
			</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>가 격</strong></div>
			<div >
				<input type="text" class="col-xs-8 col-md-4" name="price" id="price">원
			</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2"><strong>상품이미지</strong></div>
			<div >
				<input type="file" class="col-xs-8 col-md-4" name="fileName" id="fileName">
			</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-md-12 text-center ">
	  			<button type="button" class="btn btn-primary">등록</button>
	  			<button type="button" class="btn btn-primary">취소</button>
	  		</div>
		</div>
		
		<br/>
		
 	</div>
 	<!--  화면구성 div Start /////////////////////////////////////-->

<!-- 
	<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
		<tr>
			<td width="15" height="37">
				<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
			</td>
			<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="93%" class="ct_ttl01">상품등록</td>
						<td width="20%" align="right">&nbsp;</td>
					</tr>
				</table>
			</td>
			<td width="12" height="37">
				<img src="/images/ct_ttl_img03.gif"	width="12" height="37"/>
			</td>
		</tr>
	</table>
	
	<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 13px;">
		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>
		<tr>
			<td width="104" class="ct_write">
				상품명 <imgsrc="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle">
			</td>
			<td bgcolor="D6D6D6" width="1"></td>
			<td class="ct_write01">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="105">
							<input type="text" name="prodName" class="ct_input_g" 
										style="width: 100px; height: 19px" maxLength="20">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>
		<tr>
			<td width="104" class="ct_write">
				상품상세정보 <img	src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
			</td>
			<td bgcolor="D6D6D6" width="1"></td>
			<td class="ct_write01">
				<input type="text" name="prodDetail" class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="10" minLength="6"/>
			</td>
		</tr>
		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>

		<tr>
			<td width="104" class="ct_write">
				제조일자 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
			</td>
			<td bgcolor="D6D6D6" width="1"></td>
			<td class="ct_write01">
				<input type="text" name="manuDate" readonly="readonly" class="ct_input_g"  
							style="width: 100px; height: 19px"	maxLength="10" minLength="6"/>
					&nbsp;<img src="../images/ct_icon_date.gif" width="15" height="15" 
											onclick="show_calendar('document.detailForm.manuDate', document.detailForm.manuDate.value)"/>
			</td>
		</tr>
		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>
		<tr>
			<td width="104" class="ct_write">
				가격 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
			</td>
			<td bgcolor="D6D6D6" width="1"></td>
			<td class="ct_write01">
				<input type="text" name="price" 	class="ct_input_g" 
							style="width: 100px; height: 19px" maxLength="10">&nbsp;원
			</td>
		</tr>
		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>
		<tr>
			<td width="104" class="ct_write">상품이미지</td>
			<td bgcolor="D6D6D6" width="1"></td>
			<td class="ct_write01">
				<input		type="text" name="fileName" class="ct_input_g" 
								style="width: 200px; height: 19px" maxLength="13"/>
			</td>
		</tr>
		<tr>
			<td height="1" colspan="3" bgcolor="D6D6D6"></td>
		</tr>
	</table>	
 -->	

	
	
<!-- 
	<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
		<tr>
			<td width="53%"></td>
			<td align="right">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01"  style="padding-top: 3px;">
					//////////////////////////jQuery Event 처리로 변경됨///////////////////////////// 
						<a href="javascript:fncAddProduct();">등록</a>									
					 /////////////////////////////////////////////////////////////////////////////////
					 등록
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
					<td width="30"></td>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01"	 style="padding-top: 3px;">
					//////////////////////////jQuery Event 처리로 변경됨///////////////////////////// 
						<a href="javascript:resetData();">취소</a>				
					 /////////////////////////////////////////////////////////////////////////////////
					 취소
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
 -->	

	
	</form>
</body>
</html>