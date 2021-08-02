package com.demo.demo.controller;

import com.demo.demo.model.FileInfo;
import com.demo.demo.model.Image;
import com.demo.demo.model.ResponseMessage;
import com.demo.demo.repository.ImageRepository;
import com.demo.demo.security.services.FileStorageService;
import com.demo.demo.security.services.ImageServiceImpl;
import com.demo.demo.security.services.iservice.ImageService;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/image")
public class ImageUploadController {

    @Autowired
    private ImageServiceImpl imageServiceImpl;
    @Autowired
    private ImageService imageService;



    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            imageService.save(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = imageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(ImageUploadController.class, "getFile", path.getFileName().toString()).build().toString();

            return new FileInfo(filename, url);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = imageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

//

    @PostMapping(value = "/uploadAvatar")
    public ResponseEntity  uploadImage(@RequestParam("imageFile") MultipartFile file,String name){
        try {
            File newFile = new File("D:\\Mock\\fronend\\front-end-mock\\front-end-mock\\src\\assets\\images\\"+file.getOriginalFilename());
            FileOutputStream fileOutputStream;
            fileOutputStream=new FileOutputStream(newFile);
            fileOutputStream.write(file.getBytes());
            fileOutputStream.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        Image img = new Image("assets/images/"+file.getOriginalFilename(), file.getOriginalFilename());
        imageService.saveImage(img);
        return new ResponseEntity(img, HttpStatus.OK);
    }

    @GetMapping(path = { "/get/{imageName}" })
    public Optional<Image> getImage(@PathVariable("imageName") String imageName) {
        Optional<Image> img = imageService.findByName(imageName);
        return img;
    }

}
