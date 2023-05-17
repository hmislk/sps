package org.ohmis.serialsender;

import com.fazecast.jSerialComm.SerialPort;

import java.util.Random;

public class SerialSender {

    private static String portName = "com5"; // Change this to the appropriate port name
    private static int baudRate = 9600; // Change this to the desired baud rate

    public static void main(String[] args) {
        SerialPort serialPort = SerialPort.getCommPort(portName);
        serialPort.setBaudRate(baudRate);

        if (serialPort.openPort()) {
            System.out.println("Serial port opened successfully.");

            Random random = new Random();
            byte[] data = new byte[10]; // Change the length of the string as needed

            while (true) {
                random.nextBytes(data);
                serialPort.writeBytes(data, data.length);
                System.out.println("Sent: " + new String(data));

                try {
                    Thread.sleep(1000); // Change the interval between sending strings as needed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Failed to open serial port.");
        }
    }
}
