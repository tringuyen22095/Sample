package project.personal.social.network;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import project.personal.shared.common.config.GlobalExceptionHandler;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {
		"project.personal.social.network"
})
@RestControllerAdvice(basePackageClasses = {
		GlobalExceptionHandler.class
})
public class ResourceApplication {
	
	@Value("${HOME_DIR:./}")
	private static String HOME_DIR;

	public static void main(String[] args) {
		SpringApplication.run(ResourceApplication.class, args);
//		generateKey();
	}

	@SuppressWarnings("unused")
	private static void generateKey() {
		try {
			SecureRandom sr = new SecureRandom();
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(1024, sr);

			KeyPair kp = kpg.genKeyPair();
			PublicKey publicKey = kp.getPublic();
			PrivateKey privateKey = kp.getPrivate();

			File publicKeyFile = createKeyFile(new File(HOME_DIR + "publicKey.rsa"));
			File privateKeyFile = createKeyFile(new File(HOME_DIR + "privateKey.rsa"));

			FileOutputStream fos = new FileOutputStream(publicKeyFile);
			fos.write(publicKey.getEncoded());
			fos.close();

			fos = new FileOutputStream(privateKeyFile);
			fos.write(privateKey.getEncoded());
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static File createKeyFile(File file) throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		} else {
			file.delete();
			file.createNewFile();
		}
		return file;
	}

}
