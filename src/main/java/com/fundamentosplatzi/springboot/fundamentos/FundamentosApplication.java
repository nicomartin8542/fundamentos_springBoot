package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperty;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyOperation;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import com.fundamentosplatzi.springboot.fundamentos.service.UserService;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;

	private MyOperation myOperation;

	private MyBeanWithDependency myBeanWithDependency;

	private MyBeanWithProperty myBeanWithProperty;

	private UserPojo userPojo;

	private UserRepository userRepository;

	private UserService userService;

	public FundamentosApplication(@Qualifier("componentImplement") ComponentDependency componentDependency, MyBean myBean, MyOperation myOperation, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperty myBeanWithProperty, UserPojo userPojo, UserRepository userRepository, UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myOperation = myOperation;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperty = myBeanWithProperty;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//clasesAnteriores();
		saveUsersInDataBase();
		saveWithErrorTransaccional();
	}


	private void saveWithErrorTransaccional() {
		List<User> users = new ArrayList<>();
		users.add( new User("Nico 1","nicomartin1@gmail.com", LocalDate.of(2021, 3,20)));
		users.add( new User("Jorge 2","nicomartin1@gmail.com", LocalDate.of(2021, 3,20)));

		try {
			userService.saveUserTransaccion(users);
		} catch ( Exception e) {
			LOGGER.error("No se pudo completar el insert. Motivo: " + e);
		}

		userService.getAllUsers().stream().forEach(user -> LOGGER.info("Este es el usuario dentro del metodo transaccion" + user));

	}

	private void saveUsersInDataBase() {
		List<User> users = new ArrayList<>();
		users.add( new User("Nico","nicomartin@gmail.com", LocalDate.of(2021, 3,20)));
		users.add( new User("Jorge","jorge@gmail.com", LocalDate.of(2021, 3,20)));

		userRepository.saveAll(users);
		getUserByEmail();

	}

	private void getUserByEmail() {
		try {
			LOGGER.info("usuario : "+ userRepository.findByUseremail("nicomartin@gmail.com"));
            LOGGER.info(userRepository.findByName("Nico"));
			LOGGER.info(userRepository.findByNameAndEmail("Jorge", "jorge@gmail.com"));
			LOGGER.info(userRepository.getAllByBirthDateAndEmail(LocalDate.of(2021, 3,20),
					                                       "nicomartin@gmail.com")
					.orElseThrow(()-> new RuntimeException("No se encontro el usuario por fecha y email")));

		} catch (Exception e)  {
			LOGGER.error("No se encontro el usuario por el email");
		}
	}



	private void clasesAnteriores() {
		componentDependency.saludar();
		myBean.print();
		myOperation.suma(1);
		myBeanWithDependency.printWithDependency(5);
		System.out.println(myBeanWithProperty.function());
		System.out.println(userPojo.getMail() + "-" +userPojo.getPassword());
		try {
			//Ocuerr un error
			int value = 10/0;
			LOGGER.debug("mi valor es "+ value );
		}catch (Exception e) {
			LOGGER.error("Esto es un error al dividir por cero");
		}
	}
}
