package br.com.fiap.postech.goodbuy.security;

import br.com.fiap.postech.goodbuy.security.enums.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class JwtServiceTest {
    private JwtService jwtService;

    private AutoCloseable mock;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        jwtService = new JwtService();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void extractUsername() {
        // Arrange
        var user = new User("login", "pass", UserRole.USER);
        var userDetails = new UserDetailsImpl(user);
        var token = jwtService.generateToken(user);
        // Act
        var username = jwtService.extractUsername(token);
        // Assert
        assertThat(username).isEqualTo(user.getLogin());
    }

    @Test
    void extractExpiration() {
        // Arrange
        var user = new User("login", "pass", UserRole.USER);
        var userDetails = new UserDetailsImpl(user);
        var token = jwtService.generateToken(user);
        // Act
        var data = jwtService.extractExpiration(token);
        // Assert
        assertThat(data)
                .isInstanceOf(Date.class)
                .isNotNull();
        //assertThat(username).isEqualTo(user.getLogin());
    }

    @Test
    void validateToken() {
        // Arrange
        var user = new User("login", "pass", UserRole.USER);
        var userDetails = new UserDetailsImpl(user);
        var token = jwtService.generateToken(user);
        // Act
        var isValid = jwtService.validateToken(token, userDetails);
        // Assert
        assertThat(isValid).isTrue();
    }

    @Test
    void generateToken() {
        // Arrange
        var user = new User("login", "pass", UserRole.USER);
        // Act
        var token = jwtService.generateToken(user);
        // Assert
        assertThat(token)
                .isInstanceOf(String.class)
                .isNotNull();
    }
}