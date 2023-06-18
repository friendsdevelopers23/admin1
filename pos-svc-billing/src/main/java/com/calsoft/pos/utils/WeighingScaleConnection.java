package com.calsoft.pos.utils;

import org.springframework.stereotype.Component;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;


@Component
public class WeighingScaleConnection {

	private static SerialPort serialPort;

	public Double getWeight(String serialLine) {

		Double receivedData = 0.0;
		serialPort = new SerialPort(serialLine);
		try {
			// opening port
			serialPort.openPort();

			serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			SerialPortEvent event = new SerialPortEvent(serialLine, 1, 9);

			receivedData = Double.valueOf(serialPort.readString(event.getEventValue()));

			serialPort.closePort();

			System.out.println(receivedData);
		} catch (SerialPortException ex) {
			System.out.println("Error in writing data to port: " + ex);
		}
		return receivedData;
	}

}
