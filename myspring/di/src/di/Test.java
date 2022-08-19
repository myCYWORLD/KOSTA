package di;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.repository.ProductRepository;
import com.my.service.CustomerService;
import com.my.service.ProductService;

public class Test {

	public static void main(String[] args) {
		Product p1, p2;
//		p1= new Product(); (X)
		//스프링 컨테이너 구동 (spring용 library를 사용해야함)
		String configurationPath = "configuration2.xml";
		ClassPathXmlApplicationContext ctx;
		ctx = new ClassPathXmlApplicationContext(configurationPath);
		
		//Product 객체 찾아 사용하기
		//("key값",  casting 할 수 있는 자료형으로) Product타입으로 다운캐스팅이 가능한가
		p1 = ctx.getBean("p", Product.class); 
		System.out.println(p1.hashCode());
		System.out.println(p1);
		
		p2 = ctx.getBean("p", Product.class); 
		System.out.println(p2.hashCode());
		System.out.println(p1 == p2);
		
		CustomerService service = ctx.getBean("customerService", CustomerService.class);
		System.out.println(service.hashCode());
		
		//서비스에 주입된 리파지토리 얻기
		ProductService pService = ctx.getBean("productService", ProductService.class);
		//getBean method -> 컨테이너에서 객체를 찾는 역할 (구체화된 클래스가 아니라 일반화된 인터페이스 타입의 클래스를 사용하는 것이 좋음)
//		ProductRepository r1 = pService.getRepository();
		
		//리파지토리 찾기
		ProductRepository r2 = ctx.getBean("productRepository", ProductRepository.class);

//		System.out.println(r1 == r2);
		
		try {
			Product p = r2.selectByProdNo("C0001");
			System.out.println(p);//p.toString()자동호출됨
		}catch (FindException e) {
			e.printStackTrace();
		}
		
	}

}