package keystore;

import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;

import static org.assertj.core.api.Assertions.assertThat;

public class PrivateKeyFactoryTest {

    @Test
    public void shouldGenerateRSAPrivateKeyByDefault() throws Exception {
        PrivateKey privateKey = new PrivateKeyFactory().createPrivateKey(getFileBytes("private_key.pk8"));
        assertThat(privateKey.getAlgorithm()).isEqualTo("RSA");
    }

    @Test
    public void shouldGenerateECPrivateKeyWhenNotSuppliedRSAKey() throws Exception {
        byte[] bytes = getFileBytes("ec_private_key.pk8");
        PrivateKey privateKey = new PrivateKeyFactory().createPrivateKey(bytes);
        assertThat(privateKey.getAlgorithm()).isEqualTo("EC");
    }

    private byte[] getFileBytes(String fileName) throws IOException {
        URL resource = getClass().getClassLoader().getResource(fileName);
        return Files.readAllBytes(Paths.get(resource.getPath()));
    }

}
