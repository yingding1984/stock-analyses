/**
 * 
 */
package com.obv.core.httpunit.test;

import java.io.IOException;
import java.util.ArrayList;

import org.xml.sax.SAXException;

import com.meterware.httpunit.HttpUnitOptions;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;
import com.meterware.httpunit.WebTable;

/**
 * @author yingdin
 * 
 */
public class HttpUnitRoom {

	public static ArrayList<String> getPriceVolume() throws IOException,
			SAXException {
		ArrayList<String> priceVolumeResults = new ArrayList<String>();
		HttpUnitOptions.setScriptingEnabled(false);
		WebConversation wc = new WebConversation();
		WebResponse response = wc
				.getResponse("http://finance.yahoo.com/q/hp?s=002243.SZ&a=05&b=17&c=2013&d=08&e=4&f=2013&g=d");
		WebTable[] table = response.getTables();

		String infoTable = table[4].getTableCell(1, 0).getText()
				.replaceAll(",", "");
		String[] strlines = infoTable.split("\n");
		for (int i = 0; i < strlines.length; i++) {
			if (strlines[i].contains("2013")) {
				priceVolumeResults.add(strlines[i]);
			}
		}

		for (int i = 0; i < priceVolumeResults.size(); i++) {

			String recordLine = priceVolumeResults.get(i);
			// System.out.println(priceVolumeResults.get(i));
			String[] recordColumns = recordLine.split("\\|");
			System.out.println(i + " row data is : Date:'" + recordColumns[1]
					+ "' Open:'" + recordColumns[2] + "' High:'"
					+ recordColumns[3] + "' Low:'" + recordColumns[4]
					+ "' Close:'" + recordColumns[5] + "' Volumn:'"
					+ recordColumns[6] + "' ");
		}

		/*
		 * String recordLine = priceVolumeResults.get(0); // String record =
		 * recordLine.replaceAll(" ", ""); String[] recordColumns =
		 * recordLine.split("\\|"); for (int i = 0; i < recordColumns.length;
		 * i++) { if (i != 1) recordColumns[i] =
		 * recordColumns[i].replaceAll(" ", "");
		 * System.out.println(recordColumns[i]); }
		 */
		return priceVolumeResults;
	}

	public static void main(String[] args) throws IOException, SAXException {
		getPriceVolume();
		// String s = "|111|2|3|4";
		// String[] s_arr = s.split("111");
		// for(int i = 0; i <s_arr.length;i++){
		// System.out.println(s_arr[i]);
		// }
	}

}
