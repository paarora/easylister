package com.cts.app.data;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cts.app.parser.util.ParserConstants;

public class ExtractDataHtmlHelper {
	
	private static final String SPECIAL_CHARS = "@#$%^&*()_-<>?:{}[]|;\t+=<>/~`\b\n\f\r\"\'\\";
	
	/*public static void extractMakeModelAdvancedSearch(Vehicle vehicle , String vehicleTitle){
		
	}*/
	
	public static void extractMakeModel(Vehicle vehicle , String vehicleTitle){
		try {			
			String[] makeArray = {"Acura","Alfa Romeo","AMC","Aston Martin","Audi","Austin","Austin Healey","Bentley","BMW","Bugatti","Buick","Cadillac","Chevrolet","Chrysler","Citroen","Cord","Daewoo","Datsun","DeLorean","DeSoto","Dodge","Eagle","Edsel","Ferrari","Fiat","Ford","Geo","GMC","Honda","Hummer","Hyundai","Infiniti","International Harvester","Isuzu","Jaguar","Jeep","Kia","Lamborghini","Lancia","Land Rover","Lexus","Lincoln","Lotus","Maserati","Maybach","Mazda","Mercedes-Benz","Mercury","MG","Mini","Mitsubishi","Nash","Nissan","Oldsmobile","Opel","Packard","Peugeot","Plymouth","Pontiac","Porsche","Renault","Rolls-Royce","Saab","Saturn","Scion","Shelby","Smart","Studebaker","Subaru","Suzuki","Toyota","Triumph","Volkswagen","Volvo","Willys"};
			String[] modelArray = {"Any@Any$CL@CL$Integra@Integra$Legend@Legend$MDX@MDX$NSX@NSX$RDX@RDX$RL@RL$RSX@RSX$SLX@SLX$TL@TL$TSX@TSX$Vigor@Vigor$Other@Other","Any@Any$Spider@Spider$Other@Other","Any@Any","Any@Any$DB7@DB7$DB9@DB9$DBS@DBS$Vanquish@Vanquish$Vantage@Vantage$Other@Other","Any@Any$90@90$100@100$A3@A3$A4@A4$A5@A5$A6@A6$A8@A8$Allroad@Allroad$Cabriolet@Cabriolet$Q7@Q7$R8@R8$RS4@RS4$RS6@RS6$S4@S4$S5@S5$S6@S6$S8@S8$TT@TT$Other@Other","Any@Any","Any@Any","Any@Any$Arnage@Arnage$Azure@Azure$Brooklands@Brooklands$Continental Flying Spur@Continental Flying Spur$Continental GT@Continental GT$Turbo R@Turbo R$Other@Other","Any@Any$2002@2002$1-Series@1-Series$3-Series@3-Series$5-Series@5-Series$6-Series@6-Series$7-Series@7-Series$8-Series@8-Series$M3@M3$M5@M5$M6@M6$M Roadster & Coupe@M Roadster %26 Coupe$X3@X3$X5@X5$X6@X6$Z3@Z3$Z4@Z4$Z8@Z8$Other@Other","Any@Any$Veyron@Veyron$Other@Other","Any@Any$Century@Century$Electra@Electra$Enclave@Enclave$Grand National@Grand National$Lacrosse@Lacrosse$LeSabre@LeSabre$Lucerne@Lucerne$Park Avenue@Park Avenue$Rainier@Rainier$Reatta@Reatta$Regal@Regal$Rendezvous@Rendezvous$Riviera@Riviera$Roadmaster@Roadmaster$Skylark@Skylark$Terraza@Terraza$Other@Other","Any@Any$Allante@Allante$Catera@Catera$CTS@CTS$DeVille & DTS@DeVille %26 DTS$Eldorado@Eldorado$Escalade@Escalade$Fleetwood@Fleetwood$Seville@Seville$SRX@SRX$STS@STS$XLR@XLR$Other@Other","Any@Any$Astro@Astro$Avalanche@Avalanche$Aveo@Aveo$Bel Air/150/210@Bel Air%2F150%2F210$Blazer@Blazer$C/K Pickup 1500@C%2FK Pickup 1500$C/K Pickup 2500@C%2FK Pickup 2500$C/K Pickup 3500@C%2FK Pickup 3500$C-10@C-10$Camaro@Camaro$Caprice@Caprice$Cavalier@Cavalier$Chevelle@Chevelle$Cheyenne@Cheyenne$Cobalt@Cobalt$Colorado@Colorado$Corsica@Corsica$Corvair@Corvair$Corvette@Corvette$El Camino@El Camino$Equinox@Equinox$Express@Express$G20 Van@G20 Van$HHR@HHR$Impala@Impala$Lumina@Lumina$Malibu@Malibu$Monte Carlo@Monte Carlo$Nova@Nova$S-10@S-10$Silverado 1500@Silverado 1500$Silverado 2500@Silverado 2500$Silverado 3500@Silverado 3500$SSR@SSR$Suburban@Suburban$Tahoe@Tahoe$Tracker@Tracker$Trailblazer@Trailblazer$Traverse@Traverse$Uplander@Uplander$Venture@Venture$Other Pickups@Other Pickups$Other@Other","Any@Any$300 Series@300 Series$Aspen@Aspen$Cirrus@Cirrus$Concorde@Concorde$Crossfire@Crossfire$Imperial@Imperial$LeBaron@LeBaron$LHS@LHS$New Yorker@New Yorker$Newport@Newport$Pacifica@Pacifica$Prowler@Prowler$PT Cruiser@PT Cruiser$Royal@Royal$Sebring@Sebring$Town & Country@Town %26 Country$Other@Other","Any@Any","Any@Any","Any@Any$Lanos@Lanos$Leganza@Leganza$Other@Other","Any@Any$Z-Series@Z-Series$Other@Other","Any@Any","Any@Any","Any@Any$Avenger@Avenger$Caliber@Caliber$Caravan@Caravan$Challenger@Challenger$Charger@Charger$Coronet@Coronet$Dakota@Dakota$Dart@Dart$Durango@Durango$Grand Caravan@Grand Caravan$Intrepid@Intrepid$Journey@Journey$Lancer@Lancer$Magnum@Magnum$Neon@Neon$Nitro@Nitro$Power Wagon@Power Wagon$Ram 1500@Ram 1500$Ram 2500@Ram 2500$Ram 3500@Ram 3500$Ram Van@Ram Van$Shadow@Shadow$Sprinter@Sprinter$Stealth@Stealth$Stratus@Stratus$Viper@Viper$Other Pickups@Other Pickups$Other@Other","Any@Any","Any@Any","Any@Any$308@308$328@328$348@348$355@355$360@360$430@430$456@456$550@550$575@575$Mondial@Mondial$Testarossa@Testarossa$Other@Other","Any@Any","Any@Any$Aerostar@Aerostar$Aspire@Aspire$Bronco@Bronco$Bronco II@Bronco II$Contour@Contour$Crown Victoria@Crown Victoria$Edge@Edge$Escape@Escape$Escort@Escort$E-Series Van@E-Series Van$Excursion@Excursion$Expedition@Expedition$Explorer@Explorer$Explorer Sport@Explorer Sport$Explorer Sport Trac@Explorer Sport Trac$F-100@F-100$F-150@F-150$F-250@F-250$F-350@F-350$Fairlane@Fairlane$Fairmont@Fairmont$Falcon@Falcon$Five Hundred@Five Hundred$Flex@Flex$Focus@Focus$Ford GT@Ford GT$Freestar@Freestar$FreeStyle/Taurus X@FreeStyle%2FTaurus X$Fusion@Fusion$Galaxie@Galaxie$Model A@Model A$Model T@Model T$Mustang@Mustang$Probe@Probe$Ranchero@Ranchero$Ranger@Ranger$Taurus@Taurus$Tempo@Tempo$Thunderbird@Thunderbird$Torino@Torino$Windstar@Windstar$Other Pickups@Other Pickups$Other@Other","Any@Any","Any@Any$Acadia@Acadia$Canyon@Canyon$Envoy@Envoy$Jimmy@Jimmy$Safari@Safari$Savana@Savana$Sierra 1500@Sierra 1500$Sierra 2500@Sierra 2500$Sierra 3500@Sierra 3500$Sonoma@Sonoma$Suburban@Suburban$Typhoon@Typhoon$Yukon@Yukon$Other@Other","Any@Any$Accord@Accord$Civic@Civic$CR-V@CR-V$CRX@CRX$Del Sol@Del Sol$Element@Element$Fit@Fit$Insight@Insight$Odyssey@Odyssey$Passport@Passport$Pilot@Pilot$Prelude@Prelude$Ridgeline@Ridgeline$S2000@S2000$Other@Other","Any@Any$H1@H1$H2@H2$H3@H3$H3T@H3T$Other@Other","Any@Any$Accent@Accent$Azera@Azera$Elantra@Elantra$Entourage@Entourage$Genesis@Genesis$Santa Fe@Santa Fe$Sonata@Sonata$Tiburon@Tiburon$Tucson@Tucson$Veracruz@Veracruz$Other@Other","Any@Any$EX@EX$FX@FX$G20@G20$G35@G35$G37@G37$I30@I30$I35@I35$J30@J30$M@M$Q45@Q45$QX4@QX4$QX56@QX56$Other@Other","Any@Any$Scout@Scout$Other@Other","Any@Any$Amigo@Amigo$Rodeo@Rodeo$Trooper@Trooper$VehiCROSS@VehiCROSS$Other@Other","Any@Any$E-Type@E-Type$S-Type@S-Type$XF@XF$XJ6@XJ6$XJ8@XJ8$XJR@XJR$XJS@XJS$XK8@XK8$X-Type@X-Type$Other@Other","Any@Any$Cherokee@Cherokee$CJ@CJ$Commander@Commander$Commando@Commando$Compass@Compass$Grand Cherokee@Grand Cherokee$Liberty@Liberty$Renegade@Renegade$Wagoneer@Wagoneer$Wrangler@Wrangler$Other@Other","Any@Any$Amanti@Amanti$Borrego@Borrego$Optima@Optima$Rio@Rio$Sedona@Sedona$Sephia@Sephia$Sorento@Sorento$Spectra@Spectra$Sportage@Sportage$Other@Other","Any@Any$Diablo@Diablo$Gallardo@Gallardo$Murcielago@Murcielago$Other@Other","Any@Any","Any@Any$Defender@Defender$Discovery@Discovery$Freelander@Freelander$LR2@LR2$LR3@LR3$Range Rover@Range Rover$Range Rover Sport@Range Rover Sport$Other@Other","Any@Any$ES@ES$GS@GS$GX@GX$IS@IS$LS@LS$LX@LX$RX@RX$SC@SC$Other@Other","Any@Any$Aviator@Aviator$Continental@Continental$LS@LS$Mark Series@Mark Series$MKS@MKS$MKX@MKX$Navigator@Navigator$Town Car@Town Car$MKZ/Zephyr@MKZ%2FZephyr$Other@Other","Any@Any$Elise@Elise$Esprit@Esprit$Exige@Exige$Other@Other","Any@Any$Gran Turismo@Gran Turismo$Quattroporte@Quattroporte$Other@Other","Any@Any","Any@Any$323@323$626@626$929@929$B-Series Pickups@B-Series Pickups$CX-7@CX-7$CX-9@CX-9$Mazda3@Mazda3$Mazda5@Mazda5$Mazda6@Mazda6$Millenia@Millenia$MPV@MPV$MX-3@MX-3$MX-5 Miata@MX-5 Miata$MX-6@MX-6$Protege@Protege$RX-7@RX-7$RX-8@RX-8$Tribute@Tribute$Other@Other","Any@Any$190-Series@190-Series$200-Series@200-Series$300-Series@300-Series$400-Series@400-Series$500-Series@500-Series$600-Series@600-Series$C-Class@C-Class$CL-Class@CL-Class$CLK-Class@CLK-Class$CLS-Class@CLS-Class$E-Class@E-Class$G-Class@G-Class$GL-Class@GL-Class$M-Class@M-Class$R-Class@R-Class$S-Class@S-Class$SL-Class@SL-Class$SLK-Class@SLK-Class$SLR McLaren@SLR McLaren$Other@Other","Any@Any$Capri@Capri$Comet@Comet$Cougar@Cougar$Grand Marquis@Grand Marquis$Mariner@Mariner$Milan@Milan$Montego@Montego$Monterey@Monterey$Mountaineer@Mountaineer$Mystique@Mystique$Sable@Sable$Tracer@Tracer$Villager@Villager$Other@Other","Any@Any$MGA@MGA$MGB@MGB$Midget@Midget$T-Series@T-Series$Other@Other","Any@Any$Classic Mini@Classic Mini$Clubman@Clubman$Mini Cooper@Mini Cooper$Mini Cooper S@Mini Cooper S$Other@Other","Any@Any$3000GT@3000GT$Diamante@Diamante$Eclipse@Eclipse$Endeavor@Endeavor$Evolution@Evolution$Galant@Galant$Lancer@Lancer$Mirage@Mirage$Montero@Montero$Outlander@Outlander$Other@Other","Any@Any","Any@Any$200SX@200SX$240SX@240SX$280ZX@280ZX$300ZX@300ZX$350Z@350Z$Altima@Altima$Armada@Armada$Frontier@Frontier$GT-R@GT-R$Maxima@Maxima$Murano@Murano$Pathfinder@Pathfinder$Quest@Quest$Rogue@Rogue$Sentra@Sentra$Stanza@Stanza$Titan@Titan$Versa@Versa$Xterra@Xterra$Other Pickups@Other Pickups$Other@Other","Any@Any$442@442$Alero@Alero$Aurora@Aurora$Bravada@Bravada$Cutlass@Cutlass$Eighty-Eight@Eighty-Eight$Intrigue@Intrigue$Ninety-Eight@Ninety-Eight$Silhouette@Silhouette$Toronado@Toronado$Other@Other","Any@Any","Any@Any","Any@Any","Any@Any$Acclaim@Acclaim$Barracuda@Barracuda$Duster@Duster$Fury@Fury$Grand Voyager@Grand Voyager$GTX@GTX$Neon@Neon$Prowler@Prowler$Road Runner@Road Runner$Satellite@Satellite$Sundance@Sundance$Voyager@Voyager$Other@Other","Any@Any$Bonneville@Bonneville$Catalina@Catalina$Fiero@Fiero$Firebird@Firebird$G6@G6$G8@G8$Grand Am@Grand Am$Grand Prix@Grand Prix$GTO@GTO$Le Mans@Le Mans$Montana@Montana$Solstice@Solstice$Sunbird@Sunbird$Sunfire@Sunfire$Tempest@Tempest$Trans Am@Trans Am$Vibe@Vibe$Other@Other","Any@Any$356@356$911@911$912@912$914@914$924@924$928@928$930@930$944@944$968@968$Boxster@Boxster$Cayenne@Cayenne$Cayman@Cayman$Other@Other","Any@Any","Any@Any$Corniche@Corniche$Phantom@Phantom$Silver Seraph@Silver Seraph$Silver Shadow@Silver Shadow$Silver Spirit/Spur/Dawn@Silver Spirit%2FSpur%2FDawn$Other@Other","Any@Any$900@900$9000@9000$9-2X@9-2X$9-3@9-3$9-5@9-5$9-7x@9-7x$Other@Other","Any@Any$Astra@Astra$Aura@Aura$Ion@Ion$L-Series@L-Series$Outlook@Outlook$S-Series@S-Series$Sky@Sky$Vue@Vue$Other@Other","Any@Any$tC@tC$xA@xA$xB@xB$xD@xD$Other@Other","Any@Any","Any@Any","Any@Any","Any@Any$Baja@Baja$Forester@Forester$Impreza@Impreza$Legacy@Legacy$Outback@Outback$SVX@SVX$Tribeca@Tribeca$WRX@WRX$Other@Other","Any@Any","Any@Any$4Runner@4Runner$Avalon@Avalon$Camry@Camry$Celica@Celica$Corolla@Corolla$FJ Cruiser@FJ Cruiser$Highlander@Highlander$Land Cruiser@Land Cruiser$Matrix@Matrix$MR2@MR2$Paseo@Paseo$Previa@Previa$Prius@Prius$RAV4@RAV4$Sequoia@Sequoia$Sienna@Sienna$Solara@Solara$Supra@Supra$Tacoma@Tacoma$Tercel@Tercel$Tundra@Tundra$Venza@Venza$Yaris@Yaris$Other@Other","Any@Any$Spitfire@Spitfire$TR-6@TR-6$Other@Other","Any@Any$Beetle - Classic@Beetle - Classic$Beetle - New@Beetle - New$Bus/Vanagon@Bus%2FVanagon$Cabrio@Cabrio$Eos@Eos$EuroVan@EuroVan$Golf@Golf$Jetta@Jetta$Karmann Ghia@Karmann Ghia$Passat@Passat$Passat CC@Passat CC$Phaeton@Phaeton$Rabbit@Rabbit$Routan@Routan$Thing@Thing$Tiguan@Tiguan$Touareg@Touareg$Type III@Type III$Other@Other","Any@Any$240@240$740@740$850@850$940@940$C30@C30$C70@C70$S40@S40$S60@S60$S70@S70$S80@S80$V40@V40$V50@V50$V70@V70$XC@XC$XC60@XC60$XC70@XC70$XC90@XC90$Other@Other","Any@Any"};
			HashMap<String, Integer> makeHash = new HashMap<String, Integer>();
			Map<String, String> makeMap = new HashMap<String, String>();
			for (int i = 0; i < makeArray.length; i++) {
				makeHash.put(makeArray[i].toLowerCase(), new Integer(i));
				makeMap.put(makeArray[i].toLowerCase(), makeArray[i]);
			}
			StringTokenizer vToken = new StringTokenizer(vehicleTitle.toLowerCase()," ");
			while(vToken.hasMoreTokens()) {
				String vTitleWord = vToken.nextToken();
				
				if (makeHash.containsKey(vTitleWord)) {
					vehicle.setMake(makeMap.get(vTitleWord));
					String modelList = modelArray[((Integer)makeHash.get(vTitleWord)).intValue()];
					StringTokenizer sToken = new StringTokenizer(modelList,"$");
					while(sToken.hasMoreTokens()) {
						String token = sToken.nextToken();
						int index = token.indexOf("@");
						String modelName = token.substring(0,index);
						if (vehicleTitle.toLowerCase().indexOf(modelName.toLowerCase()) > 0) {
							vehicle.setModel(modelName);
							break;
						}
					}
					break;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void extractModelAdvanced(Map<String, Integer> makeHash, Map<String, String> makeMap, String vehicleTitle, Vehicle vehicle, String modelList){
		for (String key: makeHash.keySet()){
			if (vehicleTitle.contains(key)) {
				vehicle.setMake(key);
				
			}
		}
	}
	
	public static String formatText(String s) {
		if(s!=null) {
			s = s.replaceAll("\\n","");
		    s = s.replaceAll("\\t","");
		    s= s.replaceAll("\\b\\s{2,}\\b", " ");
			s = s.trim();
			return s;
		}
		return null;
	}
	
	public static String extractVin(String description){
		StringTokenizer tokens = new StringTokenizer(description," \t\n\r\f:.");
		while (tokens.hasMoreTokens()) {
			String s = tokens.nextToken();
			if (s.length() == 17 && !hasSpecialCharacters(s)) {
				if (isAlphaNumeric(s)) {
					return s;
				}
			}
		}		
		return "";
	}
	
	public static boolean isAlphaNumeric(String value) {
		boolean hasDigit = false;
		boolean hasChar = false;
		for(int i=0;i<value.length();i++){
			if ( hasDigit && hasChar) {
				break;
			}
			if ((!hasDigit) && (Character.isDigit(value.charAt(i)))) {
				hasDigit = true;
				continue;
			}
			if ((!hasChar) && (Character.isLetter(value.charAt(i)))) {
				hasChar = true;
				continue;
			}
		}
		return hasDigit && hasChar;
	}
	
	private static boolean hasSpecialCharacters(String fieldValue) {
		String text = null;
		for (int index = 0; index < fieldValue.length(); index++) {
			text = Character.valueOf(fieldValue.charAt(index)).toString();
			if (SPECIAL_CHARS.contains(text)) {
				return true;
			}
		}
		return false;
	}

	public static String extractTransmission(String header, String plainTextDesc) {
		
		boolean found = transmissionMatch(header, plainTextDesc, "auto[a-zA-Z]");
		if (found) {
			return ParserConstants.AUTOMATIC;
		} 
		found = transmissionMatch(header, plainTextDesc, "manua[a-zA-Z]");
		
		if (found) {
			return ParserConstants.MANUAL;
		} 
		
		return "Unspecified";
	}
	
	private static boolean transmissionMatch(String header, String plainTextDesc, String regex){
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(header.toLowerCase());
		boolean found =  m.find();
		
		if (!found) {
			m = p.matcher(plainTextDesc.toLowerCase());
			found = m.find();
		}
		return found;
	}
	
	public static String extractZip(String text){
		String zip = null;
		Pattern p = Pattern.compile("(\\d{5})");
		Matcher m = p.matcher(text);
		if (m.find()) {
		    zip = m.group(1);
		}
		return zip;
	}

	
	public static String extractColor(String content){
		int start = -1;
		Vehicle vehicle = new Vehicle();
		for (String vehicleColor : vehicle.getAllExteriorColors().values()) {
			start = content.toLowerCase().indexOf(vehicleColor.toLowerCase()); 
			if (start != -1) {
				if ( (Character.isLetter(content.charAt(start+vehicleColor.length()))) || //if there isn't a space before and after the color then it would be something else like bluetooth 
						(Character.isLetter(content.charAt(start-1))) ){
					continue;
				}
				return vehicleColor;
			}
		}
		return "Other";
	}
	
	
	public static String stripHtmlTag(String input) {
		String noHTMLString = input.replaceAll("\\<.*?\\>", " ");

		return noHTMLString.replaceAll("&nbsp;", " ");
	}
	
	public static String removeLineBreaks(String s) {
		if(s!=null) {
			s = s.replaceAll("\n","");
		    return s;
		}
		return null;
	}
	
	public static String getNumericString(String s) {
		StringBuffer sBuff = new StringBuffer();
		if (s == null)
			return null;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c) || c == '.') {
				sBuff.append(c);
			}
		}
		return sBuff.toString();
	}
	
	public static String extractTitleStatus(String vehicleTitle,
			String descriptionText) {
		// get vehicle title status
		if (vehicleTitle.toLowerCase().indexOf(ParserConstants.SALVAGE.toLowerCase()) > 0
				|| descriptionText.toLowerCase().indexOf(
						ParserConstants.SALVAGE.toLowerCase()) > 0) {
			return ParserConstants.SALVAGE;
		} else if (vehicleTitle.toLowerCase().indexOf(ParserConstants.CLEAR.toLowerCase()) > 0
				|| descriptionText.toLowerCase().indexOf(ParserConstants.CLEAR.toLowerCase()) > 0
				|| vehicleTitle.toLowerCase().indexOf(ParserConstants.CLEAN.toLowerCase()) > 0
				|| descriptionText.toLowerCase().indexOf(ParserConstants.CLEAN.toLowerCase()) > 0) {
			return ParserConstants.CLEAR;
		}
		return ParserConstants.OTHER;
	}
}
