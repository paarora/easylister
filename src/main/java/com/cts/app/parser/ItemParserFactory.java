package com.cts.app.parser;

public class ItemParserFactory {
	
	private static ItemParserFactory instance;

	public static ItemParserFactory getInstance() {
		if(instance==null){
			instance = new ItemParserFactory();
		}
		return instance;
	}
	
	public ItemParser getItemParser(String url){
		ItemParser parser = null;
		if(url!=null){
			if(url.indexOf("cars.com")>0){
				parser = new CarsItemParser();
			} else if (url.indexOf("autotrader.com")>0){
				parser = new AutotraderItemParser();
			} else if (url.indexOf("craigslist.org")>0){
				parser = new CraigslistItemParser();
			}
		}
		return parser;
	}

}
