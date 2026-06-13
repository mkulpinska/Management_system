package com.company.enroller.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadData {

    public class ReadFile {

        public static void main(String[] args) {
            String filePath = "dane.txt";

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String linia;
                while ((linia = br.readLine()) != null) {
                    System.out.println(linia);
                }
            } catch (IOException e) {
                System.out.println("Wystąpił błąd podczas odczytu pliku: " + e.getMessage());
            }
        }
    }
}


