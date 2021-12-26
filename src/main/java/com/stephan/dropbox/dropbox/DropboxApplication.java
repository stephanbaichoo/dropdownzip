package com.stephan.dropbox.dropbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stephan.dropbox.dropbox.client.WebclientDropdown;

@SpringBootApplication
public class DropboxApplication implements CommandLineRunner {

    private final WebclientDropdown webclientDropdown;

    @Autowired
    public DropboxApplication(WebclientDropdown webclientDropdown) {
        this.webclientDropdown = webclientDropdown;
    }

    // 7wjjz41t58jjni3
    // m8m6ayuwma7myda

    public static void main(String[] args) {
        SpringApplication.run(DropboxApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        webclientDropdown.dropDownAccessClient();
    }
}
