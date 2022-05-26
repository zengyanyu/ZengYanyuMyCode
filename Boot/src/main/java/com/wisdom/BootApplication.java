package com.wisdom;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZengYanyu
 * @Description
 * @Date 2020年5月30日 上午9:50:49
 */
@SpringBootApplication(scanBasePackages = "com.wisdom")
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(BootApplication.class);
		//关闭图标
		springApplication.setBannerMode(Banner.Mode.CONSOLE);
		springApplication.run(args);
	}

}
