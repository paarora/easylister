<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.cts.app.data.Vehicle"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Easylister Form</title>
</head>
<style type="text/css">
body {
	color: #000000;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 13px;
	font-style: normal;
	font-variant: normal;
	font-weight: 700;
	line-height: normal;
}

td {
	font-size: 13px;
}

.padlrb {
	padding-bottom: 20px;
}

.txtrgt {
	text-align: right;
}

.bdysec {
	padding: 0 10px;
}

.htltH2 {
	font-size: 22px;
	font-weight: bold;
	border-bottom: 1px solid #ccc;
}

.borderBottom {
	border-bottom: 3px solid #848484;
	padding-bottom: 15px;
}

.h10 {
	font-size: 0px;
	line-height: 0px;
	height: 10px;
	padding: 0px;
	margin: 0px;
}

.h13 {
	font-size: 0px;
	line-height: 0px;
	height: 13px;
	padding: 0px;
	margin: 0px;
}

.pad10 {
	padding: 10px;
}

.pad5 {
	padding: 5px;
}

.colrff0 {
	color: #FF0000;
	padding-top: 2px;
	padding-bottom: 2px;
}

.bold {
	font-weight: bold;
}

.fldmadry {
	color: #009900;
	font-size: 20px;
	font-weight: bold;
	vertical-align: -6px;
}

.x_SV {
	font: normal 10px Verdana, Arial, Helvetica, sans-serif;
	color: #333;
}

.htlth2 {
	border-color: #151515;
	border-bottom-style: solid;
	border-bottom-width: 1px;
	font-size: 22px;
	font-weight: bold;
	color: #848484;
	padding-top: 20px;
	padding-bottom: 5px;
}

.b4 {
	border: 1px solid #ccc;
}

.imgsrlHtxt {
	font-size: 16px;
	color: #666;
	font-weight: bold;
}

.padLnR20 {
	padding: 10px 20px;
}

.padLRB {
	padding-bottom: 20px;
}

.imgsroll {
	background-color: #efefef;
}

.thumbSec {
	padding: 5px;
	background-color: #FFFFFF;
	cursor: pointer;
}

.b4Black {
	border: 1px solid #000;
	cursor: pointer;
}

.txtCntr {
	text-align: center;
}

.valignImg {
	vertical-align: -6px;
}
.floatRight {
	float: right;
}
.padding5{
	border-spacing: 0;padding: 5px;
}
.alignMiddle{
	vertical-align: middle;
}
ul {
            display:table-row;
        }
ul li
        {
            list-style: circle outside;
    		margin-left: 10px;
            vertical-align: middle;
        }
li:before {
    content: "* ";
    color: red;
}
</style>
<script type="text/javascript">
	function load(modelValue) {
		handleMakeChange(modelValue);
	}
	function isZip(s){
	     reZip = new RegExp(/(^\d{5}$)/);
	     return reZip.test(s);
	}
	
	function isMileage(s){
	     return (s.length<7);
	}
	
	function isVin(s){     
	     return (s.length==17);
	}
	
	function isVinAlphanumeric(s){
		var aplhabet=new RegExp(/^[A-Za-z]+$/);
		var hasAlphabet = false;
		for(i=0;i<s.length;i++)
		{
			if(aplhabet.test(s.charAt(i))){
				hasAlphabet = true ;
				break;
			}
		}
		var number=new RegExp(/^[0-9]+$/);
		var hasNumber = false;
		for(i=0;i<s.length;i++)
		{
			if(number.test(s.charAt(i))){
				hasNumber = true ;
				break;
			}
		}
		return(hasAlphabet && hasNumber);
	}
	
	function checkEmpty(str){
		var blankRE=new RegExp(/^\s*$/);
		return blankRE.test(str);
	}
	
	function isAlphaNumeric(str){
		var aplhanum=new RegExp(/^[0-9A-Za-z]+$/);
		return aplhanum.test(str);
	}
	
	function validate() {
		var errorCount = 0;
		var alertStr = "false";
		var yearAttr = document.getElementById("attr_d1137_38_y");
		var validateValue = yearAttr.options[yearAttr.selectedIndex].value;
		if (isNaN(validateValue)){
			var errorLayer = document.getElementById("errorYear");
			errorLayer.style.display = "block";
			var pageErrorLayer = document.getElementById("yearli");
			pageErrorLayer.style.display = "list-item";
			alertStr = "true";
			errorCount++;
		} else {
			var errorLayer = document.getElementById("errorYear");
			errorLayer.style.display = "none";
			var pageErrorLayer = document.getElementById("yearli");
			pageErrorLayer.style.display = "none";
		}
		
		validateValue = document.getElementById("zipcode").value
		if(!isZip(validateValue)) {
		    var errorLayer = document.getElementById("errorZip");
			errorLayer.style.display = "block";
			var pageErrorLayer = document.getElementById("zipli");
			pageErrorLayer.style.display = "list-item";
			alertStr = "true";
			errorCount++;
		} else {
			var errorLayer = document.getElementById("errorZip");
			errorLayer.style.display = "none";
			var pageErrorLayer = document.getElementById("zipli");
			pageErrorLayer.style.display = "none";
		}
		validateValue =  document.getElementById("price").value;
		if(checkEmpty(validateValue) || isNaN(validateValue)) {
			var errorLayer = document.getElementById("errorPrice");
			errorLayer.style.display = "block";
			var pageErrorLayer = document.getElementById("priceli");
			pageErrorLayer.style.display = "list-item";
			alertStr = "true";
			errorCount++;
		} else {
			var errorLayer = document.getElementById("errorPrice");
			errorLayer.style.display = "none";
			var pageErrorLayer = document.getElementById("priceli");
			pageErrorLayer.style.display = "none";
		}
		
		validateValue =  document.getElementById("mileage").value;
		if(isNaN(validateValue)||checkEmpty(validateValue)||!isMileage(validateValue)) {
			var errorLayer = document.getElementById("errorMileage");
			errorLayer.style.display = "block";
			var pageErrorLayer = document.getElementById("mileageli");
			pageErrorLayer.style.display = "list-item";
			alertStr = "true";
			errorCount++;
		} else {
			var errorLayer = document.getElementById("errorMileage");
			errorLayer.style.display = "none";
			var pageErrorLayer = document.getElementById("mileageli");
			pageErrorLayer.style.display = "none";
		}
		
		var phone =  document.getElementById("phonenumber").value;
		var areaCode = document.getElementById("areacode").value;
		
		if (isNaN(phone)  || isNaN(areaCode) ) {
			var errorLayer = document.getElementById("errorPhone");
			errorLayer.style.display = "block";
			var pageErrorLayer = document.getElementById("numberli");
			pageErrorLayer.style.display = "list-item";
			alertStr = "true";
			errorCount++;
		} else {
			var errorLayer = document.getElementById("errorPhone");
			errorLayer.style.display = "none";
			var pageErrorLayer = document.getElementById("numberli");
			pageErrorLayer.style.display = "none";
		}
		
		validateValue =  document.getElementById("title").value;
		if(checkEmpty(validateValue)) {
			var errorLayer = document.getElementById("errorDescTitle");
			errorLayer.style.display = "block";
			var pageErrorLayer = document.getElementById("titleli");
			pageErrorLayer.style.display = "list-item";
			alertStr = "true";
			errorCount++;
		} else {
			var errorLayer = document.getElementById("errorDescTitle");
			errorLayer.style.display = "none";
			var pageErrorLayer = document.getElementById("titleli");
			pageErrorLayer.style.display = "none";
		}
		
		validateValue = document.getElementById("vin").value;
		var attrValue = document.getElementById("attr_d1137_38_y"); 
		if(attrValue.options[attrValue.selectedIndex].value>1980){
			if(checkEmpty(validateValue) || !isVin(validateValue) || !isVinAlphanumeric(validateValue)) {
				var errorLayer = document.getElementById("errorVin");
				errorLayer.style.display = "block";
				var pageErrorLayer = document.getElementById("vinli");
				pageErrorLayer.style.display = "list-item";
				alertStr = "true";
				errorCount++;
			} else {
				var errorLayer = document.getElementById("errorVin");
				errorLayer.style.display = "none";
				var pageErrorLayer = document.getElementById("vinli");
				pageErrorLayer.style.display = "none";
			}
		}
		else {
			if(!checkEmpty(validateValue) &&(!isVin(validateValue) || !isVinAlphanumeric(validateValue))) {
				var errorLayer = document.getElementById("errorVin");
				errorLayer.style.display = "block";
				var pageErrorLayer = document.getElementById("vinli");
				pageErrorLayer.style.display = "list-item";
				alertStr = "true";
				errorCount++;
			} else {
				var errorLayer = document.getElementById("errorVin");
				errorLayer.style.display = "none";
				var pageErrorLayer = document.getElementById("vinli");
				pageErrorLayer.style.display = "none";
			}
		}
		
		validateValue = document.getElementById("textarea").value;
		if(checkEmpty(validateValue)) {
			var errorLayer = document.getElementById("errorDescription");
			errorLayer.style.display = "block";
			var pageErrorLayer = document.getElementById("descli");
			pageErrorLayer.style.display = "list-item";
			alertStr = "true";
			errorCount++;
		} else {
			var errorLayer = document.getElementById("errorDescription");
			errorLayer.style.display = "none";
			var pageErrorLayer = document.getElementById("descli");
			pageErrorLayer.style.display = "none";
		}
		
		var pic1 = document.getElementById("pic1").value;
		var pic2 = document.getElementById("pic2").value;
		var pic3 = document.getElementById("pic3").value;
		var pic4 = document.getElementById("pic4").value;
		var pic5 = document.getElementById("pic5").value;
		var pic6 = document.getElementById("pic6").value;
		var pic7 = document.getElementById("pic7").value;
		var pic8 = document.getElementById("pic8").value;
		
		
		
		validateValue = document.getElementById("v4-10");
		if(validateValue.options[validateValue.selectedIndex].value=='-1') {
			var errorLayer = document.getElementById("errorMake");
			errorLayer.style.display = "block";
			var pageErrorLayer = document.getElementById("makeli");
			pageErrorLayer.style.display = "list-item";
			alertStr = "true";
			errorCount++;
		} else {
			var errorLayer = document.getElementById("errorMake");
			errorLayer.style.display = "none";
			var pageErrorLayer = document.getElementById("makeli");
			pageErrorLayer.style.display = "none";
		}
				
		if(alertStr=="false") {
			var errorLayer = document.getElementById("pageerror");
			errorLayer.style.display = "none";
			document.placeyourad.submit();
		}else {
			var errorHeader = document.getElementById("pageErrorHeader");
			if (errorCount > 1) {
				errorHeader.innerHTML = "Please enter the correct information in the highlighted fields:";
			}else {
				errorHeader.innerHTML = "Please enter the correct information in the highlighted field:";
			}
			var errorLayer = document.getElementById("pageerror");
			errorLayer.style.display = "block";
			window.scroll(0,0);
		}
	}

	function handleMakeChange(modelValue) {
		var makeList = document.getElementById("make");
		var modelList = document.getElementById("model");
		var makeVal = makeList.options[makeList.selectedIndex].text;
		var makeArray = new Array("Acura", "Alfa Romeo", "AMC", "Aston Martin",
				"Audi", "Austin", "Austin Healey", "Bentley", "BMW", "Bugatti",
				"Buick", "Cadillac", "Chevrolet", "Chrysler", "Citroen",
				"Cord", "Daewoo", "Datsun", "DeLorean", "DeSoto", "Dodge",
				"Eagle", "Edsel", "Ferrari", "Fiat", "Ford", "Geo", "GMC",
				"Honda", "Hummer", "Hyundai", "Infiniti",
				"International Harvester", "Isuzu", "Jaguar", "Jeep", "Kia",
				"Lamborghini", "Lancia", "Land Rover", "Lexus", "Lincoln",
				"Lotus", "Maserati", "Maybach", "Mazda", "Mercedes-Benz",
				"Mercury", "MG", "Mini", "Mitsubishi", "Nash", "Nissan",
				"Oldsmobile", "Opel", "Packard", "Peugeot", "Plymouth",
				"Pontiac", "Porsche", "Renault", "Rolls-Royce", "Saab",
				"Saturn", "Scion", "Shelby", "Smart", "Studebaker", "Subaru",
				"Suzuki", "Toyota", "Triumph", "Volkswagen", "Volvo", "Willys");
		var modelArray = new Array(
				"Any@Any$CL@CL$Integra@Integra$Legend@Legend$MDX@MDX$NSX@NSX$RDX@RDX$RL@RL$RSX@RSX$SLX@SLX$TL@TL$TSX@TSX$Vigor@Vigor$Other@Other",
				"Any@Any$Spider@Spider$Other@Other",
				"Any@Any",
				"Any@Any$DB7@DB7$DB9@DB9$DBS@DBS$Vanquish@Vanquish$Vantage@Vantage$Other@Other",
				"Any@Any$90@90$100@100$A3@A3$A4@A4$A5@A5$A6@A6$A8@A8$Allroad@Allroad$Cabriolet@Cabriolet$Q7@Q7$R8@R8$RS4@RS4$RS6@RS6$S4@S4$S5@S5$S6@S6$S8@S8$TT@TT$Other@Other",
				"Any@Any",
				"Any@Any",
				"Any@Any$Arnage@Arnage$Azure@Azure$Brooklands@Brooklands$Continental Flying Spur@Continental Flying Spur$Continental GT@Continental GT$Turbo R@Turbo R$Other@Other",
				"Any@Any$2002@2002$1-Series@1-Series$3-Series@3-Series$5-Series@5-Series$6-Series@6-Series$7-Series@7-Series$8-Series@8-Series$M3@M3$M5@M5$M6@M6$M Roadster & Coupe@M Roadster %26 Coupe$X3@X3$X5@X5$X6@X6$Z3@Z3$Z4@Z4$Z8@Z8$Other@Other",
				"Any@Any$Veyron@Veyron$Other@Other",
				"Any@Any$Century@Century$Electra@Electra$Enclave@Enclave$Grand National@Grand National$Lacrosse@Lacrosse$LeSabre@LeSabre$Lucerne@Lucerne$Park Avenue@Park Avenue$Rainier@Rainier$Reatta@Reatta$Regal@Regal$Rendezvous@Rendezvous$Riviera@Riviera$Roadmaster@Roadmaster$Skylark@Skylark$Terraza@Terraza$Other@Other",
				"Any@Any$Allante@Allante$Catera@Catera$CTS@CTS$DeVille & DTS@DeVille %26 DTS$Eldorado@Eldorado$Escalade@Escalade$Fleetwood@Fleetwood$Seville@Seville$SRX@SRX$STS@STS$XLR@XLR$Other@Other",
				"Any@Any$Astro@Astro$Avalanche@Avalanche$Aveo@Aveo$Bel Air/150/210@Bel Air%2F150%2F210$Blazer@Blazer$C/K Pickup 1500@C%2FK Pickup 1500$C/K Pickup 2500@C%2FK Pickup 2500$C/K Pickup 3500@C%2FK Pickup 3500$C-10@C-10$Camaro@Camaro$Caprice@Caprice$Cavalier@Cavalier$Chevelle@Chevelle$Cheyenne@Cheyenne$Cobalt@Cobalt$Colorado@Colorado$Corsica@Corsica$Corvair@Corvair$Corvette@Corvette$El Camino@El Camino$Equinox@Equinox$Express@Express$G20 Van@G20 Van$HHR@HHR$Impala@Impala$Lumina@Lumina$Malibu@Malibu$Monte Carlo@Monte Carlo$Nova@Nova$S-10@S-10$Silverado 1500@Silverado 1500$Silverado 2500@Silverado 2500$Silverado 3500@Silverado 3500$SSR@SSR$Suburban@Suburban$Tahoe@Tahoe$Tracker@Tracker$Trailblazer@Trailblazer$Traverse@Traverse$Uplander@Uplander$Venture@Venture$Other Pickups@Other Pickups$Other@Other",
				"Any@Any$300 Series@300 Series$Aspen@Aspen$Cirrus@Cirrus$Concorde@Concorde$Crossfire@Crossfire$Imperial@Imperial$LeBaron@LeBaron$LHS@LHS$New Yorker@New Yorker$Newport@Newport$Pacifica@Pacifica$Prowler@Prowler$PT Cruiser@PT Cruiser$Royal@Royal$Sebring@Sebring$Town & Country@Town %26 Country$Other@Other",
				"Any@Any",
				"Any@Any",
				"Any@Any$Lanos@Lanos$Leganza@Leganza$Other@Other",
				"Any@Any$Z-Series@Z-Series$Other@Other",
				"Any@Any",
				"Any@Any",
				"Any@Any$Avenger@Avenger$Caliber@Caliber$Caravan@Caravan$Challenger@Challenger$Charger@Charger$Coronet@Coronet$Dakota@Dakota$Dart@Dart$Durango@Durango$Grand Caravan@Grand Caravan$Intrepid@Intrepid$Journey@Journey$Lancer@Lancer$Magnum@Magnum$Neon@Neon$Nitro@Nitro$Power Wagon@Power Wagon$Ram 1500@Ram 1500$Ram 2500@Ram 2500$Ram 3500@Ram 3500$Ram Van@Ram Van$Shadow@Shadow$Sprinter@Sprinter$Stealth@Stealth$Stratus@Stratus$Viper@Viper$Other Pickups@Other Pickups$Other@Other",
				"Any@Any",
				"Any@Any",
				"Any@Any$308@308$328@328$348@348$355@355$360@360$430@430$456@456$550@550$575@575$Mondial@Mondial$Testarossa@Testarossa$Other@Other",
				"Any@Any",
				"Any@Any$Aerostar@Aerostar$Aspire@Aspire$Bronco@Bronco$Bronco II@Bronco II$Contour@Contour$Crown Victoria@Crown Victoria$Edge@Edge$Escape@Escape$Escort@Escort$E-Series Van@E-Series Van$Excursion@Excursion$Expedition@Expedition$Explorer@Explorer$Explorer Sport@Explorer Sport$Explorer Sport Trac@Explorer Sport Trac$F-100@F-100$F-150@F-150$F-250@F-250$F-350@F-350$Fairlane@Fairlane$Fairmont@Fairmont$Falcon@Falcon$Five Hundred@Five Hundred$Flex@Flex$Focus@Focus$Ford GT@Ford GT$Freestar@Freestar$FreeStyle/Taurus X@FreeStyle%2FTaurus X$Fusion@Fusion$Galaxie@Galaxie$Model A@Model A$Model T@Model T$Mustang@Mustang$Probe@Probe$Ranchero@Ranchero$Ranger@Ranger$Taurus@Taurus$Tempo@Tempo$Thunderbird@Thunderbird$Torino@Torino$Windstar@Windstar$Other Pickups@Other Pickups$Other@Other",
				"Any@Any",
				"Any@Any$Acadia@Acadia$Canyon@Canyon$Envoy@Envoy$Jimmy@Jimmy$Safari@Safari$Savana@Savana$Sierra 1500@Sierra 1500$Sierra 2500@Sierra 2500$Sierra 3500@Sierra 3500$Sonoma@Sonoma$Suburban@Suburban$Typhoon@Typhoon$Yukon@Yukon$Other@Other",
				"Any@Any$Accord@Accord$Civic@Civic$CR-V@CR-V$CRX@CRX$Del Sol@Del Sol$Element@Element$Fit@Fit$Insight@Insight$Odyssey@Odyssey$Passport@Passport$Pilot@Pilot$Prelude@Prelude$Ridgeline@Ridgeline$S2000@S2000$Other@Other",
				"Any@Any$H1@H1$H2@H2$H3@H3$H3T@H3T$Other@Other",
				"Any@Any$Accent@Accent$Azera@Azera$Elantra@Elantra$Entourage@Entourage$Genesis@Genesis$Santa Fe@Santa Fe$Sonata@Sonata$Tiburon@Tiburon$Tucson@Tucson$Veracruz@Veracruz$Other@Other",
				"Any@Any$EX@EX$FX@FX$G20@G20$G35@G35$G37@G37$I30@I30$I35@I35$J30@J30$M@M$Q45@Q45$QX4@QX4$QX56@QX56$Other@Other",
				"Any@Any$Scout@Scout$Other@Other",
				"Any@Any$Amigo@Amigo$Rodeo@Rodeo$Trooper@Trooper$VehiCROSS@VehiCROSS$Other@Other",
				"Any@Any$E-Type@E-Type$S-Type@S-Type$XF@XF$XJ6@XJ6$XJ8@XJ8$XJR@XJR$XJS@XJS$XK8@XK8$X-Type@X-Type$Other@Other",
				"Any@Any$Cherokee@Cherokee$CJ@CJ$Commander@Commander$Commando@Commando$Compass@Compass$Grand Cherokee@Grand Cherokee$Liberty@Liberty$Renegade@Renegade$Wagoneer@Wagoneer$Wrangler@Wrangler$Other@Other",
				"Any@Any$Amanti@Amanti$Borrego@Borrego$Optima@Optima$Rio@Rio$Sedona@Sedona$Sephia@Sephia$Sorento@Sorento$Spectra@Spectra$Sportage@Sportage$Other@Other",
				"Any@Any$Diablo@Diablo$Gallardo@Gallardo$Murcielago@Murcielago$Other@Other",
				"Any@Any",
				"Any@Any$Defender@Defender$Discovery@Discovery$Freelander@Freelander$LR2@LR2$LR3@LR3$Range Rover@Range Rover$Range Rover Sport@Range Rover Sport$Other@Other",
				"Any@Any$ES@ES$GS@GS$GX@GX$IS@IS$LS@LS$LX@LX$RX@RX$SC@SC$Other@Other",
				"Any@Any$Aviator@Aviator$Continental@Continental$LS@LS$Mark Series@Mark Series$MKS@MKS$MKX@MKX$Navigator@Navigator$Town Car@Town Car$MKZ/Zephyr@MKZ%2FZephyr$Other@Other",
				"Any@Any$Elise@Elise$Esprit@Esprit$Exige@Exige$Other@Other",
				"Any@Any$Gran Turismo@Gran Turismo$Quattroporte@Quattroporte$Other@Other",
				"Any@Any",
				"Any@Any$323@323$626@626$929@929$B-Series Pickups@B-Series Pickups$CX-7@CX-7$CX-9@CX-9$Mazda3@Mazda3$Mazda5@Mazda5$Mazda6@Mazda6$Millenia@Millenia$MPV@MPV$MX-3@MX-3$MX-5 Miata@MX-5 Miata$MX-6@MX-6$Protege@Protege$RX-7@RX-7$RX-8@RX-8$Tribute@Tribute$Other@Other",
				"Any@Any$190-Series@190-Series$200-Series@200-Series$300-Series@300-Series$400-Series@400-Series$500-Series@500-Series$600-Series@600-Series$C-Class@C-Class$CL-Class@CL-Class$CLK-Class@CLK-Class$CLS-Class@CLS-Class$E-Class@E-Class$G-Class@G-Class$GL-Class@GL-Class$M-Class@M-Class$R-Class@R-Class$S-Class@S-Class$SL-Class@SL-Class$SLK-Class@SLK-Class$SLR McLaren@SLR McLaren$Other@Other",
				"Any@Any$Capri@Capri$Comet@Comet$Cougar@Cougar$Grand Marquis@Grand Marquis$Mariner@Mariner$Milan@Milan$Montego@Montego$Monterey@Monterey$Mountaineer@Mountaineer$Mystique@Mystique$Sable@Sable$Tracer@Tracer$Villager@Villager$Other@Other",
				"Any@Any$MGA@MGA$MGB@MGB$Midget@Midget$T-Series@T-Series$Other@Other",
				"Any@Any$Classic Mini@Classic Mini$Clubman@Clubman$Mini Cooper@Mini Cooper$Mini Cooper S@Mini Cooper S$Other@Other",
				"Any@Any$3000GT@3000GT$Diamante@Diamante$Eclipse@Eclipse$Endeavor@Endeavor$Evolution@Evolution$Galant@Galant$Lancer@Lancer$Mirage@Mirage$Montero@Montero$Outlander@Outlander$Other@Other",
				"Any@Any",
				"Any@Any$200SX@200SX$240SX@240SX$280ZX@280ZX$300ZX@300ZX$350Z@350Z$Altima@Altima$Armada@Armada$Frontier@Frontier$GT-R@GT-R$Maxima@Maxima$Murano@Murano$Pathfinder@Pathfinder$Quest@Quest$Rogue@Rogue$Sentra@Sentra$Stanza@Stanza$Titan@Titan$Versa@Versa$Xterra@Xterra$Other Pickups@Other Pickups$Other@Other",
				"Any@Any$442@442$Alero@Alero$Aurora@Aurora$Bravada@Bravada$Cutlass@Cutlass$Eighty-Eight@Eighty-Eight$Intrigue@Intrigue$Ninety-Eight@Ninety-Eight$Silhouette@Silhouette$Toronado@Toronado$Other@Other",
				"Any@Any",
				"Any@Any",
				"Any@Any",
				"Any@Any$Acclaim@Acclaim$Barracuda@Barracuda$Duster@Duster$Fury@Fury$Grand Voyager@Grand Voyager$GTX@GTX$Neon@Neon$Prowler@Prowler$Road Runner@Road Runner$Satellite@Satellite$Sundance@Sundance$Voyager@Voyager$Other@Other",
				"Any@Any$Bonneville@Bonneville$Catalina@Catalina$Fiero@Fiero$Firebird@Firebird$G6@G6$G8@G8$Grand Am@Grand Am$Grand Prix@Grand Prix$GTO@GTO$Le Mans@Le Mans$Montana@Montana$Solstice@Solstice$Sunbird@Sunbird$Sunfire@Sunfire$Tempest@Tempest$Trans Am@Trans Am$Vibe@Vibe$Other@Other",
				"Any@Any$356@356$911@911$912@912$914@914$924@924$928@928$930@930$944@944$968@968$Boxster@Boxster$Cayenne@Cayenne$Cayman@Cayman$Other@Other",
				"Any@Any",
				"Any@Any$Corniche@Corniche$Phantom@Phantom$Silver Seraph@Silver Seraph$Silver Shadow@Silver Shadow$Silver Spirit/Spur/Dawn@Silver Spirit%2FSpur%2FDawn$Other@Other",
				"Any@Any$900@900$9000@9000$9-2X@9-2X$9-3@9-3$9-5@9-5$9-7x@9-7x$Other@Other",
				"Any@Any$Astra@Astra$Aura@Aura$Ion@Ion$L-Series@L-Series$Outlook@Outlook$S-Series@S-Series$Sky@Sky$Vue@Vue$Other@Other",
				"Any@Any$tC@tC$xA@xA$xB@xB$xD@xD$Other@Other",
				"Any@Any",
				"Any@Any",
				"Any@Any",
				"Any@Any$Baja@Baja$Forester@Forester$Impreza@Impreza$Legacy@Legacy$Outback@Outback$SVX@SVX$Tribeca@Tribeca$WRX@WRX$Other@Other",
				"Any@Any",
				"Any@Any$4Runner@4Runner$Avalon@Avalon$Camry@Camry$Celica@Celica$Corolla@Corolla$FJ Cruiser@FJ Cruiser$Highlander@Highlander$Land Cruiser@Land Cruiser$Matrix@Matrix$MR2@MR2$Paseo@Paseo$Previa@Previa$Prius@Prius$RAV4@RAV4$Sequoia@Sequoia$Sienna@Sienna$Solara@Solara$Supra@Supra$Tacoma@Tacoma$Tercel@Tercel$Tundra@Tundra$Venza@Venza$Yaris@Yaris$Other@Other",
				"Any@Any$Spitfire@Spitfire$TR-6@TR-6$Other@Other",
				"Any@Any$Beetle - Classic@Beetle - Classic$Beetle - New@Beetle - New$Bus/Vanagon@Bus%2FVanagon$Cabrio@Cabrio$Eos@Eos$EuroVan@EuroVan$Golf@Golf$Jetta@Jetta$Karmann Ghia@Karmann Ghia$Passat@Passat$Passat CC@Passat CC$Phaeton@Phaeton$Rabbit@Rabbit$Routan@Routan$Thing@Thing$Tiguan@Tiguan$Touareg@Touareg$Type III@Type III$Other@Other",
				"Any@Any$240@240$740@740$850@850$940@940$C30@C30$C70@C70$S40@S40$S60@S60$S70@S70$S80@S80$V40@V40$V50@V50$V70@V70$XC@XC$XC60@XC60$XC70@XC70$XC90@XC90$Other@Other",
				"Any@Any");
		modelList.options.length = 0;
		if (makeVal == 'Any') {
			option = new Option("Any", "Any");
			modelList.options[0] = option;
			modelList.disabled = true;
		} else {
			for (key in makeArray) {
				if (makeArray[key] == makeVal) {
					var modelStr = modelArray[key];
					var modelTokens = modelStr.split("$");
					var i = 0, modelSelected = true;
					for (token in modelTokens) {
						var mySplitResult = modelTokens[token].split("@");
						option = new Option(mySplitResult[0], mySplitResult[1]);
						modelList.options[i] = option;
						if(modelValue && modelValue == mySplitResult[0]){
							modelList.options[i].selected = true;
							modelSelected = false;
						}
						
						i++;

					}
					
					if(modelSelected){
						modelList.options[0].selected = true;
					}
					modelList.disabled = false;
					return;
				}
			}
		}
	}
	
	function loadImages(){
		var imgURL1 = document.getElementById('img1'),
			imgURL2 = document.getElementById('img2'),
			imgURL3 = document.getElementById('img3'),
			imgURL4 = document.getElementById('img4'),
			img1 = document.getElementById('picimg1'),
			img2 = document.getElementById('picimg2'),
			img3 = document.getElementById('picimg3'),
			img4 = document.getElementById('picimg4');
		if(imgURL1 && imgURL1.value && img1){
			img1.src = imgURL1.value;
		} else {
			img1.src = 'http://s20.postimg.org/9e0k8ewi1/emptyimage.jpg';
		}
		if(imgURL2 && imgURL2.value && img2){
			img2.src = imgURL2.value;
		} else {
			img2.src = 'http://s20.postimg.org/9e0k8ewi1/emptyimage.jpg';
		}
		if(imgURL3 && imgURL3.value && img3){
			img3.src = imgURL3.value;
		} else {
			img3.src = 'http://s20.postimg.org/9e0k8ewi1/emptyimage.jpg';
		}
		if(imgURL4 && imgURL4.value && img4){
			img4.src = imgURL4.value;
		} else {
			img4.src = 'http://s20.postimg.org/9e0k8ewi1/emptyimage.jpg';
		}
	}
</script>
<body onload="load('${vehicle.model}'); loadImages()">
	<table width="900px" border="0" align="center" cellspacing="0"
		cellpadding="0">
		<tr>
			<td>
				<div class="floatRight">
					<table >
						<tr valign="top">
							<td><a href="/easylister/history?user=testuser_paarora">HISTORY</a></td>
							<td>|</td>
							<td><a href="/easylister/welcome">HOME</a></td>
						</tr>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div class="borderBottom">
					<a href="/easylister/welcome"><img src="http://s20.postimg.org/5uhdqunf1/logo7.png" width="400"
						align="middle" alt="Easylister" /></a>
				</div></td>
		</tr>
		<tr>
			<td>
				<!--Body Section-->
				<form:form modelAttribute="vehicle" name="placeyourad" action="/easylister/form" method="post">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><div class="htltH2 bold">Advertisement Creator</div>
							</td>
						</tr>

						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>
								<div id="pageerror"
									style="padding: 5px;<c:choose>
																<c:when test = "${error.isErrorPresent}">
																	display: block;
																</c:when> 
																<c:otherwise>
																	display: none;
																</c:otherwise> 
															</c:choose>"
									class="b4">
									<table width="100%" class="padding5">
										
										<tr>
											<td class="alignMiddle"><ul class="colrff0">
													<li id="titleli" style="
													<c:choose>
																<c:when test = "${error.isTitleValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>
															">Please enter a
														valid title</li>
													<li id="makeli" style="<c:choose>
																<c:when test = "${error.isMakeValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>">Please select a
														make</li>
													<li id="modelli" style="<c:choose>
																<c:when test = "${error.isModelValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>">Please select
														a model</li>
													<li id="yearli" style="<c:choose>
																<c:when test = "${error.isYearValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>">Please select a
														year</li>
													<li id="zipli" style="<c:choose>
																<c:when test = "${error.isZipValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>">Please enter a
														valid zip code</li>
													<li id="priceli" style="<c:choose>
																<c:when test = "${error.isPriceValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>">Please enter a
														valid price</li>
													<li id="tsli" style="<c:choose>
																<c:when test = "${error.isTitleStatusValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>">Please select a valid title status</li>
													<li id="vinli" style="<c:choose>
																<c:when test = "${error.isVINValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>">Please enter a
														valid VIN number</li>
													<li id="mileageli" style="<c:choose>
																<c:when test = "${error.isMileageValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>">Please enter
														valid mileage</li>
													<li id="descli" style="<c:choose>
																<c:when test = "${error.isDescriptionValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>">Please enter
														valid description</li>
													<li id="picli" style="<c:choose>
																<c:when test = "${error.isImagesValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>">At least one
														image is required</li>

												</ul>
											</td>
										</tr>

									</table>
								</div>
							</td>
						</tr>
						<tr>
							<td>&nbsp; 
							</td>
						</tr>
						<tr>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td valign="top" width="500">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0" class="lh16">
												<tr>
													<td><table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td width="50%" class="bold">Descriptive listing
																	title <span class="fldmadry">*</span>
																</td>
																<td class="txtrgt"><span class="fldmadry">*</span>
																	<span>= Required field</span>
																</td>
															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td><input type="text" id="vehicleTitle" name="vehicleTitle"
														size="55" maxlength="55" value="${vehicle.vehicleTitle}">
														<div class="x_SV">Keywords help buyers find your
															listing. Choose keywords that help describe your vehicle,
															such as color, condition, body style, and unique options.</div>
														<div id="errorDescTitle" style="
															<c:choose>
																<c:when test = "${error.isTitleValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>"
															class="colrff0">Please enter a valid title</div>
													</td>
												</tr>

												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>
												<tr>
													<td class="bold">Make <span class="fldmadry">*</span>
													</td>
												</tr>
												<tr>
													<td>
													<form:select path="make" class="widthflx" onChange="handleMakeChange();return false;">
														<form:options items="${vehicle.allMakes}"/>
													</form:select>
													<%-- <select id="v4-10" class="widthflx" name="Make"
														onChange="handleMakeChange(this);return false;">

															<c:forEach var="makes" items="${vehicle.allMakes}">
															
															<option value='${makes.key}'}>${makes.key}</option>
															</c:forEach>
													</select> --%>
														<div id="errorMake" style="
															<c:choose>
																<c:when test = "${error.isMakeValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>" class="colrff0">Please
															select a make</div></td>
												</tr>
												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>

												<tr>
													<td class="bold">Model <span class="fldmadry">*</span>
													</td>
												</tr>
												<tr>
													<td>
													<select id="model" name="model"
														disabled="disabled" >
															<option value="-1" selected="selected">Any</option>
													</select>
														<div id="errorModel" style="
															<c:choose>
																<c:when test = "${error.isModelValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>"
															class="colrff0">Please select a model</div>
													</td>
												</tr>
												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>
												<tr>
													<td class="bold">Year <span class="fldmadry">*</span>
													</td>
												</tr>
												<tr>
													<td>
													<form:select path="year">
														<form:options items="${vehicle.years}"/>
													</form:select>
														<div id="errorYear" style="
															<c:choose>
																<c:when test = "${error.isYearValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose> 
														" class="colrff0">Please
															select a year</div>
													</td>
												</tr>
												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>
												<tr>
													<td class="bold">Zip Code <span class="fldmadry">*</span>
													</td>
												</tr>
												<tr>
													<td><input type="text" id="zip" name="zip"
														size="10" maxlength="5" value="${vehicle.zip}" />
														<div class="x_SV">Enter only a 5 digit numeric
															value.</div>
															<div id="errorZip" style="
															<c:choose>
																<c:when test = "${error.isZipValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose> 
														" class="colrff0">Please
															enter a valid zip code</div>
													</td>
												</tr>
												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>
												<tr>
													<td class="bold">Advertised price <span
														class="fldmadry">*</span>
													</td>
												</tr>
												<tr>
													<td><input type="text" id="price" name="price"
														size="12" maxlength="8" value="${vehicle.price}" />
														<div class="x_SV">Enter only numeric value, don't
															use "," or "." or "$".</div>
														<div id="errorPrice" style="<c:choose>
																<c:when test = "${error.isPriceValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>"
															class="colrff0">Please enter a valid price</div>
													</td>
												</tr>
												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>
												<tr>
													<td class="bold">Vehicle title <span class="fldmadry">*</span>
													</td>
												</tr>
												<tr>
												
												
													<td>
													<form:select path="titleStatus">
														<form:options items="${vehicle.allTitleStatus}"/>
													</form:select>
														<div id="errorTitle" style="<c:choose>
																<c:when test = "${error.isTitleStatusValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>"
															class="colrff0">Please select a title</div>
													</td>
												</tr>
												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>
												<tr>
													<td class="bold">VIN <span class="fldmadry">*</span>
													</td>
												</tr>
												<tr>
													<td><input type="text" id="vin" name="vin" size="30"
														maxlength="17" value="${vehicle.vin}" />
														<div class="x_SV">Required for all vehicles with a
															model year of 1981 or later. 17 character limit.</div>
														<div id="errorVin" style="
														<c:choose>
																<c:when test = "${error.isVINValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>" class="colrff0">Please
															enter a valid VIN number</div>
													</td>
												</tr>
												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>
												<tr>
													<td class="bold">Mileage <span class="fldmadry">*</span>
													</td>
												</tr>
												<tr>
													<td><input type="text" id="mileage" name="mileage"
														size="30" maxlength="6" value="${vehicle.mileage}" />
														<div class="x_SV">Max. value 999999</div>
														<div id="errorMileage" style="<c:choose>
																<c:when test = "${error.isMileageValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>"
															class="colrff0">Please enter valid mileage</div>
													</td>
												</tr>
												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>
												<tr>
													<td class="bold">Transmission <span class="fldmadry">*</span>
													</td>
												</tr>
												<tr>
													<td>
													<form:select path="transmission">
														<form:options items="${vehicle.transmissions}"/>
													</form:select>
														<div id="errorTransmission" style="display: none;"
															class="colrff0">Please select a transmission type</div>
													</td>
												</tr>
												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>

												<tr>
													<td class="bold">Number of cylinders <span
														class="fldmadry">*</span>
													</td>
												</tr>

												<tr>
													<td>
													<form:select path="cylinder">
														<form:options items="${vehicle.allCylinders}"/>
													</form:select>
														<div id="errorCylinder" style="display: none;"
															class="colrff0">Please select number of cylinders</div>
													</td>
												</tr>
												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>
												<tr>
													<td class="bold">Exterior color <span class="fldmadry">*</span>
													</td>
												</tr>
												<tr>
													<td>
													<form:select path="exteriorColour" >
														<form:options items="${vehicle.allExteriorColors}"/>
													</form:select>

															
														<div id="errorColor" style="display: none;"
															class="colrff0">Please select an exterior color</div>
													</td>
												</tr>
												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>
												<tr>
													<td class="bold">Interior color <span class="fldmadry">*</span>
													</td>
												</tr>
												<tr>
													<td>
													<form:select path="interiorColour" >
														<form:options items="${vehicle.allInteriorColors}"/>
													</form:select>

															
														<div id="errorColor" style="display: none;"
															class="colrff0">Please select an interior color</div>
													</td>
												</tr>
												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>
												<tr>
													<td class="bold">Contact number <span class="fldmadry"></span>
													</td>
												</tr>
												<tr>
													<td><input type=text
														id="phoneNumber" name="phoneNumber" size=20 maxlength=7
														value="${vehicle.phoneNumber}" />
														<div id="errorPhone" style="display: none;"
															class="colrff0">Please enter a valid phone number</div></td>
												</tr>
												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>
												<tr>
													<td class="bold">Description <span class="fldmadry">*</span>
													</td>
												</tr>
												<tr>
													<td style="white-space: nowrap"><textarea tabindex="1"
															id="description" cols="60" rows="7" rows="10"
															name="description">${vehicle.description}</textarea>
														<div id="errorDescription" style="<c:choose>
																<c:when test = "${error.isDescriptionValid}">
																	display: none;
																</c:when> 
																<c:otherwise>
																	display: block;
																</c:otherwise> 
															</c:choose>"
															class="colrff0">Please enter valid description</div>
													</td>
												</tr>
												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>
												<tr>
													<td class="bold">Condition <span class="fldmadry">*</span>
													</td>
												</tr>
												<tr>
													<td>
													<form:select path="condition" >
														<form:options items="${vehicle.allConditions}"/>
													</form:select>
													</td>
												</tr>
												<tr>
													<td>&nbsp;</td>
												</tr>

												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>
												<tr>
													<td><table width="100%" border="0" cellspacing="0"
															cellpadding="0">
															<tr>
																<td width="140"><input type="image"
																	src="http://s20.postimg.org/d93yazfnx/submitbutton.png"
																	width="130" alt="submit" name="submit" />
																</td>

																<td
																	style="border-left: 1px solid #ccc; padding-left: 10px;"><a
																	href="/easylister/welcome">Cancel</a></td>

															</tr>
														</table>
													</td>
												</tr>
												<tr>
													<td class=" h13">&nbsp;</td>
												</tr>
												<tr>
													<td><div class="x_SV">By importing your ad, you
															certify that you are the original poster.</div>
													</td>
												</tr>
												<tr>
													<td></td>
												</tr>
											</table>
										</td>

										<td>&nbsp;</td>
										<td width="280" valign="top" align="right">
											<div style="width: 200px;">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0" class="b4 imgsroll">
													<tr>
														<td class="pad5">
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td width="80" class="thumbSec"><img
																		name="picimg1" id="picimg1"
																		src="http://s20.postimg.org/9e0k8ewi1/emptyimage.jpg"
																		alt="Pic1" width="80" height="80" /></td>
																	<td>&nbsp;</td>
																	<td width="80" class="thumbSec"><img
																		name="picimg2" id="picimg2"
																		src="http://s20.postimg.org/9e0k8ewi1/emptyimage.jpg"
																		width="80" height="80" /></td>
																</tr>
															</table>
														</td>
													</tr>
													<!-- sample rollout comment -->
													<tr>
														<td class="pad5">
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td width="80" class="thumbSec"><img
																		name="picimg3" id="picimg3"
																		src="http://s20.postimg.org/9e0k8ewi1/emptyimage.jpg"
																		alt="Pic3" width="80" height="80" /></td>
																	<td>&nbsp;</td>
																	<td width="80" class="thumbSec"><img
																		name="picimg4" id="picimg4"
																		src="http://s20.postimg.org/9e0k8ewi1/emptyimage.jpg"
																		width="80" height="80" /></td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</div>
											<div>&nbsp;</div>
											<div>
												<table width="280" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td>Image URL 1 :</td>
														<td><input type="text" id="img1" name="images"
															size="25" value="${vehicle.images[0]}" onblur="loadImages()">
														</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td>Image URL 2 :</td>
														<td><input type="text" id="img2" name="images"
															size="25" value="${vehicle.images[1]}" onblur="loadImages()">
														</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td>Image URL 3 :</td>
														<td><input type="text" id="img3" name="images"
															size="25" value="${vehicle.images[2]}" onblur="loadImages()">
														</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
													</tr>
													<tr>
														<td>Image URL 4 :</td>
														<td><input type="text" id="img4" name="images"
															size="25" value="${vehicle.images[3]}" onblur="loadImages()">
														</td>
													</tr>

												</table>
											</div>
										</td>
									</tr>
									<tr>
										<td colspan="3" valign="top"></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</form:form>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
	</table>
</body>
</html>