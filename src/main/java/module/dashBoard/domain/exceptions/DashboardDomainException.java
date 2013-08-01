package module.dashBoard.domain.exceptions;

import pt.ist.bennu.core.domain.exceptions.DomainException;

@SuppressWarnings("serial")
public class DashboardDomainException extends DomainException {
    private static final String BUNDLE = "resources.DashboardResources";

    public DashboardDomainException(String key, String... args) {
        super(BUNDLE, key, args);
    }
}
