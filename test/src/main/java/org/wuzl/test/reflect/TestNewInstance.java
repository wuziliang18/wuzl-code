package org.wuzl.test.reflect;

public class TestNewInstance {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		int count = 100000;
		for (int i = 0; i < 100; i++) {
			HotelPaymentResult newInstance = newInstance();
		}
		long start=System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
			HotelPaymentResult newInstance = newInstance();
		}
		System.out.println(System.currentTimeMillis()-start);
	}

	public static HotelPaymentResult newInstance() throws InstantiationException, IllegalAccessException {
		HotelPaymentResult newInstance = HotelPaymentResult.class.newInstance();
		return newInstance;
	}
	public static HotelPaymentResult newObject() throws InstantiationException, IllegalAccessException {
		return new HotelPaymentResult();
	}
}
