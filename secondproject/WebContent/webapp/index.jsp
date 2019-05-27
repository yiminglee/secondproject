<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lcsv</title>

<script>
	document.addEventListener("DOMContentLoaded",
			function() {
				document.getElementById("insertchange").addEventListener(
						"click", insertchange);
				document.getElementById("deletechange").addEventListener(
						"click", deletechange);
				document.getElementById("findchange").addEventListener("click",
						findchange);
				document.getElementById("updatechange").addEventListener(
						"click", updatechange);
				document.getElementById("cmdid").addEventListener("change",
						checkcmdid);
				document.getElementById("cmdname").addEventListener("change",
						checkcmdname);
				document.getElementById("indcat").addEventListener("change",
						checkindcat);
				document.getElementById("addr").addEventListener("change",
						checkaddr);
				document.getElementById("chairname").addEventListener("change",
						checkchairname);
				document.getElementById("createdate").addEventListener(
						"change", checkcreatedate);
				document.getElementById("income").addEventListener("change",
						checkincome);

				insertchange();
			});
	function checkincome() {
		let cmdid = document.getElementById("income").value;
		re = /^[0-9]{1,30}$/
		if (re.test(cmdid)) {
			document.getElementById("incomeerr").innerHTML = "<i style=\"color:green\">correct</i>";
		} else {
			if (cmdid.length > 30) {
				document.getElementById("incomeerr").innerHTML = "<i style=\"color:red\">數字請小於30位數</i>";
			} else {
				document.getElementById("incomeerr").innerHTML = "<i style=\"color:red\">請輸入數字</i>";
			}
		}
	}

	function checkcmdid() {
		let cmdid = document.getElementById("cmdid").value;
		re = /[0-9]{4}/
		if (re.test(cmdid)) {
			document.getElementById("cmdiderr").innerHTML = "<i style=\"color:green\">correct</i>";
		} else {
			document.getElementById("cmdiderr").innerHTML = "<i style=\"color:red\">請輸入四碼數字</i>";
		}
	}
	function checkcreatedate() {
		let cmdid = document.getElementById("createdate").value;
		re = re = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/
		if (re.test(cmdid)) {
			document.getElementById("createdateerr").innerHTML = "<i style=\"color:green\">correct</i>";
		} else {
			document.getElementById("createdateerr").innerHTML = "<i style=\"color:red\">請輸入日期格式yyyy-mm-dd</i>";
		}
	}
	function checkcmdname() {
		let cmdname = document.getElementById("cmdname").value;
		re = /^.{1,50}$/
		if (re.test(cmdname)) {
			document.getElementById("cmdnameerr").innerHTML = "<i style=\"color:green\">correct</i>";
		} else {
			document.getElementById("cmdnameerr").innerHTML = "<i style=\"color:red\">字數需小於50個字</i>";
		}
	}
	function checkindcat() {
		let indcat = document.getElementById("indcat").value;
		re = /^.{1,50}$/
		if (re.test(indcat)) {
			document.getElementById("indcaterr").innerHTML = "<i style=\"color:green\">correct</i>";
		} else {
			document.getElementById("indcaterr").innerHTML = "<i style=\"color:red\">字數需小於50個字</i>";
		}
	}
	function checkaddr() {
		let addr = document.getElementById("addr").value;
		re = /^.{1,100}$/
		if (re.test(addr)) {
			document.getElementById("addrerr").innerHTML = "<i style=\"color:green\">correct</i>";
		} else {
			document.getElementById("addrerr").innerHTML = "<i style=\"color:red\">字數需小於100個字</i>";
		}
	}
	function checkchairname() {
		let chairname = document.getElementById("chairname").value;
		re = /^.{1,100}$/
		if (re.test(chairname)) {
			document.getElementById("chairnameerr").innerHTML = "<i style=\"color:green\">correct</i>";
		} else {
			document.getElementById("chairnameerr").innerHTML = "<i style=\"color:red\">字數需小於100個字</i>";
		}
	}

	function insertchange() {
		document.getElementById("insertchange").style.background = "rgb(157, 240, 231)";
		document.getElementById("deletechange").style.background = "#DDDDDD";
		document.getElementById("findchange").style.background = "#DDDDDD";
		document.getElementById("updatechange").style.background = "#DDDDDD";
		document.getElementById('dcmdname').style.display = "block";
		document.getElementById('dindcat').style.display = "block";
		document.getElementById('daddr').style.display = "block";
		document.getElementById('dchairname').style.display = "block";
		document.getElementById('dincome').style.display = "block";
		document.getElementById('dcreatedate').style.display = "block";
		document.getElementById('submit1').style.display = "block";
		document.getElementById('submit2').style.display = "none";
		document.getElementById('submit3').style.display = "none";
		document.getElementById('submit4').style.display = "none";
	}
	function deletechange() {
		document.getElementById("insertchange").style.background = "#DDDDDD";
		document.getElementById("deletechange").style.background = "rgb(157, 240, 231)";
		document.getElementById("findchange").style.background = "#DDDDDD";
		document.getElementById("updatechange").style.background = "#DDDDDD";
		document.getElementById('dcmdname').style.display = "none";
		document.getElementById('dindcat').style.display = "none";
		document.getElementById('daddr').style.display = "none";
		document.getElementById('dchairname').style.display = "none";
		document.getElementById('dincome').style.display = "none";
		document.getElementById('dcreatedate').style.display = "none";
		document.getElementById('submit1').style.display = "none";
		document.getElementById('submit2').style.display = "block";
		document.getElementById('submit3').style.display = "none";
		document.getElementById('submit4').style.display = "none";
	}
	function findchange() {
		document.getElementById("insertchange").style.background = "#DDDDDD";
		document.getElementById("deletechange").style.background = "#DDDDDD";
		document.getElementById("findchange").style.background = "rgb(157, 240, 231)";
		document.getElementById("updatechange").style.background = "#DDDDDD";
		document.getElementById('dcmdname').style.display = "none";
		document.getElementById('dindcat').style.display = "none";
		document.getElementById('daddr').style.display = "none";
		document.getElementById('dchairname').style.display = "none";
		document.getElementById('dincome').style.display = "none";
		document.getElementById('dcreatedate').style.display = "none";
		document.getElementById('submit1').style.display = "none";
		document.getElementById('submit2').style.display = "none";
		document.getElementById('submit3').style.display = "block";
		document.getElementById('submit4').style.display = "none";
	}
	function updatechange() {
		document.getElementById("insertchange").style.background = "#DDDDDD";
		document.getElementById("deletechange").style.background = "#DDDDDD";
		document.getElementById("findchange").style.background = "#DDDDDD";
		document.getElementById("updatechange").style.background = "rgb(157, 240, 231)";
		document.getElementById('dcmdname').style.display = "block";
		document.getElementById('dindcat').style.display = "block";
		document.getElementById('daddr').style.display = "block";
		document.getElementById('dchairname').style.display = "block";
		document.getElementById('dincome').style.display = "block";
		document.getElementById('dcreatedate').style.display = "block";
		document.getElementById('submit1').style.display = "none";
		document.getElementById('submit2').style.display = "none";
		document.getElementById('submit3').style.display = "none";
		document.getElementById('submit4').style.display = "block";
	}
</script>
<style>
.m {
	margin: 0 20% auto;
	padding: 0 12%;
	margin-top: 10%;
	width: 27%;
	overflow: auto;
	border-bottom: 3px solid saddlebrown;
	font-size: 20px;
	text-align: center;
}

.m li {
	float: left;
	width: 6em;
	list-style: none;
	text-align: center
}

.lb {
	width: 90px;
	float: left;
	text-align: right;
	padding-right: 5px;
	margin: 5px
}

input {
	margin: 5px
}

.ft {
	width: 50%;
	border: 1px solid black;
	background-color: rgb(212, 246, 248);
	border-radius: 10px;
	padding: 10px;
	margin: 0px 20%
}
.dt {
	width: 50%;
	
	
	
	padding: 10px;
	margin: 0px 20%
}
</style>
</head>
<body>
	<ul class="m">
		<li id="insertchange">insert</li>
		<li id="deletechange">delete</li>
		<li id="findchange">find</li>
		<li id="updatechange">update</li>
	</ul>

	<form class="ft" method="POST" action="<c:url value='insert.do' />"
		name="insert">
		<div id="dcmdid">
			<label class="lb">公司代號:</label><input type="text" id="cmdid"
				name="cmdid" size="30" value="${param.cmdid}" pattern="[0-9]{4}"
				required><label id="cmdiderr" class="err">請輸入四碼數字</label><br>
		</div>
		<div id="dcmdname">
			<label class="lb">公司名稱:</label><input type="text" name="cmdname"
				id="cmdname" size="30" value="${param.cmdname}" pattern=".{1,50}"><label
				id="cmdnameerr" class="err"></label>
			<div style="color: #FF0000; font-size: 100%; display: inline">${ErrorMsg.cmdnameerr}</div>
			<br>
		</div>
		<div id="dindcat">
			<label class="lb">產業別:</label><input type="text" name="indcat"
				id="indcat" size="30" value="${param.indcat}" pattern=".{1,50}"><label
				id="indcaterr" class="err"></label>
			<div style="color: #FF0000; font-size: 100%; display: inline">${ErrorMsg.indcaterr}</div>
			<br>
		</div>
		<div id="daddr">
			<label class="lb">地址:</label><input type="text" name="addr" id="addr"
				size="30" value="${param.addr}" pattern=".{1,100}"><label
				id="addrerr" class="err"></label>
			<div style="color: #FF0000; font-size: 100%; display: inline">${ErrorMsg.addrerr}</div>
			<br>
		</div>
		<div id="dchairname">
			<label class="lb">董事長名稱:</label><input type="text" id="chairname"
				name="chairname" size="30" value="${param.chairname}"
				pattern=".{1,100}"><label id="chairnameerr" class="err"></label>
			<div style="color: #FF0000; font-size: 100%; display: inline">${ErrorMsg.chairnameerr}</div>
			<br>
		</div>
		<div id="dincome">
			<label class="lb">收益:</label><input type="text" id="income"
				name="income" size="30" value="${param.income}"
				pattern="[0-9]{1,30}"><label id="incomeerr" class="err">請輸入數字</label>
			<div style="color: #FF0000; font-size: 100%; display: inline">${ErrorMsg.incomeerr}</div>
			<br>
		</div>
		<div id="dcreatedate">
			<label class="lb">建立日期:</label><input type="text" id="createdate"
				name="createdate" size="30" value="${param.createdate}"
				pattern="[0-9]{4}-[0-9]{2}-[0-9]{2}"><label
				id="createdateerr" class="err">(yyyy-mm-dd)</label>
			<div style="color: #FF0000; font-size: 100%; display: inline">${ErrorMsg.createdateeer}</div>
			<br>

		</div>
		<div>
			<input id="submit1" type="submit" name="submit" value="insert">
			<input id="submit2" type="submit" name="submit" value="delete">
			<input id="submit3" type="submit" name="submit" value="find">
			<input id="submit4" type="submit" name="submit" value="update">
			<input type="reset" value="清除">
		</div>
	</form>
	<div class="dt">
	<div style="color: #FF0000; font-size: 150%; display: inline">${ErrorMsg.updateerr}</div>
	<div style="color: #FF0000; font-size: 150%; display: inline">${ErrorMsg.inserterr}</div>
	<div style="color: #FF0000; font-size: 150%; display: inline">${ErrorMsg.deleteerr}</div>
	<div style="color: #FF0000; font-size: 150%; display: inline">${ErrorMsg.finderr}</div></div>
</body>
</html>