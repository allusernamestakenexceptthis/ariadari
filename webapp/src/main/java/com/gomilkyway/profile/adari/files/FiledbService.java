/*
 * Copyright 2023 Ari Adari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.gomilkyway.profile.adari.files;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.gomilkyway.profile.adari.utils.SanitizingUtil;

@Service
public class FiledbService {

    public static final String IMAGE_UPLOAD_DIR = "uploads";
    
    private final FiledbRepository filedbRepository;

    public FiledbService(FiledbRepository filedbRepository) {
        this.filedbRepository = filedbRepository;
    }

    public Filedb saveFile(Filedb filedb) {
        return filedbRepository.save(filedb);
    }

    public Filedb getFile(String name) {
        return filedbRepository.findByName(name).get();
    }

    public Boolean isFileValidImage(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType == null){
            return false;
        }

        switch (contentType.toLowerCase()) {
            case "image/jpg":
            case "image/jpeg":
            case "image/png":
                return true;
            default:
                return false;
        }
    }

    public String uploadImageAndSave(MultipartFile file) throws Exception{
        String fileName = "";
        try {
            fileName = uploadImage(file);
            Assert.isTrue(!fileName.isEmpty(), "Error uploading file");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
        

        String urlName = URLEncoder.encode(System.currentTimeMillis() + fileName.substring(2, 5) + "-" + file.getOriginalFilename(), StandardCharsets.UTF_8.toString());
        Filedb filedb = new Filedb();
        filedb.setName(urlName);
        filedb.setServerFileName(fileName);
        filedb.setType(file.getContentType());
        filedbRepository.save(filedb);
        return "/uimages/" + urlName;
    }

    public String uploadImage(MultipartFile file) throws Exception{

        String fileName = file.getOriginalFilename();

        if (!isFileValidImage(file)) {
            throw new Exception("Invalid file type");
        }
        

        fileName = System.currentTimeMillis() + fileName;
        Random random = new Random(System.currentTimeMillis() + new Random().nextInt());
        Long extraSalt= random.nextLong();
        
        fileName += extraSalt.toString().substring(0, 5);
        fileName = DigestUtils.md5DigestAsHex(fileName.getBytes());

        File resource = ResourceUtils.getFile("classpath:application.properties");
        String filePath = resource.getParentFile().getAbsolutePath();
        
        File directory = new File(Paths.get(filePath, IMAGE_UPLOAD_DIR).toString());
        
        if (!directory.exists()) {
            directory.mkdirs();
        }

        Path fileNameAndPath = Paths.get(directory.toString(), fileName);

        if (Files.exists(fileNameAndPath)) {
            return fileName;
        }

        try {
            Files.copy(file.getInputStream(), fileNameAndPath);
        } catch (Exception e) {
            throw new Exception("Error uploading file");
        }

        return fileName;
    }

}
