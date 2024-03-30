package project.personal.social.network;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import project.personal.shared.common.config.GlobalExceptionHandler;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = { "project.personal.social.network" })
@Import(GlobalExceptionHandler.class)
public class ResourceApplication {

	@Value("${HOME_DIR:./}")
	private static String HOME_DIR;

	public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(ResourceApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
//		generateKey();
	}

	@SuppressWarnings("unused")
	private static void generateKey() {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(2048);

			KeyPair kp = kpg.generateKeyPair();
			PublicKey publicKey = kp.getPublic();
			PrivateKey privateKey = kp.getPrivate();

			File publicKeyFile = createKeyFile(new File("publicKey.key"));
			File privateKeyFile = createKeyFile(new File("privateKey.key"));

			FileOutputStream fos = new FileOutputStream(publicKeyFile);
			fos.write(Base64.getEncoder().encodeToString(publicKey.getEncoded()).getBytes());
			fos.close();

			fos = new FileOutputStream(privateKeyFile);
			fos.write(Base64.getEncoder().encodeToString(privateKey.getEncoded()).getBytes());
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
