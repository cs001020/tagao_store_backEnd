import com.chen.admin.AdminApplication;
import com.chen.admin.utils.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@SpringBootTest(classes = AdminApplication.class)
public class FIleTest {
    @Resource
    private FileUtils fileUtils;

    @Test
    public void test() throws UnsupportedEncodingException {
//        fileUtils.fileUpload();
    }
}
