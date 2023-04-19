package com.gomilkyway.profile.adari.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * AuditAwareImpl class implements AuditorAware interface
 * To fetch the current user from the security context
 * to be used by Spring Data JPA to set the createdBy and modifiedBy fields
 */
public class AuditAwareImpl implements AuditorAware<String> {

    /**
     * getCurrentAuditor method to get the current user from the security context
     * @return Optional<String> the current user
     * @see org.springframework.data.domain.AuditorAware#getCurrentAuditor()
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        return Optional.of(authentication.getName());
    }
    
}
