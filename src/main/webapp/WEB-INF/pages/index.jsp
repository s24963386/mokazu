<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="wx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>货物寄送地址</title>
<script src="${wx}/js/mui.min.js"></script>
<link href="${wx}/css/mui.min.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${wx}/css/mui.picker.css"/>
 <script src="${wx}/js/jquery.min.js"></script>
<script src="${wx}/js/mui.picker.min.js"></script>
<script src="${wx}/js/data.city.js"></script>
</head>
<style>
h3 {
	line-height: 50px;
	font-size: 20px;
	text-align: center;
}

#chose {
	width: 80%;
	height: 50px;
	border: 1px solid #ccc;
	border-radius: 5px;
	line-height: 50px;
	margin: 50px auto;
	text-align: center;
	font-size: 20px;
}
</style>
<body>
		<header class="mui-bar mui-bar-nav">
			<h1 class="mui-title">收货地址</h1>
		</header>
		<div class="mui-content">
			<img width="100%" height="260px" alt="" src="${wx}/img/kd.jpg">
			<form class="mui-input-group">
				<div class="mui-input-row">
					<label>收货人：</label>
					<input id="user" type="text" class="mui-input-clear" placeholder="请输入收货人">
				</div>
				<div class="mui-input-row">
					<label>手机号码：</label>
					<input id="mobile" type="text" class="mui-input-clear" placeholder="请输入手机号码">
				</div>
				<div class="mui-input-row">
					<label>所在地区：</label>
					<label id="city_text" style="width: 65%;">选择省市区</label>
				</div>
				<div class="mui-input-row">
					<label>详细地址：</label>
					<input id="detail" type="text" class="mui-input-clear" placeholder="街道、楼牌号等">
				</div>
				<div class="mui-button-row">
					<button id="ok" type="button" class="mui-btn mui-btn-primary mui-btn-outlined">确认</button>
					<button id="cancel" type="button" class="mui-btn mui-btn-danger mui-btn-outlined">取消</button>
				</div>
			</form>
		</div>
		<%-- <div>
			<input id="detail" type="text" value="${param.encrypt_code}:${param.card_id}:${param.openid}:${param.outer_str}">
		</div> --%>
		<script type="text/javascript" charset="utf-8">
			mui.init();
			var city_picker = new mui.PopPicker({
				layer: 3
			});
			city_picker.setData(init_city_picker);
			$("#city_text").on("tap", function() {
				setTimeout(function() {
					city_picker.show(function(items) {
						$("#city_text").html((items[0] || {}).text + " " + (items[1] || {}).text + " " + (items[2] || {}).text);
					});
				}, 200);
			});
			
			function checkPhone(){
		    	if(!$("#mobile").val()){
		    		mui.alert("请填写手机号码");
					$("#mobile").focus();
					return false;
				 }else if(!/^[1][3578]\d{9}$/.test($("#mobile").val())){
					 mui.alert("手机号码有误，请重新输入");
					$("#mobile").focus();
					return false;
				}else{return true;}
		    }
			
			$("#ok").on("tap",function(){
				if(!$("#user").val()){
					mui.alert("请填写收货人");
					return;					
				}
				if(!checkPhone()){
					return;
				}
				if(!$("#city_text").text()||$("#city_text").text()=='选择省市区'){
					mui.alert("请选择省市区");
					return;
				}
				if(!$("#detail").val()){
					mui.alert("请填写详细地址");
					return;					
				}
				var btnArray = ['是','否'];
					mui.confirm('确认使用该兑换券？','确认使用',btnArray,function(e){
					if(e.index==0){
						//mui.alert($("#user").val()+',礼品将给您快递到'+$("#city_text").text()+$("#detail").val()+",注意查收");
						$.post("${wx}/wx/consume",{cardid:'${param.card_id}',code:'${param.encrypt_code}',openid:'${param.openid}',username:$("#user").val(),address:$("#city_text").text()+$("#detail").val(),mobile:$("#mobile").val()},function(re){
							if(re){
								WeixinJSBridge.call('closeWindow')
							}else{
								mui.alert('兑换失败');
							}
						});
					}else{
						
					}
				});
			});
			$("#cancel").on("tap",function(){
				WeixinJSBridge.call('closeWindow');
			});
			
		</script>
	</body>
</html>