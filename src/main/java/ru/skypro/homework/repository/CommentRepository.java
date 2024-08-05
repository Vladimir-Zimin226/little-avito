package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.Comment;
import ru.skypro.homework.entity.User;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    void deleteAllByAdId(Integer adId);

}
