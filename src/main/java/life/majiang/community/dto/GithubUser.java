package life.majiang.community.dto;

/*
 * 获取Github 用户信息
 */
public class GithubUser {
    private String name;
    private Long id;
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString(){
        return "GithubUser{" +
                "'neme='" + name + '\'' +
                ", id=" + id + '\'' +
                ", bio=" + bio + '\'' +
                "}";
    }
}
