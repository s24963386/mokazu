<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="wx" value="${pageContext.request.contextPath}" />
<div class="row">
	<div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
		<h1 class="page-title txt-color-blueDark">
			<i class="fa fa-table fa-fw "></i> Table <span>> Coupon </span>
		</h1>
	</div>
</div>

<!-- widget grid -->
<section id="widget-grid" class="">

	<!-- row -->
	<div class="row">

		<!-- NEW WIDGET START -->
		<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

			<!-- Widget ID (each widget will need unique ID)-->
			<div class="jarviswidget jarviswidget-color-darken" id="wid-id-0"
				data-widget-editbutton="false">
				<!-- widget options:
				usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">

				data-widget-colorbutton="false"
				data-widget-editbutton="false"
				data-widget-togglebutton="false"
				data-widget-deletebutton="false"
				data-widget-fullscreenbutton="false"
				data-widget-custombutton="false"
				data-widget-collapsed="true"
				data-widget-sortable="false"

				-->
				<header>
					<span class="widget-icon"> <i class="fa fa-table"></i>
					</span>
					<h2>Standard Data Tables</h2>

				</header>

				<!-- widget div-->
				<div>

					<!-- widget edit box -->
					<div class="jarviswidget-editbox">
						<!-- This area used as dropdown edit box -->

					</div>
					<!-- end widget edit box -->

					<!-- widget content -->
					<div class="widget-body no-padding">

						<table id="dt_basic"
							class="table table-striped table-bordered table-hover"
							width="100%">
						</table>

					</div>
					<!-- end widget content -->

				</div>
				<!-- end widget div -->

			</div>
			<!-- end widget -->

		</article>
		<!-- WIDGET END -->
	</div>

	<!-- end row -->

	<!-- end row -->

</section>
<!-- end widget grid -->

<!-- Demo -->
<div id="addtab" title="<div class='widget-header'><h4><i class='fa fa-plus'></i> Add another coupon</h4></div>">

	<form id="couponForm">

		<fieldset>
			<div class="form-group">
				<label>Name</label>
				<input class="form-control" name="name" id="couponName" value="" placeholder="Name" type="text">
			</div>
			<div class="form-group">
				<label>Price</label>
				<input class="form-control" name="price" id="couponPrice" value="" placeholder="Price" type="text">
			</div>
			<div class="form-group">
				<label>First</label>
				<input class="form-control" name="first" id="couponFirst" value="" placeholder="First" type="text">
			</div>
			<div class="form-group">
				<label>Remark</label>
				<textarea class="form-control" name="remark" id="couponRemark" placeholder="Remark" rows="3"></textarea>
			</div>

		</fieldset>

	</form>

</div>

<div id="qrdialog" title="<div class='widget-header'><h4><i class='fa fa-plus'></i> QRCode</h4></div>">

	<form id="qrcodeForm">

		<fieldset>
			<input name="qrcardId" id="qrcardId" type="hidden">
			<div class="form-group">
				<label>Count</label>
				<input class="form-control" name="count" id="count" value="1" placeholder="Count" type="text">
			</div>
			<div class="form-group" style="width:100%;height:100%;">
				<img id="qrcodeImage" src="${wx}/img/timg.jpg" style="width:100%;height:100%;"/>
			</div>

		</fieldset>

	</form>

</div>


<script type="text/javascript">

	/* DO NOT REMOVE : GLOBAL FUNCTIONS!
	 *
	 * pageSetUp(); WILL CALL THE FOLLOWING FUNCTIONS
	 *
	 * // activate tooltips
	 * $("[rel=tooltip]").tooltip();
	 *
	 * // activate popovers
	 * $("[rel=popover]").popover();
	 *
	 * // activate popovers with hover states
	 * $("[rel=popover-hover]").popover({ trigger: "hover" });
	 *
	 * // activate inline charts
	 * runAllCharts();
	 *
	 * // setup widgets
	 * setup_widgets_desktop();
	 *
	 * // run form elements
	 * runAllForms();
	 *
	 ********************************
	 *
	 * pageSetUp() is needed whenever you load a page.
	 * It initializes and checks for all basic elements of the page
	 * and makes rendering easier.
	 *
	 */

	pageSetUp();
	
	/*
	 * ALL PAGE RELATED SCRIPTS CAN GO BELOW HERE
	 * eg alert("my home function");
	 * 
	 * var pagefunction = function() {
	 *   ...
	 * }
	 * loadScript("js/plugin/_PLUGIN_NAME_.js", pagefunction);
	 * 
	 */
	
	// PAGE RELATED SCRIPTS
	
	function deleteCard(cardId,row){
		 $.post("${wx}/product/delete",{cardId:cardId}).then(function(json){
			if(json.success){
				$('#dt_basic').dataTable().fnDeleteRow(row);
			}
		 });
	}
	 
	 $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
			_title : function(title) {
				if (!this.options.title) {
					title.html("&#160;");
				} else {
					title.html(this.options.title);
				}
			}
		}));
	 
	 
	 var qrd = $("#qrdialog").dialog({
			autoOpen : false,
			width : 600,
			resizable : false,
			modal : true,
			buttons : [{
				html : "<i class='fa fa-times'></i>&nbsp; Cancel",
				"class" : "btn btn-default",
				click : function() {
					$(this).dialog("close");
	
				}
			}, {
	
				html : "<i class='fa fa-plus'></i>&nbsp; Add",
				"class" : "btn btn-danger",
				click : function() {
					$.post("${wx}/product/qrcode",{count:$("#count").val(),cardId: $("#qrcardId").val()},function(json){
							if(json.success){
								$("#qrcodeImage").attr("src",json.obj.show_qrcode_url);
							}
					});
				}
			}]
		}); 
	 function qrcode(cardId){
		 $("#qrcardId").val(cardId);
		 qrd.dialog("open");
	 }
	 
	// pagefunction	
	var pagefunction = function() {
		//console.log("cleared");
		
		/* // DOM Position key index //
		
			l - Length changing (dropdown)
			f - Filtering input (search)
			t - The Table! (datatable)
			i - Information (records)
			p - Pagination (paging)
			r - pRocessing 
			< and > - div elements
			<"#id" and > - div with an id
			<"class" and > - div with a class
			<"#id.class" and > - div with an id and class
			
			Also see: http://legacy.datatables.net/usage/features
		*/	

		/* BASIC ;*/
			var responsiveHelper_dt_basic = undefined;
			var responsiveHelper_datatable_fixed_column = undefined;
			var responsiveHelper_datatable_col_reorder = undefined;
			var responsiveHelper_datatable_tabletools = undefined;
			
			var breakpointDefinition = {
				tablet : 1024,
				phone : 480
			};
			
			var dialog = $("#addtab").dialog({
				autoOpen : false,
				width : 600,
				resizable : false,
				modal : true,
				buttons : [{
					html : "<i class='fa fa-times'></i>&nbsp; Cancel",
					"class" : "btn btn-default",
					click : function() {
						$(this).dialog("close");
		
					}
				}, {
		
					html : "<i class='fa fa-plus'></i>&nbsp; Add",
					"class" : "btn btn-danger",
					click : function() {
						$.post("${wx}/product/add",$("#couponForm").serialize(),function(json){
								if(json.success){
									$('#dt_basic').dataTable().fnAddData(json.obj);
								}
						});
						$(this).dialog("close");
					}
				}]
			});
			
			$.get("${wx}/product/list",function(json){
				if(json.success){
					$('#dt_basic').dataTable({
						"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs add'l>r>"+
							"t"+
							"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
						"oLanguage": {
							"sSearch": '<span class="input-group-addon"><i class="glyphicon glyphicon-search"></i></span>'
						},	
						"autoWidth" : true,
						"preDrawCallback" : function() {
							// Initialize the responsive datatables helper once.
							if (!responsiveHelper_dt_basic) {
								responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic'), breakpointDefinition);
							}
						},
						"rowCallback" : function(nRow) {
							responsiveHelper_dt_basic.createExpandIcon(nRow);
						},
						"drawCallback" : function(oSettings) {
							responsiveHelper_dt_basic.respond();
						},
						aaData:json.obj,
						columns : [
									  { data : "cardId", title: "CardId"}, 
									  { data : "name", title: "Name" , render: function(data, type, row, meta) {
											  return data;
										}}, 
										 { data : "price", title: "Price" , render: function(data, type, row, meta) {
											  return data;
										}},  { data : "first", title: "First" , render: function(data, type, row, meta) {
											  return data;
										}},  { data : "remark", title: "Remark" , render: function(data, type, row, meta) {
											  return data;
										}},  { data : "createTime", title: "CreateTime" , render: function(data, type, row, meta) {
											  return data;
										}},  { data : "cardId", title: "Operation" , render: function(data, type, row, meta) {
											  console.log(meta);
											  return "<button class='btn btn-primary btn-xs'>Update</button>&nbsp;&nbsp;<button class='btn btn-danger btn-xs' onclick='deleteCard(\""+data+"\","+meta.row+")'>Delete</button>&nbsp;&nbsp;<button class='btn btn-success btn-xs' onclick='qrcode(\""+data+"\")'>QR</button>";
										}}]
					});
					$("div.add").append('<button id="addCoupon" style="float: right;" class="btn btn-success">Add Coupon</button>');
					$("#addCoupon").button().click(function() {
						dialog.dialog("open");
					});
				}
			});
			/* END BASIC */
		
	};

	// load related plugins
	
	loadScript("${wx}/smart/js/plugin/datatables/jquery.dataTables.min.js", function(){
		loadScript("${wx}/smart/js/plugin/datatables/dataTables.colVis.min.js", function(){
			loadScript("${wx}/smart/js/plugin/datatables/dataTables.tableTools.min.js", function(){
				loadScript("${wx}/smart/js/plugin/datatables/dataTables.bootstrap.min.js", function(){
					loadScript("${wx}/smart/js/plugin/datatable-responsive/datatables.responsive.min.js", pagefunction)
				});
			});
		});
	});


</script>
