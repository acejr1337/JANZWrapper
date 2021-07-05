package chace.anzwrapper;

import com.cybersource.authsdk.core.MerchantConfig;

import Api.PaymentsApi;
import Invokers.ApiClient;
import Model.CreatePaymentRequest;
import Model.PtsV2PaymentsPost201Response;
import Model.Ptsv2paymentsClientReferenceInformation;
import Model.Ptsv2paymentsOrderInformation;
import Model.Ptsv2paymentsOrderInformationAmountDetails;
import Model.Ptsv2paymentsOrderInformationBillTo;
import Model.Ptsv2paymentsPaymentInformation;
import Model.Ptsv2paymentsPaymentInformationCard;
import Model.Ptsv2paymentsProcessingInformation;

public class SimpleRequest {

	private SimpleRequestHandler simpleRequestHandler;
	
	public SimpleRequest(SimpleRequestHandler simpleRequestHandler) {
		this.simpleRequestHandler = simpleRequestHandler;
	}

	public PtsV2PaymentsPost201Response init(String cardNumber, String expiryDate, int CVV, double totalAmount, String currencyFormat) {
		
		final CreatePaymentRequest requestObject = new CreatePaymentRequest();
		final Ptsv2paymentsClientReferenceInformation clientReferenceInformation = new Ptsv2paymentsClientReferenceInformation();
		clientReferenceInformation.code("TC50171_3");
		
		requestObject.clientReferenceInformation(clientReferenceInformation);
		final Ptsv2paymentsProcessingInformation processingInformation = new Ptsv2paymentsProcessingInformation();
		processingInformation.capture(false);
		
		if(simpleRequestHandler.getUserCapture()) { 
			processingInformation.capture(true);
		}
		
		requestObject.processingInformation(processingInformation);
		
		final Ptsv2paymentsPaymentInformation paymentInformation = new Ptsv2paymentsPaymentInformation();
		final Ptsv2paymentsPaymentInformationCard paymentInformationCard = new Ptsv2paymentsPaymentInformationCard();
		
		paymentInformationCard.number(cardNumber);
		paymentInformationCard.expirationMonth(expiryDate.split("/")[0]);
		paymentInformationCard.expirationYear(expiryDate.split("/")[1]);
		
		paymentInformation.card(paymentInformationCard);
		
		requestObject.paymentInformation(paymentInformation);
		
		final Ptsv2paymentsOrderInformation orderInformation = new Ptsv2paymentsOrderInformation();
		final Ptsv2paymentsOrderInformationAmountDetails orderInformationDetails = new Ptsv2paymentsOrderInformationAmountDetails();
		
		orderInformationDetails.totalAmount(String.valueOf(totalAmount));
		orderInformationDetails.currency(currencyFormat);
		orderInformation.amountDetails(orderInformationDetails);
		
		final Ptsv2paymentsOrderInformationBillTo billInformation = new Ptsv2paymentsOrderInformationBillTo();
		
		billInformation.firstName(simpleRequestHandler.getRecipient().getFirstName());
		billInformation.lastName(simpleRequestHandler.getRecipient().getLastName());
		
		final String formattedAddress = String.valueOf(simpleRequestHandler.getRecipient().getAddress().houseNumber + " " + simpleRequestHandler.getRecipient().getAddress().streetName + " " + simpleRequestHandler.getRecipient().getAddress().streetPrefix);
		billInformation.address1(formattedAddress);
		
		billInformation.locality(simpleRequestHandler.getRecipient().getAddress().locality);
		
		billInformation.administrativeArea(simpleRequestHandler.getRecipient().getAddress().administrativeArea);
		
		billInformation.postalCode(String.valueOf(simpleRequestHandler.getRecipient().getAddress().postCode));
		
		billInformation.country(simpleRequestHandler.getRecipient().getAddress().countryPrefix);
		
		billInformation.email(simpleRequestHandler.getRecipient().getEmailAddress());
		
		billInformation.phoneNumber(simpleRequestHandler.getRecipient().getPhoneNumber());
		
		orderInformation.billTo(billInformation);
		
		requestObject.orderInformation(orderInformation);
		
		PtsV2PaymentsPost201Response result = null;
		
		try {
			simpleRequestHandler.setMerchantProperties(simpleRequestHandler.getMerchantDetails());
			final ApiClient apiClient = new ApiClient();
			final MerchantConfig merchantConfig = new MerchantConfig(simpleRequestHandler.getMerchantProperties());
			apiClient.merchantConfig = merchantConfig;
			
			PaymentsApi paymentsApi = new PaymentsApi(apiClient);
			
			result = paymentsApi.createPayment(requestObject);
			
			simpleRequestHandler.setResponseCode(apiClient.responseCode);
			simpleRequestHandler.setStatus(apiClient.status);
			
			System.out.println("ResponseCode -> " + simpleRequestHandler.getResponseCode());
			System.out.println("ResponseMessage -> " + simpleRequestHandler.getStatus());
			System.out.println("Response Result ->\n" + result);
		} catch(Throwable t) {
			t.printStackTrace();
		}
		return result;
	}
}
