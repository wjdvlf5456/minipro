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

		List<Person> flist = new ArrayList<Person>();

		Reader fr = new FileReader(
				"//Users/choijungphil/javaStudy/workspace/minipro/src/com/javaex/phonebook/PhoneDB.txt");
		BufferedReader br = new BufferedReader(fr);
		BufferedReader brr = new BufferedReader(new InputStreamReader(System.in));

		// 프로그램 시작
		System.out.println("*******************************************");
		System.out.println("*         전화번호 관리 프로그램          *");
		System.out.println("*******************************************");

		System.out.println("");
		System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
		System.out.println("-------------------------------------------");

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

			flist.add(person);

		}
		while (true) {
			String contents = brr.readLine();
			System.out.print("<메뉴번호: ");
			int active = Integer.parseInt(contents);
			// ---------5.종료------------
			if (active == 5) {
				break;

				// ------------리스트------------
			} else if (active == 1) {
				System.out.println("<1.리스트>");
				int i = 1;
				for (Person person : flist) {
					System.out.print(i);
					person.showList();
					i++;
				}
			} else if (active == 2) {
				System.out.println("<2.등록>");
				
				

			} else if (active == 3) {
				System.out.println("<3.삭제>");

			} else if (active == 4) {
				System.out.println("<4.검색>");

			} else {
				System.out.println("다시입력해주세요");
			}

		}

		Writer fw = new FileWriter(
				"/Users/choijungphil/javaStudy/workspace/minipro/src/com/javaex/phonebook/PhoneDB.txt");
		BufferedWriter bw = new BufferedWriter(fw);

		for (Person person : flist) {
			String savestr = person.getName() + "," + person.getHp() + "," + person.getCompany();
			System.out.println(savestr);

			bw.write(savestr);
			bw.newLine();

		}

		bw.close();
		br.close();

	}

}
