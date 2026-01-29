package com.bcc.cca.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bcc.cca.entites.Admin;
import com.bcc.cca.repositories.AdminRepository;
import com.bcc.cca.repositories.AdressRepository;
import com.bcc.cca.repositories.CarBrandRepository;
import com.bcc.cca.repositories.CarRepository;
import com.bcc.cca.repositories.CardInfoRepository;
import com.bcc.cca.repositories.CategoryRepository;
import com.bcc.cca.repositories.ClientRepository;
import com.bcc.cca.repositories.MarketCarItemRepository;
import com.bcc.cca.repositories.MarketCarRepository;
import com.bcc.cca.repositories.OrderRepository;
import com.bcc.cca.repositories.PaymentRepository;
import com.bcc.cca.repositories.ProductRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AdressRepository adressRepository;
	
	@Autowired
	private CarRepository carRepository;

	@Autowired
	private CarBrandRepository carBrandRepository;
	
	@Autowired
	private CardInfoRepository cardInfoRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private MarketCarRepository marketCarRepository;
	
	@Autowired
	private MarketCarItemRepository marketCarItemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void run(String... args) throws Exception {
		if (adminRepository.findByEmail("admin@test.com").isEmpty()) {
			Admin admin = new Admin();
			admin.setName("Admin Test");
			admin.setEmail("admin@test.com");
			admin.setCpf("12345678901");
			admin.setPassword(passwordEncoder.encode("123456"));
			adminRepository.save(admin);
		}
	}
	
}
