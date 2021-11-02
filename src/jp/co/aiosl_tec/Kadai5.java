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

	// parsePersonalData���\�b�h�̌J��Ԃ���
	// CSV�̍s�����J�E���g
	static int count = 0;

	public static void main(String[] args) {
		
		//�C���X�^���X����
		Kadai5 kadai5 = new Kadai5();
		// CSV�̃p�X
		final String PATH = "C:\\pleiades-2021-09-java-win-64bit-jre_20210919\\pleiades\\workspace\\kadai5\\csv\\kadai5b.csv";
		// CSV�f�[�^����p��List
		List<PersonalData> personalData;
		// CSV�f�[�^�̎擾
		personalData = kadai5.readPersonalDatas(PATH);
		// CSV�f�[�^�̈ꗗ�\��
		kadai5.showPersonalDataList(personalData);
	}

	// CSV�t�@�C������e�s��ǂݍ��݁AList�ŕԂ�
	public List<PersonalData> readPersonalDatas(String path) {

		ArrayList<PersonalData> dataList = new ArrayList<PersonalData>();

		try (FileInputStream file = new FileInputStream(path);
				InputStreamReader inputStreamReader = new InputStreamReader(file);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
			// CSV�t�@�C����1�s���Ɠǂݍ���
			String[] data = null;
			String line = bufferedReader.readLine();

			// �s���J���}��؂�Ŕz��ɕϊ�
			while (line != null) {
				data = (line.split("\n", 0));
				line = bufferedReader.readLine();
				for (String value : data) {
					dataList.add(parsePersonalData(value));
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("CSV�t�@�C����������܂���B");

		} catch (IOException e) {
			System.out.println("�f�[�^�̓ǂݍ��݂Ɏ��s���܂����B");
		}

		return dataList;

	}

	// �J���}��؂�̈�l���̃f�[�^����͂���PersonalData�N���X�ɕϊ�����
	public PersonalData parsePersonalData(String value) {

		// �d�b�ԍ��̐��K�\��
		Pattern patternTel = Pattern.compile("^[0-9]{2,3}-[0-9]{4}-[0-9]{4}$");
		// �N��̐��K�\��
		Pattern patternAge = Pattern.compile("^[0-9]{1,3}");
		// return�p
		PersonalData personalData = new PersonalData();
		// �s���̃J�E���g
		count++;
		// �s���f�[�^�t���O
		boolean hasError = false;
		// ��l���̃f�[�^���e�v�f�ɕ���
		String[] data = value.split(",", 0);

		// �f�[�^�̕s�������o
		for (int i = 0; i < data.length; i++) {

			if (i == 0 && data[i].isEmpty()) {
				System.out.println("CSV��" + count + "�s�ڂ̖��O��null�ł��B");
				hasError = true;
			}
			if (i == 1 && data[i].isEmpty()) {
				System.out.println("CSV��" + count + "�s�ڂ̏Z����null�ł��B");
				hasError = true;
			}
			if (i == 2 && data[i].isEmpty()) {
				System.out.println("CSV��" + count + "�s�ڂ̓d�b�ԍ���null�ł��B");
				hasError = true;

			} else if (i == 2 && !patternTel.matcher(data[i]).matches()) {
				System.out.println("CSV��" + count + "�s�ڂ̓d�b�ԍ����s���ł��B");
				hasError = true;
			}
			if (i == 3 && data[i].isEmpty()) {
				System.out.println("CSV��" + count + "�s�ڂ̔N�null�ł��B");
				hasError = true;

			} else if (i == 3 && !patternAge.matcher(data[i]).matches()) {
				System.out.println("CSV��" + count + "�s�ڂ̔N��s���ł��B");
				hasError = true;
			}
		}
		// �f�[�^�ɕs�����Ȃ��ꍇ�ApersonalData��Ԃ�
		// �f�[�^�ɕs��������ꍇ�Anull��Ԃ�
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

	// ���X�g�Ɋi�[���ꂽPersonalData�N���X�̓��e���ꗗ�\������B
	public void showPersonalDataList(List<PersonalData> list) {
		// null�̃f�[�^�ȊO���ꗗ�\������
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				System.out.println(list.get(i).toString());
			}
		}
	}
}
