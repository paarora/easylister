package com.cts.app.parser;

import com.cts.app.data.Vehicle;

public interface ItemParser {
	
	public Vehicle parseItem(String url) throws Exception;

}
