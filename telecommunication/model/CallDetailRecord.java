package telecommunication.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import telecommunication.GetDetails;

public class CallDetailRecord {
	private int transactionId;
	private String customerNumber;
	private String anotherNumber;
	private Plan plan;
	private Date date;
	private int duration;
    private String singleRecord;
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getAnotherNumber() {
		return anotherNumber;
	}

	public void setAnotherNumber(String anotherNumber) {
		this.anotherNumber = anotherNumber;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public CallDetailRecord(int transactionId, String customerNumber, String anotherNumber, Plan plan, Date date,
			int duration) {
		super();
		this.transactionId = transactionId;
		this.customerNumber = customerNumber;
		this.anotherNumber = anotherNumber;
		this.plan = plan;
		this.date = date;
		this.duration = duration;
	}

	public CallDetailRecord(String singleRecord) throws ParseException {
		SimpleDateFormat inputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.singleRecord = singleRecord;
		String logDetail[] = this.singleRecord.split(",");
		this.transactionId = Integer.parseInt(logDetail[0]);
		this.customerNumber = logDetail[1];
		this.anotherNumber =  logDetail[2];
		this.date = inputFormat.parse(logDetail[4]);
		this.duration = Integer.parseInt(logDetail[5]);
		GetDetails getDetails = new GetDetails();
		this.plan = getDetails.getPlan(Integer.parseInt(logDetail[3]));
	}

}
