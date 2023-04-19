package com.gomilkyway.profile.adari.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpHeaders;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FileController {
    
    private final FiledbService filedbService;


    @GetMapping("/uimages/{filename}")
    public ResponseEntity<StreamingResponseBody> serveFile(@PathVariable String filename) {
        Filedb filedb = filedbService.getFile(filename);
        if (filedb == null) {
            //forward to 404
            return ResponseEntity.notFound().build();
        }

        
        InputStream in;
        try {
            File resource = ResourceUtils.getFile("classpath:application.properties");
            String filePath = resource.getParentFile().getAbsolutePath();
            String path = Paths.get(filePath, FiledbService.IMAGE_UPLOAD_DIR).toString();
            in = Files.newInputStream(Paths.get(path, filedb.getServerFileName()), StandardOpenOption.READ);
        } catch (IOException io) {
            //forward to 404
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(filedb.getType()))
                .body(out -> {
                    //write after reading file saved in db serverName

                    out.write(StreamUtils.copy(in, out));
                    out.flush();
                });
    }


}
