/* *Class created on [ Aug 20, 2008 | 11:50:14 PM ] */
package com.asta.app2.webapp.util;

import java.io.Serializable;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author <a href="mailto:saeid3@gmail.com">Saeid Moradi</a>
 */
public class ChairModelForPrint extends AbstractTableModel implements
		Serializable {
	private static final long serialVersionUID = -5040473640906990834L;

	private String[] columnNames = { "chairNumber", "passenger",
			"ticketNumber", "ticketPrice" };

	private Object[][] data = {
			{ "Berne", new Integer(9).toString(), "James Schneider",
					"277 Seventh Av." },
			{ "Berne", new Integer(22).toString(), "Bill Ott",
					"250 - 20th Ave." },
			{ "Boston", new Integer(32).toString(), "Michael Ott",
					"339 College Av." } };

	private String chairNumber;
	private String passenger;
	private String ticketNumber;
	private String ticketPrice;

	public ChairModelForPrint() {
	}

	public ChairModelForPrint(Object[][] data) {
		this.data = data;
	}

	public ChairModelForPrint(String chairNumber,String passenger,String ticketNumber,String ticketPrice) {
		this.chairNumber = chairNumber;
		this.passenger = passenger;
		this.ticketNumber = ticketNumber;
		this.ticketPrice = ticketPrice;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public String getColumnName(int columnIndex) {
		return this.columnNames[columnIndex];
	}

	public int getRowCount() {
		return this.data.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.data[rowIndex][columnIndex];
	}

	public String getChairNumber() {
		return chairNumber;
	}

	public void setChairNumber(String chairNumber) {
		this.chairNumber = chairNumber;
	}

	public String getPassenger() {
		return passenger;
	}

	public void setPassenger(String passenger) {
		this.passenger = passenger;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

}
