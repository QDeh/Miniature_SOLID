package fr.miniaturesolid.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

public class Post {
    static int nbPosts = 0;
    private int id;
    private User owner;
    private String content;
    private List<User> likedBy = new ArrayList<>();
    private Post parent;
    private Date createdAt = new Date();
    private String formattedDate;

    public Post(User owner, String content, Post parent) {
        this.id = ++nbPosts;
        this.owner = owner;
        this.content = content;
        this.parent = parent;
        this.formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(createdAt) + " à "
                + new SimpleDateFormat("HH:mm").format(createdAt);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Post other = (Post) obj;
        if (id != other.id)
            return false;
        return true;
    }

    public int getLikes() {
        return likedBy.size();
    }

    public void updateLikes(User user) {
        if (!likedBy.contains(user)) {
            likedBy.add(user);
        } else {
            likedBy.remove(user);
        }
    }

    public List<User> addLike(User user){
        likedBy.add(user);
        return likedBy;
    }

    public List<User> removeLike(User user){
        likedBy.remove(user);
        return likedBy;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Date getcreatedAt() {
        return createdAt;
    }

    public User getOwner() {
        return owner;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public List<User> getLikedBy() {
        return likedBy;
    }

    public Post getParent() {
        return parent;
    }

}
