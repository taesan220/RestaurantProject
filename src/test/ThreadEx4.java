package test;

public class ThreadEx4 {
	static long startTime = 0;

	public static void main(String[] args) {
		ThreadEx5_1 th1 = new ThreadEx5_1();
		th1.start();
		startTime = System.currentTimeMillis();

		for (int i = 0; i < 300; i++) {
			System.out.print("-");
		}
		System.out.print("�ҿ�ð� : "
				+ (System.currentTimeMillis() - ThreadEx4.startTime));
	}
}

class ThreadEx5_1 extends Thread {

	public void run() {
		for (int i = 0; i < 300; i++) {
			System.out.print("|");
		}
		System.out.print("�ҿ�ð� : "
				+ (System.currentTimeMillis() - ThreadEx4.startTime));
	}
}
