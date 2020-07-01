package com.codeup.blog.daos;

import com.codeup.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsRepository extends JpaRepository<Post, Long> {

    //HQL
    @Query("from Post as p where p.title like %:term% or p.body like %:term%")
    List<Post> searchByTitleOrBody(@Param("term") String term);

    // SELECT * from posts where title = ? LIMIT 1;
    Post findFirstByTitle(String title);
}
