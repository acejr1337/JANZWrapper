# JANZWrapper
Java wrapper for ANZ' banking - send money online.

I created this for fun, it isn't fully finished but has features that work, feel free to use this...

API Example:

```final SimpleRequest simpleRequest = new SimpleRequest(new SimpleRequestHandler(
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
```
