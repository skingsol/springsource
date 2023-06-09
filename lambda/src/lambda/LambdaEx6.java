package lambda;

import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaEx6 {
	public static void main(String[] args) {
		
		// 문자열을 리턴하는 기능을 구현
		Supplier<String> s = () -> "String";
		System.out.println(s.get());
		
		// 문자열을 입력받아서 문자열의 길이가 0인지 판단 기능 구현
		Predicate<String> p = (str) -> str.length() == 0;  
		System.out.println(p.test("문자열 길이"));
		
		// 문자열을 입력받아서 출력하는 기능 구현
		Consumer<String> c = i -> System.out.println(i);
		c.accept("반갑습니다");
		
		// 문자열을 입력받아서 정수로 리턴하는 기능 구현
		Function<String, Integer> f = str ->  Integer.parseInt(str)+39;
		System.out.println(f.apply("561"));
	}
}
