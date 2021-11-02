package jp.co.aiosl_tec;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Kadai5 {

	// parsePersonalDataメソッドの繰り返し回数
	// CSVの行数をカウント
	static int count = 0;

	public static void main(String[] args) {
		
		//インスタンス生成
		Kadai5 kadai5 = new Kadai5();
		// CSVのパス
		final String PATH = "C:\\pleiades-2021-09-java-win-64bit-jre_20210919\\pleiades\\workspace\\kadai5\\csv\\kadai5b.csv";
		// CSVデータ代入用のList
		List<PersonalData> personalData;
		// CSVデータの取得
		personalData = kadai5.readPersonalDatas(PATH);
		// CSVデータの一覧表示
		kadai5.showPersonalDataList(personalData);
	}

	// CSVファイルから各行を読み込み、Listで返す
	public List<PersonalData> readPersonalDatas(String path) {

		ArrayList<PersonalData> dataList = new ArrayList<PersonalData>();

		try (FileInputStream file = new FileInputStream(path);
				InputStreamReader inputStreamReader = new InputStreamReader(file);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
			// CSVファイルを1行ごと読み込む
			String[] data = null;
			String line = bufferedReader.readLine();

			// 行をカンマ区切りで配列に変換
			while (line != null) {
				data = (line.split("\n", 0));
				line = bufferedReader.readLine();
				for (String value : data) {
					dataList.add(parsePersonalData(value));
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("CSVファイルが見つかりません。");

		} catch (IOException e) {
			System.out.println("データの読み込みに失敗しました。");
		}

		return dataList;

	}

	// カンマ区切りの一人分のデータを解析してPersonalDataクラスに変換する
	public PersonalData parsePersonalData(String value) {

		// 電話番号の正規表現
		Pattern patternTel = Pattern.compile("^[0-9]{2,3}-[0-9]{4}-[0-9]{4}$");
		// 年齢の正規表現
		Pattern patternAge = Pattern.compile("^[0-9]{1,3}");
		// return用
		PersonalData personalData = new PersonalData();
		// 行数のカウント
		count++;
		// 不正データフラグ
		boolean hasError = false;
		// 一人分のデータを各要素に分割
		String[] data = value.split(",", 0);

		// データの不正を検出
		for (int i = 0; i < data.length; i++) {

			if (i == 0 && data[i].isEmpty()) {
				System.out.println("CSVの" + count + "行目の名前がnullです。");
				hasError = true;
			}
			if (i == 1 && data[i].isEmpty()) {
				System.out.println("CSVの" + count + "行目の住所がnullです。");
				hasError = true;
			}
			if (i == 2 && data[i].isEmpty()) {
				System.out.println("CSVの" + count + "行目の電話番号がnullです。");
				hasError = true;

			} else if (i == 2 && !patternTel.matcher(data[i]).matches()) {
				System.out.println("CSVの" + count + "行目の電話番号が不正です。");
				hasError = true;
			}
			if (i == 3 && data[i].isEmpty()) {
				System.out.println("CSVの" + count + "行目の年齢がnullです。");
				hasError = true;

			} else if (i == 3 && !patternAge.matcher(data[i]).matches()) {
				System.out.println("CSVの" + count + "行目の年齢が不正です。");
				hasError = true;
			}
		}
		// データに不正がない場合、personalDataを返す
		// データに不正がある場合、nullを返す
		try {
			if (hasError == false) {
				personalData.setName(data[0]);
				personalData.setAddress(data[1]);
				personalData.setTel(data[2]);
				personalData.setAge(Integer.valueOf(data[3]));
				return personalData;
			} else {
				return null;
			}
		} catch (NumberFormatException e) {
			System.out.println(e);
			return null;
		}
	}

	// リストに格納されたPersonalDataクラスの内容を一覧表示する。
	public void showPersonalDataList(List<PersonalData> list) {
		// nullのデータ以外を一覧表示する
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				System.out.println(list.get(i).toString());
			}
		}
	}
}
