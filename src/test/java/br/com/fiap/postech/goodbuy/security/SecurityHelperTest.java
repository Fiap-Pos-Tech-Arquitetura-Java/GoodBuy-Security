package br.com.fiap.postech.goodbuy.security;

import br.com.fiap.postech.goodbuy.security.enums.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SecurityHelperTest {
    private SecurityHelper securityHelper;

    private AutoCloseable mock;

    @Mock
    private SecurityContext context;

    @Mock
    Authentication authentication;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
        securityHelper = new SecurityHelper();
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void getLoggedUser() {
        // Arrange
        var user = new User("login", "pass", UserRole.USER);
        try (MockedStatic<SecurityContextHolder> utilities = Mockito.mockStatic(SecurityContextHolder.class)) {
            utilities.when(SecurityContextHolder::getContext).thenReturn(context);
        }
        //when(context.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn("login");
        // Act
        var username = securityHelper.getLoggedUser();
        // Assert
        assertThat(username).isEqualTo(user.getLogin());
    }

    @Test
    void getToken() {
        // Arrange
        var token = "token";
        when(context.getAuthentication()).thenReturn(authentication);
        when(authentication.getCredentials()).thenReturn("token");
        // Act
        var tokenObtido = securityHelper.getToken();
        // Assert
        assertThat(token)
                .isInstanceOf(Date.class)
                .isNotNull();
        assertThat(token).isEqualTo(tokenObtido);
    }
}