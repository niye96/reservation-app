package com.ices.filesystem;

import com.didispace.swagger.EnableSwagger2Doc;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import(FdfsClientConfig.class)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableSwagger2Doc
public class FilesystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilesystemApplication.class, args);
	}
}
