package chace.anzwrapper;

import java.util.Properties;

import lombok.Data;
import lombok.Getter;

@Data
public class SimpleRequestHandler {

	@Getter
	private String responseCode, status;
	@Getter
	private Properties merchantProperties;
	@Getter
	private boolean userCapture;
	
	@Getter
	private Recipient recipient;
	
	@Getter
	private MerchantInformation merchantInformation;
	
	public SimpleRequestHandler(final Recipient recipient, final MerchantInformation merchantInformation, final Request request) {
		this.responseCode = null;
		this.status = null;
		this.merchantProperties = null;
		this.userCapture = false;
		this.recipient = recipient;
		this.merchantInformation = merchantInformation;
		request.onRequest(recipient);
	}
	
	public Properties getMerchantDetails() {
		final Properties props = new Properties();
		props.setProperty("authenticationType", "http_signature");
		props.setProperty("merchantID", merchantInformation.merchantId);
		props.setProperty("runEnvironment", "CyberSource.Environment.SANDBOX");
		props.setProperty("requestJsonPath", "src/main/resources/request.json");
		props.setProperty("portfolioID", "");
		props.setProperty("useMetaKey", "false");
		props.setProperty("keyAlias", merchantInformation.keyAlias);
		props.setProperty("keyPass", merchantInformation.keyPass);
		props.setProperty("keyFileName", merchantInformation.keyFileName);
		props.setProperty("keysDirectory", "src/main/resources");
		props.setProperty("merchantKeyId", merchantInformation.merchantKeyId);
		props.setProperty("merchantsecretKey", merchantInformation.merchantSecretKey);
		props.setProperty("enableLog", "true");
		props.setProperty("logDirectory", "log");
		props.setProperty("logFilename", "cybs");
		props.setProperty("logMaximumSize", "5M");
		props.setProperty("enableClientCert", "false");
		props.setProperty("clientCertDirectory", "src/main/resources");
		props.setProperty("clientCertFile", "");
		props.setProperty("clientCertPassword", "");
		props.setProperty("clientId", "");
		props.setProperty("clientSecret", "");
		return props;
	}
	public static Properties getAlternativeMerchantDetails(String merchantId, String merchantKeyId, String merchantSecretKey, String keyPass, String keyFileName, String keyAlias) {
		final Properties props = new Properties();
		props.setProperty("authenticationType", "http_signature");
		props.setProperty("merchantID", merchantId);
		props.setProperty("runEnvironment", "CyberSource.Environment.SANDBOX");
		props.setProperty("requestJsonPath", "src/main/resources/request.json");
		props.setProperty("keyAlias", keyAlias);
		props.setProperty("keyPass", keyPass);
		props.setProperty("keyFileName", keyFileName);
		props.setProperty("keysDirectory", "src/main/resources");
		props.setProperty("merchantKeyId", merchantKeyId);
		props.setProperty("merchantsecretKey", merchantSecretKey);
		props.setProperty("enableLog", "true");
		props.setProperty("logDirectory", "log");
		props.setProperty("logFilename", "cybs");
		props.setProperty("logMaximumSize", "5M");
		return props;
	}
	public boolean getUserCapture() {
		return userCapture;
	}
	public Properties getMerchantProperties() {
		return merchantProperties;
	}
	public void setMerchantProperties(Properties merchantProperties) {
		this.merchantProperties = merchantProperties;
	}
	public Recipient getRecipient() {
		return recipient;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	static class MerchantInformation {
		public String merchantId;
		public String merchantKeyId;
		public String merchantSecretKey;
		public String keyPass;
		public String keyFileName;
		public String keyAlias;
		
		public MerchantInformation(final String merchantId, final String merchantKeyId, final String merchantSecretKey, final String keyPass, final String keyFileName, final String keyAlias) {
			this.merchantId = merchantId;
			this.merchantKeyId = merchantKeyId;
			this.merchantSecretKey = merchantSecretKey;
			this.keyPass = keyPass;
			this.keyFileName = keyFileName;
			this.keyAlias = keyAlias;
		}
		
		public void setMerchantId(String merchantId) {
			this.merchantId = merchantId;
		}
		
		public void setMerchantKeyId(String merchantKeyId) {
			this.merchantKeyId = merchantKeyId;
		}
		
		public void setMerchantSecretKey(String merchantSecretKey) {
			this.merchantSecretKey = merchantSecretKey;
		}
		
		public void setKeyPass(String keyPass) {
			this.keyPass = keyPass;
		}
		
		public void setKeyFileName(String keyFileName) {
			this.keyFileName = keyFileName;
		}
		
		public void setKeyAlias(String keyAlias) {
			this.keyAlias = keyAlias;
		}
	}
}
