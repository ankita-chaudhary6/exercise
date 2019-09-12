package telecommunication;

import java.text.ParseException;

import telecommunication.model.Plan;

public class BillGenerate {

	public static void main(String[] args) throws ParseException {
		
		GetDetails getDetails = new GetDetails();
		getDetails.generateBill();

	}
}
