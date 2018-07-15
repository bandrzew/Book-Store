package pl.coderslab.app;

import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

public class SpringDiApplication {
	public static void main(String[] args) {
		LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
		emfb.setPersistenceUnitName("bookstorePersistenceUnit");
		System.out.println("działa");
	}
}
