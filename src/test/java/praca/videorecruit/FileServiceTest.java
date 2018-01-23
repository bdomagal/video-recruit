package praca.videorecruit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.mock.web.MockMultipartFile;
import praca.videorecruit.services.FileService;

import java.io.File;

/**
 * Created by Bartek on 23.01.2018.
 */
@RunWith(JUnit4.class)
public class FileServiceTest {

    private FileService fileService;

    @Before
    public void setup(){
        fileService = new FileService();
    }

    @Test
    public void ifFileIsEmptyDoNotSave() throws Exception{
        String result = fileService.store(new MockMultipartFile("sample.pdf", new byte[0]), "test/5/", "newName");
        assert result == null;
    }

    @Test
    public void ifFileIsNotEmpty_FileGetsSaved() throws Exception {
        String result = fileService.store(new MockMultipartFile("sample.pdf", new byte[500]), "test/5/", "newName");
        File f = new File("userFiles/test/5/" + result);
        assert f.exists();
    }

    @Test
    public void folderGetsDeleted(){
        fileService.deleteFolder("test/5/newName.pdf");
        File f = new File("userFiles/test/5");
        assert !f.exists();
    }
}
