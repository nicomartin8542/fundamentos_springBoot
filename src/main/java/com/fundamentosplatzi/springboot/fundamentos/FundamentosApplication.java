package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperty;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyOperation;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
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

	public FundamentosApplication(@Qualifier("componentImplement") ComponentDependency componentDependency, MyBean myBean, MyOperation myOperation, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperty myBeanWithProperty, UserPojo userPojo, UserRepository userRepository) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myOperation = myOperation;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperty = myBeanWithProperty;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//clasesAnteriores();
		saveUsersInDataBase();
	}


	private void saveUsersInDataBase() {
		List<User> users = new ArrayList<>();
		users.add( new User("Nico","nicomartin@gmail.com", LocalDate.of(2021, 03,20)));
		users.add( new User("Jorge","jorge@gmail.com", LocalDate.of(2021, 03,20)));

		userRepository.saveAll(users);
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
