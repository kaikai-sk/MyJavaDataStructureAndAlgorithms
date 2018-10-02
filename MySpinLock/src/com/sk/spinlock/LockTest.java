package com.sk.spinlock;

public class LockTest implements Runnable
{
	static int sum;
	private SpinLock lock;
	
	public LockTest(SpinLock lock)
	{
		this.lock=lock;
	}
	
	public static void main(String[] args) throws Exception
	{
		SpinLock lock=new SpinLock();
		for(int i=0;i<100;i++)
		{
			LockTest test=new LockTest(lock);
			Thread t=new Thread(test);
			t.start();
		}
		Thread.currentThread().sleep(1000);
		System.out.println(sum);
	}
	
	@Override
	public void run()
	{
		this.lock.lock();
		sum++;
		this.lock.unlock();
	}

}
