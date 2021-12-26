package com.stephan.dropbox.dropbox.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.DownloadZipResult;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

import lombok.extern.slf4j.Slf4j;

/**
 * @author sdb
 */
@Service
@Slf4j
public class WebclientDropdown {

    private static final String ACCESS_TOKEN = "";

    private static final String localPath = "";

    public void dropDownAccessClient() throws DbxException {
        // Create Dropbox client

        DbxRequestConfig config = DbxRequestConfig.newBuilder("").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        FullAccount account = client.users().getCurrentAccount();
        log.info("User : " + account.getName().getDisplayName());

        ListFolderResult result = client.files().listFolder("/pc/documents");

        result.getEntries().forEach(metadata -> log.info(metadata.getPathLower()));

        //OutputStream outputStream = new FileOutputStream(localPath);
        //client.files().downloadZipBuilder("/pc").download(outputStream);

        client.files()
              .listFolder("/pc/documents")
              .getEntries()
              .stream()
              .map(Metadata::getPathLower)
              .forEach(path -> {
                  StringBuilder newPath = new StringBuilder(localPath)
                          .append("\\")
                          .append(path.split("/")[3].replaceAll(" ", ""))
                          .append(".zip");

                  FileOutputStream fileOutputStream;

                  try {
                      File file = new File(newPath.toString());
                      fileOutputStream = new FileOutputStream(file);
                      DownloadZipResult download = client.files().downloadZipBuilder(path).download(fileOutputStream);
                      log.info("Downloaded : " + path + " " + download.getMetadata());
                  } catch (IOException | DbxException e) {
                      e.printStackTrace();
                  }
              });


    }

}
