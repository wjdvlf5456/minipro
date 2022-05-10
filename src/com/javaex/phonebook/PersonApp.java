package com.javaex.phonebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PersonApp {

	public static void main(String[] args) throws IOException {

		List<Person> fList = new ArrayList<Person>();

		Reader fr = new FileReader(
				"//Users/choijungphil/javaStudy/workspace/minipro/src/com/javaex/phonebook/PhoneDB.txt");
		BufferedReader br = new BufferedReader(fr);
		BufferedReader brr = new BufferedReader(new InputStreamReader(System.in));
		Writer fw = new FileWriter(
				"/Users/choijungphil/javaStudy/workspace/minipro/src/com/javaex/phonebook/PhoneDB_결과.txt", true);
		BufferedWriter bw = new BufferedWriter(fw);

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
				// ------------ 2.등록 ------------
			} else if (active == 2) {
				System.out.println("<2.등록>");
				System.out.print(">이름: ");
				String name = brr.readLine();

				System.out.print(">휴대전화: ");
				String hp = brr.readLine();

				System.out.print(">회사전화: ");
				String company = brr.readLine();
				System.out.println("[등록되었습니다.]");

				Person person = new Person(name, hp, company);
				fList.add(person);

				// ------------ 3.삭제 ------------
			} else if (active == 3) {
				System.out.println("<3.삭제>");
				System.out.print(">번호: ");
				String delete = brr.readLine();
				int deleteNumber = Integer.parseInt(delete) - 1;
				fList.remove(deleteNumber);

				// ------------ 4.검색 ------------
			} else if (active == 4) {
				System.out.println("<4.검색>");
				System.out.print(">이름: ");
				String search = brr.readLine();
				char cSearch = search.charAt(0);

				for (Person person : fList) {
					String pName = person.getName();
					char cn1 = pName.charAt(0);
					char cn2 = pName.charAt(1);
					char cn3 = pName.charAt(2);
					if (cSearch == cn1 || cSearch == cn2 || cSearch == cn3) {
						System.out.print(i);
						person.showList();
					}
					i++;
				}

				// ------------ 재입력 ------------
			} else {
				System.out.println("[다시 입력해 주세요]");
			}

			for (Person person : fList) {
				String savestr = person.getName() + "," + person.getHp() + "," + person.getCompany();

				bw.write(savestr);
				bw.newLine();
				bw.flush();
			}

		}
		// while문 끝

		bw.close();
		br.close();

	}

}
