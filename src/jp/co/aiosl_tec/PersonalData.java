package jp.co.aiosl_tec;

public class PersonalData {

	private String name; // ���O
	private String address; // �Z��
	private String tel; // �d�b�ԍ�
	private int age; // �N��

	// getter,setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Override
	public String toString() {
		String str = "�y" + this.name + "�z" + "�y" + this.address + "�z" 
					+ "�y" + this.tel + "�z" + "�y" + this.age + "�z";
		return str;

	}

}