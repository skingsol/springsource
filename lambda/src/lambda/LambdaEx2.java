package lambda;

public class LambdaEx2 {

	public static void main(String[] args) {
		// Lambda2 인터페이스를 사용하고 싶다면?
		// 1) 구현 클래스를 작성 - ex) 클래스명 implements Lambda2
		// 2) 익명 구현 클래스 작성
//		lambda2 lamda = new lambda2() {
//			
//			@Override
//			public void method() {
//				System.out.println("익명 구현 클래스");
//			}
//		};
//		lambda.method();
		
		lambda2 lambda = () -> System.out.println("익명 구현 클래스");
		lambda.method();
		
		lambda = () -> {
			int i = 10;
			System.out.println(i * i);
		};
		lambda.method();
	}
}
