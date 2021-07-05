package chace.anzwrapper;

import lombok.Data;
import lombok.Getter;

@Data
public class Recipient {

	@Getter
	private String firstName, lastName;
	@Getter
	private RecipientAddress address;
	@Getter
	private String emailAddress;
	@Getter
	private String phoneNumber;
	
	public Recipient(String firstName, String lastName, RecipientAddress address, String emailAddress, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public RecipientAddress getAddress() {
		return address;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	static class RecipientAddress {
		public int houseNumber;
		public String streetName;
		public String streetPrefix;
		public String locality;
		public String administrativeArea;
		public int postCode;
		public String countryPrefix;
		
		public RecipientAddress(int houseNumber, String streetName, String streetPrefix, String locality, String administrativeArea, int postCode, String countryPrefix) {
			this.houseNumber = houseNumber;
			this.streetName = streetName;
			this.streetPrefix = streetPrefix;
			this.locality = locality;
			this.administrativeArea = administrativeArea;
			this.postCode = postCode;
			this.countryPrefix = countryPrefix;
		}
		
		public void setHouseNumber(int houseNumber) {
			this.houseNumber = houseNumber;
		}
		
		public void setStreetName(String streetName) {
			this.streetName = streetName;
		}
		
		public void setStreetPrefix(String streetPrefix) {
			this.streetPrefix = streetPrefix;
		}
		
		public void setLocality(String locality) {
			this.locality = locality;
		}
		
		public void setAdministrativeArea(String administrativeArea) {
			this.administrativeArea = administrativeArea;
		}
		
		public void setPostCode(int postCode) {
			this.postCode = postCode;
		}
		
		public void setCountryPrefix(String countryPrefix) {
			this.countryPrefix = countryPrefix;
		}
	}
} 
