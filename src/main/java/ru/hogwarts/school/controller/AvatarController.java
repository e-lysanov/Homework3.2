package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping(path = "avatar")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() > 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }
        avatarService.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/avatar-from-db")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping(value = "/{id}/avatar-from-file")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException{
        Avatar avatar = avatarService.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try(InputStream is = Files.newInputStream(path);
            OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

//    @GetMapping(value = "/getAllAvatars")
//    public void getAllAvatars(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
//        List<Avatar> avatars = avatarService.getAllAvatars(pageNumber, pageSize);
//
//        for (int i = 0; i < avatars.size(); i++) {
//            Long id = avatars.get(i).getId();
//            downloadAvatar(id);
//        }
//    }

//    @GetMapping(value = "/getAllAvatars")
//    public ResponseEntity<byte[]> getAllAvatars(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
//        List<Avatar> avatars = avatarService.getAllAvatars(pageNumber, pageSize);
//
//        for (int i = 0; i < avatars.size(); i++) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.parseMediaType(avatars.get(i).getMediaType()));
//            headers.setContentLength(avatars.get(i).getData().length);
//            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatars.get(i).getData());
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(avatars.get(1).getData());
//    }

//    @GetMapping(value = "/getAllAvatars")
//    public ResponseEntity<List<Avatar>> getAllAvatars(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
//        List<Avatar> avatars = avatarService.getAllAvatars(pageNumber, pageSize);
//        return ResponseEntity.ok(avatars);

//        for (int i = 0; i < avatars.size(); i++) {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.parseMediaType(avatars.get(i).getMediaType()));
//            headers.setContentLength(avatars.get(i).getData().length);
//            return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatars.get(i).getData());
//            return ResponseEntity.ok().body(avatars.get(i).getData());
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(avatars.get(1).getData());

//        List<Avatar>   byte[]
//    }

    @GetMapping(value = "/getAllAvatars")
    public ResponseEntity<List<Avatar>> getAllAvatars(@RequestParam("page") Integer pageNumber, @RequestParam("size") Integer pageSize) {
        List<Avatar> avatars = avatarService.getAllAvatars(pageNumber, pageSize);
        return ResponseEntity.ok(avatars);

    }
}
