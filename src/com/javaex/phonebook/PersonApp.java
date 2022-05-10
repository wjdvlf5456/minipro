package com.javaex.phonebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PersonApp {

	public static void main(String[] args) throws IOException {

		List<Person> fList = new ArrayList<Person>();

		Reader fr = new FileReader("./PhoneDB.txt");
		BufferedReader br = new BufferedReader(fr);
		BufferedReader brr = new BufferedReader(new InputStreamReader(System.in));

		// 프로그램 시작
		System.out.println("*******************************************");
		System.out.println("*         전화번호 관리 프로그램          *");
		System.out.println("*******************************************");

		// 메모장의 데이터 불러오기
		while (true) {
			String str = br.readLine();

			if (str == null) {
				break;
			}
			String[] personInfo = str.split(",");

			String name = personInfo[0];
			String hp = personInfo[1];
			String company = personInfo[2];

			Person person = new Person(name, hp, company);

			fList.add(person);

		}
		
		//메모장 불러오기
		Writer fw = new FileWriter("./PhoneDB.txt");
		BufferedWriter bw = new BufferedWriter(fw);

		while (true) {
			
			int i = 1;
			System.out.println("");
			System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
			System.out.println("-------------------------------------------");
			System.out.print("<메뉴번호: ");
			String contents = brr.readLine();
			int active = Integer.parseInt(contents);

			// ---------5.종료------------
			if (active == 5) {
				System.out.println("*******************************************");
				System.out.println("*                감사합니다               *");
				System.out.println("*******************************************");
				break;

				// ------------ 1.리스트 ------------
			} else if (active == 1) {
				System.out.println("<1.리스트>");
				for (Person person : fList) {
					System.out.print(i);
					person.showList();
					i++;
				}
				change(fList);
				// ------------ 2.등록 ------------
			} else if (active == 2) {
				System.out.println("<2.등록>");
				System.out.print(">이름: ");
				String name = brr.readLine();

				String hp = brr.readLine();
				System.out.print(">휴대전화: ");

				System.out.print(">회사전화: ");
				String company = brr.readLine();
				System.out.println("[등록되었습니다.]");

				Person person = new Person(name, hp, company);
				fList.add(person);
				change(fList);

				// ------------ 3.삭제 ------------
			} else if (active == 3) {
				System.out.println("<3.삭제>");
				System.out.print(">번호: ");
				String delete = brr.readLine();
				int deleteNumber = Integer.parseInt(delete) - 1;
				fList.remove(deleteNumber);
				change(fList);

				// ------------ 4.검색 ------------
			} else if (active == 4) {
				System.out.println("<4.검색>");
				System.out.print(">이름: ");
				String search = brr.readLine();
				char cSearch = search.charAt(0);

				for (Person person : fList) {
					String pName = person.getName();
					char cn1 = pName.charAt(0);		// 이
					char cn2 = pName.charAt(1);
					char cn3 = pName.charAt(2);
					if (cSearch == cn1 || cSearch == cn2 || cSearch == cn3) {
						System.out.print(i);
						person.showList();
					}
					i++;
				}
				change(fList);

				// ------------ 재입력 ------------
			} else {
				System.out.println("[다시 입력해 주세요]");
				change(fList);
			}
		}
		for (Person person : fList) {
			String savestr = person.getName() + "," + person.getHp() + "," + person.getCompany();

			bw.write(savestr);
			bw.newLine();
		}
		// while문 끝
		bw.close();
		br.close();

	}

	// 실시간으로 반영하기 위한 전역변수
	public static void change(List<Person> fList) throws IOException {
		OutputStream os = new FileOutputStream("./PhoneDB.txt");
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);

		for (Person person : fList) {
			String savestr = person.getName() + "," + person.getHp() + "," + person.getCompany();

			bw.write(savestr);
			bw.newLine();

		}

		bw.flush();
		bw.close();

	}

}
