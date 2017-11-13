package cc.oauth.jpa.domain.view;

public interface RoleView {
    Long getRoleId();
    String getName();
    String getTitle();
    String getDescription();
    Long getOrders();
}
