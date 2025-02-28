package edu.miu.waa_lab.service.impl;

import edu.miu.waa_lab.entity.Comment;
import edu.miu.waa_lab.repository.CommentRepo;
import edu.miu.waa_lab.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;

    @Override
    public Comment create(Comment comment) {
        return commentRepo.save(comment);
    }
}
