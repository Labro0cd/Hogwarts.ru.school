package ru.hogwarts.school.school.Service;


import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.school.model.Avatar;
import ru.hogwarts.school.school.model.Student;
import ru.hogwarts.school.school.repository.RepositoryAvatars;
import ru.hogwarts.school.school.repository.StudentRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {

    @Value("path.to.avatars.folder") // ???
    private String avatarsDir;

    private final RepositoryAvatars repositoryAvatars;
    private final StudentRepository repositoryStudents;

    public AvatarService(RepositoryAvatars repositoryAvatars, StudentRepository repositoryStudents) {
        this.repositoryAvatars = repositoryAvatars;
        this.repositoryStudents = repositoryStudents;
    }

    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = repositoryStudents.getById(studentId);
        Path filePath = Path.of(avatarsDir, student + "."+ getExtensions(avatarFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try(
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is);
                BufferedOutputStream bos = new BufferedOutputStream(os)
        ){
            bis.transferTo(bos);
        }

        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setSize(avatarFile.getSize());
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setPreview(generateDataForDB(filePath));
        repositoryAvatars.save(avatar);

    }

    private byte[] generateDataForDB(Path filePath) throws IOException {
        try (
                InputStream is = Files.newInputStream(filePath);
                BufferedInputStream bis = new BufferedInputStream(is, 200);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            BufferedImage image = ImageIO.read(bis);

            int height = image.getHeight()/(image.getWidth()/100);
            BufferedImage preview = new BufferedImage(100, height, image.getType());
            Graphics2D graphics2D = preview.createGraphics();
            graphics2D.drawImage(image, 0,0,100,height,null);
            graphics2D.dispose();

            ImageIO.write(preview, getExtensions(filePath.getFileName().toString()),baos);
            return baos.toByteArray();
        }
    }


    public Avatar findAvatar(Long studentId){
        return repositoryAvatars.findByStudentId(studentId).orElse(new Avatar());
    }


    private String getExtensions(String filename) {
        return filename.substring(filename.lastIndexOf(".")+1);
    }

}
