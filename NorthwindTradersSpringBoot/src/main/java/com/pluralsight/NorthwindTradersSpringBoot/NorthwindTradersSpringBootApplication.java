package com.pluralsight.NorthwindTradersSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class NorthwindTradersSpringBootApplication {

	private static ProductDAO productDAO;
	private static ApplicationContext appContenxt;

	public static void main(String[] args) {

		appContenxt = SpringApplication.run(NorthwindTradersSpringBootApplication.class, args);
		productDAO = appContenxt.getBean(ProductDAO.class);

		String options = """
                Please select from the following choices:
                1 - Add Product
                2 - List All Products
                99 - Quit

                >>>\s""";

		int selection;

		// User Interface Loop
		do {
			selection = Console.PromptForInt(options);
			switch (selection) {
				case 1 -> processAddProduct();
				case 2 -> processGetAllProducts();
				case 99 -> System.exit(0);
				default -> System.out.println("Invalid selection. Please try again.");
			}
		} while (true);

	}

	private static void processGetAllProducts() {
		List<Product> products = productDAO.getAll();

		for(Product p : products){
			System.out.println(p);
		}
	}

	private static void processAddProduct() {
		int productId = Console.PromptForInt("Please enter the Product ID: ");
		String name = Console.PromptForString("Please enter the Product Name: ");
		String category = Console.PromptForString("Please enter the Category: ");
		double price = Console.PromptForDouble("Please enter the price: ");
		Product p1 = new Product(productId, name, category, price);

		productDAO.add(p1);
	}

}
