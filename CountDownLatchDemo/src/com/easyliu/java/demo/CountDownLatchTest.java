package com.easyliu.java.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
	private static CountDownLatch sCountDownLatch = null;
	private static final int THREAD_NUMBER = 3;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		sCountDownLatch = new CountDownLatch(THREAD_NUMBER);
		//�̳߳�
		ExecutorService fixedThreadPool = Executors
				.newFixedThreadPool(THREAD_NUMBER);
		//ִ���߳�
		fixedThreadPool.execute(new ConsumeRunnable("one"));
		fixedThreadPool.execute(new ConsumeRunnable("two"));
		fixedThreadPool.execute(new ConsumeRunnable("three"));
		System.out.println("�ȴ�3�����߳�ִ�����...");
		try {
			sCountDownLatch.await();
			System.out.println("3�����߳��Ѿ�ִ�����");
			System.out.println("����ִ�����߳�");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static class ConsumeRunnable implements Runnable {
		private String mName;

		public ConsumeRunnable(String name) {
			this.mName = name;
		}
		public void run() {
			System.out.println("���߳�" + mName + "����ִ��");
			try {
				Thread.sleep(3000);// ģ���ʱ����
				System.out.println("���߳�" + mName + "ִ�����");
				sCountDownLatch.countDown();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
