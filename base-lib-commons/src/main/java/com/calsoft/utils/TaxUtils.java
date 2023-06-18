package com.calsoft.utils;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TaxUtils {

	public static double getTaxAmout(double price, double taxPercent, int inclusiveTax, int gst,double initialTaxAmount) {

		if (inclusiveTax == 1) {

			double total = (price*taxPercent)/(100+taxPercent);

			total = Math.abs(total);

			if (gst == 1) {

				total=price-total;
				
				total =(total*initialTaxAmount)/100;
				
			}

			return total;
		} else {
			if (gst == 1) {
				return (price * initialTaxAmount) / 100;
			}else {
				return (price * taxPercent) / 100;
			}
			
		}
	}

}
