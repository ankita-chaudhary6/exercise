package telecommunication;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import telecommunication.model.CallDetailRecord;
import telecommunication.model.Plan;

public class GetDetails {

	 Plan[] plans = {
    		 new Plan(1,100,1,200),
    		 new Plan(2,200,1,400)
     };

	public Map<String, List<CallDetailRecord>> fetchDetails() throws ParseException {
		FileReader reader = null;
		BufferedReader br = null;
		List<CallDetailRecord> cdr = new ArrayList<CallDetailRecord>();
		List<String> phoneNumbers = new ArrayList<String>();
		Map<String, List<CallDetailRecord>> callMap = new HashMap<String, List<CallDetailRecord>>();
		try {
			reader = new FileReader("/Users/AnkitaChaudhary/eclipse-workspace/input.txt");
			br = new BufferedReader(reader);

			String line;
			while ((line = br.readLine()) != null) {
				CallDetailRecord detail = new CallDetailRecord(line);
			 callMap = addCallDetailToMap(detail);
			 System.out.println(callMap);
//				cdr.add(detail);
			}

//			for (CallDetailRecord record : cdr) {
//				if (!phoneNumbers.contains(record.getCustomerNumber())) {
//					phoneNumbers.add(record.getCustomerNumber());
//				}
//			}
//			for (String number : phoneNumbers) {
//				List<CallDetailRecord> details = new ArrayList<CallDetailRecord>();
//				for (CallDetailRecord eachRecord : cdr) {
//					if (eachRecord.getCustomerNumber().equalsIgnoreCase(number)) {
//						details.add(eachRecord);
//					}
//				}
//				callMap.put(number, details);
//			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return callMap;
	}

	private Map<String, List<CallDetailRecord>> addCallDetailToMap(CallDetailRecord detail) {
		List<String> phoneNumbers = new ArrayList<String>();
		List<CallDetailRecord> eachRecord = new ArrayList<CallDetailRecord>();
		Map<String, List<CallDetailRecord>> callMap = new HashMap<String, List<CallDetailRecord>>();
		if (!phoneNumbers.contains(detail.getCustomerNumber())) {
			phoneNumbers.add(detail.getCustomerNumber());
		}
		for (String number : phoneNumbers) {
				if (detail.getCustomerNumber().equalsIgnoreCase(number)) {
					eachRecord.add(detail);
				}
			
			callMap.put(number, eachRecord);
		}
		System.out.println(callMap);
		return callMap;
	}

	public Plan getPlan(int planId) {
		for(int i=0;i<plans.length;i++)
		{
			if(planId == plans[i].getId())
			return plans[i];
		}
		return null;
		
	}

	public void generateBill() throws ParseException {
		PrintWriter write = null;
		int additionalMinutes = 0;
		Map<String, List<CallDetailRecord>> planDetails = fetchDetails();
		System.out.println(planDetails);
		for (Entry<String, List<CallDetailRecord>> key : planDetails.entrySet()) {
			//generateInvoice
			additionalMinutes = 0;
			List<CallDetailRecord> display = new ArrayList<CallDetailRecord>();
			List<CallDetailRecord> value = key.getValue();
			for (CallDetailRecord plan : value) {
				additionalMinutes = additionalMinutes + plan.getDuration();
				display.add(plan);

				try {
					write = new PrintWriter("/Users/AnkitaChaudhary/eclipse-workspace/" + key.getKey() + ".txt");
					write.println("Bill for " + key.getKey());
					write.println();
					write.println("Item Description \t\t\t\t Amount");
					write.println("================ \t\t\t\t ======");
					write.println("Base talk time fare \t\t\t\t    " + plan.getPlan().getBaseFare());
					write.println("Additional talk time fare (" + (additionalMinutes - plan.getPlan().getTalktime())
							+ ") minutes\t\t    " + (additionalMinutes - plan.getPlan().getTalktime()));
					write.println("\t\t\t\t\t\t ======");
					write.println("Amount to pay" + " \t\t\t\t\t    "
							+ (plan.getPlan().getBaseFare() + (additionalMinutes - plan.getPlan().getTalktime())));
					write.println("Call Details");
					write.println("========");
					write.println("Date \t\t\t  Called Number\t       Duration");
					write.println("================== \t ============== \t=======");
					for (CallDetailRecord record : display) {
						SimpleDateFormat outPutFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
						write.println(outPutFormat.format(record.getDate()) + "\t     " + record.getAnotherNumber() + "\t\t    "
								+ record.getDuration());
					}
					write.println("\t\t\t\t\t\t ======");
					write.println("Total Call Duration \t\t\t\t    " + (additionalMinutes));
					write.println("\t\t\t\t\t\t ======");

					write.println("Additional minutes (" + additionalMinutes + " - " + plan.getPlan().getTalktime()
							+ ") \t\t\t    " + (additionalMinutes - plan.getPlan().getTalktime()));

					write.close();

				} catch (FileNotFoundException e) {

					e.printStackTrace();
				}

			}
		}
	}

}
