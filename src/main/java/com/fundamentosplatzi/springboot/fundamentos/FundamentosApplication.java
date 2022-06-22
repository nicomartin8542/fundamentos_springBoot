package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperty;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyOperation;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	Log LOGGER = LogFactory.getLog(FundamentosApplication.class);

	private ComponentDependency componentDependency;
	private MyBean myBean;

	private MyOperation myOperation;

	private MyBeanWithDependency myBeanWithDependency;

	private MyBeanWithProperty myBeanWithProperty;

	private UserPojo userPojo;

	public FundamentosApplication(@Qualifier("componentImplement") ComponentDependency componentDependency, MyBean myBean, MyOperation myOperation, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperty myBeanWithProperty, UserPojo userPojo) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myOperation = myOperation;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperty = myBeanWithProperty;
		this.userPojo = userPojo;
	}

	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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
