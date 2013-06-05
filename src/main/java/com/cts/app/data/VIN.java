package com.cts.app.data;

public class VIN {
	
	public static boolean isVinValid(String vin) {
		int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 0, 1, 2, 3, 4, 5, 0, 7, 0, 9,
				2, 3, 4, 5, 6, 7, 8, 9 };
		int[] weights = { 8, 7, 6, 5, 4, 3, 2, 10, 0, 9, 8, 7, 6, 5, 4, 3, 2 };

		vin = vin.replaceAll("-", "");
		vin = vin.toUpperCase();
		if (vin.length() != 17)
			return false;

		int sum = 0;
		for (int i = 0; i < 17; i++) {
			char c = vin.charAt(i);
			int value;
			int weight = weights[i];

			// letter
			if (c >= 'A' && c <= 'Z') {
				value = values[c - 'A'];
				if (value == 0)
					return false;
			}

			// number
			else if (c >= '0' && c <= '9')
				value = c - '0';

			// illegal character
			else
				return false;

			sum = sum + weight * value;

		}

		// check digit
		sum = sum % 11;
		char check = vin.charAt(8);
		if (check != 'X' && (check < '0' || check > '9'))
			return false;
		if (sum == 10 && check == 'X')
			return true;
		else if (sum == check - '0')
			return true;
		else
			return false;

	}
	
	public static void main(String[] args) {
		System.out.println(isVinValid("wp0ab29911s685546"));
	}

}
