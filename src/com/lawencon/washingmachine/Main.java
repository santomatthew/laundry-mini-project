package com.lawencon.washingmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public final List<String> listDirtyClothes = new ArrayList<String>();
	public final List<String> listCleanClothes = new ArrayList<String>();
	public final List<String> listTakenClothes = new ArrayList<String>();

	public static void main(String[] args) {
		final Main main = new Main();
		main.showMenu();
	}

	public static int scannerInt(String question, int min, int max, boolean type) throws RuntimeException {
		System.out.print(question);
		final Scanner scan = new Scanner(System.in);

		try {
			final String inputChecker = scan.nextLine().trim();
			if(inputChecker.equals("")) {
				System.out.println("Invalid Input");
				return scannerInt(question,min,max,type);
			}
			else {
				final int input = Integer.parseInt(inputChecker);
				if (type && input >= min) {
					return input;
				} else if (type && input < min) {
					System.out.println("Inputan tidak boleh lebih kecil dari 1");
					return scannerInt(question, min, max, true);
				} else if (input >= min && input <= max) {
					return input;
				} else if (input < min) {
					System.out.println("Inputan tidak boleh lebih kecil dari 1");
					return scannerInt(question, min, max, false);
				} else if (input > max) {
					System.out.println("Input melebihi batas");
					return scannerInt(question, min, max, false);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return scannerInt(question, min, max, type);
		}
		return scannerInt(question, min, max, type);

	}

	public static String scannerName(String question) {
		System.out.print(question);
		final Scanner scan = new Scanner(System.in);
		final String name = scan.nextLine();
		final String nameChecker = name.trim();
		if (nameChecker.equals("")) {
			System.out.println("Invalid Input");
			return scannerName(question);
		} else {
			return name;
		}
	}

	public void showMenu() {
		System.out.println(
				"1. Input Pakaian kotor \n2. Cuci Pakaian \n3. Ambil Pakaian bersih \n4. Laporan \n5. Keluar Aplikasi");
		final int chooseMenu = scannerInt("Pilih Menu :", 1, 5, false);
		showChosenMenu(chooseMenu);
	}

	public void showChosenMenu(int chooseMenu) {
		if (chooseMenu == 1) {
			showInputDirtyClothesMenu();
		} else if (chooseMenu == 2) {
			if (listDirtyClothes.size() >= 1) {
				showWashDirtyClothesMenu();
			} else {
				System.out.println("Pakaian kotor anda belum ada");
				showMenu();
			}

		} else if (chooseMenu == 3) {
			if (listCleanClothes.size() >= 1) {
				showCleanClothesMenu();
			} else {
				System.out.println("Pakaian bersih anda belum ada");
				showMenu();
			}

		} else if (chooseMenu == 4) {
			showLaundryReports();
		} else if (chooseMenu == 5) {
			System.out.println("Anda keluar menu");
		}
	}

	public void showInputDirtyClothesMenu() {
		final int totalDirtyClothes = scannerInt("Berapa pakaian kotor? :", 1, 1, true);
		addDirtyClothes(totalDirtyClothes);
		showMenu();
	}

	public void addDirtyClothes(int totalDirtyClothes) {
		for (int i = 0; i < totalDirtyClothes; i++) {
			final String clothesName = scannerName("Input pakaian ke -" + (i + 1) + " : ");
			listDirtyClothes.add(clothesName);
		}
		System.out.println("Pakaian Kotor sejumlah " + totalDirtyClothes + " telah diinputkan");
	}

	public void showWashDirtyClothesMenu() {
		final int maxDirtyClothes = listDirtyClothes.size();
		final int totalDirtyClothesToWash = scannerInt("Berapa pakaian yang mau dicuci? :", 1, maxDirtyClothes, false);
		washClothes(totalDirtyClothesToWash);
		showMenu();
	}

	public void washClothes(int totalDirtyClothesToWash) {
		for (int i = 0; i < totalDirtyClothesToWash; i++) {
			final String cleanClothes = listDirtyClothes.get(0);
			listCleanClothes.add(cleanClothes);
			listDirtyClothes.remove(0);
			
		}
		System.out.println(totalDirtyClothesToWash + " pakaian telah tercuci bersih");
	}

	public void showCleanClothesMenu() {
		final int maxCleanClothes = listCleanClothes.size();
		final int totalCleanClothesToTake = scannerInt("Berapa jumlah pakaian bersih yang mau diambil? :", 1,
				maxCleanClothes, false);
		takeCleanClothes(totalCleanClothesToTake);
		showMenu();
	}

	public void takeCleanClothes(int totalCleanClothesToTake) {
		for (int i = 0; i < totalCleanClothesToTake; i++) {
			final String takeClothes = listCleanClothes.get(0);
			listTakenClothes.add(takeClothes);
			listCleanClothes.remove(0);
		}
		System.out.println(totalCleanClothesToTake + " pakaian telah diambil");
	}

	public void showLaundryReports() {
		System.out.println("||Total Pakaian Kotor : " + listDirtyClothes.size() + "|| Total Pakaian Bersih :"
				+ listCleanClothes.size() + "|| Total Pakaian yang sudah diambil : " + listTakenClothes.size());
		showMenu();
	}

}
