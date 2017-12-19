var datawar;
var datapol;
var dataper;
var dataman;
var datawoman;

$(document).ready(function(){
	getSex();
	showChartList();	
	getChartData();	
	getAge();
	getURole();
})

function showChartList(){
		var data, options;
		// headline charts
		data = {
			labels: ['15世纪', '16世纪', '17世纪', '18世纪', '19世纪', '20世纪'],
			series: [
				{
					name:'战争原因',
					data:datawar
				},
				{
					name:'政治原因',
					data:datapol	
				},
				{
					name:'其他原因',
					data:dataper	
				}
			]
		};

		options = {
			height: 300,
			showArea: true,
			showLine: false,
			showPoint: false,
			fullWidth: true,
			axisX: {
				showGrid: false
			},
			lineSmooth: false,
		};

		new Chartist.Line('#headline-chart', data, options);
};

function showSexPie(dataman,datawoman){
	var data, options;
	var data = {
  		labels: ['男性', '女性'],
  		series: [dataman,datawoman]
	};

	var options = {
  		labelInterpolationFnc: function(value) {
    		return value[0]
    	}
  	};
  	
  	var responsiveOptions = [
  		['screen and (min-width: 640px)', {
    		chartPadding: 40,
    		labelOffset: 100,
    		labelDirection: 'explode',
    		labelInterpolationFnc: function(value) {
      			return value;
    		}
  		}],
  		['screen and (min-width: 1024px)', {
    		labelOffset: 80,
    		chartPadding: 20
  		}]
	];
  		
  	new Chartist.Pie('#sex_pie', data, options, responsiveOptions);	
}

function showAgePie(list){
	var data, options;
	var data = {
  		labels: ['青少年','壮年期','中年期','更年期','老年期','过世了'],
  		series: list
	};

	var options = {
  		labelInterpolationFnc: function(value) {
    		return value[0]
    	}
  };
  		
  	new Chartist.Pie('#age_pie', data, options);	
}

function getChartData(){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fbackup/findFamExo.do",
		dataType:"json",
		success:function(result){
			$("#inhome").children("span").eq(0).text(result.infam);
			$("#outhome").children("span").eq(0).text(result.outfam);
			$("#war").children("span").eq(0).text(result.war);
			$("#war").children("span").eq(1).text(result.warPer);
			$("#pol").children("span").eq(0).text(result.pol);
			$("#pol").children("span").eq(1).text(result.polPer);
			$("#per").children("span").eq(0).text(result.per);
			$("#per").children("span").eq(1).text(result.perPer);
			datawar=result.warList;
			datapol=result.polList;
			dataper=result.perList;
		}
	})
}

function getSex(){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fbackup/findFamSex.do",
		dataType:"json",
		success:function(result){
			$("#sex_pie_info").children("li").eq(0).children("span").text(result.manpercent);
			$("#sex_pie_info").children("li").eq(1).children("span").text(result.womanpercent);
			dataman=result.man;
			datawoman=result.woman;
			showSexPie(dataman,datawoman);
		}
	});
}

function getAge(){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fbackup/findFamAge.do",
		dataType:"json",
		success:function(result){
			$("#age_pie_info").children("li").eq(0).children("span").text(result.one);
			$("#age_pie_info").children("li").eq(1).children("span").text(result.two);
			$("#age_pie_info").children("li").eq(2).children("span").text(result.three);
			$("#age_pie_info").children("li").eq(3).children("span").text(result.four);
			$("#age_pie_info").children("li").eq(4).children("span").text(result.five);
			$("#age_pie_info").children("li").eq(5).children("span").text(result.six);
			
			showAgePie(result.list);
		}
	})
}

function backupDB(){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fdb/backDB.do",
		dataType:"json",
		success:function(result){
			if(result==true){
				alert("数据库备份成功！！！");
			}else{
				alert("数据库备份失败！！！")
			}
		}
	})
}

function importDB(){
	$.ajax({
		type:"POST",
		contentType:"application/json;charset=UTF-8",
		url:"../../fdb/importDB.do",
		dataType:"json",
		success:function(result){
			if(result==true){
				alert("数据库导入成功！！！");
			}else{
				alert("数据库导入失败！！！")
			}
		}
	})
}

function getURole(){
	$.ajax({
		type:"POST",
		url:"../../ffunc/findUserR.do",
		dataType:"json",
		success:function(data){
			if(data.role=="族管理员"){
				$("#bbutton").css("display","block");
			}else{
				$("#bbutton").css("display","none");
			}
		}
	})
}