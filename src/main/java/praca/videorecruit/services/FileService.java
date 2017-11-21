package praca.videorecruit.services;

import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolverException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileService{

    private final Path rootLocation;

    public FileService() {
        this.rootLocation = Paths.get("userFiles");
        File directory = rootLocation.toFile();
        if(!directory.exists()){
            directory.mkdir();
        }
    }


    public String store(MultipartFile file, String folder, String name) throws Exception {
        Path p = Paths.get(rootLocation.toString(), folder);
        File directory = p.toFile();
        if(!directory.exists()){
            directory.mkdirs();
        }
        String filename;
        if(name==null|| name.isEmpty()) {
            filename = StringUtils.cleanPath(file.getOriginalFilename());
        }else {
            filename = name+"."+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.') + 1);
        }
        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new Exception(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            Files.copy(file.getInputStream(), p.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            throw new Exception("Failed to store file " + filename, e);
        }
        return filename;
    }

    public Stream<Path> loadAll() throws Exception {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        }
        catch (IOException e) {
            throw new Exception("Failed to read stored files", e);
        }

    }

    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    public Resource loadAsResource(String filename) throws Exception {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new Exception(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new Exception("Could not read file: " + filename, e);
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    public void init() throws Exception {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            throw new Exception("Could not initialize storage", e);
        }
    }

    public void deleteFolder(String videoUrl) {
        Path p = load(videoUrl).getParent();
        System.out.println(p.toString());
        FileSystemUtils.deleteRecursively(p.toFile());
    }
}
