package com.bcc.cca.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.bcc.cca.entites.Client;
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
import com.bcc.cca.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	private AdminRepository adminRepository;
	
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
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Client c1 =new Client(null,"paulo", "paulo@gmail.com", "400000000", "908098049358", "984729387423");
		Client c2 =new Client(null,"joao", "joao@gmail.com", "400000000", "908098049358", "984729387423");
		Client c3 =new Client(null,"ricardo", "ricardo@gmail.com", "400000000", "908098049358", "984729387423");
		Client c4 =new Client(null,"maria", "maria@gmail.com", "400000000", "908098049358", "984729387423");
		Client c5 =new Client(null,"antonio", "antonio@gmail.com", "400000000", "908098049358", "984729387423");
		
		clientRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5));
		
	}
	
}
