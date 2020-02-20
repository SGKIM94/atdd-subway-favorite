package atdd.path.security;

import atdd.path.SoftAssertionTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TokenAuthenticationServiceTest extends SoftAssertionTest {
    private static final String SALT = "63B75D39E3F6BFE72263F7C1145AC22E";

    private TokenAuthenticationService tokenAuthenticationService;

    @BeforeEach
    void setUp() {
        this.tokenAuthenticationService = new TokenAuthenticationService();
    }

    @DisplayName("JWT 를 위한 키가 생성되는지")
    @Test
    public void generateKey(SoftAssertions softly) {
        //given
        byte[] key = tokenAuthenticationService.generateKey(SALT);

        //when

        //then
        softly.assertThat(key).isEqualTo(SALT.getBytes());
    }
}

