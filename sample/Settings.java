package sample;

public class Settings {
    private String keywords;
    private int cooldown;
    private boolean kurskOnly;
    private String ip;
    private String username;
    private String password;
    private String tablename;
    private String tgToken;
    public Settings(String keywords, int cooldown, boolean kurskOnly, String ip, String username, String password, String tablename, String tgToken){
        this.keywords = keywords;
        this.cooldown = cooldown;
        this.kurskOnly = kurskOnly;
        this.ip = ip;
        this.username = username;
        this.password = password;
        this.tablename = tablename;
        this.tgToken = tgToken;
    }

    public boolean isKurskOnly() {
        return kurskOnly;
    }

    public int getCooldown() {
        return cooldown;
    }

    public String getIp() {
        return ip;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getPassword() {
        return password;
    }

    public String getTablename() {
        return tablename;
    }

    public String getTgToken() {
        return tgToken;
    }

    public String getUsername() {
        return username;
    }
}
