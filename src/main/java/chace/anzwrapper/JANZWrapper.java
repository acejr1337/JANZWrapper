package chace.anzwrapper;

import chace.anzwrapper.Recipient.RecipientAddress;
import chace.anzwrapper.SimpleRequestHandler.MerchantInformation;

public class JANZWrapper {

	// Created by Chace Zwagerman 5/07/2021
	// Simple ANZ Wrapper API built in Java for use with the ANZ Australia banking app.
	
	public static void main(String[] args) {
		// Example...
		
		final SimpleRequest simpleRequest = new SimpleRequest(new SimpleRequestHandler(
				new Recipient("John",
							  "Doe",
							  new RecipientAddress(123, "Sesame", "ST", "Melbourne", "MEL", 3001, "AU"),
							  "john.doe@coolmail.com",
							  "0412345678"),
				new MerchantInformation("merchantId",
										"merchantKeyId",
										"merchantSecretKey", 
										"keyPass",
										"keyFileName",
										"keyAlias"),
				new Request() {
					@Override
					void onRequest(Recipient recipient) {
						System.out.println("Debug recipient exists?: " + recipient != null);
					}
				}
		));
		
							// card number // expiry // CVV // amountToSend // currencyFormat
		simpleRequest.init("4000000000000000", "11/2022", 441, 50, "AUD");
	}
}
